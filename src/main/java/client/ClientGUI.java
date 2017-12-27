package client;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import communication.EndTurnRequest;
import communication.LeaveGameRequest;
import communication.MoveRequest;
import communication.StartFastGameRequest;

public class ClientGUI extends JFrame {
	
	private String user_name = "MatCoder77";
	private String address = "localhost";
	private int port = 8988;
	

	private JLabel address_lab;
	private JLabel port_lab;
	private JLabel user_name_lab;
	private JTextField address_field;
	private JTextField port_field;
	private JTextField user_name_field;
	private JButton connect;
	private JButton disconnect;
	private JTextArea drawingArea;
	private JPanel control_panel;
	private JTextField input_field;
	private JButton insert;
	private JButton delete;
	private JButton search;
	private JButton max;
	private JButton min;
	private JButton successor;
	private JButton predecessor;
	private String command = "";
	private Client client;
	
	public ClientGUI() {
		createGUI();
	}

	private void createGUI() {
		setSize(800, 800);
		JPanel login_panel = new JPanel(new FlowLayout());
		// JPanel lab_and_field = new JPanel(new FlowLayout());
		address_lab = new JLabel("Adres ");
		address_field = new JTextField(30);
		address_field.setText(address);
		port_lab = new JLabel("Port ");
		port_field = new JTextField(30);
		port_field.setText(Integer.toString(port));
		user_name_lab = new JLabel("Użytkownik ");
		user_name_field = new JTextField(30);
		user_name_field.setText(user_name);
		connect = new JButton("Połącz");
		disconnect = new JButton("Rozłącz");
		login_panel.add(address_lab);
		login_panel.add(address_field);
		login_panel.add(port_lab);
		login_panel.add(port_field);
		login_panel.add(user_name_lab);
		login_panel.add(user_name_field);
		login_panel.add(connect);
		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String host = address_field.getText().toString();
				int portNum = Integer.parseInt(port_field.getText().toString());
				client = new Client(host, portNum, user_name_field.getText());
				client.setClientGUI(ClientGUI.this);
				if(client.connect() == true) {
					drawingArea.append("Połączono z serwerem...\n");
				} else {
					drawingArea.append("Nie udało się połączyc...\n");
				}
			}
		});
		login_panel.add(disconnect);
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(client.disconnect() == true) {
					drawingArea.append("Rozłączono\n");
				} else {
					drawingArea.append("Nie udało się rozłączyc\n");
				}
			}
		});
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		add(login_panel, gbc);

		drawingArea = new JTextArea();
		// drawingArea.setLayout(null);
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 0.8;
		add(drawingArea, gbc);

		control_panel = new JPanel(new GridBagLayout());
		JPanel input_panel = new JPanel(new FlowLayout());
		input_panel.add(new JLabel("Wprowadz komende:"));
		input_field = new JTextField(30);
		input_panel.add(input_field);
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.gridx = 0;
		gbc2.gridy = 0;
		gbc2.weightx = 1.0;
		gbc2.weighty = 0.1;
		control_panel.add(input_panel, gbc2);

		JPanel options_panel = new JPanel(new GridLayout(2, 4));
		JPanel type_panel = new JPanel(new FlowLayout());
		type_panel.add(new JLabel("Ilośc graczy"));
		String[] types = { "2", "3", "4", "6" };
		JComboBox<String> types_box = new JComboBox<>(types);
		type_panel.add(types_box);
		types_box.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selected_type = (String) e.getItem();
					
				}
			}
		});
		options_panel.add(type_panel);

		insert = new JButton("Graj");
		options_panel.add(insert);
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.sendRequest(new StartFastGameRequest(user_name_field.getText()));
			}
		});

		delete = new JButton("Opuśc grę");
		options_panel.add(delete);
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.sendRequest(new LeaveGameRequest());
			}
		});

		search = new JButton("Wykonaj ruch");
		options_panel.add(search);
		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.sendRequest(new MoveRequest(new Point(3, 7), new Point(4,9)));
				addToLog("Sent moveRequest");
			}
		});

		max = new JButton("Zakończ turę");
		options_panel.add(max);
		max.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				client.sendRequest(new EndTurnRequest());
			}
		});

		min = new JButton("Cofnij");
		options_panel.add(min);
		min.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		successor = new JButton("Wolne");
		options_panel.add(successor);
		successor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
	
			}
		});

		predecessor = new JButton("Wolne");
		options_panel.add(predecessor);
		predecessor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.gridx = 0;
		gbc2.gridy = 1;
		gbc2.weightx = 1.0;
		gbc2.weighty = 0.8;
		control_panel.add(options_panel, gbc2);

		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 0.1;
		add(control_panel, gbc);

		setVisible(true);

	}
	
	void addToLog(String msg) {
		drawingArea.append(msg + '\n'); 
	}
	
	void moveButtonActive(boolean state) {
		search.setEnabled(state);
	}
	
	void endTurnButtonActive(boolean state) {
		max.setEnabled(state);
	}
	
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new ClientGUI();
			}
		});
	}

}
