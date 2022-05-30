package com.hrms.Hrms.cognito;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;


@Service
public class S3Implementation implements S3Storage {
	
	@Value("${accesskey}")
	String accessKey;
	@Value("${secretkey}")
	 String secretKey;

	@Override
	public String readFromBucket(String bucketName, String fileName) {
		// TODO Auto-generated method stub
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();

	  try{
		
		   S3Object fullObject = s3client.getObject(new GetObjectRequest(bucketName, fileName));
		   
		   System.out.println("content type :-" + fullObject.getObjectMetadata().getContentType());
		
		fullObject.getObjectContent();
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return null;
	}

	@Override
	public String writeIntoBucket(String bucketName, String fileName, String contentType, byte[] content) {
		// TODO Auto-generated method stub
		AWSCredentials credentials = new BasicAWSCredentials(accessKey,
				secretKey);
		AmazonS3 s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.AP_SOUTH_1).build();

		try {

			// Upload a file as a new object with ContentType and title specified.
			
			InputStream is = new ByteArrayInputStream(content);
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentType(contentType);
			metadata.setContentLength(content.length);
			metadata.addUserMetadata("x-amz-meta-title", "");
			// Upload a text string as a new object.
			s3client.putObject(bucketName, fileName, is, metadata);
			System.out.println(s3client.getBucketAcl(bucketName));
		} catch (AmazonServiceException e) {
			// The call was transmitted successfully, but Amazon S3 couldn't process
			// it, so it returned an error response.
			e.printStackTrace();
		} catch (SdkClientException e) {
			// Amazon S3 couldn't be contacted for a response, or the client
			// couldn't parse the response from Amazon S3.
			e.printStackTrace();
		}
		
		
		return "sucess";
	}
	
	 @SuppressWarnings("unused")
	private static void displayTextInputStream(InputStream input) throws IOException {
	        // Read the text input stream one line at a time and display each line.
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        String line = null;
	        while ((line = reader.readLine()) != null) {
	            System.out.println(line);
	        }
	        System.out.println();
	    }

}
