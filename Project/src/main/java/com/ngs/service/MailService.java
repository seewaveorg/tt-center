package com.ngs.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailService {
	
	private JavaMailSender mailSender;
	
	public boolean doSendEmail(String recipient, String subject, String message) throws Exception{
    	
		
		
		try{
			
			System.out.println(" ########################################### I am in mail sender ");
	    	//bellow 3 lines are working instead of autowired
	    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-mail.xml");
			this.mailSender = (JavaMailSender) applicationContext.getBean("mailSender");

	        // prints debug info
	        System.out.println("To: " + recipient);
	        System.out.println("Subject: " + subject);
	        System.out.println("Message: " + message);
	         
	        // creates a simple e-mail object
	        SimpleMailMessage email = new SimpleMailMessage();
	        email.setTo(recipient);
	        email.setSubject(subject);
	        email.setText(message);
	         
	        // sends the e-mail
	        	mailSender.send(email);
	 
	        	return true;
			
		}catch(Exception ex){
			return false;
		}
		
		
		
		
    }

}

