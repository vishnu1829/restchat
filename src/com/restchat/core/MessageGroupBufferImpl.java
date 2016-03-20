package com.restchat.core;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.restchat.exception.ChatException;
import com.restchat.exception.ChatExceptionCodes;

public class MessageGroupBufferImpl implements MessageGroup {

	private Map<String, JSONArray> messageBuffer = new HashMap<String, JSONArray>();

	@Override
	public JSONArray getMessages(String groupId, int index) throws ChatException {
		if (messageBuffer.containsKey(groupId)) {
			JSONArray tmp = new JSONArray();
			try {
				for (int i = index; i < messageBuffer.get(groupId).length(); i++) {
					tmp.put(messageBuffer.get(groupId).get(i));
				}
				return tmp;
			} catch (JSONException je) {
				ChatException ce = new ChatException(je.getMessage());
				throw ce;
			}

		} else {
			ChatException ce = new ChatException(ChatExceptionCodes.INVALID_GROUPID_MSG,
					ChatExceptionCodes.INVALID_GROUPID_CODE);
			throw ce;
		}
	}

	@Override
	public void postMessage(String groupId, JSONObject message) throws ChatException {
		if (messageBuffer.containsKey(groupId)) {
			messageBuffer.get(groupId).put(message);
		} else {
				messageBuffer.put(groupId, new JSONArray().put(message));
		}

	}

	@Override
	public int getSubscribeIndex(String groupId) throws ChatException {
		if (messageBuffer.containsKey(groupId)) {
			return messageBuffer.get(groupId).length();
		} else {
			ChatException ce = new ChatException(ChatExceptionCodes.INVALID_GROUPID_MSG,
					ChatExceptionCodes.INVALID_GROUPID_CODE);
			throw ce;
		}
	}

}
