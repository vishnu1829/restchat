package com.restchat.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.restchat.constants.Constants;
import com.restchat.exception.ChatException;
import com.restchat.exception.ChatExceptionCodes;

public class MessageUtil {

	public static JSONObject prepareMessage( String message,String senderId) throws ChatException {
		try {
			JSONObject messageDetails = new JSONObject();
			messageDetails.put(Constants.senderId, senderId);
			messageDetails.put(Constants.message, message);

			return messageDetails;
		} catch (JSONException je) {
			throw new ChatException(ChatExceptionCodes.CREATION_ERROR_MSG,
					ChatExceptionCodes.CREATION_ERROR_CODE);
		}

	}
	public static String getMessage(JSONObject messageDetails) throws ChatException{
		try{
		String message=messageDetails.getString(Constants.message);
		return message;
		} catch (JSONException je) {
			throw new ChatException(ChatExceptionCodes.READ_ERROR_MSG,
					ChatExceptionCodes.READ_ERROR_CODE);
		}
	}
}
