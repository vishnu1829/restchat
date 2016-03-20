package com.restchat.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.restchat.constants.Constants;
import com.restchat.exception.ChatException;
import com.restchat.exception.ChatExceptionCodes;

public class MessageUtil {

	public static JSONObject prepareMessage(String message, String senderId) throws ChatException {
		try {
			JSONObject messageDetails = new JSONObject();
			messageDetails.put(Constants.senderId, senderId);
			messageDetails.put(Constants.message, message);
			return messageDetails;
		} catch (JSONException je) {
			throw new ChatException(ChatExceptionCodes.CREATION_ERROR_MSG, ChatExceptionCodes.CREATION_ERROR_CODE);
		}

	}

	public static String getMessage(JSONObject messageDetails) throws ChatException {
		try {
			String message = messageDetails.getString(Constants.message);
			return message;
		} catch (JSONException je) {
			throw new ChatException(ChatExceptionCodes.READ_ERROR_MSG, ChatExceptionCodes.READ_ERROR_CODE);
		}
	}

	public static JSONObject parseAndGetMessageDetails(String messagePayload) throws ChatException {
		try {
			JSONObject messageJson = new JSONObject(messagePayload);
			if (messageJson.has(Constants.message)) {
				return messageJson;
			} else {
				ChatException ce = new ChatException(ChatExceptionCodes.INVALID_MESSAGE_MSG,
						ChatExceptionCodes.INVALID_MESSAGE_CODE);
				throw ce;
			}
		} catch (JSONException je) {
			ChatException ce = new ChatException(ChatExceptionCodes.INVALID_JSON_MSG,
					ChatExceptionCodes.INVALID_JSON_CODE);
			throw ce;
		}
	}

	public static void validateAndPrepareMessage(String senderId, JSONObject messageDetails) throws ChatException {
		try {
			if (senderId != null) {
				messageDetails.put(Constants.senderId, senderId);
			} else {
				ChatException ce = new ChatException(ChatExceptionCodes.INVALID_SENDERID_MSG,
						ChatExceptionCodes.INVALID_SENDERID_CODE);
				throw ce;
			}
		} catch (JSONException e) {
			ChatException ce = new ChatException(e.getMessage());
			throw ce;
		}
	}

}
