package com.ngs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import sun.misc.BASE64Decoder;


	
//@Controller
@SessionAttributes("user")
public class ImageController {
	
	private static final long serialVersionUID = 1L;

	
	
	 
	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
	 public void  SaveImage(@ModelAttribute("personid") String personid,HttpServletRequest request, HttpServletResponse response)
	 {
		
	 try
	 {
		 
		
	 StringBuffer buffer = new StringBuffer();
	 Reader reader = request.getReader();
	 int current;
	 
	 while((current = reader.read()) >= 0)
	 buffer.append((char) current);
	 
	 String data = new String(buffer);
	 data = data.substring(data.indexOf(",") + 1);
	 
	 //System.out.println("PNG image data on Base64: " + data);

	 //System.out.println("##################### person id is "+personid);
	 
	 // FileOutputStream output = new FileOutputStream(new File("/C:/wamp/www/personimages/" + new Random().nextInt(100000) + ".png"));
	 // windows path
	 FileOutputStream output = new FileOutputStream(new File("/C:/wamp/www/personimages/" + personid + ".png"));
	 // linux path
	 // FileOutputStream output = new FileOutputStream(new File("/opt/nextgenmed/personimages/" + personid + ".png"));
	 
	 output.write(new BASE64Decoder().decodeBuffer(data));
	 output.flush();
	 output.close();
	 }
	 catch (Exception e)
	 {
	 e.printStackTrace();
	 }
	 
	
    	
    	
	 }



}
