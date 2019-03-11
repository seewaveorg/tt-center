package com.ngs.util;

import java.util.ArrayList;
import java.util.List;

//import com.ngs.model.Patients;

public class myArrayListQeue {
	
	 // create an array list
    public static ArrayList al = new ArrayList();
    
	
	/**public static void initiateQue(List<Patients> list){
	     
	      System.out.println("Initial size of al: " + al.size());

	      // add elements to the array list
	      for(Patients patient:list){
	    	  al.add(patient);
	      }
	      
	      /**
	      al.add("C");
	      al.add("A");
	      al.add("E");
	      al.add("B");
	      al.add("D");
	      al.add("F");
	     
	      System.out.println("Size of al after additions: " + al.size());

	      // display the array list
	      System.out.println("Contents of al: " + al);
	      // Remove elements from the array list
	      al.remove("F");
	      al.remove(2);
	      System.out.println("Size of al after deletions: " + al.size());
	   		**/
	  /******** }
	
	
	public static Patients getFirstPatient(){
		Patients patient =  (Patients) al.get(0);
		removePatient(0);
		return patient;
	}
	
	public boolean setPatient(Patients patatient, int index){
		 al.add(index, patatient);
		 return true;
	}

	public static boolean removePatient(int index){
		al.remove(index);
		return true;
	}
	
	public static boolean sendBackToQPatient(Patients patient){
		al.add(0,patient);
		return true;
	}
	
	public static String getListString(){
		String tem = "{";
		if(al.size()>0){
		for(int i =0; i< al.size(); i++){
			Patients patient = (Patients) al.get(i);
			tem = tem + patient.getDisplay()+",";
		}
		//tem = tem.substring(0,al.size()-1);
		}
		tem = tem + "}";
		 return tem;
		   
	}
	
	public static ArrayList getQ(){
		return al;
	}
	
	// q management functions
	
	// adding a normal patient to the q
	public static int addNormalPatient(Patients patient){
		int tem = 10;
			//tem = al.size() -1;
			//al.add(tem, patient);
			al.add(patient);
			
		return tem;
		
	}
	
	// adding Emergency patient to the q
	public static int addEmergencyPatient(Patients patient){
	int tem = 0;	
		for(int i = 0; i< al.size(); i++){
		Patients obj = (Patients) al.get(i);
			if(obj.getPiority()!= 3){
				al.add(i,patient);
				break;
			}
			tem = i;
		}
		return tem;
	}
	
	// adding piority patient to the q
	public static int addPiorityPatient(Patients patient){
		int tem = 10;
		for(int i = 0; i< al.size(); i++){
		if((i % 4) == 0 && i >3){
		Patients obj = (Patients) al.get(i);
			if(obj.getPiority()!= 2){
				al.add(i,patient);
				tem = i;
				break;
			}
		}else if(i==al.size()-1){
			al.add(patient);
			tem = i;
			break;
		}
		
		}
		return tem;
	}
	
	public static void makeArrayClear(){
		al.clear();
	}
	
	public static int getSize(){
		return al.size();
	}
	
	public static void initiateQeue(List<Patients> list){
		 makeArrayClear();
		for(Patients patient:list){
			if(patient.getPiority()==1){
				addNormalPatient(patient);
			}else if(patient.getPiority()==2){
				addPiorityPatient(patient);
			}else if(patient.getPiority()==3){
				addEmergencyPatient(patient);
			}
		}
		
	}******/
	
}
