package com.restchat.exception;

public class ChatException extends Exception {

	int exceptionCode;

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

}
