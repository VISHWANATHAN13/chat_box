package com.main.My_chatbox.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketSessionManager {

	private final ArrayList<String> activeUserNames = new ArrayList<>();
	private final SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	public WebSocketSessionManager(SimpMessagingTemplate simpMessagingTemplate) {
		this.simpMessagingTemplate = simpMessagingTemplate;
	}

	public void addUserName(String username) {
		activeUserNames.add(username);
	}


	public void removeUserName(String username) {
		activeUserNames.remove(username);
	}

	public void broadCastUserName() {
		simpMessagingTemplate.convertAndSend("/topic/users", activeUserNames);
		System.out.println("Broadcasting active users to /topic/users " + activeUserNames);
	}

	public void broadcastActiveUserNames() {
		simpMessagingTemplate.convertAndSend("/topic/users",activeUserNames);
	}

	public void removeUsername(String username) {
		activeUserNames.remove(username);
	}
	

}
