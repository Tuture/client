package fr.ensisa.hassenforder.chatrooms.client;

import java.io.OutputStream;

import fr.ensisa.hassenforder.chatrooms.client.model.Channel;
import fr.ensisa.hassenforder.chatrooms.client.model.ChannelType;
import fr.ensisa.hassenforder.network.BasicAbstractWriter;
import fr.ensisa.hassenforder.network.Protocol;

public class CommandWriter extends BasicAbstractWriter {

	public CommandWriter(OutputStream outputStream) {
		super (outputStream);
	}

	public void createConnect(String name) {
		writeInt(Protocol.RQ_CONNECT);
		writeString(name);
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
	}

	public void createChannelSubscriptionChange(String name, Channel description, boolean selected) {
		writeInt(Protocol.RQ_UNSUSCRIBE);
		writeString(name);
		writeString(description.getName());
		writeBoolean(selected);
	}

}
