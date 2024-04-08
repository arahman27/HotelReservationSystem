/**********************************************
Project: Hotel Reservation System
Course:APD545 - Semester 5
Last Name: Rahman
First Name: Aditya
ID: 046207130
Section: NAA
This assignment represents my own work in accordance with Seneca Academic Policy.
Signature - Aditya Rahman
Date: August 13, 2023
**********************************************/

package application.controllers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import application.utility.Alerts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class LoginController {

    @FXML
    private Label errLbl;

    @FXML
    private Label errorLbl;

    @FXML
    private Button loginBtn;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userName;

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private static Socket s = null;
    private DataInputStream din = null;
    private DataOutputStream dout = null;
    private String serverMsg;
    
    private static String currUser;
    
    @FXML
    void loginClick(ActionEvent event) throws IOException{
    	
    	if(userName.getText().isEmpty() || password.getText().isEmpty()) {
    		Alerts.error("Missing information", null, "Error! Please enter username and password.").showAndWait();
    	
    	}
    	else {
    		try {
				dout.writeUTF("Login," + userName.getText() + "," + password.getText());
				serverMsg = din.readUTF();
				
				if(serverMsg.equals("success")) {
        			Alerts.msg("Login success", null, "Welcome " + userName.getText()).showAndWait();
        			currUser = userName.getText();
                	Parent root = FXMLLoader.load(getClass().getResource("/application/views/Menu.fxml"));
                	stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                	scene = new Scene(root);
        			
                	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
        				public void handle(WindowEvent ev) {
        					try {
        						LoginController.closeSocket();
        					} catch (IOException e) {

        					}	
        				}
        			});
                	
                	stage.setScene(scene);
                	stage.show();
        			
				}
				else if(serverMsg.equals("User already logged in")) {
					Alerts.msg("Login failed", null, "This admin is already logged in.").showAndWait();
					
				}
				else {
    				Alerts.msg("Login failed", null, "Please enter correct username and password.").showAndWait();
					
				}
    						
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    }
    
    public void setDataStreams(DataInputStream is, DataOutputStream os, Socket s) {
    	this.din = is;
    	this.dout = os;
    	this.s = s;
    	
    }
    
	public static void closeSocket() throws IOException {
		s.close();
		
	}
    
    public static String getCurrUser() {
    	return currUser;
    	
    }
}
