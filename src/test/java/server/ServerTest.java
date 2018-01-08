package server;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import client.Client;
import communication.ConnectRequest;
import communication.DisconnectRequest;
import communication.StartFastGameRequest;
import jdk.nashorn.internal.ir.annotations.Ignore;
import server.Server;
import server.GameType.BoardSize;

public class ServerTest {
	static Thread serverThread;
	public static class ServerRunner implements Runnable {
		
		public ServerRunner() {};
		
		@Override
		public void run() {
			Server.getInstance().listenForNewConnections();		
		}

	}
	@BeforeClass 
	static public void runServer() {
		serverThread = new Thread(new ServerRunner());
		serverThread.start();
	}
	
	@AfterClass static public void stopServer() {
		serverThread.interrupt();
	}

	@Test
	public void gettingServerInstance() {
		Server server = Server.getInstance();
		assertNotNull(server);
		Server server2 = Server.getInstance();
		assertEquals(server, server2);
	}
	
	@Test
	public void serverClosing() {
		
		
	}
	
	@Test
	public void clientConnection() {
		Thread serverThread = new Thread(new ServerRunner());
		serverThread.start();
		Client client = new Client("localhost", 8988, "User1");
		client.connect();
		assertTrue(client.isConnected());
		//serverThread.interrupt();
	}
	
	@Test
	public void manyClientsConnection() {
		Client c1 = new Client("localhost", 8988, "C1");
		Client c2 = new Client("localhost", 8988, "C1");
		Client c3 = new Client("localhost", 8988, "C1");
		
		c1.connect();
		c3.connect();
		c2.connect();
		assertTrue(c1.isConnected());
		assertTrue(c2.isConnected());
		assertTrue(c3.isConnected());
	}

}

