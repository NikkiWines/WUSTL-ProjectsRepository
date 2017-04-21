@echo off
set xv_path=C:\\Apps\\Xilinx\\Vivado\\2016.2\\bin
echo "xvhdl -m64 --relax -prj params_vhdl.prj"
call %xv_path%/xvhdl  -m64 --relax -prj params_vhdl.prj -log xvhdl.log
call type xvhdl.log > compile.log
if "%errorlevel%"=="1" goto END
if "%errorlevel%"=="0" goto SUCCESS
:END
exit 1
:SUCCESS
exit 0
