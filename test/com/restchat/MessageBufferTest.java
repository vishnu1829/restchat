package com.restchat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import com.restchat.core.Message;
import com.restchat.core.MessageBufferImpl;
import com.restchat.exception.ChatException;
import com.restchat.util.MessageUtil;

public class MessageBufferTest {

	Message message=new MessageBufferImpl();
	public static String userId1= "user1";
	public static String userId2="user2";
	
	
	@Test
	public void testEmptyGetMessages(){
		JSONArray messages=message.getMessages(userId1);
		Assert.assertEquals(0,messages.length());
	}
	
	@Test
	public void testgetMessage() throws ChatException, JSONException{
		String txtmessage="hai how are you";
		//userid1 sends to userid2
		JSONObject messageDetails=MessageUtil.prepareMessage(txtmessage,userId1 );
		message.postMessage(userId2, messageDetails);
		
		String txtmessage1="I am fine";
		messageDetails=MessageUtil.prepareMessage(txtmessage1,userId2 );
		message.postMessage(userId1, messageDetails);
		
		String txtmessage2="where are you";
		messageDetails=MessageUtil.prepareMessage(txtmessage2,userId1 );
		message.postMessage(userId2, messageDetails);
		
		JSONArray messages=message.getMessages(userId2);
		Assert.assertEquals(2, messages.length());
		Assert.assertEquals(txtmessage, MessageUtil.getMessage(messages.getJSONObject(0)));
		Assert.assertEquals(txtmessage2, MessageUtil.getMessage(messages.getJSONObject(1)));
		
		messages=message.getMessages(userId2);
		Assert.assertEquals(0, messages.length());
		
		 messages=message.getMessages(userId1);
		Assert.assertEquals(1, messages.length());
		Assert.assertEquals(txtmessage1, MessageUtil.getMessage(messages.getJSONObject(0)));
		

	}
	
	
	

}
