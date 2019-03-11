package com.ngs.util;

import java.io.File;

public class FileSupport {
	
	// Author : Tharang Rajapaksha
	public boolean createFolder(String theFilePath) throws Exception{
	
	    boolean result = false;

	    File directory = new File(theFilePath);

	    if (directory.exists()) {
	        System.out.println("Folder already exists");
	    } else {
	        result = directory.mkdirs();
	    }

	    return result;
	}
	
	
	// check whether a file is exists
	public boolean existsFolder(String theFilePath) throws Exception{
	
	    boolean result = false;

	    File directory = new File(theFilePath);

	    if (directory.exists()) {
	        result = true;
	    } 

	    return result;
	}
	
	
	

}
