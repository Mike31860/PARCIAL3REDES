package io.controller;

import java.io.IOException;
import java.io.InputStream;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import io.connection.Client;
import io.connection.IObserver;
import io.view.Main;

/**
 * Controller
 */
public class Controller implements IObserver{

    private Main main;
    private Client client;
    private Stage stage = new Stage();

    
    public void open(String email, String Password) throws Exception {
        
        FXMLLoader loader = new FXMLLoader();
        
        InputStream lobbyViewStream = getClass().getResourceAsStream("../view/lobby.fxml");
        
        AnchorPane pane = loader.load(lobbyViewStream);
        main=new Main();
        main.init(pane);
        main.setPlayButtonListener(e->{
            play(email, Password);
        });
        main.setPlayButtonListener2(e->{
        	playStreaming();
        });

        
        
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        main.setStage(stage);
       
        
    }
    
    private EventHandler<KeyEvent> getGameKeyHandler() {
    	return e->{
    		
    		String c =e.getCharacter();
    		String mov = "";
			switch (c.toLowerCase()) {
			case "w":
				mov="up";
				break;
			case "s":
				mov="down";
				break;
			case "a":
				mov="left";
				break;				
			case "d":
				mov="right";
				break;
			}
			
			if(!mov.isEmpty()) {
				client.move(mov);
			}
			
    		
    	};
    	
		
	}

    public void play(String email, String password){
        String userName = main.getNameTxt();
        //Using default client, view the several class constructos
 
        client = new Client();
        client.setUserName(userName);
        client.setObserver(this);
 
        try {
            client.connect(email, password, userName);
		} catch (Exception e) {
			main.setMessage("No se puede conectar con el servidor");
		}
        
    }
    
    public void playStreaming(){
        String userName = main.getNameTxt();
        //Using default client, view the several class constructos
        client = new Client();
        client.setUserName(userName);
        client.setObserver(this);

        try {
            client.conectarStreaming();
         
		} catch (Exception e) {
			main.setMessage("No se puede conectar con el servidor");
		}
        
    }
    
    
    
    
    

    @Override
    public void callback(String mensaje){
    	switch (client.getGame().getState()) {
    	case Join:
    		Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
//		    		main.hide();	
		    		try {
						main.showGameView(client.getGame());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		main.getGameView().setKeyHandler(getGameKeyHandler());
		    		main.drawGame();
				}

				
			});
    		break;
    	case streaming:
    		Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
//		    		main.hide();	
		    		try {
						main.showGameViewStreaming(client.getGame(), client.getUserName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		    		main.drawGameStreaming();
				}

				
			});
    		break;
		case Playing:
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					main.drawGame();					
				}
			});
			
			break;
		case streaming2:
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					main.drawGameStreaming();					
				}
			});
			
			break;
		case Chat:
			
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					main.appendChat(mensaje);					
				}
			});
			
			break;
		case Lost:
			Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					main.getGameView().hide();
					main.show();
					String message = client.getGame().getState().getMessage();
					main.setMessage(message);
					
				}
			});
			break;

		default:
			
			break;
		}
    }
    
//    public static void main(String[] args) {
//		launch(args);
//	}
}