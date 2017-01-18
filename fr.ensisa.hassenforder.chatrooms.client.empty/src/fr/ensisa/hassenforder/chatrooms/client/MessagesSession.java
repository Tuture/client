package fr.ensisa.hassenforder.chatrooms.client;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import fr.ensisa.hassenforder.network.Protocol;
import fr.ensisa.hassenforder.chatrooms.client.model.*;

public class MessagesSession extends Thread {

	private Socket connection;
	private String name;
	private NetworkListener listener;

	public MessagesSession(String name, NetworkListener listener) {
		this.name = name;
		if (listener == null)
			throw new RuntimeException("listener cannot be null");
		this.listener = listener;
	}

	public boolean close() {
		this.interrupt();
		try {
			if (connection != null)
				connection.close();
			connection = null;
		} catch (IOException e) {
		}
		return true;
	}

	public boolean open() {
		this.close();
		try {
			connection = new Socket("localhost", Protocol.CHATROOMS_MSG_PORT_ID);
			start();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	public boolean operate() {
		boolean ok = false;
		try {
			MessagesReader r = new MessagesReader(connection.getInputStream());
			r.receive();
			/*
			 * TODO : a giant switch case to manage the request/reply
			 */
			switch (r.getType()) {
			case Protocol.RP_INCOMMING_MESSAGES:
				List<Message> messages = r.createIncomingMessages();
				listener.notifyIncomingMessages(messages);
				ok = true;
				break;
			case Protocol.RP_PENDING_MESSAGES:
				List<Message> moderations = r.createIncomingModerations();
				listener.notifyIncomingMessageModerate(moderations);
				ok = true;
				break;
			}
		} catch (IOException e) {
		}
		return ok;
	}

	public void run() {
		try {
			MessagesWriter w = new MessagesWriter(connection.getOutputStream());
			w.sendName(name);
		} catch (IOException e) {
		}
		while (true) {
			if (!operate())
				break;
		}
		try {
			if (connection != null)
				connection.close();
		} catch (IOException e) {
		}
	}
}
