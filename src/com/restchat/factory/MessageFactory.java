package com.restchat.factory;

import com.restchat.core.Message;
import com.restchat.core.MessageBufferImpl;

public class MessageFactory {
	public static MessageBufferImpl messageBufferImpl=new MessageBufferImpl();
	
	public static Message getMessageImpl(String implType){
		
		return messageBufferImpl;
		
	}

}
