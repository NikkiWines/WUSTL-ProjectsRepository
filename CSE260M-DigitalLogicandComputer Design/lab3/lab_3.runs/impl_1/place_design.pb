
�
�No debug cores found in the current design.
Before running the implement_debug_core command, either use the Set Up Debug wizard (GUI mode)
or use the create_debug_core and connect_debug_core Tcl commands to insert debug cores into the design.
154*	chipscopeZ16-241h px� 
Q
Command: %s
53*	vivadotcl2 
place_design2default:defaultZ4-113h px� 
�
@Attempting to get a license for feature '%s' and/or device '%s'
308*common2"
Implementation2default:default2
xc7a100t2default:defaultZ17-347h px� 
�
0Got license for feature '%s' and/or device '%s'
310*common2"
Implementation2default:default2
xc7a100t2default:defaultZ17-349h px� 
P
Running DRC with %s threads
24*drc2
22default:defaultZ23-27h px� 
V
DRC finished with %s
79*	vivadotcl2
0 Errors2default:defaultZ4-198h px� 
e
BPlease refer to the DRC report (report_drc) for more information.
80*	vivadotclZ4-199h px� 
p
,Running DRC as a precondition to command %s
22*	vivadotcl2 
place_design2default:defaultZ4-22h px� 
P
Running DRC with %s threads
24*drc2
22default:defaultZ23-27h px� 
V
DRC finished with %s
79*	vivadotcl2
0 Errors2default:defaultZ4-198h px� 
e
BPlease refer to the DRC report (report_drc) for more information.
80*	vivadotclZ4-199h px� 
U

Starting %s Task
103*constraints2
Placer2default:defaultZ18-103h px� 
}
BMultithreading enabled for place_design using a maximum of %s CPUs12*	placeflow2
22default:defaultZ30-611h px� 
v

Phase %s%s
101*constraints2
1 2default:default2)
Placer Initialization2default:defaultZ18-101h px� 
�
I%sTime (s): cpu = %s ; elapsed = %s . Memory (MB): peak = %s ; gain = %s
268*common2.
Netlist sorting complete. 2default:default2
00:00:002default:default2
00:00:002default:default2
979.7662default:default2
0.0002default:defaultZ17-268h px� 
�
I%sTime (s): cpu = %s ; elapsed = %s . Memory (MB): peak = %s ; gain = %s
268*common2.
Netlist sorting complete. 2default:default2
00:00:002default:default2
00:00:002default:default2
979.7662default:default2
0.0002default:defaultZ17-268h px� 
�

Phase %s%s
101*constraints2
1.1 2default:default2F
2IO Placement/ Clock Placement/ Build Placer Device2default:defaultZ18-101h px� 
�

Phase %s%s
101*constraints2
1.1.1 2default:default22
ParallelPlaceIOClockAndInitTop2default:defaultZ18-101h px� 
v

Phase %s%s
101*constraints2
1.1.1.1 2default:default2#
Pre-Place Cells2default:defaultZ18-101h px� 
H
3Phase 1.1.1.1 Pre-Place Cells | Checksum: 641d9fd7
*commonh px� 
�

%s
*constraints2r
^Time (s): cpu = 00:00:00 ; elapsed = 00:00:00.059 . Memory (MB): peak = 979.766 ; gain = 0.0002default:defaulth px� 
�

Phase %s%s
101*constraints2
1.1.1.2 2default:default2/
Constructing HAPIClkRuleMgr2default:defaultZ18-101h px� 
T
?Phase 1.1.1.2 Constructing HAPIClkRuleMgr | Checksum: 641d9fd7
*commonh px� 
�

%s
*constraints2r
^Time (s): cpu = 00:00:00 ; elapsed = 00:00:00.111 . Memory (MB): peak = 979.766 ; gain = 0.0002default:defaulth px� 
E
%Done setting XDC timing constraints.
35*timingZ38-35h px� 
z

Phase %s%s
101*constraints2
1.1.1.3 2default:default2'
IO and Clk Clean Up2default:defaultZ18-101h px� 
�

Phase %s%s
101*constraints2

1.1.1.3.1 2default:default2/
Constructing HAPIClkRuleMgr2default:defaultZ18-101h px� 
V
APhase 1.1.1.3.1 Constructing HAPIClkRuleMgr | Checksum: 641d9fd7
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
L
7Phase 1.1.1.3 IO and Clk Clean Up | Checksum: 641d9fd7
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
�

Phase %s%s
101*constraints2
1.1.1.4 2default:default2>
*Implementation Feasibility check On IDelay2default:defaultZ18-101h px� 
c
NPhase 1.1.1.4 Implementation Feasibility check On IDelay | Checksum: 641d9fd7
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
z

Phase %s%s
101*constraints2
1.1.1.5 2default:default2'
Commit IO Placement2default:defaultZ18-101h px� 
L
7Phase 1.1.1.5 Commit IO Placement | Checksum: ffafa981
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
U
@Phase 1.1.1 ParallelPlaceIOClockAndInitTop | Checksum: ffafa981
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
h
SPhase 1.1 IO Placement/ Clock Placement/ Build Placer Device | Checksum: 10b2fc302
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
}

Phase %s%s
101*constraints2
1.2 2default:default2.
Build Placer Netlist Model2default:defaultZ18-101h px� 
v

Phase %s%s
101*constraints2
1.2.1 2default:default2%
Place Init Design2default:defaultZ18-101h px� 
r

Phase %s%s
101*constraints2
1.2.1.1 2default:default2
Make Others2default:defaultZ18-101h px� 
E
0Phase 1.2.1.1 Make Others | Checksum: 13b831a9e
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
~

Phase %s%s
101*constraints2
1.2.1.2 2default:default2+
Init Lut Pin Assignment2default:defaultZ18-101h px� 
Q
<Phase 1.2.1.2 Init Lut Pin Assignment | Checksum: 13b831a9e
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:02 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
I
4Phase 1.2.1 Place Init Design | Checksum: 1f4c96687
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
P
;Phase 1.2 Build Placer Netlist Model | Checksum: 1f4c96687
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
z

Phase %s%s
101*constraints2
1.3 2default:default2+
Constrain Clocks/Macros2default:defaultZ18-101h px� 
M
8Phase 1.3 Constrain Clocks/Macros | Checksum: 1f4c96687
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
I
4Phase 1 Placer Initialization | Checksum: 1f4c96687
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:03 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
q

Phase %s%s
101*constraints2
2 2default:default2$
Global Placement2default:defaultZ18-101h px� 
D
/Phase 2 Global Placement | Checksum: 15c1efea3
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:04 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
q

Phase %s%s
101*constraints2
3 2default:default2$
Detail Placement2default:defaultZ18-101h px� 
}

Phase %s%s
101*constraints2
3.1 2default:default2.
Commit Multi Column Macros2default:defaultZ18-101h px� 
P
;Phase 3.1 Commit Multi Column Macros | Checksum: 15c1efea3
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:04 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 


Phase %s%s
101*constraints2
3.2 2default:default20
Commit Most Macros & LUTRAMs2default:defaultZ18-101h px� 
Q
<Phase 3.2 Commit Most Macros & LUTRAMs | Checksum: dccb1a1c
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:05 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
y

Phase %s%s
101*constraints2
3.3 2default:default2*
Area Swap Optimization2default:defaultZ18-101h px� 
K
6Phase 3.3 Area Swap Optimization | Checksum: f609d65d
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:05 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
x

Phase %s%s
101*constraints2
3.4 2default:default2)
updateClock Trees: DP2default:defaultZ18-101h px� 
J
5Phase 3.4 updateClock Trees: DP | Checksum: f609d65d
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:05 ; elapsed = 00:00:03 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
x

Phase %s%s
101*constraints2
3.5 2default:default2)
Timing Path Optimizer2default:defaultZ18-101h px� 
K
6Phase 3.5 Timing Path Optimizer | Checksum: 15d8afe77
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:05 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
t

Phase %s%s
101*constraints2
3.6 2default:default2%
Fast Optimization2default:defaultZ18-101h px� 
G
2Phase 3.6 Fast Optimization | Checksum: 15d8afe77
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:05 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 


Phase %s%s
101*constraints2
3.7 2default:default20
Small Shape Detail Placement2default:defaultZ18-101h px� 
R
=Phase 3.7 Small Shape Detail Placement | Checksum: 180de468c
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
u

Phase %s%s
101*constraints2
3.8 2default:default2&
Re-assign LUT pins2default:defaultZ18-101h px� 
G
2Phase 3.8 Re-assign LUT pins | Checksum: df62c5a4
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
�

Phase %s%s
101*constraints2
3.9 2default:default22
Pipeline Register Optimization2default:defaultZ18-101h px� 
S
>Phase 3.9 Pipeline Register Optimization | Checksum: df62c5a4
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
u

Phase %s%s
101*constraints2
3.10 2default:default2%
Fast Optimization2default:defaultZ18-101h px� 
G
2Phase 3.10 Fast Optimization | Checksum: df62c5a4
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
C
.Phase 3 Detail Placement | Checksum: df62c5a4
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:04 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
�

Phase %s%s
101*constraints2
4 2default:default2<
(Post Placement Optimization and Clean-Up2default:defaultZ18-101h px� 
{

Phase %s%s
101*constraints2
4.1 2default:default2,
Post Commit Optimization2default:defaultZ18-101h px� 
E
%Done setting XDC timing constraints.
35*timingZ38-35h px� 
}

Phase %s%s
101*constraints2
4.1.1 2default:default2,
updateClock Trees: PCOPT2default:defaultZ18-101h px� 
O
:Phase 4.1.1 updateClock Trees: PCOPT | Checksum: 47fe798e
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
�

Phase %s%s
101*constraints2
4.1.2 2default:default2/
Post Placement Optimization2default:defaultZ18-101h px� 
�
hPost Placement Timing Summary WNS=%s. For the most accurate timing information please run report_timing.610*place2
2.8842default:defaultZ30-746h px� 
R
=Phase 4.1.2 Post Placement Optimization | Checksum: e9aebc99
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
M
8Phase 4.1 Post Commit Optimization | Checksum: e9aebc99
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
�

Phase %s%s
101*constraints2
4.2 2default:default25
!Sweep Clock Roots: Post-Placement2default:defaultZ18-101h px� 
V
APhase 4.2 Sweep Clock Roots: Post-Placement | Checksum: e9aebc99
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
�

Phase %s%s
101*constraints2
4.3 2default:default27
#Uram Pipeline Register Optimization2default:defaultZ18-101h px� 
X
CPhase 4.3 Uram Pipeline Register Optimization | Checksum: e9aebc99
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
y

Phase %s%s
101*constraints2
4.4 2default:default2*
Post Placement Cleanup2default:defaultZ18-101h px� 
K
6Phase 4.4 Post Placement Cleanup | Checksum: e9aebc99
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
s

Phase %s%s
101*constraints2
4.5 2default:default2$
Placer Reporting2default:defaultZ18-101h px� 
E
0Phase 4.5 Placer Reporting | Checksum: e9aebc99
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
z

Phase %s%s
101*constraints2
4.6 2default:default2+
Final Placement Cleanup2default:defaultZ18-101h px� 
M
8Phase 4.6 Final Placement Cleanup | Checksum: 10cd031fe
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
\
GPhase 4 Post Placement Optimization and Clean-Up | Checksum: 10cd031fe
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
=
(Ending Placer Task | Checksum: db40aad9
*commonh px� 
�

%s
*constraints2o
[Time (s): cpu = 00:00:06 ; elapsed = 00:00:05 . Memory (MB): peak = 995.676 ; gain = 15.9102default:defaulth px� 
Z
Releasing license: %s
83*common2"
Implementation2default:defaultZ17-83h px� 
�
G%s Infos, %s Warnings, %s Critical Warnings and %s Errors encountered.
28*	vivadotcl2
362default:default2
02default:default2
02default:default2
02default:defaultZ4-41h px� 
^
%s completed successfully
29*	vivadotcl2 
place_design2default:defaultZ4-42h px� 
�
I%sTime (s): cpu = %s ; elapsed = %s . Memory (MB): peak = %s ; gain = %s
268*common2"
place_design: 2default:default2
00:00:072default:default2
00:00:062default:default2
995.6762default:default2
15.9102default:defaultZ17-268h px� 
D
Writing placer database...
1603*designutilsZ20-1893h px� 
=
Writing XDEF routing.
211*designutilsZ20-211h px� 
J
#Writing XDEF routing logical nets.
209*designutilsZ20-209h px� 
J
#Writing XDEF routing special nets.
210*designutilsZ20-210h px� 
�
I%sTime (s): cpu = %s ; elapsed = %s . Memory (MB): peak = %s ; gain = %s
268*common2)
Write XDEF Complete: 2default:default2
00:00:012default:default2 
00:00:00.6712default:default2
995.6762default:default2
0.0002default:defaultZ17-268h px� 

jreport_io: Time (s): cpu = 00:00:00 ; elapsed = 00:00:00.137 . Memory (MB): peak = 995.676 ; gain = 0.000
*commonh px� 
�
sreport_utilization: Time (s): cpu = 00:00:00 ; elapsed = 00:00:00.072 . Memory (MB): peak = 995.676 ; gain = 0.000
*commonh px� 
�
treport_control_sets: Time (s): cpu = 00:00:00 ; elapsed = 00:00:00.010 . Memory (MB): peak = 995.676 ; gain = 0.000
*commonh px� 


End Record