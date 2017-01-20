package fr.ensisa.hassenforder.chatrooms.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class MessagesWriter extends BasicAbstractWriter {

	public MessagesWriter(OutputStream outputStream) {
		super (outputStream);
	}
	
	public void sendName(String name){
		this.writeInt(Protocol.RQ_CONNECT);
		this.writeString(name);
	}

}
