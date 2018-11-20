#!/bin/bash

#load Config File

service_path="/home/appzone/Lastmile/MicroServices/Services/" 
config_file_path="/home/appzone/Lastmile/"

source $config_file_path"service.config"

##################################################
####       Loading Functions 
##################################################
function load_all(){
	# killing All Old Running Process  
	kill_all	
	# Getting Max Number of Modules 

	echo -e "\n***********************************"
	echo -e "**    Start Loading ...            **"
	echo -e "\n***********************************"
        n_modules="${#modules[@]}"	
	# Loop on All Modules 	
	for (( i=0; i<${n_modules}; i++ ));
	do
	   ## Getting Module Information 
	   jar="${jars[$i]}"
           module="${modules[$i]}" 
	   option="${options[$i]}"
           config="${configs[$i]}"					
	  echo ">> Loading Module $module ........"
	  echo ">> Found Jar "$jar
          echo ">> OPTIONS "$option 				
	  run_command="nohup java -Dname=$module $option -jar $service_path$jar $config &"
	  ## Executing Command
	  eval $run_command
          echo $run_command
	  echo "Module $module is Now Up & Running"	
	  echo "***************************************"
	   sleep 20	
	done
	## Done Message	 
	echo "** 	All Services Successfully Loaded"
}

function load_group(){
        ## Getting passed List 
	package_list=("$@")
	## Killing Old
	kill_group ${package_list[@]}
	### Start to Load 
        echo -e "\n***********************************"
	echo -e "**    Start Loading ...            **"
	echo -e "\n***********************************"
	for i in "${package_list[@]}"; do
	           echo "Element $i"
		    ## Getting Module Information 
	           jar="${jars[$i]}"
		   module="${modules[$i]}" 
		   option="${options[$i]}"
		   config="${configs[$i]}"
                   translate_file="${translate_files[$i]}"			
		   echo ">> Loading Module $module ........"
		   echo ">> Found Jar "$jar
		   echo ">> OPTIONS "$option
		   run_command="nohup java -Dname=$module $option -jar $service_path$jar $config $translate_file &"
		 ## Executing Command
		   eval $run_command
		   echo $run_command	
		   echo "Module $module is Now Up & Running"	
		   echo "***************************************"
		   sleep 25
        done
}

function load_single(){
	## Access Specific Task
	index=$1
	#killing Service
	kill_single $index
	## Load Module	
	i=$((index-1))	
	jar="${jars[$i]}"
        module="${modules[$i]}" 
	option="${options[$i]}"
	config="${configs[$i]}"
        translate_file="${translate_files[$i]}"
	#######Show Service
	echo ">> Loading Module $module ........."
	echo ">> Found Jar "$jar
	echo ">> OPTIONS "$option	
	run_command="nohup java -Dname=$module $option -jar $service_path$jar $config $translate_file &"
        echo $run_command
	## Execute Command	
	eval $run_command
	echo ">> Module $module is Now Up And Running"
}

##################################################
####       Killing Modules
##################################################
function kill_all(){
	echo -e "\n***********************************"
	echo -e "**    Kill Loading ...            **"
	echo -e "\n***********************************"
	# Getting ArrayLength 
        n_modules="${#modules[@]}"	
	# Loop on All Modules 	
	for (( i=0; i<${n_modules}; i++ ));
	do
	   ## Getting Module Information 
           module="${modules[$i]}" 
	   run_command="pkill -f $module"
	   echo ">> Killing Service $module if running ........."
	   ## Execute Command 
	  eval $run_command
	  echo ">> Module $module is Now Killed"
	done	  
	echo "All Services Successfully Killed" 
}

function kill_group(){
	## Getting passed List 
	package_list=("$@")
        echo "Killing Group"
	for i in "${package_list[@]}"; do
	           echo "Element $i"
		   ## Getting Module Information 
	           module="${modules[$i]}" 
		   run_command="pkill -f $module"
		   echo ">> Killing Service $module if running ........."
		   ## Execute Command 
		  eval $run_command
		  echo ">> Module $module is Now Killed"
        done	
}

function kill_single(){
	## Access Specific Task
	index=$1
	i=$((index-1))
        module="${modules[$i]}" 
	echo ">> Killing Module $module if running ........."
	run_command="pkill -f $module"
	## Execute Command	
        eval $run_command
	echo ">> Module $module is Now Killed"
}


########################################################################################################
####       Modules Main Functions 
########################################################################################################
	function exit_script(){
	    echo -e "\n#####################################################################" 
	    echo "**** 	                Good By .. AppZone BackEnd Team           ****"
	    echo -e "#####################################################################\n"
	    exit
	}

  function process_group(){
      
	package_list=("$@")

	echo -e "\n***********************************"
	echo -e "** Action To Do    :             **"
	echo -e "** ================              **"
	echo -e "** 1- Load/Run                   **"
	echo -e "** 2- Kill                       **"
	echo -e "************************************"

	echo -n "Enter Your Choise Here >> "
	read action_choise
	## Service	 
	if [ $action_choise -eq 1 ]; 
	then   
		# Process Group Of BackEnd MicroServices 
		load_group ${package_list[@]}  
	elif [ $action_choise -eq 2 ];
	then
    		kill_group ${package_list[@]}  
        fi      	


      
  }	  

  function standalone_main(){
	echo -e "\n************************************"       
	echo -e "\n***********************************"
	echo -e "** Select Service :                "
	echo -e "** ================                "
        # Getting Maximum Number of Modules Provided By System 
	n_modules="${#modules[@]}"
	# Viewing all Supported Modules
	for (( i=0; i<${n_modules}; i++ ));
	do
	   ## Getting Module Information 
           module="${modules[$i]}"
	   index=$((i+1)) 
                if [ $index -le 9 ]; 
		then   
			echo "**  $index- $module             "			
		else
			echo "** $index- $module             "		
		fi
	   #echo "** $index- $module             "	
	done 
	
	echo "** 0- ALL"
	echo -e "***********************************\n"

	echo -n "What is Your Choise ??  "
	read service_choise
	
	echo -e "\n***********************************"
	echo -e "** Action To Do    :             **"
	echo -e "** ================              **"
	echo -e "** 1- Load/Run                   **"
	echo -e "** 2- Kill                       **"
	echo -e "************************************"

	echo -n "Enter Your Choise Here >> "
	read action_choise	

	if [ $service_choise -eq 0 ]; 
	then
		if [ $action_choise -eq 1 ]; 
		then   
			load_all
		elif [ $action_choise -eq 2 ];
		then
		    	kill_all
		fi 		
	else 
		if [ $action_choise -eq 1 ]; 
		then   
			load_single $service_choise
		elif [ $action_choise -eq 2 ];
		then
		    	kill_single $service_choise
		fi 

	fi	
   }


function modules_main(){
	
	echo -e "\n*******************************************"
	echo -e "** Which Module You want To Process ??  **"
	echo -e "** ================                     **"
	echo -e "** 1- Back-End Services                 **"
	echo -e "** 2- Web App                           **"
	echo -e "** 3- Static Content                    **"
	echo -e "** 4- RealTime Services                 **"
	echo -e "** 0- Back                              **"
	echo -e "********************************************\n"

	echo -n "** Enter Your Choise Here >> "
	read service_choise 	

	if [ $service_choise -eq 1 ]; 
	then   
		# Process Group Of BackEnd MicroServices 
		process_group ${backEnd[@]}  
	elif [ $service_choise -eq 2 ];
	then 
		# Process Group Of Web App MicroServices 
		process_group ${webApp[@]}
	elif [ $service_choise -eq 3 ];
	then 
		#Process Group Of Static Content MicroServices 
		process_group ${staticContent[@]}
	elif [ $service_choise -eq 4 ];
	then 
		#Process Group Of RealTime MicroServices
		process_group ${realTime[@]}
	elif [ $service_choise -eq 0 ];
	then 
		#Exit Script 
		main_screen
	else
		echo "Sorry , You Entered Invalid Action ,, GoodBye"
		main_screen
	fi

   }



###################################################################################
# Main Start Of Application 
###################################################################################
function appzone_logo(){

  echo  "       /\\     _____  _____    _____   ____          ______"
  echo  "      /  \\    |    \\ |    \\       /  |    | |\   | |"
  echo  "     /    \\   |____/ |____/      /   |    | | \  | | " 
  echo  "    /______\\  |      |          /    |    | |  \ | |_____"
  echo  "   /        \\ |      |         /     |    | |   \| |"
  echo  "  /          \\|      |        /____  ------ |    | |______"


  
}

function main_screen(){
	echo -e "\n\n#####################################################################"
	echo "**** 	               Welcome to Appzone Loading Tool         ****"
	echo "#####################################################################"
	echo -e "\n***********************************"
	echo -e "** Select Service :                **"
	echo -e "** ================                **"
	echo -e "** 1- Standalone Services          **"
	echo -e "** 2- Module Services              **"
	echo -e "** 0- Exit                         **"
	echo -e "***********************************\n"
	echo -n "Enter Your Choise Here >> "
	read service_choise

	if [ $service_choise -eq 1 ]; 
	then   
		# Case Developer Want to Run Standalone Service	  	
		standalone_main  
	elif [ $service_choise -eq 2 ];
	then 
		# Case Developer want to Run Module Services 
		modules_main
	elif [ $service_choise -eq 0 ];
	then 
		#Exit Script 
		exit_script
	else
		echo -e "Sorry , You Entered Invalid Action ,, Try Again\n"
		main_screen
	fi
}
        
if [ $# -eq 0 ]; then
    appzone_logo
    main_screen
else
    if [ $1 == 'run' ]; 
	then   	
	load_group ${basic[@]} 
    else
	exit
    fi	
fi


##### Start Application

