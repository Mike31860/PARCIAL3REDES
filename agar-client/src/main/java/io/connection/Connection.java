package io.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.Calendar;

/**
 * Connection
 */
public class Connection extends Thread {

	private Client client;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String userName;
	private String email;
	private String password;

	public Connection(Client client, String email, String password, String userName) throws Exception {

		this.client = client;
		socket = new Socket(client.getHost(), client.getPort());
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		this.email=email;
		this.password=password;
		this.userName=userName;

	}

	private void joinToGame() {
		out.println("join:" + client.getUserName());
	}

	@Override
	public synchronized void run() {

		// REVIEW: To join to the game

		joinToGame();

		while (socket.isConnected()) {

			String data = "";
			try {
				while (in.ready()) {
					data += in.readLine() + "@";
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			if (!data.isEmpty()) {
				try {
					
					if(data.contains("lost"))
				{
						
					    Calendar a= Calendar.getInstance();
					   
						write("ACHU"+";"+userName+";"+email+";"+password+";"+client.getGame().getScore()+";"+a.getTime()+";"+false);
							
				
				}
					
					client.processTCP(data, false, email, password, userName);
					
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

	public void close() {
		try {
			socket.close();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public void write(String message) {
		out.println(message);
	}
}