package io.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.sun.glass.ui.Window.Level;
import com.sun.javafx.logging.Logger;

public class ConectionUDP extends Thread {

	
	 final int PUERTO_SERVIDOR = 5000;
	private  byte[] buffer;
	 private Client client;
	 private DatagramSocket socketUDP;
	private  DatagramPacket peticion;
	 
	 public ConectionUDP(Client client) throws Exception {

			this.client = client;
            socketUDP = new DatagramSocket();
            
            buffer = new byte[1024];
		}

	 @Override
		public synchronized void run() {

			// REVIEW: To join to the game
		 
		 boolean es=false;
		
			while (true) {
				
				  try {
					  
			            InetAddress direccionServidor = InetAddress.getByName("localhost");

			            DatagramSocket socketUDP = new DatagramSocket();
			 
			            String mensaje = "join:" + client.getUserName();
			 
			           
			            buffer = mensaje.getBytes();
			 
			            DatagramPacket servPaquete;
			            DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
			 
			          
			            //System.out.println("Envio el datagrama");
			            //System.out.println(mensaje);
			            socketUDP.send(pregunta);
			            byte[] RecogerServidor_bytes = new byte[3072];
			           
			     
			            servPaquete = new DatagramPacket(RecogerServidor_bytes,3072);
			            socketUDP.receive(servPaquete);
			          
			           // System.out.println("Recibo la peticion");
			 
			           String cadenaMensaje = new String(RecogerServidor_bytes).trim();
			   
			            //System.out.println(cadenaMensaje);
			            client.process(cadenaMensaje, es);
			            
			           es=true;

			 
			        } catch (SocketException ex) {
			        	ex.getLocalizedMessage();
			        } catch (UnknownHostException ex) {
			        	ex.getLocalizedMessage();
			        } catch (IOException ex) {
			        	ex.getLocalizedMessage();
			        }


			}

		}
	
	
	
	 
	
	
	
	
	
	
	
	
}
