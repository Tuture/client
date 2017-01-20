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
		System.out.println("Receiving result in command reader client");
		type = readInt ();
		System.out.println("Type : " + type);
		switch (type) {
			case Protocol.RP_OK:
				break;
			case Protocol.RP_KO:
				break;
			case Protocol.RP_CHANNELS:
				// nombre de channels
				readChannels(readInt());
				break;
			case Protocol.RP_LOAD:
				readString();
				break;
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
				if(type == Protocol.FREE) {
					channel.setType(ChannelType.FREE);
				}
				else {
					channel.setType(ChannelType.MODERATED);
					channel.setModerator(readString());
				}
				channel.setSubscribed(readBoolean());
				channels.add(channel);
			}
		}
	}

	public List<Channel> getChannels() {
		return channels;
	}

}
