package server;

import client.Client;
import communication.ConnectRequest;

public class ClientTest {
	public static void main(String args[]) 
    {
		Client client = new Client("localhost", 8982);
		client.connect();
		client.sendRequest(new ConnectRequest("Mateusz"));
    }
}
