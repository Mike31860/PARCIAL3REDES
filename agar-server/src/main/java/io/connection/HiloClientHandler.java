package io.connection;

/**
 * Hilo que se encarga de darle una respuesta en formato HTTMl a un cliente que accede
 * a traves de localhost:puerto para consultar la pagina del login , ingresar su cedula 
 * y realizar la busqueda de sus apuestas
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.StringTokenizer;

public class HiloClientHandler extends Thread {

	private Socket socket;
	private Seerver1 server;

	public HiloClientHandler(Socket socket, Seerver1 seerver1) {

		this.socket = socket;
		this.server = server;
	}

	public void run() {

		// le quite el while true
		handleRequest(this.socket);

	}

	private void handleRequest(Socket socket2) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String headerLine = in.readLine();
			StringTokenizer tokenizer = new StringTokenizer(headerLine);
			String httpMethod = tokenizer.nextToken();
			System.out.println("encontrado");
			if (httpMethod.equals("GET")) {
				System.out.println("Get method processed");
				String httpQueryString = tokenizer.nextToken();
				System.out.println(httpQueryString);
				System.out.println("encontrado");
				if (httpQueryString.equals("/")) {
					StringBuilder responseBuffer = new StringBuilder();
					String str = "";
					BufferedReader buf = new BufferedReader(new FileReader("pagina/Formulario.html"));
					while ((str = buf.readLine()) != null) {
						responseBuffer.append(str);
					}
					System.out.println("encontrado");
					sendResponse(socket, 200, responseBuffer.toString());
					buf.close();
				}
				// permite obtener el dato ingresado en el submit
				if (httpQueryString.contains("/?nombre=")) {
					System.out.println("Get method processed");
					String[] response = httpQueryString.split("=");
					String[] infoemail = response[1].split("&");
					String email = infoemail[0];
					String password = response[2];
					System.out.println(email);
					System.out.println(password);

					File archivo = new File("./ArchivoTexto/LOGIN.txt");
					FileReader fr = new FileReader(archivo);
					BufferedReader br = new BufferedReader(fr);

					
					String linea = br.readLine();
					System.out.println(linea);

					StringBuilder responseBuffer = new StringBuilder();

					responseBuffer.append("<html>")
					.append("<head>")
					.append("<style>")
					.append("body{")
					.append(
							"	background-image: url(\"https://thumbor.forbes.com/thumbor/960x0/https%3A%2F%2Fblogs-images.forbes.com%2Fscottmendelson%2Ffiles%2F2019%2F03%2FAvengers-Chinese-Poster-D.jpg\");")
							
					.append("}")
					.append("</style>")
					.append("<title>Informacion Usuarios Agario</title>")		
					.append("</head>")
					.append("<body>")
                    .append("<table>")
                    .append("<tr>")
                    .append("<td><strong>UserName</strong></td>")
					.append("<td><strong>Email</strong></td>")
					.append("<td><strong>Password</strong></td>")
					.append("<td><strong>Score</strong></td>")
					.append("<td><strong>Fecha </strong></td>")
					.append("<td><strong>GANO?</strong></td>");
					responseBuffer.append("</tr>");

					boolean salir=true;
					while (linea!=null&&salir) {
						
						String[] es = linea.split(";");
						String email1 = es[0];
						String contrasena = es[1];
						System.out.println(email1);
						System.out.println(contrasena);

						if (email1.equals(email) && contrasena.equals(password))

						{
							File archivo1 = new File("./ArchivoTexto/archivo.txt");
							FileReader fr1 = new FileReader(archivo1);
							BufferedReader br1 = new BufferedReader(fr1);

							br1.readLine();
							String linea1 = br1.readLine();
							while (!linea1.equals("")) {
								String[] es2= linea1.split(";");
								responseBuffer.append("<tr>");
								responseBuffer.append("<td><strong>" + es2[1] + "</strong></td>");
								responseBuffer.append("<td><strong>" + es2[2] + "</strong></td>");
								responseBuffer.append("<td><strong>" + es2[3] + "</strong></td>");
								responseBuffer.append("<td><strong>" + es2[4] + "</strong></td>");
								responseBuffer.append("<td><strong>" + es2[5] + "</strong></td>");
								responseBuffer.append("<td><strong>" + es2[6] + "</strong></td>");
								responseBuffer.append("</tr>");
								linea1=br1.readLine();
							}
							salir=false;

						}

						linea = br.readLine();

					}
					
					if(salir)
					{
						responseBuffer.append("<tr>");
						responseBuffer.append("<td><strong>" + "USER WAS NOT FOUND TRY AGAIN" + "</strong></td>");
						responseBuffer.append("</tr>");
						
					}

					responseBuffer
					.append("</table>")
					.append("<body>")
					.append("</html>");

					sendResponse(socket, 200, responseBuffer.toString());

					// String mensajeObtenido = server.pedirDatosAlServerBD(name[1], response[2]);
					// String[] lista = mensajeObtenido.split("/n");

				}
			} else {
				System.out.println("The HTTP method is not recognized");
				sendResponse(socket, 405, "Method Not Allowed");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void sendResponse(Socket socket, int statusCode, String responseString) {
		String statusLine;
		String serverHeader = "Server: WebServer\r\n";
		String contentTypeHeader = "Content-Type: text/html\r\n";

		try {
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			if (statusCode == 200) {
				statusLine = "HTTP/1.0 200 OK" + "\r\n";
				String contentLengthHeader = "Content-Length: " + responseString.length() + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes(serverHeader);
				out.writeBytes(contentTypeHeader);
				out.writeBytes(contentLengthHeader);
				out.writeBytes("\r\n");
				out.writeBytes(responseString);
			} else if (statusCode == 405) {
				statusLine = "HTTP/1.0 405 Method Not Allowed" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			} else {
				statusLine = "HTTP/1.0 404 Not Found" + "\r\n";
				out.writeBytes(statusLine);
				out.writeBytes("\r\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
