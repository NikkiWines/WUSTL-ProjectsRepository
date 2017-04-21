LIBRARY IEEE;
USE IEEE.STD_LOGIC_1164.ALL;

entity params is 
    PORT    ( SWITCH : in STD_LOGIC_VECTOR(3 downto 0); -- Set 4 bit switch vector: SW0, SW1, SW2, SW3 
              LED : out STD_LOGIC_VECTOR (15 downto 0)  -- Set 16 bit LED vector: LED0 through LD15
             );
            
end params;
    
architecture Behavioral of params is 
begin 
process (SWITCH)
begin
    
     case SWITCH is -- case statements: when switch value is 'this' , corresponding LED value should be 'that'
          when "0000" =>   LED <= X"0001"; -- 1st LED lights up when switch is set to 0. LED is set in hexidecimal rather than binary.
          when "0001" =>   LED <= X"0002";
          when "0010" =>   LED <= X"0004";
          when "0011" =>   LED <= X"0008";
          when "0100" =>   LED <= X"0010";
          when "0101" =>   LED <= X"0020";
          when "0110" =>   LED <= X"0040";
          when "0111" =>   LED <= X"0080";
          when "1000" =>   LED <= X"0100";
          when "1001" =>   LED <= X"0200";
          when "1010" =>   LED <= X"0400";
          when "1011" =>   LED <= X"0800";
          when "1100" =>   LED <= X"1000";
          when "1101" =>   LED <= X"2000";
          when "1110" =>   LED <= X"4000";
          when "1111" =>   LED <= X"8000"; -- 16th LED lights up when switch is set to 15 
          when others =>   LED <= X"0000"; -- Any other cases should be LED = LOW.
                  
end case;

end process;  
    
end Behavioral;
