package com.restchat.exception;

public interface ChatExceptionCodes {

	// 500
	public String CREATION_ERROR_MSG = "unable to create message";
	public int CREATION_ERROR_CODE = 50001;

	public String READ_ERROR_MSG = "unable to read message";
	public int READ_ERROR_CODE = 50002;

	// 401
	public String UNAUTHORIZED_MSG = "invalid session token";
	public int UNAUTHORIZED_ERROR_CODE = 40101;

	// 400
	public String USERNAME_MISSING_MSG = "username missing in headers";
	public int USERNAME_MISSING_CODE = 40001;
	public String INVALID_JSON_MSG = "invalid json input";
	public int INVALID_JSON_CODE = 40002;
	public String INVALID_MESSAGE_MSG = "invalid message input";
	public int INVALID_MESSAGE_CODE = 40003;
	public String INVALID_SENDERID_MSG = "invalid senderId ";
	public int INVALID_SENDERID_CODE = 40004;

}
