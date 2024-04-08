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

import application.dao.BillCRUD;
import application.dao.BookingCRUD;
import application.models.Reservation;
import application.utility.Alerts;
import application.utility.Navigation;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class BillServiceController {

    @FXML
    private Button availableBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private Button currBookingsBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Circle logoCircle;

    @FXML
    private TextField bookingID;
    
    @FXML
    private TextArea billInfoTA;
    
    @FXML
    private Slider discountSlider;
    
    @FXML
    private Label totalLbl;

    @FXML
    private Button saveBillBtn;

    @FXML
    private Label userName;
    
    private Navigation nav = new Navigation();

    private double originalTotal = 0.00;
    private double discountTotal = 0.00;
    private double discountAmt = 0.00;
    
    @FXML
    void availableClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/AvailableRooms.fxml");

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
    void dashboardClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/Menu.fxml");

    }

    @FXML
    void exitClick(ActionEvent event) throws IOException {
    	Optional<ButtonType> result = Alerts.msg("Exit", null, "Logout and exit the program?").showAndWait();
    	
    	if (result.get() == ButtonType.OK) {
    		LoginController.closeSocket();
    		Platform.exit();
    		
    	}
    }

    @FXML
    void saveBillClick(ActionEvent event) {
    	if(bookingID.getText().isEmpty() && originalTotal == 0.00 && discountTotal == 0.00) {
    		Alerts.error("Missing fields", null, "Please enter a booking ID to view information.").showAndWait();

    	}
    	else {
    		if(originalTotal != 0.00 && discountTotal != 0.00) {
    			BillCRUD.insertBill(Integer.parseInt(bookingID.getText()), discountTotal);
    			Alerts.msg("Success", null, "Bill information for booking ID: " + bookingID.getText() + " saved.").showAndWait();
    			
    		}
    		else {
    			BillCRUD.insertBill(Integer.parseInt(bookingID.getText()), originalTotal);
    			Alerts.msg("Success", null, "Bill information for booking ID: " + bookingID.getText() + " saved.").showAndWait();
    			
    		}
    	}
    }
    
    public void initialize() {
    	Image im = new Image(getClass().getResourceAsStream("/images/abc_logo.jpg"));
    	logoCircle.setFill(new ImagePattern(im));
    	
    	userName.setText(LoginController.getCurrUser());
    	
    	bookingID.textProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
    			try {
	    			if(!newVal.matches("\\d*")) {
	    				newVal = "";
	    				bookingID.setText(newVal);
	    				
	    			}
	    			else {
	    				Reservation reservation = BookingCRUD.getBookingByID(Integer.parseInt(bookingID.getText()));
	    				
	    				if(reservation != null) {
	    					billInfoTA.clear();
	    					double rate = 0.00;
	    					
	    		    		if(reservation.getRoomType().get().equals("Single")) {
	    		    			rate = 140.99;
	    		    			
	    		    		}
	    		    		else if(reservation.getRoomType().get().equals("Double")) {
	    		    			rate = 180.99;
	    		    			
	    		    		}
	    		    		else if(reservation.getRoomType().get().equals("Delux")) {
	    		    			rate = 200.99;
	    		    			
	    		    		}
	    		    		else if(reservation.getRoomType().get().equals("Pent")) {
	    		    			rate = 300.99;
	    		    			
	    		    		}
	    					
	    					billInfoTA.setText("Booking ID: " + reservation.getBookingId().get() + "\n" +
	    										"Guest name: " + reservation.getCustomerName().get() + "\n" +
	    										"Room number: " + reservation.getRoomNum().get() + "\n" +
	    										"Type of room: " + reservation.getRoomType().get() + "\n" +
	    										"Rate per night: $" + rate);
	    					
	    					originalTotal = rate * reservation.getDays().get();
	    					totalLbl.setText("$"+originalTotal);
	    					
	    				}
	    				else {
	    					billInfoTA.clear();
	    					billInfoTA.setText("Booking ID not found.");
	    					
	    				}
	    			}
    			}catch(Exception exp) {
    				
    			}
    		}
    	});
    	
    	discountSlider.valueProperty().addListener(new ChangeListener<Number>() {	
    		@Override
    		public void changed(ObservableValue<? extends Number> objectValue, Number oldValue, Number newValue){
    			if(originalTotal == 0.00) {
    				discountAmt = 0.00;
    				
    			}
    			else {
    				int percentage = newValue.intValue();
    				discountAmt = (originalTotal / 100) * percentage;
    				discountTotal = Math.round((originalTotal - discountAmt)*100.0)/100.0;
    				totalLbl.setText("$" + String.format("%2f", discountTotal));
    				
    			}   			
    		}
    	});
    	
    }
}
