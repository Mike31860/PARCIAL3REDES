package io.view;

import io.controller.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignUpView {

    @FXML
    private Button SignUpButton;

    @FXML
    private Label TitleEmail;

    @FXML
    private Label TitlePassword;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordTxt;

    @FXML
    void SignUpBut(ActionEvent event) throws Exception {
    	
    	
    	Controller controller = new Controller();
    	controller.open(emailTxt.getText(), passwordTxt.getText());
    	

    }

}