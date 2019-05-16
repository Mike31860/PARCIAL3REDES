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

public class ConectionUDPSer extends Thread {
	
	 private Server servidor; 
     private byte[] buffer;
    private  DatagramSocket socketUDP;
    private DatagramPacket peticion;
    private String  mensaje;
	
	
     public ConectionUDPSer(Server server, DatagramSocket cone , byte[] buffer) throws Exception {

 		this.servidor = server;
 		this.socketUDP = cone;
 		this.buffer=buffer;
 		mensaje="a";
 

 	}
     
     
	
     @Override
		public synchronized void run() {

    	
             System.out.println("Iniciado el servidor UDP");
       
        
             while (true) {
            	 try {
             byte[] RecogerServidor_bytes = new byte[3072];
             
               peticion = new DatagramPacket(RecogerServidor_bytes,3072);
                 socketUDP.receive(peticion);
                 
           //      System.out.println("Recibo la informacion del cliente");
                  
                String mensaje1 = new String(RecogerServidor_bytes).trim();
            //    System.out.println(mensaje1);
  
            
                 int puertoCliente = peticion.getPort();
                 InetAddress direccion = peticion.getAddress();
               //  servidor.JoinUDP(mensaje1, this);
             String otro = servidor.processUDP(mensaje1, this);
             buffer = otro.getBytes();
             
                	// buffer = mensaje.getBytes();
                     //System.out.println(mensaje);
                	  
               
                     DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
      
                
        
                     socketUDP.send(respuesta);
                     Thread.sleep(25);
                	
                	
		       
  
         } catch (SocketException ex) {
           ex.getLocalizedMessage();
         } catch (IOException ex) {
        	 ex.getLocalizedMessage();
         } catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     

		}

     }

	public String getMensaje() {
		return mensaje;
	}



	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
	
	

}
