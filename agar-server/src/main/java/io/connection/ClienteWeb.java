package io.connection;

public class ClienteWeb {

	
	
	private String score;
	private String username;
	private boolean gana;
	private String puntaje;
	public ClienteWeb(String score, String username, boolean gana, String puntaje) {
		super();
		this.score = score;
		this.username = username;
		this.gana = gana;
		this.puntaje = puntaje;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isGana() {
		return gana;
	}
	public void setGana(boolean gana) {
		this.gana = gana;
	}
	public String getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}
	
	
	
	
	
	
	
	
	
}
