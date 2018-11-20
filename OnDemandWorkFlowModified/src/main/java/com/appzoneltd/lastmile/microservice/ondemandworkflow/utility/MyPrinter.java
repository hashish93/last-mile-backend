package com.appzoneltd.lastmile.microservice.ondemandworkflow.utility;

public class MyPrinter {

	public static void print(String Module,String text){
		
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println("> > >  Module Name "+Module);
        System.out.println("> > >  print "+text);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");	
	}
	
	public static void workflowStep(String stepName){		
		System.out.println("********************************************************************");
        System.out.println("> > >  STEP : "+stepName);
        System.out.println("********************************************************************");	
	}
	
}
