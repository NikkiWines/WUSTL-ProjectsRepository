----------------------------------------------------------------------------------
-- Engineer: Nikki Wines 437324
-- Create Date: 11/06/2016 08:19:54 PM
-- Design Name: Lab 3 Difference Engine
-- Module Name: lab_3_src - Behavioral
-- Description: This lab calculates the maximum value of the polynomial
-- 1,000,000x - x^2 + x^3 - x^4 with a 12 state mealy-model finite state machine.
-- It stops calculations when the maximum value (peak of graphed polynomial) has
-- been reached. The LED's on the FPGA display the maximum value.

-- Coefficient Calculations:
-- x    f(x)                                                f        g       h      i     j
-- 0    1,000,000(0) - 0^2 + 0^3 -0^4 = 0
--                                   (f(1) - f(0))       999,999
-- 1    1,000,000(1) - 1^2 + 1^3 -1^4 = 999,999                     -10
--                                   (f(2) - f(1))       999,989            -30
-- 2    1,000,000(2) - 2^2 + 2^3 -2^4 = 1,999,988                   -40            -24
--                                   (f(3) - f(2))       999,949            -54           0
-- 3    1,000,000(3) - 3^2 + 3^3 -3^4 = 2,999,937                   -94            -24
--                                   (f(4) - f(3))       999,855            -78           0
-- 4    1,000,000(4) - 4^2 + 4^3 -4^4 = 3,999,792                   -172           -24
--                                   (f(5) - f(4))       999,683            -104
-- 5    1,000,000(5) - 5^2 + 5^3 -5^4 = 4,999,475                   -274
--                                   (f(6) - f(5))       999,409
-- 6    1,000,000(6) - 6^2 + 6^3 -6^4 = 5,998,884

----------------------------------------------------------------------------------
LIBRARY IEEE ;
USE IEEE.STD_LOGIC_1164.ALL ;
USE IEEE.STD_LOGIC_ARITH.ALL ;
USE IEEE.STD_LOGIC_UNSIGNED.ALL ;

ENTITY difeng IS
 PORT (
   clk : IN STD_LOGIC ;
   reset_l : IN STD_LOGIC ;
   switch : IN STD_LOGIC ; -- switch to determine LED display
   LED : OUT STD_LOGIC_VECTOR (15 DOWNTO 0) -- 16 LED array
 ) ;
END difeng ;

ARCHITECTURE foo OF difeng IS
 SIGNAL f : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL g : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL h : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL i : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL j : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL f_in : STD_LOGIC ;
 SIGNAL g_in : STD_LOGIC ;
 SIGNAL h_in : STD_LOGIC ;
 SIGNAL i_in : STD_LOGIC ;
 SIGNAL j_in : STD_LOGIC ;
 SIGNAL a_in : STD_LOGIC ;
 SIGNAL c_in : STD_LOGIC ;
 SIGNAL f_out : STD_LOGIC ;
 SIGNAL g_out : STD_LOGIC ;
 SIGNAL h_out : STD_LOGIC ;
 SIGNAL i_out : STD_LOGIC ;
 SIGNAL j_out : STD_LOGIC ;
 SIGNAL c_out : STD_LOGIC ;
 SIGNAL cpu_bus : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL a_bus : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL c_bus : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL c_reg : STD_LOGIC_VECTOR(31 DOWNTO 0) ;
 SIGNAL reset_l_tmp : STD_LOGIC ;
 SIGNAL reset_l_sync : STD_LOGIC ;
 TYPE states IS (s0,s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11);
 SIGNAL state: states := s0;
 SIGNAL nxt_state : states := s0;
 SIGNAL stop : STD_LOGIC; -- stop signal to prevent overflows and continual calculations (used with a comparator)
BEGIN
-------------------- This is the reset_l synchronizer! -----------------
PROCESS(clk)
 BEGIN
   IF (clk'event AND clk = '1') THEN
     reset_l_tmp <= reset_l ;
     reset_l_sync <= reset_l_tmp ;
   END IF ;
END PROCESS ;
---------------------- This is the register file! -----------------------
PROCESS(clk)
 BEGIN
   IF (clk'event AND clk = '1') THEN
     IF (reset_l_sync = '0') THEN
       f <= "00000000000000000000000000000000" ; -- 0 in decimal
       g <= "00000000000011110100001000111111" ; -- 999,999 in decimal
       h <= "11111111111111111111111111110110" ; -- -10 in decimal (first bit acts as -)
       i <= "11111111111111111111111111100010" ; -- -30 in decimal
       j <= "11111111111111111111111111101000" ; -- -24 in decimal
     ELSE
       IF f_in = '1' THEN
         f <= cpu_bus ;
       END IF ;
       IF g_in = '1' THEN
         g <= cpu_bus ;
       END IF ;
       IF h_in = '1' THEN
         h <= cpu_bus ;
       END IF ;
       IF i_in = '1' THEN
         i <= cpu_bus ;
       END IF ;
       IF j_in = '1' THEN
         j <= cpu_bus ;
       END IF ;
     END IF ;
   END IF ;
END PROCESS ;

cpu_bus <= f when f_out = '1' ELSE (OTHERS => 'Z') ;
cpu_bus <= g when g_out = '1' ELSE (OTHERS => 'Z') ;
cpu_bus <= h when h_out = '1' ELSE (OTHERS => 'Z') ;
cpu_bus <= i when i_out = '1' ELSE (OTHERS => 'Z') ;
cpu_bus <= j when j_out = '1' ELSE (OTHERS => 'Z') ;
------------------------ This is the A register! --------------------------
PROCESS(clk)
 BEGIN
   IF (clk'event AND clk = '1') THEN
     IF (a_in = '1') THEN
       a_bus <= cpu_bus ;
     END IF ;
   END IF ;
END PROCESS ;
------------------------ This is the C register! --------------------------
PROCESS(clk)
 BEGIN
   IF (clk'event AND clk = '1') THEN
     IF (c_in = '1') THEN
       c_reg <= c_bus ;
     END IF ;
   END IF ;
END PROCESS ;

cpu_bus <= c_reg when c_out = '1' ELSE (OTHERS => 'Z') ;
------------------------ This is the ALU! ---------------------------------
c_bus <= a_bus + cpu_bus ;
------------------------ This is the controller! --------------------------
setstate: PROCESS (clk)
 BEGIN
   IF (clk'EVENT AND clk='1') THEN -- the following code is the based off the code in my lab 2
     IF (reset_l_sync = '0') THEN   -- reset has been pressed (active low reset)
       state <= s0; --   set state back to state 0
     ELSE
       state <= nxt_state; -- otherwise reset hasn't been pressed and we can move forward
     END IF;
   END IF;
END PROCESS setstate;

statetransition : PROCESS (stop, reset_l_sync, state)
 BEGIN
   nxt_state <= state; -- without this Timing Summary == NA for all params. 
   f_in <= '0';  -- good form to initialize values to 0 (from class)
   g_in <= '0';  -- also resets every value back to zero to prevent incorrect calculations
   h_in <= '0';
   i_in <= '0';
   j_in <= '0';
   a_in <= '0';
   c_in <= '0';
   f_out <= '0';
   g_out <= '0';
   h_out <= '0';
   i_out <= '0';
   j_out <= '0';
   c_out <= '0';
   IF (stop = '0') THEN -- we haven't reached our max yet, so allow states to be set.
     CASE state IS
         WHEN s0 => nxt_state <= s1; -- set next states for each state so that s0 --> s1 --> s2 --> s3 --> ... --> s11 --> s0
         WHEN s1 => nxt_state <= s2; 
         WHEN s2 => nxt_state <= s3;
         WHEN s3 => nxt_state <= s4;
         WHEN s4 => nxt_state <= s5;
         WHEN s5 => nxt_state <= s6;
         WHEN s6 => nxt_state <= s7;
         WHEN s7 => nxt_state <= s8;
         WHEN s8 => nxt_state <= s9;
         WHEN s9 => nxt_state <= s10;
         WHEN s10 => nxt_state <= s11;
         WHEN s11 => nxt_state <= s0;
     END CASE;
   END IF;
-- at this point stop hasn't been set, so we are continuing on to calculate the max value with the fsm.
 CASE state IS -- case statements to determine what happens at each individual state.
   WHEN s0 => f_out <= '1'; a_in <= '1';   -- 12 state mealy-model FSM where the output of the is dependent on both the input and the state
   WHEN s1 => g_out <= '1'; c_in <= '1';
   WHEN s2 => c_out <= '1'; f_in <= '1';   -- c_out (f+g) goes back into f, effectively setting f = f+g
   WHEN s3 => g_out <= '1'; a_in <= '1';
   WHEN s4 => h_out <= '1'; c_in <= '1';
   WHEN s5 => c_out <= '1'; g_in <= '1';   -- same process but now c_out = (g+h) and goes back into g --> g= g+h
   WHEN s6 => h_out <= '1'; a_in <= '1';
   WHEN s7 => i_out <= '1'; c_in <= '1';
   WHEN s8 => c_out <= '1'; h_in <= '1';   -- repeate for c_out = (h+i) going back into h --> h = h+i
   WHEN s9 => i_out <= '1'; a_in <= '1';
   WHEN s10 => j_out <= '1'; c_in <= '1';
   WHEN s11 => c_out <= '1'; i_in <= '1';  -- lastly, repeat for c_out = (i+j) going back into i --> i = i+j
 END CASE ;
END PROCESS statetransition ;
stop <= '1' WHEN (g(31) = '1') ELSE '0'; -- stop calculations/state transitions when you've reached peak of parabola (i.e. g becomes negative) 
-- since our numbers are unsigned, g is negative when the 31st bit of g == 1 
LED <= f(31 DOWNTO 16) WHEN switch = '1' ELSE f(15 DOWNTO 0); -- display the values on FPGA via LEDs 
-- this code is the same as lab 2's (f is the equivalent of our lab 2 "max") in that f is the max val of the parabola. 
END foo ;
