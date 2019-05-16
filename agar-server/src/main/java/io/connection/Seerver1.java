package io.connection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Seerver1 {

	public ServerSocket serverSocketWebService;
	public static final int PORT_WEB_SERVICE = 7000;
	private HiloDespliegueAppWeb hiloDespliegueAppWeb;
	public static final String LOCAL_HOST = "localhost";
	public static final int PORT_BD = 6500;
	public boolean webService;

	public Seerver1() {
		webService = true;
		try {
			serverSocketWebService = new ServerSocket(PORT_WEB_SERVICE);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		hiloDespliegueAppWeb = new HiloDespliegueAppWeb(this);
		hiloDespliegueAppWeb.start();

	}

	public String pedirDatosAlServerBD(String nombre, String password) {
		String datosObtenidos = "";
		try {
			Socket socket = new Socket(LOCAL_HOST, PORT_BD);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			String mensaje = "PEDIR_DATOS;" + nombre + ";" + password;
			System.out.println("Chequeo Method pedir Datos Al Server BD");
			out.writeUTF(mensaje);
			String mensajeObtenido = in.readUTF();
			datosObtenidos = mensajeObtenido;
			System.out.println("Mensaje Obtenido por el Servidor BD al PEDIR DATOS : " + mensajeObtenido);
			socket.close();
		} catch (IOException e) {
			System.out.println("Exception in ConexServerBD");
		}
		return datosObtenidos;
	}

	public ServerSocket getServerSocketWebService() {
		return serverSocketWebService;
	}

	public static void main(String[] args) {
		Seerver1 server = new Seerver1();

		System.out.println("Inicializar todo");
	}

}
