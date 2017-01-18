package fr.ensisa.hassenforder.chatrooms.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.chatrooms.client.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

public class MessagesReader extends BasicAbstractReader {

	public MessagesReader(InputStream inputStream) {
		super(inputStream);
	}

	public void receive() {
		type = readInt();
		switch (type) {
		case Protocol.RP_OK:
			break;
		case Protocol.RP_INCOMMING_MESSAGES:
			break;
		case Protocol.RP_PENDING_MESSAGES:
			break;
		}
	}

	public List<Message> createIncomingMessages() {
		List<Message> messages = new ArrayList<Message>();
		int nbMsg = readInt();
		while (nbMsg != 0) {
			messages.add(new Message(readString(), readInt(), readString(), readString()));
			nbMsg--;
		}
		return messages;
	}

	public List<Message> createIncomingModerations() {
		List<Message> moderations = new ArrayList<Message>();
		int nbMsg = readInt();
		while (nbMsg != 0) {
			moderations.add(new Message(readString(), readInt(), readString(), readString()));
			nbMsg--;
		}
		return moderations;
	}

}
