package server;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ServerGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private volatile ArrayList<String> userNames;
	private JTextArea log;
	private JScrollPane scrollPanel;
	private JPanel controlPanel;
	private JButton start;
	private JButton stop;
	private JButton clearLog;
	private JButton showConnected;
	private Thread serverThread;
	Server server;
	
	/**
	 * Constructs server
	 */
	ServerGUI() {
		createGUI();
	}
	
	class ServerRunner implements Runnable {
		
		@Override
		public void run() {
			server.listenForNewConnections();		
		}

	}
	
	/**
	 * Creates gui
	 */
	private void createGUI() { 
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(400, 500);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		log = new JTextArea();
		scrollPanel = new JScrollPane(log);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.8;
		add(scrollPanel, gbc);
		
		controlPanel = new JPanel(new GridLayout(2, 2));
		start = new JButton("Uruchom");
		stop = new JButton("Zakończ");
		clearLog = new JButton("Wyczyść dziennik");
		showConnected = new JButton("Połączeni użytkownicy");		
		start.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerGUI.this.addToLog("Uruchomiono serwer...");
				server = Server.getInstance();
				server.setServerGUI(ServerGUI.this);
				serverThread = new Thread(new ServerRunner());
				serverThread.start();
			}
		});
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
		       ServerGUI.this.addToLog("Zamykanie serwera...");
		       ServerGUI.this.addToLog("Zamknięto...");
				
			}
		});
		clearLog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.setText("");	
			}
		});
		showConnected.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerGUI.this.addToLog("Połączeni użytkownicy: \n");		
			}
		});	
		controlPanel.add(start);		
		controlPanel.add(stop);	
		controlPanel.add(clearLog);	
		controlPanel.add(showConnected);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.2;
		add(controlPanel, gbc);
		setVisible(true);
	}
	
	/**
	 * Adds to log
	 * @param message message
	 */
	void addToLog(String message) {
		log.append(message + "\n");
	}
	
	void addUser(String user) {
		userNames.add(user);
		addToLog("Nawiązano połączenie z " + user);
	}
	
	void removeUser(String user) {
		userNames.remove(user);
		addToLog("Rozłączono z " + user);
	}
	
	public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
    }
	
}

