package com.ngs.service;

//import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


//import com.ngs.dao.implementation.DoctorDaoImpl;

import com.ngs.model.Activities;
import com.ngs.model.Rolls;
/***import com.ngs.model.Alergies;
import com.ngs.model.Availability;
import com.ngs.model.Body;
import com.ngs.model.Decease;
import com.ngs.model.DeceaseCategory;
import com.ngs.model.DoctorVisit;
import com.ngs.model.DoctorVisitHasDeceise;
import com.ngs.model.DoctorVisitHasHospitalService;
import com.ngs.model.DoctorVisitHasMedicleTests;
import com.ngs.model.DoctorVisitHasSymptoms;
import com.ngs.model.Doctors;
import com.ngs.model.Dose;
import com.ngs.model.DrugProducts;
import com.ngs.model.Frequencies;
import com.ngs.model.HospitalHasServices;**/
import com.ngs.model.Login;
/**import com.ngs.model.Medicine;
import com.ngs.model.MedicleTests;
import com.ngs.model.Patients;
import com.ngs.model.Person;
import com.ngs.model.PersonHasDeceases;
import com.ngs.model.PersonHasSocialhabits;
import com.ngs.model.Pharmasist;
import com.ngs.model.Prescription;
import com.ngs.model.Readings;

import com.ngs.model.Sits;
import com.ngs.model.Stock;
import com.ngs.model.Substock;
import com.ngs.model.Symptoms;
import com.ngs.model.TestParameters;
import com.ngs.model.TestsResults;
import com.ngs.model.Units;
import com.ngs.util.Age;
import com.ngs.util.AgeCalculator;
import com.ngs.util.FileSupport;
import com.ngs.util.MyDateUtil;
import com.ngs.model.Ruutes;
import com.ngs.model.Services;***/

public class HtmlServiceOPD {
	
	static Logger log4j = Logger.getLogger(HtmlServiceOPD.class);
	
	 // generate a person table
	/********** public String generatePersonTable(List<Person> list){
		 String table ="<table class='table' > <thead> <tr> <th>image</th> <th>Initials</th><th>First Name</th>"+
                 "<th>Birth Date</th><th>NIC</th><th>ID</th></tr> </thead> <tbody>";
		int i=0;
			
		 for (Person person : list) {
			 i++;
			 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='LoadInPopup' name='GetPersonImage.do' value='"+person.getId()+"' alt='' onClick='LoadOnClick();' />"; 
				
			 table= table +
	                "<tr value='"+person.getId()+"' class='side-link' onClick='rowClick()'><td>"+transferImage+"</td> <td class='firstname'>"+
					 person.getInitials()+"</td> <td class='lastname'>"+person.getFirstname() +"</td> "
					 		+ " <td class='birthday'>"+person.getBirthdate()+"</td><td class='nic'>"+
					 person.getNic()+"</td><td class='id'>"+person.getId()+"</td></tr>";
	                
		 }
			return table+"</tbody></table>";

	 }
	 
	 
	 
	 // generate a person table for searching options with less data
	 public String generatePersonTableWithoutImage(List<Person> list){
		 String table ="<table class='table' > <thead> <tr>  <th>Initials</th><th>First Name</th>"+
                 "<th>Family Name</th><th>NIC</th><th>ID</th></tr> </thead> <tbody>";
		int i=0;
			
		 for (Person person : list) {
			 i++;
				
			 table= table +
	                "<tr value='"+person.getId()+"' class='side-link' onClick='rowClick()'> <td class='firstname'>"+person.getInitials()+"</td> <td class='lastname'>"+person.getFirstname() +"</td> <td class='familyname'>"+person.getFamilyname()+"</td> <td class='nic'>"+person.getNic()+"</td><td class='id'>"+person.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 
	 // generate a person table
	 public String generateLoginTable(List<Login> list){
		 String table ="<table class='table' > <thead> <tr> <th>#</th> <th>Person Name</th><th>Rolle</th>"+
                 "<th>User name</th><th>Login ID</th><th>NIC</th><th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Login login : list) {
			 i++;
			 table= table +
	                "<tr value='"+login.getId()+"' class='side-link' onClick='LoginRowClick()'><td>"+i+"</td> <td class='firstname'>"+login.getPerson().getFirstname()+"</td> <td class='lastname'>"+login.getRolls().getName()+"</td> <td class='familyname'>"+login.getUsername()+"</td> <td class='birthday'>"+login.getId()+"</td><td class='nic'>"+login.getPerson().getNic()+"</td> <td class='id'>"+login.getPerson().getId()+"</td></tr>";
	                
		 }
		 
		    table = table+"</table>";
			
		    return table;

	 }
	 
	 public String generatePatientTable(List<Patients> list){
		// System.out.println("################## Generating patient table");
		 String table ="<table class='table' > <thead> <tr>  <th>No</th> <th>First Name</th><th>Last Name</th>"+
                 "<th>Arrival </th><th>Piority</th><th>Q </th><th>Reg ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Patients patient : list) {
			 i++;
				
			 table= table +
	                "<tr value='"+patient.getId()+"' class='side-link' onClick='rowClick()'> <td>"+i+"</td> <td class='firstname'>"+patient.getPerson().getFirstname()+"</td> <td class='lastname'>"+patient.getPerson().getInitials()+"</td> <td class='date'>"+patient.getArrivaltime()+"</td> <td class='Piority'>"+patient.getPiority()+"</td><td class='qnumber'>"+patient.getQnumber()+"</td> <td class='id'>"+patient.getPerson().getId()+"</td></tr>";
	                
		 }
		 
		 table = table + "</table>";
		   
		 System.out.println(table);
		return table;
	 }
	 
	 
	 // this method will open a window to edit patient prescription
	 public String generatePatientTableForHistory(List<Patients> list){
		
		 String table ="<table class='table' > <thead> <tr><th> Presc. </th> <th> Tests </th> <th> Reffered. </th> <th>Patient ID</th> <th>First Name</th><th>Attendence </th>"+
                 "<th> Symptoms </th><th> Deceases </th><th> Medicines </th> <tbody>";
		int i=0;
		 for (Patients patient : list) {
			// String printImage = "<img src='resources/images/Open.png'  height='25' width='25' class='LoadInPopup' name='getPatientHistoryPrescription.do' value='"+patient.getId()+"' alt='patientID' onClick='LoadOnClick();' />"; 
			   String printImage = "<img src='resources/images/Open.png'  height='25' width='25' class='LoadInPopup' name='getPatientHistoryPrescription.do' value='"+patient.getId()+"' alt='patientID'  />"; 
					
			 i++;
			 String deceses = "";
			 String medicines = "";
			 String symptoms = "";
			 String services ="";
			 String tests ="";
			 
			 if(patient.getDoctorVisits() != null){
				 Set< DoctorVisit> dvlist =patient.getDoctorVisits();
				 for (DoctorVisit dv: dvlist) {
					  // now get al deceses any doctor visti have
					 		if(dv.getDoctorVisitHasDeceises() != null){
					 			Set<DoctorVisitHasDeceise> dvhdlist = dv.getDoctorVisitHasDeceises();
					 				for(DoctorVisitHasDeceise dvhd :dvhdlist){
					 					deceses = deceses +"<br>"+ dvhd.getDecease().getName();
					 				}
					 			
					 		}
					 		if(dv.getPrescriptions() != null){
					 			Set<Prescription> preslist = dv.getPrescriptions();
					 				for(Prescription pr :preslist){
					 					medicines = medicines +"<br>"+ pr.getMedicine().getName();
					 				}
					 			
					 		}
					 		if(dv.getDoctorVisitHasSymptomses() != null){
					 			Set<DoctorVisitHasSymptoms> symptomlist = dv.getDoctorVisitHasSymptomses();
					 				for(DoctorVisitHasSymptoms symptom :symptomlist){
					 					symptoms = symptoms +"<br>"+ symptom.getSymptoms().getName();
					 				}
					 			
					 		}
					 		// get the all Reffered to informations
					 		if(dv.getDoctorVisitHasHospitalServices() != null){
					 			Set<DoctorVisitHasHospitalService> dvhhslist = dv.getDoctorVisitHasHospitalServices();
					 				for(DoctorVisitHasHospitalService dvhhs :dvhhslist){
					 					services = services +"<br>"+ dvhhs.getServices().getName();
					 				}
					 			
					 		}
							// get the all Medicle Test  informations
					 		if(dv.getDoctorVisitHasMedicleTestses() != null){
					 			Set<DoctorVisitHasMedicleTests> dvhmtlist = dv.getDoctorVisitHasMedicleTestses();
					 				for(DoctorVisitHasMedicleTests dvhmt :dvhmtlist){
					 					tests = tests +"<br>"+ dvhmt.getMedicleTests().getName();
					 				}
					 			
					 		}
					 		
					  }

			 }
				
			 table= table +
	                "<tr value='"+patient.getId()+"' class='side-link' ><td>"+printImage+"</td> "+
					 "<td> "+tests+"</td> <td> "+services+"</td><td>"+
	                patient.getId()+"</td> <td class='firstname'>"+patient.getPerson().getFirstname()+"</td> <td class='lastname'>"+
					 patient.getArrivaltime()+"</td> <td >"+symptoms+"</td> <td>"+deceses+
	                "</td> <td >"+medicines+"</td></tr>";
	                
		 }
		 
		 table = table + "</table>";
		   
		// System.out.println(table);
			return table;
	 }
	 
	 
	 public String generatePatientTableForAday(List<Patients> list){
		 System.out.println("################## Generating patient table");
		 String table ="<table class='table' > <thead> <tr> <th>Print</th> <th>Edit</th> <th>First Name</th><th>Last Name</th><th>Birth Date</th>"+
                 "<th> Arrival</th><th> Piority</th><th>Q </th><th>NIC</th></tr> </thead> <tbody>";
		int i=0;
		 for (Patients patient : list) {
			 String printImage = "<img src='resources/images/print.png'  height='20' width='20' class='LoadInPopup' name='printOpdChit.do' value='"+patient.getId()+"' alt='patientID' onClick='LoadOnClick();' />"; 
				
			 i++;
			 table= table +
	                "<tr value='"+patient.getId()+"'  class='side-link' onClick='#'><td> "+printImage+
	                "</td><td> Edit</td> <td class='firstname'>"+patient.getPerson().getFirstname()+"</td> <td class='lastname'>"+patient.getPerson().getInitials()+"</td> <td>"+patient.getPerson().getBirthdate()+"</td> <td class='date'>"+patient.getArrivaltime()+"</td> <td class='Piority'>"+patient.getPiority()+"</td><td class='qnumber'>"+patient.getQnumber() +"</td> <td class='id'>"+patient.getPerson().getNic()+"</td></tr>";
			  
		 	}
		 
		 table = table + "</table>";
		   
		 System.out.println(table);
			return table;
	 }
	 
	 
	 
		
		
	 public String generateDoctorsTable(List<Doctors> list){
		
		 String table ="<table class='table' > <thead> <tr> <th>No</th> <th>First Name</th><th>Last Name</th>"+
                "</tr> </thead> <tbody>";
		int i=0;
		 for (Doctors doctors : list) {
			 i++;
			 table= table +
	                "<tr value='"+doctors.getId()+"' class='side-link' onClick='rowClick()'><td>"+i+"</td> <td class='firstname'>"+doctors.getPerson().getFirstname()+"</td> <td class='lastname'>"+doctors.getPerson().getFamilyname()+"</td> </tr>";
	                
		 }
		 
		 table = table + "</table>";
		   
		 System.out.println(table);
			return table;
	 }
	 
	 
	 
		
		
	 public String generatePharmasistTable(List<Pharmasist> list){
		
		 String table ="<table class='table' > <thead> <tr> <th>NIC</th> <th>First Name</th><th>Family Name</th>"+
                "<th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (Pharmasist parmasist : list) {
			 i++;
			 table= table +
	                "<tr value='"+parmasist.getId()+"' class='side-link' onClick='rowClickPharmasist()'><td>"+parmasist.getPerson().getNic()+"</td> <td class='firstname'>"+parmasist.getPerson().getFirstname()+"</td> <td class='lastname'>"+parmasist.getPerson().getFamilyname()+"</td> <td>"+parmasist.getId()+"</td> </tr>";
	                
		 }
		 
		 table = table + "</table>";
		   
		 System.out.println(table);
			return table;
	 }
	 
	 
	 // Generate Medicle Tests table
	 public String generateMedicleTestTable(List<MedicleTests> list){
			
		 String table ="<table class='table' > <thead> <tr> <th>Test Name</th> <th>Test Type</th><th>Description</th>"+
                "<th>ID</th></tr> </thead> <tbody>";
		int i=0;
		 for (MedicleTests medtest : list) {
			 i++;
			 table= table +
	                "<tr value='"+medtest.getId()+"' class='side-link' onClick='rowClick()'><td>"+medtest.getName()+"</td> <td class='firstname'>"+medtest.getTesttypes().getName()+"</td> <td class='lastname'>"+medtest.getDescription()+"</td> <td>"+medtest.getId()+"</td> </tr>";
	                
		 }
		 
		 table = table + "</table>";
		   
		 
			return table;
	 }
	 
		
	 // generate a person table
	 public String generateDeceaseTable(List<Decease> list){
		 String table ="<table class='table' > <thead> <tr>  <th>Name</th>"+
                 "<th>Code</th><th>ID</th></tr> </thead> <tbody>";
		int i=0;
			
		 for (Decease decease : list) {
			 i++;
			
			 table= table +
	                "<tr value='"+decease.getId()+"' class='side-link' onClick='rowClick()'><td class='lastname'>"+decease.getName() +"</td> <td class='familyname'>"+decease.getCode()+"</td> <td class='birthday'>"+decease.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 // generate a decease Category table
	 public String generateDeceaseCategoryTable(List<DeceaseCategory> list){
		 String table ="<table class='table' > <thead> <tr>  <th>Name</th>"+
                 "<th>Code</th><th>ID</th></tr> </thead> <tbody>";
		int i=0;
			
		 for (DeceaseCategory deceasecat : list) {
			 i++;
			
			 table= table +
	                "<tr value='"+deceasecat.getId()+"' class='side-link' onClick='rowClick()'><td class='lastname'>"+deceasecat.getName() +"</td> <td class='familyname'>"+deceasecat.getCode()+"</td> <td class='birthday'>"+deceasecat.getId()+"</td></tr>";
	                
		 }
			return table+"</table>";

	 }
	 
	 // get the table to view all lab tests
	 public String generatePersonAlergiesTable(List<Alergies> list){
		 String style = "font-family: 	Arial, Verdana, sans-serif; color: red;	font-size:	15px;";
		 
		 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th>Allergy Type </th><th>Medicine coused </th><th> Other couses </th>"+
		 "<th>Comment </th></tr> </thead> <tbody>";
		int i=0;
		 for (Alergies alergies : list) {
			 i++;
			 String medname="Not Relevent";
		     if(alergies.getMedicine()!=null){
		    	 medname = alergies.getMedicine().getName();
		     }
			 table= table +
	                "<tr style ='"+style+"' value='"+alergies.getId()+"' style='color:red;'  class='side-link' onClick='#'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+alergies.getAllergytype()+"</td> <td >"
	                 +medname+"</td>"+
	                "<td>"+alergies.getName() +"</td>  "+
	                "<td >"+ alergies.getDescription()+"</td></tr>";
			  
			
		 }
		 
		 table = table + "</table>";
		   
		
			return table;
	 }
	 
	// get the table to view patients decease records
		 public String generatePersonDeceaseTable(List<PersonHasDeceases> list){
			 
			 String style = "font-family: 	Arial, Verdana, sans-serif; color: Orange;	font-size:	15px;";
			 
			 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th> Decease </th><th> Code </th><th> Duration </th>"+
			 "</tr> </thead> <tbody>";
			int i=0;
			 for (PersonHasDeceases personhasdecease : list) {
				 i++;
					 table= table +
		                "<tr style ='"+style+"' value='"+personhasdecease.getId()+"' style='color:Orange;' onClick='#'><td> "+i+" </td>"+
		               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
		                "<td >"+personhasdecease.getDecease().getName()+"</td> <td >"+personhasdecease.getDecease().getCode()+"</td>"+
		                "<td >"+personhasdecease.getComment()+"</td></tr>";
				  
				
			 }
			 
			 table = table + "</table>";
	
			return table;
		 }
		 
		// get the table to view patients decease records
		public String generatePersonSocialAttributeTable(List<PersonHasSocialhabits> list){
			 String style = "font-family: 	Arial, Verdana, sans-serif; color: Brown;	font-size:	15px;";
			 
			 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th> Social Attribute </th><th> Duration </th>"+
			 "</tr> </thead> <tbody>";
			int i=0;
			 for (PersonHasSocialhabits personhassociaattrib : list) {
				 i++;
					 table= table +
		                "<tr style ='"+style+"' value='"+personhassociaattrib.getId()+"' style='color:Brown;'	  onClick='#'><td> "+i+" </td>"+
		               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
		                "<td >"+personhassociaattrib.getSocialhabits().getName()+"</td> <td >"+personhassociaattrib.getDescription()+"</td>"+
		                "</tr>";
				  
				
			 }
			 
			 table = table + "</table>";
	
			return table;
		 }
		 
	 
	 public String generateStockTable(List<Stock> list){
	 
		 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th>Add</th><th>Tranf</th><th>Black List</th><th>Deduct</th>"+
		 "<th>Medicine</th><th>Product</th><th>Code</th><th>Expire Date</th><th>Quantity</th><th>Batch No</th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		 for (Stock stock : list) {
			 System.out.println("#############stock id is "+stock.getId());
			 i++;
			 String addImage ="<img src='resources/images/add.png' height='20' width='20' class='doFunction' name='showWindowAddStock.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getDrugProducts().getName()+"' />";
			 String transferImage = "<img src='resources/images/tranfer.png'  height='20' width='20' class='doTransfer' name='showStockTransfer.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
			 String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 String deductImage = "<img src='resources/images/deduct.png'  height='20' width='20' class='doDeduct' name='showStockDeduct.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 
			 String medicinename = "";
			 if(stock.getDrugProducts().getMedicine() != null ){
				 medicinename = stock.getDrugProducts().getMedicine().getName();
			 }
			 String color= "white";
			 if(stock.getStatus()==null){
				 color ="white";
			 }else{
			 int status = stock.getStatus();
			 if(status==-1){
				 color = "red";
			 }else if(status==1){
				 color = "white";
			 }else if(status==2){
				 color="green";
			 }
			 
			 }
			 
			 table= table +
	                "<tr bgcolor= "+color+" value='"+stock.getId()+"'  class='side-link' onClick='#'><td> "+i+" </td>"+
	                "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td><td>"+deductImage+"</td>"+
	                "<td >"+medicinename
	                +"</td> <td >"+stock.getDrugProducts().getName()+"</td>"+
	                "<td>"+stock.getDrugProducts().getCode() +"</td>  "+
	                "<td >"+stock.getExpiredDate()+"</td><td >"+stock.getQuantitiy() +"</td> <td >"+stock.getBatch()+"</td></tr>";
			  
			
		     }
		 
		      table = table + "</table>";
		   
		
			return table;
	 }
	 
	 
// this table is going to be used to show in stock override times
	 public String generateAvaialbleStockTable(List<Stock> list, int hospitalID){
	 
		 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th> Use </th> "+
		 //"<th>Add</th><th>Tranf</th><th>Black List</th>"+
		 "<th>Medicine</th><th>Product</th><th>Code</th><th>Expire Date</th><th>Quantity</th><th> Sub Stock </th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		 for (Stock stock : list) {
			 String check ="<input type='checkbox' onClick='selectStock();' name='fooby[1][]' class='check-stock'  id='check-stock'  value='"+stock.getId()+"'>";
			 i++;
			 
			 String color= "white";
			 if(stock.getStatus()==null){
				 color ="white";
			 }else{
			 int status = stock.getStatus();
			 if(status==5){ // neer to break even
				 color = "red";
			 }else if(status==1){ // this is new stock but not used so far
				 color = "green";
			 }else if(status==2){ // this is what consuming now
				 color="white";
			 }
			 
			 }
			 
			 table= table +
	                "<tr bgcolor= "+color+" value='"+stock.getId()+"'  class='side-link' onClick='#'><td> "+i+" </td> <td> "+check+"</td>"+
	                
	                "<td >"+stock.getDrugProducts().getMedicine().getName() +"</td> <td >"+stock.getDrugProducts().getName()+"</td>"+
	                "<td>"+stock.getDrugProducts().getCode() +"</td>  "+
	                "<td >"+stock.getExpiredDate()+"</td><td >"+stock.getQuantitiy() +"</td> <td >"+stock.getSubstock().getName()  +"</td></tr>";
			  
			
		     }
		 
		      table = table + "</table>";
		   
		
			return table;
	 }
	 
	 
	 
	 
	 public String generateStockReceiverTable(List<Stock> list){
		 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th>Confirme</th>"+
				 "<th>Medicine</th><th>Product</th><th>Code</th><th>Expire Date</th><th>Quantity</th><th>Batch No</th>"+
		                 "</tr> </thead> <tbody>";
				int i=0;
				 for (Stock stock : list) {
					 if(stock.getGrn()== null){ // all transfered drug stocks are haveing null grns
					 i++;
					  String Image = "<img src='resources/images/confirme.png'  height='20' width='20' class='doBlacklist' name='showStockWhiteList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
					 
					 table= table +
			                "<tr value='"+stock.getId()+"'  class='side-link' onClick='#'><td> "+i+" </td>"+
			                "<td>"+Image+"</td>"+
			                "<td >"+stock.getDrugProducts().getMedicine().getName() +"</td> <td >"+stock.getDrugProducts().getName()+"</td>"+
			                "<td>"+stock.getDrugProducts().getCode() +"</td>  "+
			                "<td >"+stock.getExpiredDate()+"</td><td >"+stock.getQuantitiy() +"</td> <td >"+stock.getBatch()+"</td></tr>";
					  
					 }// clossing if for checking black listed or not
				 }
				 
				 table = table + "</table>";
			
				return table;
	 }
	 
	
	 public String  generateStockBlackListTable(List<Stock> list){
		 
		 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th>White List</th>"+
		 "<th>Medicine</th><th>Product</th><th>Code</th><th>Expire Date</th><th>Quantity</th><th>Batch No</th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		 for (Stock stock : list) {
			 if(stock.getStatus() == -1){
			 i++;
			  String blackListImage = "<img src='resources/images/white.png'  height='20' width='20' class='doBlacklist' name='showStockWhiteList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 
			 table= table +
	                "<tr value='"+stock.getId()+"'  class='side-link' onClick='#'><td> "+i+" </td>"+
	                "<td>"+blackListImage+"</td>"+
	                "<td >"+stock.getDrugProducts().getMedicine().getName() +"</td> <td >"+stock.getDrugProducts().getName()+"</td>"+
	                "<td>"+stock.getDrugProducts().getCode() +"</td>  "+
	                "<td >"+stock.getExpiredDate()+"</td><td >"+stock.getQuantitiy() +"</td> <td >"+stock.getBatch()+"</td></tr>";
			  
			 }// clossing if for checking black listed or not
		 }
		 
		 table = table + "</table>";
	
		return table;
	 }
	 
	 
	 // get the table to view all lab tests
	 public String generateMedicleTestsTable(List<MedicleTests> list){
		 
		 String table ="<table class='table' id='drugTable'> <thead> <tr> <th>No</th> <th>Add</th><th>Tranf</th><th>Black List</th>"+
		 "<th>Medicine</th><th>Product</th><th>Code</th><th>Expire Date</th><th>Quantity</th><th>Batch No</th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		 for (MedicleTests medicaltest : list) {
			 i++;
			
			 table= table +
	                "<tr value='"+medicaltest.getId()+"'  class='side-link' onClick='#'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+medicaltest.getName()+"</td> <td >"+medicaltest.getName()+"</td>"+
	                "<td>"+medicaltest.getDescription() +"</td>  "+
	                "<td >"+medicaltest.getName()+"</td></tr>";
			  
			
		 }
		 
		 table = table + "</table>";
		   
		
			return table;
	 }
	 
	 
	 // get the Assigned Lab test table
	 public String generateAssignedMedicleTestsTable(List<DoctorVisitHasMedicleTests>  list){
		
          
		 String table ="<thead> <tr> <th>No</th> <th>Patient </th><th> Patient NIC</th><th>Patient ID</th>"+
		 "<th>Doctor</th><th>Test Name </th><th> Hos. ID </th><th>Test ID</th><th>Print</th><th>OPen</th>"+
         "</tr> </thead> <tbody>";
		 int i=0;
		 for (DoctorVisitHasMedicleTests medicaltest : list) {
			 String bgcolor = "white";
			 i++;
			 if(medicaltest.getStatus()== 1){
				 bgcolor = "black";
			 }else if(medicaltest.getStatus() == 2){
				 bgcolor = "green";
			 }else{
				 bgcolor = "red";
			 }
			 String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='printMedTestBarCordeHtml.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />";
			 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistMediTest.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />"; 
			 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
			// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 
			 table= table +
	                "<tr value='"+medicaltest.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getFamilyname()+"</td> <td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
	                "<td>"+medicaltest.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
	                "<td >"+medicaltest.getDoctorVisit().getDoctors().getPerson().getFamilyname()+"</td><td >"+medicaltest.getMedicleTests().getName() +"</td> <td >"
	                +medicaltest.getDoctorVisit().getPatients().getHosid()+"</td><td >"+medicaltest.getId()+"</td><td >"+addImage+"</td><td >"+transferImage+"</td></tr>";
		
		 	}
	
			return table;
	 }
	 
	 
	// get the Assigned Lab test table
		 public String generateAssignedMedicleTestsTableForaDoctorVisit(List<DoctorVisitHasMedicleTests>  list){
			
	         String table = " No any prescribed Medicle Test found !";
	
			 int i=0;
			 for (DoctorVisitHasMedicleTests medicaltest : list) {
				 String bgcolor = "white";
				 table =" <table width='100%' height='20px' cellpadding='1' cellspacing='1'  id = 'MediTestTable' class='table'><thead> <tr> <th>No</th> <th>Patient </th><th> Patient NIC</th><th>Patient ID</th>"+
						 "<th>Doctor</th><th>Test Name </th><th> Hos. ID </th><th>Test ID</th><th>Print</th><th>OPen</th>"+
				         "</tr> </thead> <tbody>";
				 i++;
				 if(medicaltest.getStatus()== 1){
					 bgcolor = "black";
				 }else if(medicaltest.getStatus() == 2){
					 bgcolor = "green";
				 }else{
					 bgcolor = "red";
				 }
				 String addImage ="<img src='resources/images/print.png' height='20' width='20' class='printbarcodesticker' name='printMedTestBarCordeHtml.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' onClick='fuckmeifyoucan("+medicaltest.getId()+");' />";
				 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistMediTest.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' onClick='catchmeifyoucan("+medicaltest.getId()+");' />"; 
				
				 table= table +
		                "<tr value='"+medicaltest.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
		               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
		                "<td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getFamilyname()+"</td> <td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
		                "<td>"+medicaltest.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
		                "<td >"+medicaltest.getDoctorVisit().getDoctors().getPerson().getFamilyname()+"</td><td >"+medicaltest.getMedicleTests().getName() +"</td> <td >"
		                +medicaltest.getDoctorVisit().getPatients().getHosid()+"</td><td >"+medicaltest.getId()+"</td><td >"+addImage+"</td><td >"+transferImage+"</td></tr>";
				  
				
			 	}
			 
			 table = table + "</table>";
			   
			
				return table;
		 }
		 
	 
	 // get the Assigned Lab test table
	 public String generateAssignedMedicleTestsTableForFullView(List<DoctorVisitHasMedicleTests>  list){
		 
		 String table ="<table class='table'><thead> <tr> <th>No</th> <th>Patient </th><th> Patient NIC</th><th>Patient ID</th>"+
		 "<th>Doctor</th><th>Test Name </th><th> Hos. ID </th><th>Test ID</th><th> Result </th><th> Reports </th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		 for (DoctorVisitHasMedicleTests medicaltest : list) {
			 String bgcolor = "white";
			 i++;
			 if(medicaltest.getStatus()== 1){
				 bgcolor = "black";
			 }else if(medicaltest.getStatus() == 2){
				 bgcolor = "green";
			 }else{
				 bgcolor = "red";
			 }
			 
			 // get files if a folder available from given name
			   FileSupport fs = new FileSupport();
			   String report = "No Reports";
			   try {
				if(fs.existsFolder("/home/admin/nextgenmed/labreports/"+medicaltest.getId()+"/")){
				report = "<a href='downloadLabReport.do?medtestid="+medicaltest.getId()+"' > Download </a>";
				}
			   } catch (Exception e) {
				// TODO Auto-generated catch block
				log4j.error("check whether file folder available error "+e.getMessage());
			   }
			 
			 
			  String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVistHasMedicleTests' id='showDoctorVistHasMedicleTests' name='ShowDocVistMediTestResults.do' value='"+medicaltest.getId()+"' alt='medtestid' onClick='showResults();' />"; 
			  
			  table= table +
	                "<tr value='"+medicaltest.getId()+"'  style='color:"+bgcolor+";'><td> "+i+" </td>"+
	               
	                "<td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getFamilyname()+"</td> <td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
	                "<td>"+medicaltest.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
	                "<td >"+medicaltest.getDoctorVisit().getDoctors().getPerson().getFamilyname()+"</td><td >"+medicaltest.getMedicleTests().getName() +"</td> <td >"
	                +medicaltest.getDoctorVisit().getPatients().getHosid()+"</td><td >"+medicaltest.getId()+"</td><td >"+transferImage+"</td> <td>"+report+"</td></tr>";
			  
			
		 }
		 
		table = table + "</table>";
		   
		
		return table;
	 }
	 
	 
	 
	// get the Assigned Lab test table for ECG or Xray
	public String generateAssignedMedicleTestsTableForTestTypes(List<DoctorVisitHasMedicleTests>  list, int testID){
			 
			 String table =" <thead> <tr> <th>No</th> <th>Patient </th><th> Patient NIC</th><th>Patient ID</th>"+
			 "<th>Doctor</th><th>Test Name </th><th> Note </th><th>Test ID</th><th>Print</th><th>OPen</th>"+
	                 "</tr> </thead> <tbody>";
			int i=0;
			 for (DoctorVisitHasMedicleTests medicaltest : list) {
				 String bgcolor = "white";
				 i++;
				 if(medicaltest.getStatus()== 1){
					 bgcolor = "black";
				 }else if(medicaltest.getStatus() == 2){
					 bgcolor = "green";
				 }else{
					 bgcolor = "red";
				 }
				 String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='printMedTestBarCordeHtml.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />";
				 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistMediTestFoerSpecific.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />"; 
				 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
				// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
				 if(medicaltest.getMedicleTests().getId()==testID){
				 table= table +
		                "<tr value='"+medicaltest.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
		               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
		                "<td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getFamilyname()+"</td> <td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
		                "<td>"+medicaltest.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
		                "<td >"+medicaltest.getDoctorVisit().getDoctors().getPerson().getFamilyname()+"</td><td >"+medicaltest.getMedicleTests().getName() +"</td> <td >"+medicaltest.getNote()+"</td><td >"+medicaltest.getId()+"</td><td >"+addImage+"</td><td >"+transferImage+"</td></tr>";
				 }
				
			 }
			 
			// table = table + "</table>";
			   
			
				return table;
		 }
		 
		 
	 // get the Assigned vaccination table for nurse
	 public String generateAssignedVaccinationsTable(List<DoctorVisitHasHospitalService>  list){
		 
		 String table =" <thead> <tr> <th>No</th> <th>Patient </th><th> Patient NIC</th><th>Patient ID</th>"+
		 "<th>Doctor</th><th>Test Name </th><th> Note </th><th>Test ID</th><th>OPen</th>"+
                 "</tr> </thead> <tbody>";
		 int i=0;
		 for (DoctorVisitHasHospitalService dvhhs : list) {
			 String bgcolor = "white";
			 i++;
			 if(dvhhs.getStatus()== 1){
				 bgcolor = "black";
			 }else if(dvhhs.getStatus() == 2){
				 bgcolor = "green";
			 }else{
				 bgcolor = "red";
			 }
			// String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='printMedTestBarCordeHtml.do' value='"+dvhhs.getId()+"' alt='"+dvhhs.getId()+"' />";
			 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistVaccinationPopup.do' value='"+dvhhs.getId()+"' alt='"+dvhhs.getId()+"' />"; 
			 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
			// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 String drugProduct = "Not Defined";
			 if(dvhhs.getDrugProducts()!= null){
				 drugProduct = dvhhs.getDrugProducts().getName();
			 }
			 table= table +
	                "<tr value='"+dvhhs.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+dvhhs.getDoctorVisit().getPatients().getPerson().getFamilyname()+"</td> <td >"+dvhhs.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
	                "<td>"+dvhhs.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
	                "<td >"+dvhhs.getDoctorVisit().getDoctors().getPerson().getFamilyname()+
	                "</td><td >"+drugProduct +
	                "</td> <td >"+dvhhs.getComment()+
	                "</td><td >"+dvhhs.getId()+
	                "</td><td >"+transferImage+"</td></tr>";
			  
			
		      }
		 
		  // table = table + "</table>";
		   
		
			return table;
	 }
	 
	 
	 // get the Etureffered table by doctors
	 public String generatRefferedETUTable(List<DoctorVisitHasHospitalService>  list){
		 
		 String table ="<table class='table'> <thead> <tr> <th>No</th> <th>Service </th><th> Patient NIC </th><th>Patient ID</th>"+
		 "<th> Name </th><th>Hos. Reg no</th><th> Note </th><th>Test ID</th><th>OPen</th>"+
                 "</tr> </thead> <tbody>";
		 int i=0;
		 for (DoctorVisitHasHospitalService dvhhs : list) {
			 String bgcolor = "white";
			 i++;
			 if(dvhhs.getStatus()== 1){
				 bgcolor = "black";
			 }else if(dvhhs.getStatus() == 2){
				 bgcolor = "green";
			 }else{
				 bgcolor = "red";
			 }
			// String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='printMedTestBarCordeHtml.do' value='"+dvhhs.getId()+"' alt='"+dvhhs.getId()+"' />";
			 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistServicePopup.do' value='"+dvhhs.getId()+"' alt='"+dvhhs.getId()+"' onClick='updateDoctorVisit();' />"; 
			 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
			// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			
			 /**
			  * no need to use drug according to new arrangement
			 String drug = "not relevent";
			 if(dvhhs.getDrugProducts()!=null){
				drug = dvhhs.getDrugProducts().getName();
				log4j.error("doctorvisithashosptialservice table has null doctorvisits"+dvhhs.getId()); 
			 }
			 **/
			 // get only ETU reffered hospital services
			// if(dvhhs.getServices().getId()== 2 || dvhhs.getServices().getId() == 3 || dvhhs.getServices().getId() ==4){
			 /**************if(dvhhs.getDoctorVisit().getPatients().getPerson() != null ){
			 table= table +
	                "<tr value='"+dvhhs.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+dvhhs.getServices().getName()+"</td> <td >"
	               +dvhhs.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
	                "<td >"+dvhhs.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
	                "<td >"+dvhhs.getDoctorVisit().getDoctors().getPerson().getFamilyname()+"</td>"
	                 +"<td >"+dvhhs.getDoctorVisit().getPatients().getHosid()+"</td ><td >"
	                +dvhhs.getComment()+"</td><td >"
	                +dvhhs.getId()+"</td ><td >"
	                +transferImage+"</td ></tr >";
			 }
		 }
		 
		 table = table + "</table>";
		   
		
			return table;
	 }
	 
	 
	 
	 // get sample collected lab test to view by lab assistant
	 public String generateCollectedMedicleTestsTable(List<DoctorVisitHasMedicleTests>  list){
		 
		 String table =" <thead> <tr> <th>No</th> <th>Patient </th><th> Patient NIC</th><th>Patient ID</th>"+
		 "<th>Doctor</th><th>Test Name </th><th> Note </th><th>Test ID</th><th>Upload</th><th>Open</th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		 for (DoctorVisitHasMedicleTests medicaltest : list) {
			 String bgcolor = "white";
			 i++;
			 if(medicaltest.getStatus()== 1){
				 bgcolor = "black";
			 }else if(medicaltest.getStatus() == 2){
				 bgcolor = "green";
			 }else{
				 bgcolor = "red";
			 }
			 String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='ShowTestReportUpload.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />";
			 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistMediTestForLabUpdate.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />"; 
			 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
			// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 
			 table= table +
	                "<tr value='"+medicaltest.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getFamilyname()+"</td> <td >"+medicaltest.getDoctorVisit().getPatients().getPerson().getNic()+"</td>"+
	                "<td>"+medicaltest.getDoctorVisit().getPatients().getPerson().getId()+"</td>  "+
	                "<td >"+medicaltest.getDoctorVisit().getDoctors().getPerson().getFamilyname()+"</td><td >"
	                +medicaltest.getMedicleTests().getName() +"</td> <td >"+medicaltest.getNote()+"</td><td >"+medicaltest.getId()+"</td><td >"+addImage+"</td><td >"+transferImage+"</td></tr>";
			  
			
		 }
		 
		// table = table + "</table>";
		   
		
			return table;
	 }
	 
	 /**
	 // get the table for test parameters for lab test result update
	 public String generateTestParametersUpdateTable(List<TestParameters>  list, List<TestsResults> testresults){
		 
		 String table ="Please Fill Test Results <br> <table class='table'><thead> <tr> <th>No</th> <th>Parameter</th>"+
		 "<th>low</th><th>Upper</th><th> Value</th><th> Unit</th>"+
                 "</tr> </thead> <tbody>";
		int i=0;
		
		 for (TestParameters testparameters : list) {
		
			 String bgcolor = "black";
			 i++;
			 String vlaue = 0.0;
			 
			 // get the value of test parameter and update it
			 if(testresults != null){
				 for (TestsResults result : testresults) {
					 if(testparameters.getId() == result.getTestParameters().getId()){
						 vlaue = result.getTestvalue();
					 }
				 }
			 }
			 
			 /**
			 if(testparameters.getStatus()== 1){
				 bgcolor = "black";
			 }else if(medicaltest.getStatus() == 2){
				 bgcolor = "green";
			 }else{
				 bgcolor = "red";
			 }
			 String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='printMedTestBarCordeHtml.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />";
			 String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistMediTest.do' value='"+medicaltest.getId()+"' alt='"+medicaltest.getId()+"' />"; 
			 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
			// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
			 
			 table= table +
	                "<tr value='"+testparameters.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
	               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
	                "<td >"+testparameters.getName()+"</td> <td >"+testparameters.getLowlimit()+"</td>"+
	                "<td>"+testparameters.getUplimit()+"</td>  "+
	                "<td >"+"<input type='text' class='paraminputs'alt='"+testparameters.getId()+"' value='"+vlaue+"' />"+"</td><td>"+testparameters.getUnit()+" </td></tr>";
			  
			
		 }
		 
		 table = table + "</table>";
	
			return table;
	 } 
	 
	 **/
	 // generate symptom table when search by attributes
	/****************** public String generateSymptomSearchTable(List<Symptoms> list){
		String table ="<table class='table' > <thead> <tr> <th>No</th> <th>Name</th><th>Description</th>"+
                 "<th>ID </th></tr> </thead> <tbody>";
		int i=0;
		 for (Symptoms symptom : list) {
			 i++;
			 table= table +
	                "<tr value='"+symptom.getId()+"' class='side-link' onClick='rowClick();'><td>"+i+"</td> <td class='name'>"+symptom.getName()+"</td> <td class='description'>"+symptom.getDescription()+"</td> <td class='id'>"+symptom.getId()+"</td> </tr>";
	                
		 }
		 
		 table = table + "</table>";
		return table;
	 }
	 
	
	 
	 
	 
	

	 
	 public String generateBodyTable(List<Body> list){
			// System.out.println("################## Generating Body table");
			 String table ="<table class='table' > <thead> <tr> <th>Id</th> <th>Name</th>"+
	                "</tr> </thead> <tbody>";
			int i=0;
			 for (Body body : list) {
				 i++;
				 table= table +
		                "<tr value='"+body.getId()+"' class='side-link' onClick='rowClick()'><td>"+i+"</td> <td class='firstname'>"+body.getName()+"</td></tr>";
		                
			 }
			 
			 table = table + "</table>";
			   
			 System.out.println(table);
				return table;
		 }
	 
	 
	 
	 public String generateDrugProductsTable(List<DrugProducts> list){
			// System.out.println("################## Generating Drugs table");
			 String table ="<table class='table' > <thead> <tr> <th>No</th> <th>Drug Product Name</th><th>Drug Product Code</th>"+
	                "</tr> </thead> <tbody>";
			int i=0;
			 for (DrugProducts drugproducts : list) {
				 i++;
				 table= table +
		                "<tr value='"+drugproducts.getId()+"' class='side-link' onClick='rowClick()'><td>"+i+"</td> <td class='firstname'>"+drugproducts.getName()+"</td><td class='lastname'>"+drugproducts.getCode()+"</td></tr>";
		                
			 }
			 
			 table = table + "</table>";
			   
			 System.out.println(table);
				return table;
		 }
	 
	 
	 
	   // this will generate the medicien table
	 public String generateMedicineTable(List<Medicine> list){
			
			 String table ="<table class='table' > <thead> <tr> <th>No</th> <th>Medicine Name</th><th>Description</th>"+
	                "</tr> </thead> <tbody>";
			int i=0;
			 for (Medicine medicine : list) {
				 i++;
				 table= table +
		                "<tr value='"+medicine.getId()+"' class='side-link' onClick='rowClick()'><td>"+i+"</td> <td class='Medicine Name'>"+medicine.getName()+"</td> <td class='Code'>"+medicine.getDescription()+"</td> </tr>";
		                
			 }
			 
			 table = table + "</table>";
			   
			 System.out.println(table);
			 return table;
		 }
	
	 
	 
	 
	 // generate symptom table when search by attributes
	 public String generateDrugDoseTable(List<Dose> list){
		String table ="<table class='table' > <thead> <tr> <th>Param </th> <th>Age bellow </th><th> Dose </th>"+
                 "<th> Frequency </th> <th> Duration </th>  </tr> </thead> <tbody>";
		int i=0;
		 for (Dose dose: list) {
			 i++;
			 table= table +
	                "<tr value='"+dose.getId()+"' class='side-link' onClick='rowClick();'><td>"+dose.getDosepara().getName()+"</td> <td class='name'>"+dose.getValuelimit()+
	                "</td> <td class='description'>"+dose.getDose()+"</td> <td class='id'>"+dose.getTimesperday()+"</td>"+
	                " <td class='id'>"+dose.getDuration()+"</td></tr>";
	                
		 }
		 
		 table = table + "</table>";
		return table;
	 }
	 
	 
	 // generate symptom table when search by attributes
	 public String generateSubStockTable(List<Substock> list){
		String table ="<table class='table' > <thead> <tr> <th>No</th> <th> Name </th><th> Hospital</th>"+
                 "<th> Location </th> <th> ID </th>  </tr> </thead> <tbody>";
		int i=0;
		 for (Substock object: list) {
			 i++;
			 table= table +
	                "<tr value='"+object.getId()+"' class='side-link' onClick='rowClick();'><td>"+i+"</td> <td class='name'>"+object.getName()+
	                "</td> <td class='description'>"+object.getHospital().getName()+"</td> <td class='id'>"+object.getLocation()+"</td>"+
	                " <td class='id'>"+object.getId()+"</td></tr>";
	                
		 }
		 
		 table = table + "</table>";
		return table;
	 }

	 //-------------------------------------------------- end of generate tables-------------------------------------------------

	 
	 // ------------------------------------------------- Start of html report generation of drug and patient vise dlevery
	
	public String generateDrugsDeleveryReportTable(List<Prescription>  list){
			 MyDateUtil date = new MyDateUtil();
			 String table ="<table class='table' >  <thead> <tr> <th>No</th> <th> Time </th><th> Person ID </th><th> Patient ID/ Hos ID </th>"+
			 "<th> Time  </th><th>Quantity </th>"+
	                 "</tr> </thead> <tbody>";
			int i=0;
			int cat = 0;
			double tot = 0.0;
			int drugproduct = 0;
			 for (Prescription prescription : list) {
				 String bgcolor = "red";
				 
				 if(drugproduct != prescription.getDrugProducts().getId()){
					 if (drugproduct !=0){
						 table =table +"<tr value='"+prescription.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> # </td>"+
						 		"<td> </td> <td> </td> <td> </td> <td> </td> <td>"+ tot +"</td></tr>";
					 }
					
					
					
					 
					 drugproduct = prescription.getDrugProducts().getId();
					 
					
					 cat = cat + 1;
						table =  table + "<tr> </tr>"+
			                "<tr value='"+prescription.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+cat+" </td>"+
			                 "<td >"+prescription.getDrugProducts().getName()+"</td>  <td> ("+prescription.getDrugProducts().getCode()+") </td></tr>";
				
						// reset the total
						 tot = 0.0;
						
				 } 
			
				 
				 i++;
				 if(prescription.getStatus()== 3){ // overrided
					 bgcolor = "green";
				 }else if(prescription.getStatus() == 2){ // normal drug delevery
					 bgcolor = "black";
				 }else{
					 bgcolor = "green";
				 }
				 
				 // create the date 
				 String day = date.getStringDateTimeFromDate(prescription.getRegtime());
				 
				 // calculate the total
				 tot = tot + prescription.getTotal();
				// String addImage ="<img src='resources/images/print.png' height='20' width='20' class='doFunction' name='printMedTestBarCordeHtml.do' value='"+dvhhs.getId()+"' alt='"+dvhhs.getId()+"' />";
				// String transferImage = "<img src='resources/images/Open.png'  height='20' width='20' class='showDoctorVist' name='ShowDocVistVaccinationPopup.do' value='"+dvhhs.getId()+"' alt='"+dvhhs.getId()+"' />"; 
				 //String receiverImage = "<img src='resources/images/receiver.JPG' height='20' width='20' class='doFunction' value='"+stock.getDrugProducts().getId()+"' />";
				// String blackListImage = "<img src='resources/images/Cancel.png'  height='20' width='20' class='doBlacklist' name='showStockBlackList.do' value='"+stock.getDrugProducts().getId()+"' alt='"+stock.getId()+"' />"; 
				 
				 table= table +
		                "<tr value='"+prescription.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
		               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
		                "<td >"+ day +"</td> <td >"+prescription.getDoctorVisit().getPatients().getPerson().getId() +"</td>"+
		                "<td>"+prescription.getDoctorVisit().getPatients().getId()+" / "+ prescription.getDoctorVisit().getPatients().getHosid()+"</td>  "+
		                "<td >"+prescription.getRegtime()+"</td><td >"+prescription.getTotal() +"</td> </tr>";
				  
				 
				 
			 }
			 
			 // to note down last drug product total count
			 table =table +"<tr   class='side-link' onClick='#' style='color:red;'><td> # </td>"+
				 		"<td> </td> <td> </td> <td> </td> <td> </td> <td>"+ tot +"</td></tr>";
			 
			 table = table + "</table>";
			   
			
				return table;
		 }
		 
		
	
	 // ------------------------------------------------- Start of html report drug balance report
	
	public String generateDrugsBalanceReportTable(List<Prescription>  list){
			 MyDateUtil date = new MyDateUtil();
			 String table ="<table class='table' >  <thead> <tr> <th>No</th> <th> Drug </th><th> Code </th><th> Deduct </th>"+
			 "<th> Balance </th></tr> </thead> <tbody>";
			int i=0;
			int cat = 0;
			double tot = 0.0;
			int drugproduct = 0;
			 for (Prescription prescription : list) {
				 String bgcolor = "red";
				 
				 if(drugproduct != prescription.getDrugProducts().getId()){
					 if (drugproduct !=0){
						 // get the total
						 if(tot != 0.0){
						
							 table =table +" <td>"+ tot +"</td> <td> </td></tr>";
						 }else{
							 table =table +" <tr> </tr>";
						 }
					 }else{
						 table =table +" <tr> </tr>";
					 }
					
					
				
					 
					 drugproduct = prescription.getDrugProducts().getId();
					 
					
					cat = cat + 1;
						table =  table + "<tr> </tr>"+
			                "<tr value='"+prescription.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+cat+" </td>"+
			                 "<td >"+prescription.getDrugProducts().getName()+"</td>  <td> "+prescription.getDrugProducts().getCode()+" </td>";
				
					// reset the total
					tot = 0.0;
						
				 } 
			
				 
				 i++;
				 if(prescription.getStatus()== 3){ // overrided
					 bgcolor = "green";
				 }else if(prescription.getStatus() == 2){ // normal drug delevery
					 bgcolor = "black";
				 }else{
					 bgcolor = "green";
				 }
				 
				 // create the date 
				 String day = date.getStringDateTimeFromDate(prescription.getRegtime());
				 
				 // calculate the total
				 tot = tot + prescription.getTotal();
				
				 /**
				 table= table +
		                "<tr value='"+prescription.getId()+"'  class='side-link' onClick='#' style='color:"+bgcolor+";'><td> "+i+" </td>"+
		               // "<td>"+addImage+"</td><td>"+transferImage+"</td><td>"+blackListImage+"</td>"+
		                "<td >"+ day +"</td> <td >"+prescription.getDoctorVisit().getPatients().getPerson().getId() +"</td>"+
		                "<td>"+prescription.getDoctorVisit().getPatients().getId()+" / "+ prescription.getDoctorVisit().getPatients().getHosid()+"</td>  "+
		                "<td >"+prescription.getRegtime()+"</td><td >"+prescription.getTotal() +"</td> </tr>";
				 **/ 
				 
				 
			/*************************8 }
			 
			 // to note down last drug product total count
			 table =table +" <td>"+ tot +"</td> <td> </td></tr>";
			 
			 table = table + "</table>";
			   
			
				return table;
		 }
		 
	
	 
	 // -------------------------------------------------end of generate reports ------------------------------------------
	 
		 
	public String getBodyPartsOptions(List<Body> bodyparts){
		 String options="";
		 for(Body body: bodyparts){
			 options = options+"<option value='"+body.getId()+"'>"+body.getName()+"</option>";
		 }
		 return options;
		 
		 
	 }
	 
	 public String getSymptomsOptions(List<Symptoms> symptoms){
		 String options="";
		 for(Symptoms symp: symptoms){
			 options = options+"<option value='"+symp.getId()+"'>"+symp.getName()+"</option>";
		 }
		 return options;
		 
		 
	 }
	 
	 public String getDeceiseOptionsList(List<Decease> deceises){
		 String options="<option value='0'>Select decease </option>";
		 for(Decease deceise: deceises){
			 options = options+"<option value='"+deceise.getId()+"'>"+deceise.getName()+"</option>";
		 }
		 return options; 
		 
	 }
	 
	 public String getMedicineOptionsList(List<Medicine> medicines){
		 String options="<option > Select Medicine </option>";
		 for(Medicine medicine: medicines){
			 options = options+"<option value='"+medicine.getId()+"'>"+medicine.getName()+"</option>";
		 }
		 return options; 
		 
	 }
	 
	 public String getOPDMedicineOptionsList(List<Medicine> medicines){
		 String options="<option value='0'> Select Medicine </option>";
		 for(Medicine medicine: medicines){
			// if(medicine.getStatus()==2){ // addd opd requirements only
			 options = options+"<option value='"+medicine.getId()+"'>"+medicine.getName()+"</option>";
			// }
		 }
		 return options; 
		 
	 }
	 
	 public String getReadingsOptionsList(List<Readings> readings){
		 String options="";
		 for(Readings reading: readings){
			 options = options+"<option alt='"+reading.getUnit()+"' value='"+reading.getId()+"'>"+reading.getName()+"</option>";
		 }
		 return options; 
		 
	 }
	 
	 public String getServiceOptionsList(List<HospitalHasServices> hhs){
		 String options="";
		 for(HospitalHasServices reading: hhs){
			 options = options+"<option value='"+reading.getId()+"'>"+reading.getServices().getName()+"</option>";
		 }
		 return options; 
		 
	 }
	 
	 public String getVaccinesOptionsList(List<DrugProducts> list){
		 String options="";
		 for(DrugProducts drugs: list){
			 options = options+"<option value='"+drugs.getId()+"'>"+drugs.getName()+"</option>";
		 }
		 return options; 
		 
	 }
	 
		public String getServicesOptions(List<Services> services){
			 String options="";
			 for(Services service: services){
				 options = options+"<option value='"+service.getId()+"'>"+service.getName()+"</option>";
			 }
			 return options;
			 
			 
		 }
		
		
	  
	  
	  
	  
		 public String getDeceiseTableRecords(List<Decease> deceises){
			 String options=" ";
			 for(Decease deceise: deceises){
				// there are 2 lines hear. 1 one is used for advanced prescriptions
				// options = options+"<tr><td><input type='checkbox' class='check-decease'  id='check-decease' value='"+deceise.getId()+"' onclick='check_medicines();' /></td><td>"+deceise.getName()+"</td><td>"+deceise.getCode()+"</td></tr>";
				 options = options+"<tr><td><input type='checkbox' class='check-decease'  id='check-decease' value='"+deceise.getId()+"' alt='"+deceise.getName()+"' onclick='check_opd_medicines_for_decease("+deceise.getId()+");' checked /></td><td>"+deceise.getName()+"</td><td>"+deceise.getCode()+"</td></tr>";
			 }
			 return options; 
			 
		 }
		 
			 
		   // get decease table for opd status should be 2 onley
			 public String getDeceiseTableRecordsForOPD(List<Decease> deceises){
				 String options=" ";
				 for(Decease deceise: deceises){
					//if(deceise.getStatus()==2){  // removing opd special requirements
					 options = options+"<tr><td><input type='checkbox' class='check-opd-decease'  id='check-opd-decease' value='"+deceise.getId()+
							 "' onclick='check_opd_medicines_for_decease("+deceise.getId()+");' /></td><td>"+deceise.getName()+"</td><td>"+deceise.getCode()+"</td><td>"+deceise.getDeceaseCategory().getName()+"</td>/tr>";
					// }
					}
				 return options; 
				 
			 }
		 
		// this is used to pring external drug prescription for pharmacies
		 public String getExtDrugTable(List<Prescription> prescriptionList){
			    //String options="<table ><tr><th>Dose</th><th>Freq:</th><th>Duration</th></tr>";
			 	String options = "";
				List<DrugProducts> drugs = new  ArrayList();
				for(Prescription prescription: prescriptionList){
					drugs.add(prescription.getDrugProducts());
					String patientDose ="";
					if(prescription.getDose()==0){
						patientDose = "_";
					}else{
						patientDose = prescription.getDose()+"";
					}
					options = options+prescription.getRuutes().getName()+" . "+prescription.getMedicine().getName()+" - "
							+" : </br>"
							+ patientDose +" "+prescription.getUnits().getName()+
							" "+prescription.getFrequencies().getName()+
									" X "+prescription.getNuOfDays()+" days</br></br>";
				
			
				}
				
			/**
			 for(DrugProducts drug: drugs){
				
				 options = options+"<tr><td>"+drug.getMedicine().getName()+"</td><td>"+drug.getName()+"</td><td>"+drug.getCode()+"</td></tr>";
			 }
			 **/
			/******************** options = options+"</table>";
			// System.out.println("################### Table is "+options);
			 return options; 
			 
		 }
	 
	 
		 public String createMedicineForDeceiseTableRecords(List<Medicine> medicines, List<List<DrugProducts>> druglist){
			 
			 String records="";
			 int i =0;
			 for(Medicine medicine: medicines){
				 String options=" ";
				 List<DrugProducts> drugs = druglist.get(i);
				 for(DrugProducts drug: drugs){
					 options = options+"<option value='"+drug.getId()+"'>"+drug.getName()+"</option>";
				 }
				 records = records+"<tr><td> <input type='checkbox'class='check-medicine'  id='check-medicine'  value='"+medicine.getId()+"' alt='"+medicine.getName()+"' > </td> <td>"+medicine.getName()+"</td><td>"+medicine.getCode()+"</td><td><select>"+options+"</select></td> </tr>";
                  i++;
			 }
			
			 return records; 
			 
		 }

		 public String createMedicineTableRecord(Medicine medicine, List<DrugProducts> druglist){
			 
			 String records="";
			 int i =0;
			
				 String options=" ";
				
				 for(DrugProducts drug: druglist){
					 options = options+"<option value='"+drug.getId()+"'>"+drug.getName()+"</option>";
				 }
				 records = records+"<tr><td> <input type='checkbox'class='check-medicine'  id='check-medicine'  value='"+medicine.getId()+"'  alt='"+medicine.getName()+"' > </td> <td>"+medicine.getName()+"</td><td>"+medicine.getCode()+"</td><td><select>"+options+"</select></td> </tr>";
                 i++;
			
			
			 return records; 
			 
		 }
		 
		 
		 public String createServiceTableRecords(List<Services> services){
			 
			 String records="";
			 int i =0;
			
				 String options=" ";
				
				 for(Services service: services){
				
				 records = records+"<tr><td> <input type='checkbox' class='check-service'  id='check-service'  value='"+service.getId()+"'  alt='"+service.getName()+"' checked> </td> <td>"+service.getName()+"</td><td>"+service.getId()+"</td> <td><textarea class='ServiceTextarea' name='serviceinfo' placeholder='Please mention, addices for Services'></textarea> </td></tr>";
                 i++;
				 }
			
			 return records; 
			 
		 }
		 
		 public String createOPDMedicineTableRecord(Medicine medicine, List<DrugProducts> druglist){
			 
			 String records="";
			 int i =0;
			     String opt ="<option value='"+1+"'>1</option><option value='"+2+"'>2 </option><option value='"+3+"' selected='selected' >3</option><option value='"+4+"'> 4 </option> <option value='"+5+"'>5</option><option value='"+6+"'> 6 </option>";
				 String options=" ";
				
				 for(DrugProducts drug: druglist){
					 if(drug==null){
					 options = options+"<option value='"+0+"'>No Drug Products </option>";	 
					 }else{
					 options = options+"<option value='"+drug.getId()+"'>"+drug.getName()+"</option>";
					 }
				 }
				 records = records+"<tr><td> <input type='checkbox' class='check-opd_medicine' value='"+medicine.getId()+"'  alt='"+medicine.getName()+
						 "' > </td> <td>"+medicine.getName()+"</td><td>"+medicine.getDescription()+"</td><td><select>"+options+
						 "</select></td> <td><input type='text'  class='medicine_dose' value ='0' /> mg/ml</td>"+
						 "<td><select value='3' class='medicine_freq'  >"+opt+"</select></td> <td>"+
						 "<select name='medicine_duration'  value='3'  class='medicine_duration' >"+
						 opt+"</select></td></tr>";
                 i++;
			
			 return records; 
			 
		 }
		 
		 
		 public String createOPDMedicineTableRecordWithDose(Medicine medicine, List<Dose> doses, List<Units> units, List<Frequencies> frequencies, List<Ruutes> rootes){
			 
			 String records="";
			 int i =0;
			 	 String aftermeal = "<option value='N/A'>N/A</option><option value='Yes'>yes</option><option value='No'>No</option>";
			     String opt ="<option value='"+1+"'>1</option><option value='"+2+"'>2 </option><option value='"+3+"' selected='selected' >3</option><option value='"+4+"'> 4 </option> <option value='"+5+"'>5</option><option value='"+6+"'> 6 </option>";
				 String options=" ";
				 String freq_options ="";
				 String roots_options ="";
				 String units_options ="";
				 
				if(doses.isEmpty()){
					options = "<option value='"+0+"'>No Any Dose configuration </option>";	 	
				}else{
				 for(Dose dose: doses){
					 if(dose==null ){
					 options = options+"<option value='"+0+"'>No Any Dose configuration </option>";	 
					 }else{
					 options = options+"<option value='"+dose.getId()+"'>"+dose.getDosepara().getName()+"-"+dose.getValuelimit()+"-"+dose.getDose()+"</option>";
					 }
				 }
				}
				
				if(units.isEmpty()){
					units_options = "<option value='"+0+"'>No Any Unit Configuration </option>";	 	
				}else{
				 for(Units unit: units){
					 if(unit==null ){
						 units_options = units_options+"<option value='"+0+"'>No Any Unit Configured </option>";	 
					 }else{
						 units_options = units_options+"<option value='"+unit.getId()+"'>"+unit.getName()+"</option>";
					 }
				 }
				}
				
				if(frequencies.isEmpty()){
					freq_options = "<option value='"+0+"'>No Any Frequency configuration </option>";	 	
				}else{
				 for(Frequencies frequency: frequencies){
					 if(frequency==null ){
						 freq_options = freq_options+"<option value='"+0+"'>No freq</option>";	 
					 }else{
						 freq_options = freq_options+"<option value='"+frequency.getId()+"'>"+frequency.getName()+"</option>";
					 }
				 }
				 
				}
				
				
				if(rootes.isEmpty()){
					roots_options = "<option value='"+0+"'> No </option>";	 
				}else{
					 for(Ruutes root: rootes){
						 if(root==null ){
							 roots_options = roots_options+"<option value='"+0+"'>No Root</option>";	 
						 }else{
							 roots_options = roots_options+"<option value='"+root.getId()+"'>"+root.getName()+"</option>";
						 }
					 }
				}
				
				 records = records+"<tr><td> <input type='checkbox' class='check-opd_medicine' value='"
				+medicine.getId()+"'  alt='"+medicine.getName()+"' checked> </td> <td>"+medicine.getName()+"</td>"
						 //+ "<td>"+medicine.getDescription()+"</td>"
						 + "<td><select>"+options+"</select></td> "
						 + "<td><select  class='medicine_root'  >"+roots_options+"</select></td> "
						 + "<td><input type='text'  class='medicine_dose'  /> </td> "
						 + "<td><select  class='medicine_unit' value ='1' >"+units_options+"</select></td> "
						 
						 + "<td><select value='3' class='medicine_freq'  >"+freq_options+"</select></td> "
						 + "<td><select class='aftermeal'  >"+aftermeal+"</select></td> "
						 + "<td><select name='medicine_duration'  value='3'  class='medicine_duration' >"+opt+"</select></td></tr>";
                 i++;
			
			
			 return records; 
			 
		 }
		 
		 
 
 public String createOpdEditablePrescription(int prescriptionid, Medicine medicine, List<Dose> doses, List<Units> units, List<Frequencies> frequencies, List<Ruutes> rootes){
			 
			String records ="";
			 int i =0;
			 	 String aftermeal = "<option value='N/A'>N/A</option><option value='Yes'>yes</option><option value='No'>No</option>";
			     String opt ="<option value='"+1+"'>1</option><option value='"+2+"'>2 </option><option value='"+3+"' selected='selected' >3</option><option value='"+4+"'> 4 </option> <option value='"+5+"'>5</option><option value='"+6+"'> 6 </option>";
				 String options=" ";
				 String freq_options ="";
				 String roots_options ="";
				 String units_options ="";
				 
				if(doses.isEmpty()){
					options = "<option value='"+0+"'>No Any Dose configuration </option>";	 	
				}else{
				 for(Dose dose: doses){
					 if(dose==null ){
					 options = options+"<option value='"+0+"'>No Any Dose configuration </option>";	 
					 }else{
					 options = options+"<option value='"+dose.getId()+"'>"+dose.getDosepara().getName()+"-"+dose.getValuelimit()+"-"+dose.getDose()+"</option>";
					 }
				 }
				}
				
				if(units.isEmpty()){
					units_options = "<option value='"+0+"'>No Any Unit Configuration </option>";	 	
				}else{
				 for(Units unit: units){
					 if(unit==null ){
						 units_options = units_options+"<option value='"+0+"'>No Any Unit Configured </option>";	 
					 }else{
						 units_options = units_options+"<option value='"+unit.getId()+"'>"+unit.getName()+"</option>";
					 }
				 }
				}
				
				if(frequencies.isEmpty()){
					freq_options = "<option value='"+0+"'>No Any Frequency configuration </option>";	 	
				}else{
				 for(Frequencies frequency: frequencies){
					 if(frequency==null ){
						 freq_options = freq_options+"<option value='"+0+"'>No freq</option>";	 
					 }else{
						 freq_options = freq_options+"<option value='"+frequency.getId()+"'>"+frequency.getName()+"</option>";
					 }
				 }
				}
				
				
				if(rootes.isEmpty()){
					roots_options = "<option value='"+0+"'> No </option>";	 
				}else{
					 for(Ruutes root: rootes){
						 if(root==null ){
							 roots_options = roots_options+"<option value='"+0+"'>No Root</option>";	 
						 }else{
							 roots_options = roots_options+"<option value='"+root.getId()+"'>"+root.getName()+"</option>";
						 }
					 }
				}
				
				 records = records+"<tr><td> <input type='checkbox' class='check-opd_medicine' value='"
				 +prescriptionid+"'  alt='"+medicine.getName()+"'  > </td> <td>"+medicine.getName()+"</td>"
						 //+ "<td>"+medicine.getDescription()+"</td>"
						 //+ "<td><select>"+options+"</select></td> "
						 + "<td><select  class='medicine_root'  >"+roots_options+"</select></td> "
						 + "<td><input type='text'  class='medicine_dose' value ='20' /> </td> "
						 + "<td><select  class='medicine_unit' value ='1' >"+units_options+"</select></td> "
						 
						 + "<td><select value='3' class='medicine_freq'  >"+freq_options+"</select></td> "
						 + "<td><select class='aftermeal'  >"+aftermeal+"</select></td> "
						 + "<td><select name='medicine_duration'  value='3'  class='medicine_duration' >"+opt+"</select></td></tr>";
                
				 
				 
				 i++;
			
			    // records = records + "</table>";
			 return records; 
			 
		 }
		 
 
  
		 
	 // this method to be tested latter. Object wrapping machanism may have to be used in DeceiceController 
	 /**
	 public String getOptions(List<Object> object, String type){
		 String options="";
		 switch(type){
		 case	"Symptoms":
			 for(Object obj: object){
				 Symptoms symptom= (Symptoms)obj;
				 options = options+"<option value='"+symptom.getId()+"'>"+symptom.getName()+"</option>";
			 }
			 break;
		 case  "Deceise":
			 for(Object obj: object){
				 Deceise deceise= (Deceise)obj;
				 options = options+"<option value='"+deceise.getId()+"'>"+deceise.getName()+"</option>";
			 }
			 break;	 
		 
		 }
		
		 return options;
		 
		 
	 }
	 
	 **/
	 
	 /**
	 //generate user specific menu
	// public String generateUserMenu(List<Activities> activities){
	 public String generateUserMenu(String[] activities){
		 
		 System.out.println("####################Processing the menu"+activities.length);
		 String menu = "";
		 if(activities.length > 0){
		menu ="<ul class='nav nav-list bs-docs-sidenav nav-collapse collapse'>"+
                     "<li class='active'>"+
                         "<a href='welcome.do'><i class='icon-chevron-right'></i> Home </a>"+
                      "</li>";
		 for(int x = 0; x < activities.length ;x++) {
			 
	       //System.out.println("###########Actvitites are "+activ.getActivitiy());
	     	menu=menu+	 "<li> <a href='#'><i class='icon-chevron-right'></i>"+activities[x]+"</a> </li>";
	    
	    }
		 }else{  // this man should be a doctor 
		 menu ="<ul class='nav nav-list bs-docs-sidenav nav-collapse collapse'>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> Control Pannel </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> Possition :<input type='text' value='03' /> </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> Patient Name </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> Patient Age </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> Patient Occupation </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i>  <input type='button' value='Add' class="butn" /> </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> <input type='button' value='Save' class="butn" /> </a></li>"+
                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> <input type='button' value='Next' class="butn" /> </a></li>";
		 } 
		  menu = menu+"</ul>"; 
		  return menu;
	 }
	 
	 **/
	 
	 //generate user specific menu
	// public String generateUserMenu(List<Activities> activities){
	 public String getUserMenu(List<Activities> activities){
		 String menu =" ";
		 
		 if(activities.isEmpty()){  // a doctor has logged in  (temporary arrangement
			 // this man should be a doctor 
			 menu ="<ul class='nav nav-list bs-docs-sidenav nav-collapse collapse'>"+
	                     "<li class='active'><a href='welcome.do'><i class='icon-chevron-right'></i> Control Pannel </a></li>"+
	                     "<li ><a ><div><i class='icon-chevron-right'></i> Possition :<select name='select3' size='1' style='width:160px;' > <option value='0'>Select a Possition</option> <option value='1'>Possition 1</option> <option value='2'>Possition 2</option><option value='3'>Possition 3</option><option value='4'>Possition 4</option></select></div> </a></li>"+
	                     "<li ><a ><div><i class='icon-chevron-right'></i> Person ID :<input type='text' value='' id='person_id' name='person_id' /></div> </a></li>"+
	                     "<li ><a ><div><i class='icon-chevron-right'></i> Load Patient :<input type='button' value='Load Patient' id='getPatient' name='getPatient' /></div> </a></li>"+
		                 
	                     "<li > Name </li>"+
	                     "<li > Age </li>"+
	                     "<li > Occupation </li>"+
	                     "<li > Person ID </li>"+
	                     "<li ><a href='welcome.do'><i class='icon-chevron-right'></i>  <input type='button' value='Add'  /> </a></li>"+
	                     "<li ><a href='welcome.do'><i class='icon-chevron-right'></i> <input type='button' value='Save'   /> </a></li>"+
	                     "<li ><a ><i class='icon-chevron-right'></i> <input type='button' value='Next' id='next'   /> </a></li>";
	                     
			
		 	}else{
			 menu ="<ul class='nav nav-list bs-docs-sidenav nav-collapse collapse'>"+
                     "<li class='active'>"+
                         "<a href='welcome.do'><i class='icon-chevron-right'></i> Home </a>"+
                      "</li>"; 
		 for(Activities activity : activities) {
			 
	       //System.out.println("###########Actvitites are "+activ.getActivitiy());
	     	menu=menu+	 "<li> <a href='"+'#'+"'> <div class='popmeup' id = '"+activity.getId()+"' value='"+activity.getUrl()+"' onClick='loadMe("+activity.getId()+");' data-loc-subject='"+activity.getUrl()+"' > <i class='icon-chevron-right'></i>"+activity.getActivitiy()+" </div></a> </li>";
	         

		 }	 
		
		 }
		return menu;


		// menu = menu+"</ul>"; 
		//  return menu;
	 }
	 
}
	 
	 // this will create a table to show some of patients still in the Q 
	/* public String createPatientQForControllPannel(List<Patients> patients){
		 String menu = " ";
		 int noofpa = 0 ;
		 int n =0,m=0,h=0;
		 int normal[] = {0,0,0,0,0,0,0,0,0,0};
		 String normalVal[] = {"","","","","","","","","",""};
		 int medium[] = {0,0,0,0,0,0,0,0,0,0};
		 String mediumVal[] = {"","","","","","","","","",""};
		 int high[] = {0,0,0,0,0,0,0,0,0,0};
		 String highVal[] = {"","","","","","","","","",""};
		 
	 for(Patients patient: patients){
					 noofpa++;
				 if(patient.getPiority()==1){
					 if(n>9){
						 break;
						 //continue;
					 }else{
					 normal[n] = patient.getQnumber();
					 normalVal[n] = patient.getPerson().getFirstname()+"|"+patient.getPerson().getBirthdate()+"|"+patient.getPerson().getOccupation()+"|"+patient.getId();
					 n++;
				 	}
				 }else if(patient.getPiority()==2){
					 if(m>9){
						 break;
					 }else{
						 medium[m] = patient.getQnumber();
						 mediumVal[m] = patient.getPerson().getFirstname()+"|"+patient.getPerson().getBirthdate()+"|"+patient.getPerson().getOccupation()+"|"+patient.getId();
						 m++;
					 }
				 }else if(patient.getPiority() == 3){
					 if(h>9){
						 break;
					 }else {
						 high[h] = patient.getQnumber();
						 highVal[h] = patient.getPerson().getFirstname()+"|"+patient.getPerson().getBirthdate()+"|"+patient.getPerson().getOccupation()+"|"+patient.getId();
						 h++;
					 }
				 }
					
		 }
	 String a,b,c;
	 
	 menu="<Center><form><div = id='ControllPannelQ'><table padding='1' width='90%' style=''><tr><td width='30%' bgcolor='#00CC00'> Normal </td><td width='30%' bgcolor='#FFCC00' > Emergency </td> <td width='30%' bgcolor='#CC145E'> Piority </td></tr>";
	 for (int i =0; i<=9; i++){
		 if(normal[i]==0){a="<tr><td> </td>"; }else{a="<tr><td><div id="+normalVal[i]+" name='n"+i+"' onclick='javascript: getPatient(this.id);' style='text-align:center;vertical-align:middle;border-spacing: 10px;background-color:#00CC00; border-style: solid;	border-width: 5px;border-color: #FFFFFF;color:white;'> "+normal[i]+"</div></td> ";}
		 if(medium[i]==0){b="<td> </td>"; }else{b="<td class='m' value='"+mediumVal[i]+"' style='text-align:center;vertical-align:middle;border-spacing: 10px;border-spacing: 10px;background-color: #FFCC00;border-style: solid;	border-width: 5px;border-color: #FFFFFF;color:white;'> "+medium[i]+" </td> ";}
		 if(high[i]==0){c="<td> </td></tr>"; }else{c="<td class='h' value='"+highVal[i]+"' style='text-align:center;vertical-align:middle;border-spacing: 10px;background-color:#CC145E;border-style: solid;	border-width: 5px;border-color: #FFFFFF;color:white;'> "+high[i]+" </td>";}
	 menu = menu+a+b+c;
	 //"<td style='text-align:center;vertical-align:middle;border-spacing: 10px;background-color:#CC145E;color:white;'> "+c+" </td></tr>";
     	
	 }
	 menu = menu + "<tr><td colspan='3'>Patients Ramining : "+noofpa+"</td></tr></table></div></form></center>";
	 return menu;
	 }
	
	 
	 
	 public String generateOPDChit(Patients patient){
		 
		 return null;
	 }

	 
	 // this method will generate a table for doctor visited but drugs undelevered patients
	 public String getPrescriptionsForDelevery(List<Patients> patientsList){
		 ApplicationContext context = new ClassPathXmlApplicationContext("spring-database.xml");
		// DoctorDaoImpl doctorDaoImpl = context.getBean(DoctorDaoImpl.class);
		 	log4j.info("Start getting patient informations for drugs delevery ############");
		    String options="<th>Date</th><th>Last Name</th><th>ID </th><th>NIC </th> <th>Gender </th><th>Age </th>";
			MyDateUtil date = new MyDateUtil();
			for(Patients patient: patientsList){
				if(patient.getPerson()== null ){
					log4j.error("null person receved for patient received "+patient.getId());
				}else{
				String gender = "";
				log4j.info("person of the patient"+patient.getPerson().getId());
				if(patient.getPerson().getGender().equals(null)){
					gender = "Not updated";
					log4j.error("null gender for patients ");
				}else{
				if(patient.getPerson().getGender() != null && patient.getPerson().getGender()==1){
					gender = "Male";
				}else{
					gender = "female";
				}
				}
				
				// get formated date
				String time = date.getStringDateTimeFromDate(patient.getArrivaltime());
				
				// make patient age
				 String personage ="N/A";
				AgeCalculator cal = new AgeCalculator();
				if(patient.getPerson().getBirthdate()!=null){
					Age age = cal.calculateAge(patient.getPerson().getBirthdate());
				    // String personage = "Years : "+age.getYears()+" Months : "+age.getMonths();
				    personage = age.toString();
				}
			
				DoctorVisit dv = null;
				try {
					//dv = doctorDaoImpl.getDoctorVisitByPatient(patient);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					log4j.error("docvisitedpatient taking error"+e.getMessage());
				}
				if(dv==null){
					log4j.error("************ null doctor visit object for patient id "+patient.getId());
				}else{
				options = options+"<tr  onClick='showMyPrescription("+dv.getId()+");' ><td>"
						+time +
						"</td><td>"+patient.getPerson().getFamilyname()+"</td><td >"+patient.getPerson().getId() +
						"</td><td>"+patient.getPerson().getNic()+"</td>"+
						"</td><td>"+gender+"</td><td >"+personage +
						"</tr>";
			
				}
			} //else of checking patient is null
			}
		// options = options+"</table>";
		 return options; 
	 }
	
	 public String getPrescriptionsForADoctorVisitEditable(List<Prescription> prescriptions){
		
		 String options="<table><th>#<th>Medicine</th><th> Dose</th><th> Root </th><th> Freq </th><th> Dur </th><th>Drug Product </th><th>Pharmacist</th>";
			
			for(Prescription prescription: prescriptions){
				/**
				options = options+"<tr ><td> <input type='checkbox'  value='"+prescription.getId()+"' class='prescription' "+checkStatus+" "+status+"></td><td>"+
				"<HTML><font color='"+color+"'>"+prescription.getMedicine().getName()+"</font></HTML></td>"+
				"<td>"+prescription.getDose()+"</td><td>"+prescription.getRuutes().getName() +"</td><td>"+prescription.getFrequencies().getName() +"</td><td>"+
				prescription.getNuOfDays()+"</td><td>"+select+"</td>"+
				"<td> "+total+"</td></tr>";
			  **/
		
		//	}
			
	//		options = options+"</table>";
		
	//	 return options; 
	// }
 
	/********* public String getCurrentSits(List<Sits> sits){
		 String result="";
		 for(Sits sit: sits){
			 
			result= result+"[ {"+
			        "'pos': '"+sit.getConsultationRoom().getId()+"',"+
			        "'status': '"+sit.getStatus()+"',"+
			        "'dis: '"+sit.getQnumber()+"',},";
		   }
			        
			result = result + "]";     
			
		 return result;
	 }***/
	 
	 
//}
