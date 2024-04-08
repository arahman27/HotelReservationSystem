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

import java.io.IOException;
import java.util.Optional;

import application.dao.BookingCRUD;
import application.dao.GuestCRUD;
import application.dao.RoomCRUD;
import application.utility.Alerts;
import application.utility.Navigation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class GuestInfoController {

    @FXML
    private Button confirmGuestBtn;

    @FXML
    private Button exitBtn;
    
    @FXML
    private Button goBackBtn;

    @FXML
    private Circle logoCircle;

    @FXML
    private Button resetBtn;
    
    @FXML
    private TextField address;

    @FXML
    private TextField email;

    @FXML
    private TextField fname;

    @FXML
    private TextField lname;

    @FXML
    private TextField phone;

    @FXML
    private TextField title;
    
    @FXML
    private Label userName;

    private Navigation nav = new Navigation();
    
    @FXML
    void goBackClick(ActionEvent event) {
    	Optional<ButtonType> result = Alerts.msg("Go back", null, "Return to room information form? Unsaved room and guest information will be discarded.").showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    		BookingCRUD.removeBooking(RoomController.getSelectedRoom());
    		RoomCRUD.changeAvailability(RoomController.getSelectedRoom(), "Available");
    		nav.changePage(event, "/application/views/Room.fxml");
    		
    	}	
    }

    @FXML
    void confirmGuestClick(ActionEvent event) {
    	if(title.getText().isEmpty() || fname.getText().isEmpty() || lname.getText().isEmpty() || address.getText().isEmpty() || phone.getText().isEmpty() || email.getText().isEmpty()) {
    		Alerts.error("Missing information", null, "Error! Please enter all information.").showAndWait();
    		
    	}
    	else if(!phone.getText().matches("(\\d{3})-(\\d{3})-(\\d{4})")) {
    		Alerts.error("Invalid phone number", null, "Error! Please enter a valid Canadian phone number [NNN-NNN-NNNN].").showAndWait();
    		
    	}
    	else if(!email.getText().matches("^(.+)@(.+)$")) {
    		Alerts.error("Invalid email", null, "Error! Please enter a valid email address.").showAndWait();
    		
    	}
    	else {
    		GuestCRUD.insertGuest(title.getText(), fname.getText(), lname.getText(), address.getText(), phone.getText(), email.getText());
    		BookingCRUD.setCustomerName(fname.getText() + " " +  lname.getText(), RoomController.getSelectedRoom());
    		Alerts.msg("Success", null, "Room and guest information successfully saved.").showAndWait();
    		nav.changePage(event, "/application/views/Bookings.fxml");
    		
    	}
    }

    @FXML
    void exitClick(ActionEvent event) throws IOException {
    	Optional<ButtonType> result = Alerts.msg("Exit", null, "Logout and exit the program? Unsaved room and guest information will be discarded.").showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    		BookingCRUD.removeBooking(RoomController.getSelectedRoom());
    		LoginController.closeSocket();
    		Platform.exit();
    		
    	}
    }

    @FXML
    void resetClick(ActionEvent event) { 
        title.clear();
        fname.clear();
        lname.clear();
        address.clear();
        phone.clear();
        email.clear();
        
    }
    
    public void initialize() {
    	Image im = new Image(getClass().getResourceAsStream("/images/abc_logo.jpg"));
    	logoCircle.setFill(new ImagePattern(im));
    	
    	userName.setText(LoginController.getCurrUser());
    }

}
