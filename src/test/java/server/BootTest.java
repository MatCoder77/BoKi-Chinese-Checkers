package server;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import server.Bot;

public class BootTest {
	Bot myBot;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		myBot = new Bot(new EasyBot());
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void constrictionTest() {
		Bot bot = new Bot(new EasyBot());
		assertNotNull(bot);
	}

}
