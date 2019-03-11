package com.ngs.service;

import java.util.List;

import com.ngs.model.Activities;
import com.ngs.model.Rolls;
/**import com.ngs.model.Body;
import com.ngs.model.Decease;
import com.ngs.model.Doctors;
import com.ngs.model.DrugProducts;
import com.ngs.model.Medicine;

import com.ngs.model.Symptoms;**/


public class HtmlCommonMap{
	
	
	 
	 
	 // Single side Table Generation #####################################################################################
	 
	 // generate a Rolls table
	 public String generateRollsTable(List<Rolls> list){
		 String table ="<table class='table' id='objectOneTable' name='objectOneTable' > <thead> <tr><th>Act</th> <th>NO</th> <th>Roll</th>"+
                "<th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Rolls roll : list) {
			 i++;
			 table= table +
	                "<tr value='"+roll.getId()+"' class='side-link' onClick='rowClick()'>"
	                		+ "<td><input type='checkbox'class='check-objectOne'  id='check-objectOne'  value='"+roll.getId()+"'></td>"
	                		+ "<td>"+i+"</td> <td class='firstname'>"+roll.getName()+"</td> <td class='lastname'>"+roll.getId() +"</td> </tr>";
	                
		 }
			return table+"</table>";

	 }
	
	 
	 // generate a Decease table
	 /**public String generateFirstObjectTableDecease(List<Decease> list, String url, String alt){
		 String table ="<table class='table' id='objectOneTable' name='objectOneTable' > <thead> <tr><th>Act</th> <th>NO</th> <th>Roll</th>"+
                "<th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Decease object: list) {
			 i++;
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	                		+ "<td><input type='checkbox'class='check-objectOne' alt='"+alt+"' id='check-objectOne' name='"+url+"'  value='"+object.getId()+"'></td>"
	                		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getId() +"</td> </tr>";
	                
		 }
			return table+"</table>";

	 }
	
	 // generate a Body Table
	 public String generateFirstObjectTableBody(List<Body> list, String url,String alt){
		 String table ="<table class='table' id='objectOneTable' name='objectOneTable' > <thead> <tr><th>Act</th> <th>NO</th> <th>Roll</th>"+
                "<th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Body object: list) {
			 i++;
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	                		+ "<td><input type='checkbox'class='check-objectOne' alt='"+alt+"' id='check-objectOne' name='"+url+"'  value='"+object.getId()+"'></td>"
	                		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getId() +"</td> </tr>";
	                
		 }
			return table+"</table>";

	 }******/
	 
	 // generate a Roll Table
	 public String generateFirstObjectTableRolls(List<Rolls> list, String url,String alt){
		 String table ="<table class='table' id='objectOneTable' name='objectOneTable' > <thead> <tr><th>Act</th> <th>NO</th> <th>Roll</th>"+
                "<th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Rolls object: list) {
			 i++;
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	                		+ "<td><input type='checkbox'class='check-objectOne' alt='"+alt+"' id='check-objectOne' name='"+url+"'  value='"+object.getId()+"'></td>"
	                		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getId() +"</td> </tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 
	 // End of single side table generation ##############################################################################
	 
	 // Double side table generation *************************************************************************************
	 
	 // generate a Activities table
	 public String generateActivitiesTable(List<Activities> list){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th>Activity Name</th>"+
                "<th>Description</th> <th>Section</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Activities activity : list) {
			 i++; 
			 table= table +
	                "<tr value='"+activity.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td><input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+activity.getId()+"'></td>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+activity.getActivitiy()+"</td> <td class='lastname'>"+activity.getDescription() +"</td> <td >"+activity.getCategory().getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 
	 // generate a Symptoms table
	/** public String generateSecondObjectTableSymptoms(List<Symptoms> list){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th> Name</th>"+
                "<th>Description</th> <th>ID</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Symptoms  object: list) {
			 i++; 
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td><input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"'></td>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getDescription() +"</td> <td >"+object.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 // generate a Symptoms table with selected options
	 public String generateSecondObjectTableSymptomsSelected(List<Symptoms> list, List<Symptoms> select){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th> Name</th>"+
                "<th>Description</th> <th>ID</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Symptoms  object: list) {
			 
			 String  checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"'>	";				 
			  
			// String checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+sobject.getId()+"'>";
			 for(Symptoms sym: select){
				 
				// if(object.equals(sym)){
				 if(object.getId() == sym.getId()){
					checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"' checked>	";				 
					  break;
				 } 
			 }
			 i++; 
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td> "+checkbox +"<d>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getDescription() +"</td> <td >"+object.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 
	 
	 // generate a Medicine table
	 public String generateSecondObjectTableMedicine(List<Medicine> list){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th> Name</th>"+
                "<th>Description</th> <th>ID</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Medicine  object: list) {
			 i++; 
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td><input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"'></td>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getDescription() +"</td> <td >"+object.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 
	 // generate a Symptoms table with selected options
	 public String generateSecondObjectTableMedicinesSelected(List<Medicine> list, List<Medicine> select){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th> Name</th>"+
                "<th>Description</th> <th>ID</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Medicine  object: list) {
			 
			 String  checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"'>	";				 
			  
			// String checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+sobject.getId()+"'>";
			 for(Medicine sym: select){
				 
				// if(object.equals(sym)){
				 if(object.getId() == sym.getId()){
					checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"' checked>	";				 
					  break;
				 } 
			 }
			 i++; 
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td> "+checkbox +"<d>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+object.getName()+"</td> <td class='lastname'>"+object.getDescription() +"</td> <td >"+object.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }*****/
	 
	 
	 
	 
	 // generate a Activites table
	 public String generateSecondObjectTableActivities(List<Activities> list){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th> Name</th>"+
                "<th>Description</th> <th>ID</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Activities object: list) {
			 i++; 
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td><input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"'></td>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+object.getActivitiy()+"</td> <td class='lastname'>"+object.getDescription() +"</td> <td >"+object.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 
	 // generate a Activites table with selected options
	 public String generateSecondObjectTableActivitiesSelected(List<Activities> list, List<Activities> select){
		 String table ="<table class='table' id='objectManyTable' name='objectManyTable' > <thead> <tr><th>Act</th> <th> No </th><th> Name</th>"+
                "<th>Description</th> <th>ID</th> </tr> </thead> <tbody>";
		int i=0;
		 for (Activities  object: list) {
			 
			 String  checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"'>	";				 
			  
			// String checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+sobject.getId()+"'>";
			 for(Activities sym: select){
				 
				// if(object.equals(sym)){
				 if(object.getId() == sym.getId()){
					checkbox = "<input type='checkbox' class='check-objectMany'  id='check-objectMany'  value='"+object.getId()+"' checked>	";				 
					  break;
				 } 
			 }
			 i++; 
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick()'>"
	            	+ "<td> "+checkbox +"<d>"
           		+ "<td>"+i+"</td> <td class='firstname'>"+object.getActivitiy()+"</td> <td class='lastname'>"+object.getDescription() +"</td> <td >"+object.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 // End of douoble side table generation *****************************************************************************************************************
	 

	 
	 
	 
}

