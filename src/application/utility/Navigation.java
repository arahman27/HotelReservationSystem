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

package application.utility;

import java.io.IOException;

import application.controllers.LoginController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Navigation {
    private Stage stage;
    private Scene scene;
    private Parent root;
	
	public void changePage(ActionEvent e, String page){
		try {
			Parent root;
			root = FXMLLoader.load(getClass().getResource(page));
	    	stage = (Stage)((Node)e.getSource()).getScene().getWindow();
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
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}
}
