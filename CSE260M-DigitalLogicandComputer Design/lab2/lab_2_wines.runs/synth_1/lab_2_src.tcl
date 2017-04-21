# 
# Synthesis run script generated by Vivado
# 

set_msg_config -id {HDL 9-1061} -limit 100000
set_msg_config -id {HDL 9-1654} -limit 100000
create_project -in_memory -part xc7a100tcsg324-1

set_param project.singleFileAddWarning.threshold 0
set_param project.compositeFile.enableAutoGeneration 0
set_param synth.vivado.isSynthRun true
set_property webtalk.parent_dir {H:/My Documents/CSE260M/lab2/lab_2_wines.cache/wt} [current_project]
set_property parent.project_path {H:/My Documents/CSE260M/lab2/lab_2_wines.xpr} [current_project]
set_property default_lib xil_defaultlib [current_project]
set_property target_language VHDL [current_project]
read_vhdl -library xil_defaultlib {{H:/My Documents/CSE260M/lab2/lab_2_wines.srcs/sources_1/new/lab_2_src.vhd}}
foreach dcp [get_files -quiet -all *.dcp] {
  set_property used_in_implementation false $dcp
}
read_xdc {{H:/My Documents/CSE260M/lab2/lab_2_wines.srcs/constrs_1/new/lab_2_const.xdc}}
set_property used_in_implementation false [get_files {{H:/My Documents/CSE260M/lab2/lab_2_wines.srcs/constrs_1/new/lab_2_const.xdc}}]


synth_design -top lab_2_src -part xc7a100tcsg324-1


write_checkpoint -force -noxdef lab_2_src.dcp

catch { report_utilization -file lab_2_src_utilization_synth.rpt -pb lab_2_src_utilization_synth.pb }