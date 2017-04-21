@echo off
set xv_path=C:\\Apps\\Xilinx\\Vivado\\2016.2\\bin
call %xv_path%/xelab  -wto e21f1a332e5f46079b9bb99ed6d94973 -m64 --debug typical --relax --mt 2 -L xil_defaultlib -L secureip --snapshot difeng_behav xil_defaultlib.difeng -log elaborate.log
if "%errorlevel%"=="0" goto SUCCESS
if "%errorlevel%"=="1" goto END
:END
exit 1
:SUCCESS
exit 0
