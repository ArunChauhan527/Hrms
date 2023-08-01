package com.hrms.Hrms.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
 

@Service
public class AESEncryption {
	
	
	@Value("${secretKey}")
	String sectretKey;
	
	public String encryption(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKey secretKey = new SecretKeySpec(sectretKey.getBytes(), "AES");
        // CBC PAdding PKCS5
        Cipher encryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] InitVectorBytes = secretKey.getEncoded();
        IvParameterSpec parameterSpec = new IvParameterSpec(InitVectorBytes);
        encryptionCipher.init(Cipher.ENCRYPT_MODE,secretKey,parameterSpec);
        byte[] encryptedMessageBytes =
        encryptionCipher.doFinal(value.getBytes());
        String encryptedMessage =
        Base64.getEncoder().encodeToString(encryptedMessageBytes);
 
		return encryptedMessage;
	}
	
	
	
	public String decryption(String value) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
	{
		SecretKey secretKey = new SecretKeySpec(sectretKey.getBytes(), "AES");
		 Cipher decryptionCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 byte[] InitVectorBytes = secretKey.getEncoded();
		 IvParameterSpec parameterSpec = new IvParameterSpec(InitVectorBytes);
	        decryptionCipher.init(Cipher.DECRYPT_MODE,secretKey,parameterSpec);
	        byte[] decryptedMessageBytes =
	        decryptionCipher.doFinal(value.getBytes());
	        String decryptedMessage = new String(decryptedMessageBytes);
		return decryptedMessage;
	}
	
   
}