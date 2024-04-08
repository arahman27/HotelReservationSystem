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

import application.models.Reservation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookingCRUD {
	public static boolean insertBooking(String cname, String type, int roomNum, int days) {
		try(Connection connection = BookingDatabase.connect()){
			int value = 0;
			PreparedStatement ps = null;
			String query = "Insert into Booking (Customer_name, Room_Type, Room_Num, Days) values (?, ?, ?, ?)";
			
			ps = connection.prepareStatement(query);
			ps.setString(1, cname);
			ps.setString(2, type);
			ps.setInt(3, roomNum);
			ps.setInt(4, days);
			value = ps.executeUpdate();
			
			if(value != 0) {
				return true;
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return false;
		
	}
	
	public static void setCustomerName(String cname, int roomNum) {
		try(Connection connection = BookingDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Update Booking Set Customer_Name='" + cname + "' where Room_Num = " + roomNum;
			
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void removeBooking(int roomNum) {
		try(Connection connection = BookingDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Delete from Booking where Room_Num = " + roomNum;
			
			ps = connection.prepareStatement(query);
			ps.executeUpdate();
			
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Reservation getBookingByID(int book_ID) {
		Reservation reservation = null;
		
		try(Connection connection = BookingDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Select * from Booking where Book_ID=" + book_ID;
			
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				reservation = new Reservation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5));
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return reservation;
		
	}
	
	public static ObservableList<Reservation> getBookings() {
		ObservableList<Reservation> bookingList = FXCollections.observableArrayList();
		
		try(Connection connection = BookingDatabase.connect()){
			PreparedStatement ps = null;
			String query = "Select * from Booking";
			
			ps = connection.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			bookingList.clear();
			
			while(rs.next()) {
				bookingList.add(new Reservation(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5)));
				
			}
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookingList;
		
	}
}
