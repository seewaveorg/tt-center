package com.ngs.util;

import java.security.Key;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ngs.service.DbService;


public class SupportUtil {
	
	@Autowired
	private DbService dBConnector;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	FileValidator fileValidator;
	
	Key key;
	IvParameterSpec iv;
	
	//XXXXXXXXXXXXXXXXXXXXXXXXX UTILS XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	
	protected String formatErrorMessage(String title, String message){
		return "<div class=\"alert adjusted alert-error\">"+
					"<button class=\"close\" data-dismiss=\"alert\"></button>"+
					"<i class=\"cus-cross-octagon\"></i>"+
					"<strong>"+title+": </strong>"+message+
				"</div>";
	}
	
	protected String formatSuccessMessage(String title, String message){
		return "<div class=\"alert adjusted alert-success\">"+
				"<button class=\"close\" data-dismiss=\"alert\"></button>"+
					"<i class=\"cus-accept\"></i>"+
					"<strong>"+title+": </strong>"+message+
				"</div>";
	}
	
	protected String formatWarningMessage(String title, String message){
		return "<div class=\"alert adjusted alert-warning\">"+
				"<button class=\"close\" data-dismiss=\"alert\"></button>"+
					"<i class=\"cus-exclamation-octagon-fram\"></i>"+
					"<strong>"+title+": </strong>"+message+
				"</div>";
	}
	

	
	protected String formatNumber(String number){
			number = number.replaceAll("\\s","");
			number = number.replaceAll("[()]","");
			number = number.replaceAll("-","");
			number = number.replaceFirst("^0+(?!$)", "");
			number = number.replaceFirst("^\\++(?!$)", "");
			number = number.length()==9?"94"+number:number;
			//number = number.replaceAll("[)]","");
			
			return number;
		}
			
	protected String encrypt(int id){
		byte[] input = Integer.toString(id).getBytes();
		byte[] raw = null;
		try {
			if(key==null){
				KeyGenerator kg = KeyGenerator.getInstance("AES");
				SecureRandom random = new SecureRandom();
				kg.init(random);
				key = kg.generateKey();
			    iv = new IvParameterSpec(random.generateSeed(16));
			}
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE,key,iv);
		    raw = cipher.doFinal(input);
   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		String base64Str=Base64.encodeBase64String(raw);
		base64Str = base64Str.replaceAll("(?:\\r\\n|\\n\\r|\\n|\\r)", "");
				
		return base64Str;
	}
	
	protected String encrypt(long id){
		byte[] input = Long.toString(id).getBytes();
		byte[] raw = null;
		try {
			if(key==null){
				KeyGenerator kg = KeyGenerator.getInstance("AES");
				SecureRandom random = new SecureRandom();
				kg.init(random);
				key = kg.generateKey();
			    iv = new IvParameterSpec(random.generateSeed(16));
			}
			
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.ENCRYPT_MODE,key,iv);
		    raw = cipher.doFinal(input);
   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String base64Str=Base64.encodeBase64String(raw);
		base64Str = base64Str.replaceAll("(?:\\r\\n|\\n\\r|\\n|\\r)", "");
				
		return base64Str;
	    //System.out.println(new String(cipherText));

		//return new String(cipherText);
	}
	
	protected int decrypt(String text){
		//byte[] input = text.getBytes();
		String clearText="0";
		try{
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		    cipher.init(Cipher.DECRYPT_MODE, key,iv);

		    byte[] raw = Base64.decodeBase64(text);
		    byte[] stringBytes = cipher.doFinal(raw);

		    clearText = new String(stringBytes, "UTF8");
		    //return clearText;
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			//System.out.println("##############################################"+clearText);
			return Integer.parseInt(clearText);
		}catch(Exception e){
			return 0;
		}
		
	}
	
	 protected boolean hasRole(String role) {
	        // get security context from thread local
	        SecurityContext context = SecurityContextHolder.getContext();
	        if (context == null)
	            return false;

	        Authentication authentication = context.getAuthentication();
	        if (authentication == null)
	            return false;

	        for (GrantedAuthority auth : authentication.getAuthorities()) {
	            if (role.equals(auth.getAuthority()))
	                return true;
	        }

	        return false;
	    }

}
