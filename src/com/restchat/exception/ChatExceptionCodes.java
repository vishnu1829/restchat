package com.restchat.exception;

public interface ChatExceptionCodes {
	
	//500
	public String CREATION_ERROR_MSG="unable to create message";
	public int CREATION_ERROR_CODE=50001;
	
	public String READ_ERROR_MSG="unable to read message";
	public int READ_ERROR_CODE=50002;
	
	//401
	public String UNAUTHORIZED_MSG="invalid session token";
	public int UNAUTHORIZED_ERROR_CODE=40101;
	

}
