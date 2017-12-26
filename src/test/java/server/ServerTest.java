package server;

import static org.junit.Assert.*;
import org.junit.Test;
import server.Server;

public class ServerTest {

	@Test
	public void testInitialization() {
		Server server = Server.getInstance();
		assertNotNull(server);
	}

}

