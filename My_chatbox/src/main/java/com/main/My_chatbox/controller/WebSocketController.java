package com.main.My_chatbox.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.main.My_chatbox.entity.Message;
import com.main.My_chatbox.service.WebSocketSessionManager;

@Controller
public class WebSocketController {

	private final SimpMessagingTemplate simpMessagingTemplate;
	private final WebSocketSessionManager webSocketSessionManager;

	public WebSocketController(SimpMessagingTemplate simpMessagingTemplate,
			WebSocketSessionManager webSocketSessionManager) {
		this.simpMessagingTemplate = simpMessagingTemplate;
		this.webSocketSessionManager = webSocketSessionManager;
	}

	@MessageMapping("/message")
	public void handleMessage(Message message) {

		System.out.println("Recieved message from user: " + message.getUser() + " : " + message.getMessage());
		simpMessagingTemplate.convertAndSend("/topic/message", message);
		System.out.println("Sent message to /topic/messages: " + message.getUser() + " : " + message.getMessage());

	}

	@MessageMapping("/connect")
	public void connectUser(String username) {
		webSocketSessionManager.addUserName(username);
		webSocketSessionManager.broadcastActiveUserNames();
		System.out.println(username + " connected ");
	}

	@MessageMapping("/disconnect")
	public void disconnectUser(String username) {
		webSocketSessionManager.removeUsername(username);
		webSocketSessionManager.broadcastActiveUserNames();
		System.out.println(username + " disconnected ");
	}

}
