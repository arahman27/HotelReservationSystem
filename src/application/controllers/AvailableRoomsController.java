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

import application.dao.RoomCRUD;
import application.models.Room;
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

public class AvailableRoomsController {

    @FXML
    private Button billBtn;

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
    private TableColumn<Room, Integer> roomIDCol;

    @FXML
    private TableColumn<Room, String> roomTypeCol;

    @FXML
    private Label roomsCount;

    @FXML
    private TableView<Room> roomsTbl;

    @FXML
    private Label userName;
    
    private Navigation nav = new Navigation();
    private static ObservableList<Room> availableRoomList = FXCollections.observableArrayList();

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
    	
    	ObservableList<Room> tempList = RoomCRUD.getAvailableRooms();
    	availableRoomList.clear();
    	
    	for (Room r : tempList) {
    		availableRoomList.add(new Room(r.getRoomId(), r.getRoomType(), r.getRate(), r.getAvailability()));
	    	roomIDCol.setCellValueFactory(cellData -> cellData.getValue().getRoomIdProperty().asObject());
	    	roomTypeCol.setCellValueFactory(cellData -> cellData.getValue().getRoomTypeProperty());
    	
    	}
    	
    	roomsTbl.setItems(availableRoomList);
    	roomsCount.setText("" + availableRoomList.size());
    }
    
    public static ObservableList<Room> getAvailableRoomList() {
    	return availableRoomList;
    	
    }
}
