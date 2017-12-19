package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.hamcrest.core.IsInstanceOf;

import communication.ConnectResponse;

public class ServerListener implements Runnable {

	volatile ObjectInputStream clientInput;
	
	ServerListener(ObjectInputStream clientInput) {
		this.clientInput = clientInput;
	}
	
	@Override
	public void run() {
		Object receivedObject;
		try {
			while((receivedObject = clientInput.readObject()) != null) {
				if(receivedObject instanceof ConnectResponse) {
					System.out.println("You were succesfully connected");
				}
			}
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
