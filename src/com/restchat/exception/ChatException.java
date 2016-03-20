package com.restchat.exception;

public class ChatException extends Exception {

	int exceptionCode;
	String errorFormat="{\"errorCode\":\"%s\",\"errorMessage\":%d}";

	public int getExceptionCode() {
		return exceptionCode;
	}

	public void setExceptionCode(int exceptionCode) {
		this.exceptionCode = exceptionCode;
	}

	public ChatException(String mesage, int errorCode) {
		super(mesage);
		this.exceptionCode = errorCode;
	}
	
	public ChatException(String message){
		super(message);
	}
	
	public int getStatus(){
		int status;
		if(exceptionCode!=0){
			status=exceptionCode/100;
		}else{
			status=500;
		}
		return status;
	}
	
	public String getErrorResponse(){
		return String.format(errorFormat, getMessage(),exceptionCode);
	}

}
