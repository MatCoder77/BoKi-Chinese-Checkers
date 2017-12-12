package server;

import client.Client;
import communication.ConnectRequest;
import org.junit.Test;
import server.Server;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    
    @Test
    public void sendRequestTest() {
    	Server server = Server.getInstance(8975);
    	Client client = new Client("localhost", 8975);
    	server.listenForNewConnections();
    	client.connect();
    	client.sendRequest(new ConnectRequest("Mateusz"));
    }
}
