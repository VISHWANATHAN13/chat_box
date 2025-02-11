package com.main.My_chatbox.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import com.main.My_chatbox.entity.Message;

public class MyStompClient {

	private StompSession session;
	private String username;

	public MyStompClient(String username) throws InterruptedException, ExecutionException {
		this.username = username;

		List<Transport> transports = new ArrayList<>();
		transports.add(new WebSocketTransport(new StandardWebSocketClient()));

		SockJsClient sockJsClient = new SockJsClient(transports);
		WebSocketStompClient stompClient = new WebSocketStompClient(sockJsClient);
		stompClient.setMessageConverter(new MappingJackson2MessageConverter());
		StompSessionHandler sessionHandler = new MyStompSessionHandler(username);
		String url = "ws://localhost:8080/ws";

		session = stompClient.connectAsync(url, sessionHandler).get();
	}

	public void sendMessage(Message messge) {
		try {
			session.send("/app/message", messge);
			System.out.println("Message Sent : " + messge.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void disconnectUser(String string) {
		session.send("/app/disconnect", username);
		System.out.println("Disconnect User : " + username);
	}

}
