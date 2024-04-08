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

import application.dao.BillDatabase;
import application.dao.BookingDatabase;
import application.dao.GuestDatabase;
import application.dao.RoomDatabase;
import application.utility.Alerts;
import application.utility.Navigation;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class MenuController {

    @FXML
    private Button availableBtn;

    @FXML
    private Button billBtn;
    
    @FXML
    private Label billsCheck;

    @FXML
    private Button bookBtn;
    
    @FXML
    private Label bookingCheck;
    
    @FXML
    private Label bookingsCount;

    @FXML
    private Button currBookingsBtn;

    @FXML
    private Button exitBtn;
    
    @FXML
    private Label guestCheck;

    @FXML
    private Circle logoCircle;
    
    @FXML
    private Label roomCheck;

    @FXML
    private Label userName;
    
    private Navigation nav = new Navigation();

    @FXML
    void availableClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/AvailableRooms.fxml");

    }

    @FXML
    void billClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/BillService.fxml");

    }

    @FXML
    void bookClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/Room.fxml");

    }

    @FXML
    void currBookingsClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/Bookings.fxml");

    }

    @FXML
    void exitClick(ActionEvent event) throws IOException {
    	Optional<ButtonType> result = Alerts.msg("Exit", null, "Logout and exit the program?").showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    		LoginController.closeSocket();
    		Platform.exit();
    		
    	}
    }
    
    public void initialize() {
    	Image im = new Image(getClass().getResourceAsStream("/images/abc_logo.jpg"));
    	logoCircle.setFill(new ImagePattern(im));
    	
    	userName.setText(LoginController.getCurrUser());
    	
    	databaseChecks();
    	bookingsCount.setText("" + BookingsController.getNumOfBookings());
    	
    }
    
    public void databaseChecks() {
    	if(RoomDatabase.isOk()) {
    		roomCheck.setText("OK");
    		roomCheck.setTextFill(Color.GREEN);
    		
    	}
    	else {
    		roomCheck.setText("DOWN");
    		roomCheck.setTextFill(Color.RED);
    	}
    	
    	if(GuestDatabase.isOk()) {
    		guestCheck.setText("OK");
    		guestCheck.setTextFill(Color.GREEN);
    		
    	}
    	else {
    		guestCheck.setText("DOWN");
    		guestCheck.setTextFill(Color.RED);
    	}
    	
    	if(BillDatabase.isOk()) {
    		billsCheck.setText("OK");
    		billsCheck.setTextFill(Color.GREEN);
    		
    	}
    	else {
    		billsCheck.setText("DOWN");
    		billsCheck.setTextFill(Color.RED);
    	}
    	
    	if(BookingDatabase.isOk()) {
    		bookingCheck.setText("OK");
    		bookingCheck.setTextFill(Color.GREEN);
    		
    	}
    	else {
    		bookingCheck.setText("DOWN");
    		bookingCheck.setTextFill(Color.RED);
    		
    	}
    }
}
