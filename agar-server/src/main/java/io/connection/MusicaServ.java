package io.connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MusicaServ extends Thread {

	private Server server ;
	private DatagramSocket socketMusic;
	private  byte[] buffer;
	private DatagramPacket peticion;
	private String mensaje;
	
	
	public MusicaServ(Server server, DatagramSocket socketMusic, byte[] bufferM) {
	
		this.server = server;
 		this.socketMusic = socketMusic;
 		this.buffer=bufferM;
 		mensaje="a";
 
	}
	
	public synchronized void run() {
		
		while(true) {
			 
               try {
            	   System.out.println("Hola hilo servidor");
            	   byte[] RecogerServidor_bytes = new byte[1024];
                   
                   peticion = new DatagramPacket(RecogerServidor_bytes,1024);
                     socketMusic.receive(peticion);
                     
                     System.out.println("Recibo la informacion del cliente");
                      //nombre cancion
                    String mensaje1 = new String(RecogerServidor_bytes).trim();
                    System.out.println(mensaje1);
                    
                    //mandar
                    int puertoCliente = peticion.getPort();
                    InetAddress direccion = peticion.getAddress();
                  
                    //metodo que devuelve la ruta
                    String ruta = server.ruta(mensaje1);
                	buffer = ruta.getBytes();
                
                   	// buffer = mensaje.getBytes();
                        //System.out.println(mensaje);
                   	  
                  
                        DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
      
                        socketMusic.send(respuesta);
                     //   Thread.sleep(25);
	              
	              
	             
	             
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               
            
			
			
		}
		
		
		
		
		
	}
	
	

}
