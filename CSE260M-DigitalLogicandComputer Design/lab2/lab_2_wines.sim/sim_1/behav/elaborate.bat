@echo off
set xv_path=C:\\Apps\\Xilinx\\Vivado\\2016.2\\bin
call %xv_path%/xelab  -wto 6055ca22c8374d35988921da84d80586 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot lab_2_src_behav xil_defaultlib.lab_2_src -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
