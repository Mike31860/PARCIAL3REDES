package io.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Connection
 */
public class Connection extends Thread {

	private Server server;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private String nick;

	public Connection(Server server, Socket socket) throws Exception {

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
					data += in.readLine() + "@";
				}

				if (!data.isEmpty()) {
					
					if(data.contains("ACHU"))
					{
						
						server.txtVerdadero(data);
						
						
					}
					
					
					server.process(data, this);
					
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void close() {
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void write(String message) {
		out.println(message);
	}

	public void setNickName(String nick) {
		this.nick = nick;

	}

	public String getNick() {
		return nick;
	}

}