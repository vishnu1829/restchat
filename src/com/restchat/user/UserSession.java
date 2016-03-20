package com.restchat.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.restchat.exception.ChatException;
import com.restchat.exception.ChatExceptionCodes;

public class UserSession {

	private UserSession() {

	}

	private static class SingletonHelper {
		private static final Map<String, String> userSession = new HashMap<String, String>();
	}

	public static String login(String userid) throws ChatException {
		if(userid!=null){
		// perform login logic
		String sessionToken = UUID.randomUUID().toString();
		SingletonHelper.userSession.put(sessionToken, userid);
		return sessionToken;
		}else{
			throw new ChatException(ChatExceptionCodes.USERNAME_MISSING_MSG, ChatExceptionCodes.USERNAME_MISSING_CODE);
		}
	}

	public static String getUserId(String sessionToken) throws ChatException {
		if(SingletonHelper.userSession.containsKey(sessionToken)){
			String userid=SingletonHelper.userSession.get(sessionToken);
			return userid;
		}else{
			throw new ChatException(ChatExceptionCodes.UNAUTHORIZED_MSG, ChatExceptionCodes.UNAUTHORIZED_ERROR_CODE);
		}

	}

}
