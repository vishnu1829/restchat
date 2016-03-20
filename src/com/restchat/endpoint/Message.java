package com.restchat.endpoint;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.restchat.constants.Constants;
import com.restchat.core.MessageBufferImpl;
import com.restchat.exception.ChatException;
import com.restchat.factory.MessageFactory;
import com.restchat.user.UserSession;
import com.restchat.util.MessageUtil;

@Path("/")
public class Message {

	com.restchat.core.Message messageBuffer = MessageFactory.getMessageImpl(null);

	@Path("/message")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getMessage(@HeaderParam("sessionToken") String sessionToken) {
		Response response;
		try {
			String userid = UserSession.getUserId(sessionToken);
			JSONArray messages = messageBuffer.getMessages(userid);
			response = Response.status(200).entity(messages.toString()).build();

		} catch (ChatException ce) {
			response = Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
		} catch (Exception ex) {
			ChatException ce = new ChatException(ex.getMessage());
			response = Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
		}

		return response;
	}

	@Path("/message")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@POST
	public Response postMessage(@HeaderParam("sessionToken") String sessionToken,@HeaderParam("to")String receiverId, String message) {
		Response response;
		try {
			String userid = UserSession.getUserId(sessionToken);
			JSONObject messageDetails=MessageUtil.parseAndGetMessageDetails(message);
			MessageUtil.validateAndPrepareMessage(userid, messageDetails);
			messageBuffer.postMessage(receiverId, messageDetails);
			response = Response.status(200).build();
		} catch (ChatException ce) {
			response = Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
		} catch (Exception ex) {
			ChatException ce = new ChatException(ex.getMessage());
			response = Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
		}

		return response;
		
	}

}
