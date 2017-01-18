package fr.ensisa.hassenforder.chatrooms.client;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fr.ensisa.hassenforder.network.BasicAbstractReader;
import fr.ensisa.hassenforder.network.Protocol;

import fr.ensisa.hassenforder.chatrooms.client.model.*;

public class CommandReader extends BasicAbstractReader {

	private List<Channel> channels = new ArrayList<>();
	
	public CommandReader(InputStream inputStream) {
		super (inputStream);
	}

	public void receive() {
		type = readInt ();
		switch (type) {
			case Protocol.RP_OK:
				break;
			case Protocol.RP_KO:
				break;
			case Protocol.RP_CHANNELS:
				// nombre de channels
				readChannels(readInt());
			case Protocol.RP_LOAD:
				readString();
		}
	}

	// Lecture de la liste de channel (pour load)
	private void readChannels(int nbChannels) {
		if(nbChannels > 0) { // Si il y a des channels à lire
			for(int i = 1; i <= nbChannels; i++) {
				// Créer un Channel
				Channel channel = new Channel();
				// channel name
				channel.setName(readString());
				// Type du channel
				int type = readInt();
				ChannelType channelType;
				if(type == 0) channelType = ChannelType.FREE;
				else channelType = ChannelType.MODERATED;
				channel.setType(channelType);
				// le modérateur
				if(channel.getType() == ChannelType.MODERATED)
					 channel.setModerator(readString());
				channel.setSubscribed(readBoolean());
				channels.add(channel);
			}
		}
	}

	public List<Channel> getChannels() {
		return channels;
	}

}
