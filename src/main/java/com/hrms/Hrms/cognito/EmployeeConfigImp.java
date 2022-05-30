package com.hrms.Hrms.cognito;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.model.NotAuthorizedException;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.ConfirmSignUpRequest;
import com.amazonaws.services.cognitoidp.model.InvalidPasswordException;
import com.amazonaws.services.cognitoidp.model.PasswordResetRequiredException;
import com.amazonaws.services.cognitoidp.model.SignUpRequest;
import com.amazonaws.services.cognitoidp.model.UserNotConfirmedException;
import com.amazonaws.services.cognitoidp.model.UserNotFoundException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("EmployeeConfig")
public class EmployeeConfigImp implements EmployeeConfig {

	@Value("${userPoolId}")
	String userPoolId;
	@Value("${clientId}")
	String clientId;
	@Value("${clientSecret}")
	String clientSecret;
	@Value("${accesskey}")
	String accessKey;
	@Value("${secretkey}")
	 String secretKey;

	
	@Override
	public String Signup(String request) {
		AWSCredentials basicawsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		
		@SuppressWarnings("deprecation")
		AWSCognitoIdentityProviderClient client = new  AWSCognitoIdentityProviderClient(basicawsCreds).withRegion(Regions.AP_SOUTH_1);
			
		try{
		Map<String,String> requestMap = new ObjectMapper().readValue(request, new TypeReference<Map<String,String>>() {});
			
		List<AttributeType> list = new ArrayList<>();
		AttributeType validatedData = new AttributeType();
		 for (Map.Entry<String,String> entry : requestMap.entrySet())  
		 {
			 AttributeType keys = new AttributeType();
			 if(entry.getKey().equals("email"))
			 {
				 validatedData.setName("email");
				 validatedData.setValue(entry.getValue());
				 list.add(validatedData);
				 }
			 else if(entry.getKey().equals("username")||entry.getKey().equals("password"))
		 {
				 
		 }
			 else
			 {
				 keys.setName(entry.getKey());
				 keys.setValue(entry.getValue());
				 list.add(keys);
			 }
			 
		 } 
		 
		 SignUpRequest signUpRequest = new SignUpRequest().withClientId(clientId).withUsername(requestMap.get("username")).withPassword(requestMap.get("password")).withValidationData(validatedData).withUserAttributes(list).withSecretHash(calculateSecretHash(clientId,clientSecret,requestMap.get("username")));
		 
		client.signUp(signUpRequest); 
		
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			client.shutdown();
		}
		
		
		
		return "success";
	}

	
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> login(String request) throws JsonParseException, JsonMappingException, IOException {
		// TODO Auto-generated method stub
		AWSCredentials basicawsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		@SuppressWarnings("deprecation")
		AWSCognitoIdentityProviderClient client = new  AWSCognitoIdentityProviderClient(basicawsCreds).withRegion(Regions.AP_SOUTH_1);
		Map<String,Object> param = new ObjectMapper().readValue(request,HashMap.class);
		try{
			
			AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest()
					.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(clientId.trim())// iusesa1sgdb20rv7jloju7uok
					.withUserPoolId(userPoolId).addAuthParametersEntry("USERNAME", param.get("username").toString())
					.addAuthParametersEntry("PASSWORD", param.get("password").toString())
					.addAuthParametersEntry("SECRET_HASH",
							calculateSecretHash(clientId.trim(), clientSecret.trim(), param.get("username").toString()));

		
			param.clear();
			AdminInitiateAuthResult authResponse = client.adminInitiateAuth(adminInitiateAuthRequest);
	
                   param.put("accessToken", authResponse.getAuthenticationResult().getAccessToken());
                   param.put("refreshToken", authResponse.getAuthenticationResult().getRefreshToken());
		} catch (UserNotFoundException e) {
			return (Map<String, Object>) param.put("error", "User not Found");
		} catch (UserNotConfirmedException e1) {
			return (Map<String, Object>) param.put("error", "User not Confirmed");
		} catch (InvalidPasswordException e1) {
			return (Map<String, Object>) param.put("error", "Password is wrong");
		} catch (NotAuthorizedException e1) {
			return (Map<String, Object>) param.put("error", "User is not authorized");
		} catch (PasswordResetRequiredException e1) {
			return (Map<String, Object>) param.put("error", "Reset you password");
		} catch (Exception e1) {
			return (Map<String, Object>) param.put("error", "Something went wrong " + e1.getMessage());
		}
        finally {
			client.shutdown();
		}
		
		return param;
	}
	
	public String calculateSecretHash(String userPoolclientId, String userPoolclientSecret, String userName) {
        if (userPoolclientSecret == null) {
            return null;
        }

        SecretKeySpec signingKey = new SecretKeySpec(
                userPoolclientSecret.getBytes(StandardCharsets.UTF_8),
               "HmacSHA256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);
            mac.update(userName.getBytes(StandardCharsets.UTF_8));
            byte[] rawHmac = mac.doFinal(userPoolclientId.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(rawHmac);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating ");
        }
    }




	@Override
	public String confirmEmail(String otp,String username) {
	
		AWSCredentials basicawsCreds = new BasicAWSCredentials(accessKey,
				secretKey);
		
		@SuppressWarnings("deprecation")
		AWSCognitoIdentityProviderClient client = new  AWSCognitoIdentityProviderClient(basicawsCreds).withRegion(Regions.AP_SOUTH_1);
		try{
		ConfirmSignUpRequest signupRequest = new ConfirmSignUpRequest().withClientId(clientId).withConfirmationCode(otp).withUsername(username).withSecretHash(calculateSecretHash(clientId, clientSecret, username));
		client.confirmSignUp(signupRequest);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return "unsuccess";
		}
		finally {
			client.shutdown();
		}
		return "success";
	}

}
