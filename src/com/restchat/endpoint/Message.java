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

import com.restchat.core.MessageGroup;
import com.restchat.exception.ChatException;
import com.restchat.exception.ChatExceptionCodes;
import com.restchat.factory.MessageFactory;
import com.restchat.factory.MessageGroupFactory;
import com.restchat.user.UserSession;
import com.restchat.util.MessageUtil;

@Path("/")
public class Message {

	com.restchat.core.Message messageBuffer = MessageFactory.getMessageImpl(null);
	MessageGroup messageGroupBuffer = MessageGroupFactory.getMessageImpl(null);

	@Path("/message")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response getMessage(@HeaderParam("sessionToken") String sessionToken,
			@HeaderParam("groupIndex") Integer groupIndex,@HeaderParam("group") String groupName) {
		Response response;
		JSONArray messages;
		try {
			String userid = UserSession.getUserId(sessionToken);
			if (groupIndex == null||groupName==null) {
				messages = messageBuffer.getMessages(userid);
			} else {
				messages = messageGroupBuffer.getMessages(groupName, groupIndex);
			}
			response = Response.status(200).entity(messages.toString()).build();

		} catch (ChatException ce) {
			response = Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
		} catch (Exception ex) {
			ChatException ce = new ChatException(ex.getMessage());
			response = Response.status(ce.getStatus()).entity(ce.getErrorResponse()).build();
		}

		return response;
	}
	
	private static final String groupIndexStatus="{\"index\":%d}";
	
	@Path("/subscribe")
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public Response subscribe(@HeaderParam("sessionToken") String sessionToken,
			@HeaderParam("group") String groupName) {
		Response response;
		int index;
		try {
			String userid = UserSession.getUserId(sessionToken);
			if (groupName!=null) {
				index = messageGroupBuffer.getSubscribeIndex(groupName);
				response = Response.status(200).entity(String.format(groupIndexStatus,index)).build();
			}else{
				ChatException ce = new ChatException(ChatExceptionCodes.INVALID_GROUPID_MSG,
						ChatExceptionCodes.INVALID_GROUPID_CODE);
				throw ce;
			}

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
	public Response postMessage(@HeaderParam("sessionToken") String sessionToken, @HeaderParam("to") String receiverId,
			@HeaderParam("isGroup") Boolean isGroup, String message) {
		Response response;
		try {
			String userid = UserSession.getUserId(sessionToken);
			JSONObject messageDetails = MessageUtil.parseAndGetMessageDetails(message);
			MessageUtil.validateAndPrepareMessage(userid, messageDetails);
			if (!isGroup) {
				messageBuffer.postMessage(receiverId, messageDetails);
			} else {
				messageGroupBuffer.postMessage(receiverId, messageDetails);
			}
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
