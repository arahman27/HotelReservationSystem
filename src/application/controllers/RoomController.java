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
import application.dao.RoomCRUD;
import application.utility.Alerts;
import application.utility.Navigation;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class RoomController {

    @FXML
    private Button availableBtn;

    @FXML
    private ComboBox<Integer> availableRooms;

    @FXML
    private Button billBtn;

    @FXML
    private Button confirmRoomBtn;

    @FXML
    private Button currBookingsBtn;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Circle logoCircle;

    @FXML
    private TextField numOfDays;

    @FXML
    private TextField numOfGuests;

    @FXML
    private TextField roomRate;

    @FXML
    private ComboBox<String> roomType;

    @FXML
    private Label userName;
    
    private Navigation nav = new Navigation();
    
    private ObservableList<Integer> roomList = FXCollections.observableArrayList();
    private ObservableList<String> typeList = FXCollections.observableArrayList();
    private String selectedType = "";
    private static int selectedRoom = 0;
    private int days = 0;
    
    @FXML
    void availableClick(ActionEvent event) throws IOException {
    	nav.changePage(event, "/application/views/AvailableRooms.fxml");

    }

    @FXML
    void billClick(ActionEvent event) {
    	nav.changePage(event, "/application/views/BillService.fxml");

    }

    @FXML
    void confirmRoomClick(ActionEvent event) {
    	if(numOfDays.getText().isEmpty() || numOfGuests.getText().isEmpty() || selectedType.equals("") || selectedRoom == 0) {
    		Alerts.error("Missing information", null, "Error! Please enter all information.").showAndWait();
    		
    	}
    	else {
    		RoomCRUD.changeAvailability(selectedRoom, "Unavailable");
    		BookingCRUD.insertBooking("", selectedType, selectedRoom, days);
    		nav.changePage(event, "/application/views/GuestInfo.fxml");
    		
    	}
    }
    
    public static int getSelectedRoom() {
    	return selectedRoom;
    	
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

    public void initialize() {
    	Image im = new Image(getClass().getResourceAsStream("/images/abc_logo.jpg"));
    	logoCircle.setFill(new ImagePattern(im));
    	
    	userName.setText(LoginController.getCurrUser());
    	
    	numOfGuests.textProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
    			if(!newVal.matches("\\d*")) {
    				numOfGuests.setText(newVal.replaceAll("[^\\d]", ""));
    			}
    			else {
    				try {
	    				if(Integer.parseInt(numOfGuests.getText()) <= 2 ) {
	    					typeList.clear();
	    					roomList.clear();
	    					availableRooms.setItems(roomList);
	    					roomRate.clear();
	    					typeList.add("Single");
	    					roomType.setItems(typeList);
	    					
	    				}
	    				else if(Integer.parseInt(numOfGuests.getText()) > 2 && Integer.parseInt(numOfGuests.getText()) <= 4) {
	    					typeList.clear();
	    					roomList.clear();
	    					availableRooms.setItems(roomList);
	    					roomRate.clear();
	    					typeList.add("Single");
	    					typeList.add("Double");
	    					roomType.setItems(typeList);
	    					
	    				}
	    				else if(Integer.parseInt(numOfGuests.getText()) > 4) {
	    					typeList.clear();
	    					roomList.clear();
	    					availableRooms.setItems(roomList);
	    					roomRate.clear();
	    					typeList.add("Delux");
	    					typeList.add("Pent");
	    					roomType.setItems(typeList);
	    					
	    				}
	    				else {
	    					typeList.clear();
	    					roomList.clear();
	    					availableRooms.setItems(roomList);
	    					roomRate.clear();
	    					numOfGuests.setText("");
	    					
	    				}
    				}catch(Exception exp) {
    					
    				}
    			}
    		}
    	});
    	
    	numOfDays.textProperty().addListener(new ChangeListener<String>() {
    		@Override
    		public void changed(ObservableValue<? extends String> observable, String oldVal, String newVal) {
    			try {
	    			if(!newVal.matches("\\d*")) {
	    				newVal = "";
	    				numOfDays.setText(newVal);
	    				
	    			}
	    			else {
	    				days = Integer.parseInt(numOfDays.getText());
	    				
	    			}
    			}catch(Exception exp) {
    				
    			}
    		}
    	});
    	
    	roomType.setOnAction(e -> {
    		if(roomType.getSelectionModel().getSelectedItem() != null) {
	    		selectedType = roomType.getSelectionModel().getSelectedItem();
	    		roomList = RoomCRUD.getRoomsByType(selectedType);
	    		
	    		if(selectedType.equals("Single")) {
	    			roomRate.setText("$140.99");
	    			
	    		}
	    		else if(selectedType.equals("Double")) {
	    			roomRate.setText("$180.99");
	    			
	    		}
	    		else if(selectedType.equals("Delux")) {
	    			roomRate.setText("$200.99");
	    			
	    		}
	    		else if(selectedType.equals("Pent")) {
	    			roomRate.setText("$300.99");
	    			
	    		}
	    		else {
	    			roomRate.clear();
	    			
	    		}
	    		
	    		availableRooms.setItems(roomList);
    		}
    	});
    	
    	availableRooms.setOnAction(e -> {
    		if(availableRooms.getSelectionModel().getSelectedItem() != null) {
    			selectedRoom = availableRooms.getSelectionModel().getSelectedItem();
    		
    		}
    	});
    }
}
