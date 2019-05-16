package io.connection;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;

import io.model.Cell;
import io.model.Game;
import io.model.State;

/**
 * Hello world!
 *
 */
public class Client {

	private String host;
	private int port;
	private Connection connection;
	private ConectionUDP conectionudp;
	private String userName;
	private IObserver observer;
	private Game game;
	private ConnectionChat chat;
	private String mensaje;
	private File archivo;
	private BufferedWriter bw;
	
	

	public Client(String host, int port, String userName) {
		this.host = host;
		this.port = port;
	
		this.userName = userName;
		game = new Game();
		mensaje = " ";
		archivo= new File("./ArchivoTexto/archivo.txt");
		bw= new BufferedWriter(new OutputStreamWriter(System.out));
		
	}

	public Client(String host, int port) {
		this(host, port, "user1");
	}

	public Client(int port) {
		this("localhost", port);
	}

	public Client(String host) {
		this(host, 8888);
	}

	public Client() {
		this("localhost", 8888);
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getUserName() {
		return userName;
	}

	/**
	 * @param observer the observer to set
	 */
	public void setObserver(IObserver observer) {
		this.observer = observer;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void connect(String email, String password, String userName) throws Exception {

		connection = new Connection(this, email, password, userName );
		connection.start();

	}
	
	public void conectarStreaming() throws Exception
	{
		conectionudp = new ConectionUDP(this);
		conectionudp.start();
		
		
	}
	
	
	public void process(String data, boolean es) {

		// FORMAT x;y;r;g;b;value;radius;userName (if cell is from user ) per line
		// if the cell is of the client then the format will be
		// an extra field, denoted with true

		if (data.contains("error")) {
			// TODO: Error while login
			String[] info = data.split(":");
			State state = State.Error;
			state.setMessage(info[1]);
			game.setState(state);
		} else if (data.contains("lost")) {
			// TODO: You lost
			System.out.println(data);
			connection.close();
			String[] info = data.split(":");
			State state = State.Lost;
			state.setMessage(info[1]);
			game.setState(state);
		} else {

			if (!es) {
				game.setState(State.streaming);
			} else {
				game.setState(State.streaming2);
			}

			String[] cellsInfo = data.split("@");
			ArrayList<Cell> cells = new ArrayList<Cell>();
			for (int i = 0; i < cellsInfo.length; i++) {
				String line = cellsInfo[i];

				if (!line.isEmpty()) {
					String[] info = line.split(";");
					try {
						int x = Integer.parseInt(info[0]);
						int y = Integer.parseInt(info[1]);
						int r = Integer.parseInt(info[2]);
						int g = Integer.parseInt(info[3]);
						int b = Integer.parseInt(info[4]);
						int value = Integer.parseInt(info[5]);
						double radius = Double.parseDouble(info[6]);

						Cell cell = new Cell(x, y, r, g, b, value, radius);

						if (info.length > 7) {
							String user = info[7];
							cell.setUser(user);
							if(user.equals(userName)) {
								game.setScore(cell.getValue());
							}
						}

						cells.add(cell);

					} catch (Exception e) {
						//e.printStackTrace();
						//System.out.println("in received data:" + data);
					}
				}
			}

			game.setCells(cells);

		}

		if (observer != null) {
			observer.callback(null);
		}

	}
	
	
	
	

	public void processTCP(String data, boolean chat, String email, String password, String userName) throws IOException {

		// FORMAT x;y;r;g;b;value;radius;userName (if cell is from user ) per line
		// if the cell is of the client then the format will be
		// an extra field, denoted with true
		if(!chat) {
		if (data.contains("error")) {
			// TODO: Error while login
			String[] info = data.split(":");
			State state = State.Error;
			state.setMessage(info[1]);
			game.setState(state);
		} else if (data.contains("lost")) {
			// TODO: You lost
					
			String[] info = data.split(":");
			State state = State.Lost;
			state.setMessage(info[1]);
			game.setState(state);
		} else {

			if (data.contains("welcome")) {
				game.setState(State.Join);
			} else {
				game.setState(State.Playing);
			}

			String[] cellsInfo = data.split("@");
			ArrayList<Cell> cells = new ArrayList<Cell>();
			for (int i = 0; i < cellsInfo.length; i++) {
				String line = cellsInfo[i];

				if (!line.isEmpty()) {
					String[] info = line.split(";");
					try {
						int x = Integer.parseInt(info[0]);
						int y = Integer.parseInt(info[1]);
						int r = Integer.parseInt(info[2]);
						int g = Integer.parseInt(info[3]);
						int b = Integer.parseInt(info[4]);
						int value = Integer.parseInt(info[5]);
						double radius = Double.parseDouble(info[6]);

						Cell cell = new Cell(x, y, r, g, b, value, radius);

						if (info.length > 7) {
							String user = info[7];
							cell.setUser(user);
							if(user.equals(userName)) {
								game.setScore(cell.getValue());
							}
						}

						cells.add(cell);

					} catch (Exception e) {
						//e.printStackTrace();
						//System.out.println("in received data:" + data);
					}
				}
			}

			game.setCells(cells);

		}
		}else {
			game.setState(State.Chat);
			mensaje = data;
		}

		if (observer != null && chat) {
			observer.callback(mensaje);
		}else {
			observer.callback(null);
		}

	}

	public void txtVerdadero(String Username, String email, String password, int Score, String Fecha, boolean gano) throws IOException
	{
		
	        
	        if(archivo.exists()) {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write(Username+";"+email+";"+password+";"+Score+";"+Fecha+";"+gano+"\n");
	        } else {
	            bw = new BufferedWriter(new FileWriter(archivo));
	            bw.write("Username, email, password, Score, Fecha, Gano?"+"\n");
	            bw.write(Username+";"+email+";"+password+";"+Score+";"+Fecha+";"+gano+"\n");
	        }
	        bw.close();
		
	}
	
	
	
	
	public static void main(String[] args) {
		Client client = new Client();
		try {
			client.connect("","", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void move(String mov) {
		connection.write("move:" + getUserName() + "=" + mov);
	}
	


	public Game getGame() {
		// TODO Auto-generated method stub
		return game;
	}
	
	
}
