package server;

import client.ClientGUI;

public class AppTest 
{
    public static void main() {
    	
    	
    	java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    	
    	java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
    	
    }
}
