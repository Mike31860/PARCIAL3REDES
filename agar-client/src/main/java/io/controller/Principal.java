package io.controller;
import java.util.ArrayList;
import io.view.StartView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
	
	@Override
	public void start(Stage stage) throws Exception {
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/Start.fxml"));
		Parent root = (Parent)fxmlLoader.load();          
		StartView controller = fxmlLoader.<StartView>getController();		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Agar.io");
		stage.show();
		
	}
	
	public static void main (String[] args) {
		
		launch(args);
		
		
		
	}
	
	
	
	

}
