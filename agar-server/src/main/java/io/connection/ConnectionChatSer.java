package io.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionChatSer extends Thread{
	
	private Server server;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String nick;

	public ConnectionChatSer(Server server, Socket socket) throws Exception {

		this.server = server;
		this.socket = socket;
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}
	
	@Override
	public synchronized void run() {
		
		
		while (socket.isConnected()) {

			String data = "";
			try {
				while (in.ready()) {
					data = in.readLine();
					System.out.println(data);
					server.processChat(data);
					
				}
//
//				if (!data.isEmpty()) {
//				//	server.process(data, this);
//				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
	}
	
	public void write(String message) {
		out.println(message);
	}

}
