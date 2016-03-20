package com.restchat;

import org.junit.Assert;
import org.junit.Test;

import com.restchat.exception.ChatException;
import com.restchat.user.UserSession;

public class UserSessionTest {
	public static String userid1 = "userid1";
	public static String userid2 = "userid2";
	
	@Test
	public void testLogin() throws ChatException {
		String sessionToken = UserSession.login(userid1);
		String userid = UserSession.getUserId(sessionToken);
		Assert.assertEquals(userid1, userid);
	}
	
	@Test
	public void testInvalidLogin() throws ChatException {
		try{
		UserSession.getUserId("sometoken");
		Assert.fail();
		}catch(ChatException ce){
			Assert.assertEquals(40101,ce.getExceptionCode());
		}
	}
	
	

}
