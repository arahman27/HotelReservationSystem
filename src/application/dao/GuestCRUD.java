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
import java.sql.SQLException;

public class GuestCRUD {
	public static boolean insertGuest(String title, String fname, String lname, String addr, String phone, String email) {
		try(Connection connection = GuestDatabase.connect()){
			int value = 0;
			PreparedStatement ps = null;
			String query = "Insert into Guest (Title, First_name, Last_name, Address, Phone, Email) values (?, ?, ?, ? , ?, ?)";
			
			ps = connection.prepareStatement(query);
			ps.setString(1, title);
			ps.setString(2, fname);
			ps.setString(3, lname);
			ps.setString(4, addr);
			ps.setString(5, phone);
			ps.setString(6, email);
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
}
