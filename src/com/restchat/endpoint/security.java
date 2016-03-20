package com.restchat.endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.restchat.exception.ChatException;
import com.restchat.user.UserSession;

@Path("/security")
public class security {
	
	final String loginFormatSucess="{\"sessionToken\":\"%s\"}";

	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response login(@HeaderParam("username") String userid){
		Response response;
		try{
		String sessionToken=UserSession.login(userid);
		response=Response.status(200).entity(String.format(loginFormatSucess, sessionToken)).build();
	}catch(ChatException ce){
		response= Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
	}catch(Exception ex){
		ChatException ce=new ChatException(ex.getMessage());
		response= Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
	}
		return response;
	}
		
}
