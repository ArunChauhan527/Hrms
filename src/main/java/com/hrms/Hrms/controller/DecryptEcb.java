package com.hrms.Hrms.controller;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import com.amazonaws.util.Base64;

public class DecryptEcb {

	
	
	 public SecretKeySpec myDesKey;
	 Cipher c;
	 public DecryptEcb() throws Exception
	 { 


		 StringBuffer sb = new StringBuffer(16);
	     sb.append("secretkey=vikash");
	     while (sb.length() < 16) {
	            sb.append("0");
	        }
	        if (sb.length() > 16) {
	            sb.setLength(16);
	        }
	       byte[] data = sb.toString().getBytes("UTF-8");
	  // Genrate the Key
	  myDesKey = new SecretKeySpec(data, "AES");
	  // Create the cipher
	  c = Cipher.getInstance("AES/ECB/PKCS5Padding");
	  

	 }
	 public byte[] doEncryption(String s) throws Exception
	 {
	   
	      // Initialize the cipher for encryption
	      c.init(Cipher.ENCRYPT_MODE, myDesKey);

	      //sensitive information
	      byte[] text = s.getBytes();
	 
	   // Encrypt the text
	      byte[] textEncrypted = c.doFinal(text);
	 
	   return(textEncrypted);

	 }
	 public String doDecryption(byte[] s)throws Exception
	 {
	 
	      // Initialize the same cipher for decryption
	      c.init(Cipher.DECRYPT_MODE, myDesKey);

	  
	      // Decrypt the text
	      byte[] textDecrypted = c.doFinal(s);
	   
	 
	   return(new String(textDecrypted));
	 }
	 
	 public static void main(String[] argv) throws Exception
	 {
	  
		 DecryptEcb d=new DecryptEcb();
	     byte[] str=d.doEncryption("Hello DB!");
	    System.out.println("Encrypted String : "+Base64.encodeAsString(str));
	   System.out.println("Decrypted String : "+d.doDecryption(str));
	    
	 }
}
