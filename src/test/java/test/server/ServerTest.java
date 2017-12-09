package test.server;

import static org.junit.Assert.*;
import org.junit.Test;
import server.Server;

public class ServerTest {

	@Test
	public void test() {
		Server server = Server.getInstance(9999);
		assertNotNull(server);
	}

}

