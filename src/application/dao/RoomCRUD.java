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

package application.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import application.models.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomCRUD {
	public static ObservableList<Room> getAvailableRooms() {
		ObservableList<Room> availableRoomList = FXCollections.observableArrayList();
		
		try(Connection connection = RoomDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Select * from Room";
			
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(4).equals("Available")){
					availableRoomList.add(new Room(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getString(4)));
				
				}
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return availableRoomList;
		
	}
	
	public static ObservableList<Integer> getRoomsByType(String type){
		ObservableList<Integer> roomList = FXCollections.observableArrayList();
		
		try(Connection connection = RoomDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Select * from Room where Room_Type = '" + type + "' and Availability='Available'";
			
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				roomList.add(rs.getInt(1));
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return roomList;
		
	}
	
	public static void changeAvailability(int roomId, String availability) {
		try(Connection connection = RoomDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Update Room Set Availability='" + availability + "' where Room_ID = " + roomId;
			
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
