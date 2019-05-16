package io.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import io.view.GameViewStreaming;

public class ConnectionChat extends Thread{
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private GameViewStreaming game;
	
	public ConnectionChat(GameViewStreaming game) throws Exception {
		this.game = game;
		socket = new Socket("localhost", 5600);
		out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

	}
	
	public synchronized void run() {

		while (socket.isConnected()) {
			
			String data = "";
			try {
				while (in.ready()) {
					data = in.readLine();
					
					//client.processTCP(data, true);
					game.append(data);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
			
		}
		
	}
	
	public void write(String message) {
		out.println(message);
	}
	
	
	
}