package com.restchat.factory;

import com.restchat.core.MessageGroup;
import com.restchat.core.MessageGroupBufferImpl;

public class MessageGroupFactory {
public static MessageGroupBufferImpl messageBufferImpl=new MessageGroupBufferImpl();
	
	public static MessageGroup getMessageImpl(String implType){
		
		return messageBufferImpl;
		
	}

}
