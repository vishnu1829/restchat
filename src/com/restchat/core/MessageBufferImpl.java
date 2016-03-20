package com.restchat.core;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class MessageBufferImpl implements Message{
	
	
	private Map<String, JSONArray> messageBuffer=new HashMap<String, JSONArray> ();
	private static JSONArray empty=new JSONArray();

	@Override
	public JSONArray getMessages(String userId) {
		JSONArray messages;
		if(messageBuffer.containsKey(userId)){
			messages=messageBuffer.get(userId);
			messageBuffer.remove(userId);
		}else{
			messages=empty;
		}
		return messages;
	}

	@Override
	public void postMessage(String userId,JSONObject message) {
		if(messageBuffer.containsKey(userId)){
			messageBuffer.get(userId).put(message);
		}else {
			messageBuffer.put(userId,new JSONArray().put(message));
		}
		
	}

	
	

}
