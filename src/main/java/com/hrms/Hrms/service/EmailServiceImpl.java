package com.hrms.Hrms.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl  {
  
    //@Autowired
   // public JavaMailSender emailSender;
 
    public void sendSimpleMessage(String to, String subject, String text) {
    	
    	try{
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("chauhanan23@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
      //  emailSender.send(message);
    	}catch (Exception e) {
			// TODO: handle exception
    		e.printStackTrace();
		}
    }
}