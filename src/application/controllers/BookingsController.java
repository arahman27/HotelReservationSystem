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
import application.models.Reservation;
import application.utility.Alerts;
import application.utility.Navigation;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class BookingsController {

    @FXML
    private Button availableBtn;

    @FXML
    private Button billBtn;

    @FXML
    private Button bookBtn;

    @FXML
    private TableColumn<Reservation, Integer> bookingIDCol;

    @FXML
    private Label bookingsCount;

    @FXML
    private TableView<Reservation> bookingsTbl;

    @FXML
    private Button dashboardBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private Circle logoCircle;

    @FXML
    private TableColumn<Reservation, String> nameCol;

    @FXML
    private TableColumn<Reservation, Integer> numOfDaysCol;

    @FXML
    private TableColumn<Reservation, Integer> numOfRoomsCol;

    @FXML
    private TableColumn<Reservation, String> typeCol;

    @FXML
    private Label userName;
    
    private Navigation nav = new Navigation();

    private static ObservableList<Reservation> currBookings = FXCollections.observableArrayList();
    
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
    	
    	ObservableList<Reservation> tempList = BookingCRUD.getBookings();
    	currBookings.clear();
    	
    	for (Reservation c : tempList) {
    		currBookings.add(new Reservation(c.getBookingId().get(), c.getCustomerName().get(), c.getRoomType().get(), c.getRoomNum().get(), c.getDays().get()));
	    	bookingIDCol.setCellValueFactory(cellData -> cellData.getValue().getBookingId().asObject());
	    	nameCol.setCellValueFactory(cellData -> cellData.getValue().getCustomerName());
	    	typeCol.setCellValueFactory(cellData -> cellData.getValue().getRoomType());
	    	numOfRoomsCol.setCellValueFactory(cellData -> cellData.getValue().getRoomNum().asObject());
	    	numOfDaysCol.setCellValueFactory(cellData -> cellData.getValue().getDays().asObject());
    	
    	}
    	
    	bookingsTbl.setItems(currBookings);
    	bookingsCount.setText("" + currBookings.size());
    	
    }
    
    public static  ObservableList<Reservation> getBookings() {
    	return currBookings;
    	
    }
    
    public static int getNumOfBookings() {
    	return currBookings.size();
    	
    }
}
