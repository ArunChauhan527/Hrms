package com.hrms.Hrms.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.hrms.Hrms.Enum.EmailType;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EmailServiceImpl implements EmailService  {
  
    @Autowired
    public JavaMailSender emailSender;
    
    @Autowired
    private AESEncryption aesEncryption;
    
    @Value("${frontEnd.url}")
    String url;
    
    
    
    public void sendSimpleMessage(String to, String subject, String text) {
    	
    	try{
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("chauhanan23@gmail.com");
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);
    	}catch (Exception e) {
    		log.error("error while sending mail {}",e);
		}
    }

	@Override
	public String getMessageFormat(String password, EmailType type) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
	
		String message = "";
		switch(type)
		{
		case NewCandiate:
			message = "Hi {candidate}, \n \n \n Please find you temporary. \n\n PASSWORD :   "+ password+"  \n\n Thanks&Regards \n HRMS ADMIN";
			break;
		case OtpPassword:
			message = "Hi, \n \n\n Please find the otp to reset password. \r \n Otp: {Otp}. \n \nNote this otp will be active only for 15 mins. \n\n click on below url: \r"+ url+"changePassword?forget="+aesEncryption.encryption(password) +"  \n Thanks&Regards \n HRMS ADMIN";
			break;
		}
		return message;
	}
    
    
}