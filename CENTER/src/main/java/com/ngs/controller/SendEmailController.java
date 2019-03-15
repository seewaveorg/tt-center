package com.ngs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ngs.model.Tt;

 
@Controller
@SessionAttributes("user")
@RequestMapping(value="/Email")
public class SendEmailController {
	
	private JavaMailSender mailSender;
    
	
    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody String doSendEmail(HttpServletRequest request, @ModelAttribute("value") Tt value) {
    	System.out.println("#############value"+value);
   // public String doSendEmail(@ModelAttribute("id")int id,  HttpServletRequest request) {
    	try{
    	System.out.println(" ########################################### I am in mail sender ");
    	//bellow 3 lines are working instead of autowired
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/spring-mail.xml");
		this.mailSender = (JavaMailSender) applicationContext.getBean("mailSender");
        // takes input from e-mail form
       // String recipientAddress = request.getParameter("recipient");
       // String subject = request.getParameter("subject");
       // String message = request.getParameter("message");
		//System.out.println("##################id"+id);
		String recipientAddress = value.getUser().getEmail();//"maduhansichani@gmail.com";
	    String subject = "Trouble ticket";
	    String message = "You have trouble ticket";
		
        // prints debug info
        System.out.println("To: " + recipientAddress);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
         
        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
         
        // sends the e-mail
        	//mailSender.send(email);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
        	// forwards to the view named "Result"
        	//return "redirect:/welcome.do";
        	//ModelAndView view = new ModelAndView();
        	//view.setViewName("reception/index");
        
        	return "sent";
    }
}
