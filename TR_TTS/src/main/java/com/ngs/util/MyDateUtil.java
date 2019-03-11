package com.ngs.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class MyDateUtil {
	
	public String getNow(){
		Date now = new Date();
		DateFormat Dayformater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return Dayformater.format(now);
	}
	
	public String uniqueIDFromCurrentTime(){
		Date now = new Date();
		DateFormat Dayformater = new SimpleDateFormat("yyMMddHHmmss");
		return Dayformater.format(now);
	}
	
	public Date getDateNow(){
		Date now = new Date();
		return now;
	}
	
	//yyyy-MM-dd HH:mm:ss
	public Date getDateFromString(String sdate){
		Date date = null;
		try{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = format.parse(sdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return date;
		
	}
	
	public Date getDateDayFromStringDay(String stdate, String format) {
		System.out.println("##########################Date is "+stdate+"    format  "+format);
		Date day = null;
		try{

	    SimpleDateFormat formatter = new SimpleDateFormat(format);
	    day = formatter.parse(stdate);
	 
		}catch(Exception e){
			e.printStackTrace();
		}
		return day;
	}
	

	
	
	public String getStringDateTimeFromDate(Date date){
		 //convert a time into simple date format
		
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 //format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		 String formatted = format.format(date);
		 
		 return formatted;
	}
	
	public String getStringDayFromDate(Date date){
		 //convert a time into simple date format
		
		 DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		 //format.setTimeZone(TimeZone.getTimeZone("Etc/UTC"));
		 String formatted = format.format(date);
		 
		 return formatted;
	}

	
	public boolean isFirstDayofMonth(Calendar calender){
	    if(calender == null)
	        return false;

	    int dayOfMonth = calender.get(Calendar.DAY_OF_MONTH);
	    return (dayOfMonth == 1);
	}
	
	
}
