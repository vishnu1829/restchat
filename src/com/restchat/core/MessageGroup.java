package com.restchat.core;

import org.json.JSONArray;
import org.json.JSONObject;

import com.restchat.exception.ChatException;

public interface MessageGroup {
	
	public JSONArray getMessages(String groupId,int index) throws ChatException;
	
	public void postMessage(String groupId,JSONObject message) throws ChatException;
	
	public int getSubscribeIndex(String groupId) throws ChatException;

}
