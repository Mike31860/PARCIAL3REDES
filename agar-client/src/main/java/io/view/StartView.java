package io.view;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class StartView {
	
	 @FXML
	    private Button SignUpInitButton;

	    @FXML
	    private Label TitleWelcome;

	    @FXML
	    private Button SignInInitButton;
	    
	    private Stage stage = new Stage();

	    @FXML
	    void SignInBut(ActionEvent event) throws IOException {
	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SignIn.fxml"));
			Parent root = (Parent)fxmlLoader.load();          
			SignInView controller = fxmlLoader.<SignInView>getController();
			Scene scene = new Scene(root);
			
			stage.setScene(scene);
			stage.setTitle("Agar.io");
			stage.show();
			Stage stageAct = (Stage) SignUpInitButton.getScene().getWindow();
			stageAct.close();

	    }

	    @FXML
	    void SignUpBut(ActionEvent event) throws IOException {
	    	
	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/SignUp.fxml"));
			Parent root = (Parent)fxmlLoader.load();       
			SignUpView controller = fxmlLoader.<SignUpView>getController();

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Agar.io");
			stage.show();
			
			Stage stageAct = (Stage) SignInInitButton.getScene().getWindow();
			stageAct.close();

	    }

}
