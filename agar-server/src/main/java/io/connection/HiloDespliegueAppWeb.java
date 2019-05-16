package io.connection;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HiloDespliegueAppWeb extends Thread {
	
	Seerver1 server;
	
	public HiloDespliegueAppWeb(Seerver1 server) {
		
		this.server = server;
	}
	
	public void run() {
		
		while(server.webService) {
			System.out.println(":::Web Server Started:::");
			ServerSocket serverSocket = server.getServerSocketWebService();
			try {
				Socket cliente = serverSocket.accept();
				HiloClientHandler hilo = new HiloClientHandler(cliente, server);
				hilo.start();	
				
			} catch (IOException e) {
				System.out.println("Exception in HiloDespliegueAppWeb");
			}
			
		}
		
	}
	

}
