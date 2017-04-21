@echo off
set xv_path=C:\\Apps\\Xilinx\\Vivado\\2016.2\\bin
call %xv_path%/xelab  -wto 3c1c2b92b4d54c0a95f248546336d77c -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot params_behav xil_defaultlib.params -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
