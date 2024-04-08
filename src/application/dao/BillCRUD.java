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

public class BillCRUD {
	public static boolean insertBill(int booking_num, Double amt) {
		try(Connection connection = BillDatabase.connect()){
			int value = 0;
			PreparedStatement ps = null;
			String query = "Insert into Bill (Amount, Booking_Num) values (?, ?)";
			
			ps = connection.prepareStatement(query);
			ps.setDouble(1, amt);
			ps.setInt(2, booking_num);
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
