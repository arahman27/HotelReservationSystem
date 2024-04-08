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

package application;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import application.controllers.LoginController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	private static Socket s;
	private DataOutputStream dout;
	private DataInputStream din;
	private LoginController login = null;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/views/Login.fxml"));
			GridPane root = loader.load();
			login = (LoginController)loader.getController();
			
			Scene scene = new Scene(root,1020,486);
			primaryStage.setTitle("Hotel ABC Reservation System");
			Image icon = new Image(getClass().getResourceAsStream("/images/abc_logo.jpg"));
			primaryStage.getIcons().add(icon);
			primaryStage.resizableProperty().setValue(false);
			
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				public void handle(WindowEvent ev) {
					try {
						s.close();
					} catch (IOException e) {

					}	
				}
			});
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			try {
				s = new Socket("localhost", 8000);
				din = new DataInputStream(s.getInputStream());
				dout = new DataOutputStream(s.getOutputStream());
				
				new Thread(()->run()).start();
				
			}catch(IOException ioex) {
				ioex.printStackTrace();
			}		
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			while(true) {
				if(login != null) {
					login.setDataStreams(din, dout, s);
					
				}	
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
