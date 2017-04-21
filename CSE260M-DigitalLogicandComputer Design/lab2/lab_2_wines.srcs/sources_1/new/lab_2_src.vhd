----------------------------------------------------------------------------------
-- Class: CSE 260M
-- Engineer: Nikki Wines
-- 
-- Create Date: 10/31/2016 06:33:15 PM
-- Design Name: lab_2_src Difference Engine
-- Module Name: lab_2_src - Behavioral
-- Description: Calculates max value of the polynomial 1,000,000x - x^2 + x^3 - x^4 using a single process and one multiplexor
----------------------------------------------------------------------------------

library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.STD_LOGIC_ARITH.ALL;
use IEEE.STD_LOGIC_SIGNED.ALL;

entity lab_2_src is -- port setup: set lock and SW0 to single vals, 16 LEDs assigned in STD_LOGIC_VECTOR
--  Port ( );
Port(clk: IN STD_LOGIC;
RESET, SW0 : in STD_LOGIC;
LED: out STD_LOGIC_VECTOR(15 DOWNTO 0));
end lab_2_src;

architecture Behavioral of lab_2_src is -- initialize the var vectors to hold max, count, and d1 through d4, and the temp vars
    SIGNAL D1, D2, D3, D4, max, count: STD_LOGIC_VECTOR(31 DOWNTO 0);
    SIGNAL T1, T2 : STD_LOGIC;
begin

process2: PROCESS(clk) 
    begin

    IF (clk = '1' and clk'EVENT) THEN  -- on a rising clock edge
      
        T1 <= RESET; -- temp value for reset btn -- maintain reset through clock cycles
        T2 <= T1; -- store temp into new temp 

        IF (T2 = '0') THEN  -- "reset" has been pressed
            max <="00000000000000000000000000000000";-- set max value back to 0
            D1 <=   "00000000000011110100001000111111"; -- 999,999 in decimal
            D2 <=   "11111111111111111111111111110110"; -- -10 in decimal
            D3 <=   "11111111111111111111111111100010"; -- -30 in decimal
            D4 <=   "11111111111111111111111111101000"; -- -24 in decimal
            count <="00000000000000000000000000000000"; --0 in decimal
           
        ELSE -- reset == 1, calculate values
        
            count <= count + D1; -- increment values
            D1 <= D1 + D2;
            D2 <= D2 + D3;
            D3 <= D3 + D4;
            
            IF (max = "00000000000000000000000000000000" AND D1 < 0) THEN -- once max value has been reached -- stop
                max <= count; -- store max in count 
            END IF;
            
        END IF;
        
    END IF;

    end process process2;
    
    LED <= max(31 DOWNTO 16) WHEN (SW0 ='1')-- display LED values based on switch value
    ELSE max(15 DOWNTO 0) WHEN (SW0 ='0');
    
end Behavioral;