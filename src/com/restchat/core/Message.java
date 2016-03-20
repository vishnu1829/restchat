package com.restchat.core;

import org.json.JSONArray;
import org.json.JSONObject;

public interface Message {

	public JSONArray getMessages(String userId);
	
	public void postMessage(String userId,JSONObject message);
	
}
