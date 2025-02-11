package com.main.My_chatbox.client;

import java.util.concurrent.ExecutionException;

import com.main.My_chatbox.entity.Message;

public class ClientGUI {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		MyStompClient myStompClient = new MyStompClient("vishwa");
		myStompClient.sendMessage(new Message("vishwa", "Hello world"));
		myStompClient.disconnectUser("vishwa");
	}

}
