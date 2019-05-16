package io.view;

import java.io.IOException;

import io.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignInView {

    @FXML
    private Button SignInButton;

    @FXML
    private Label TitleEmail;

    @FXML
    private Label TitlePassword;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;


    @FXML
    void SignInBut(ActionEvent event) throws Exception {
    	
//    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Lobby.fxml"));
//		Parent root = (Parent)fxmlLoader.load();          
//		Main controller = fxmlLoader.<Main>getController();
//		
//
//	Scene scene = new Scene(root);
//	
//	stage.setScene(scene);
//	stage.setTitle("Agar.io");
//	stage.show();
//	Stage stageAct = (Stage) SignInButton.getScene().getWindow();
//	stageAct.close();
    	
    	Controller controller = new Controller();
    	controller.open(emailTxt.getText(),passwordTxt.getText() );
    	Stage stageAct = (Stage) SignInButton.getScene().getWindow();
		stageAct.close();
    	
    	
    	
    	

    }

}