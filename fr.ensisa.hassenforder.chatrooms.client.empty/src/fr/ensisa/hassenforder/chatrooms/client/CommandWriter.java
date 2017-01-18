package fr.ensisa.hassenforder.chatrooms.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.chatrooms.client.model.Channel;
import fr.ensisa.hassenforder.chatrooms.client.model.ChannelType;
import fr.ensisa.hassenforder.chatrooms.client.model.Message;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandWriter extends BasicAbstractWriter {

	public CommandWriter(OutputStream outputStream) {
		super (outputStream);
	}

	public void createConnect(String name) {
		writeInt(Protocol.RQ_CONNECT);
		writeString(name);
		this.writeBoolean(true);
	}
	
	public void createChannel(String name, String channelName, ChannelType mode) {
		writeInt(Protocol.RQ_CHANNEL);
		writeString(name);
		writeString(channelName);
		if(mode.equals(ChannelType.FREE))
			writeInt(0);
		if(mode.equals(ChannelType.MODERATED))
			writeInt(1);
	}
	
	public void createLoadChannel(String name) {
		writeInt(Protocol.RQ_LOAD);
		writeString(name);
	}

	public void createDisconnect(String name) {
		writeInt(Protocol.RQ_DISCONNECT);
		writeString(name);
		this.writeBoolean(false);
	}

	public void createChannelSubscriptionChange(String name, Channel description, boolean selected) {
		writeInt(Protocol.RQ_UNSUSCRIBE);
		writeString(name);
		writeString(description.getName());
		writeBoolean(selected);
	}

	public void createApprobation(String name, Message message, boolean approved) {
		this.writeInt(Protocol.RQ_APPROBATION);
		this.writeString(name);
		this.writeInt(message.getId());
		this.writeBoolean(approved);
		this.send();
		
	}
	
	public void createSendingMessage(String name, String channelName, String text) {
		this.writeInt(Protocol.RQ_SEND_MESSAGE);
		this.writeString(name);
		this.writeString(channelName);
		this.writeString(text);
		this.send();
	}

}
