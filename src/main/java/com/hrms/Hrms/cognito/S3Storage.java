package com.hrms.Hrms.cognito;

public interface S3Storage {
	
	public String readFromBucket(String bucketName,String fileName);
	
	public String writeIntoBucket(String bucketName,String fileName,String contentType,byte[] content);
	
	

}
