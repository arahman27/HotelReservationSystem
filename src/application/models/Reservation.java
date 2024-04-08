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

package application.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {
	private IntegerProperty bookingId;
	private StringProperty customerName;
	private StringProperty roomType;
	private IntegerProperty roomNum;
	private IntegerProperty days;
	
	public Reservation(int bookingId, String cname, String type, int roomNum, int days) {
		this.bookingId = new SimpleIntegerProperty(bookingId);
		this.customerName = new SimpleStringProperty(cname);
		this.roomType = new SimpleStringProperty(type);
		this.roomNum = new SimpleIntegerProperty(roomNum);
		this.days = new SimpleIntegerProperty(days);
		
	}
	
	public void assingCustomer(String cname) {
		this.customerName = new SimpleStringProperty(cname);
		
	}

	public IntegerProperty getBookingId() {
		return bookingId;
	}

	public StringProperty getCustomerName() {
		return customerName;
	}

	public StringProperty getRoomType() {
		return roomType;
	}

	public IntegerProperty getRoomNum() {
		return roomNum;
	}

	public IntegerProperty getDays() {
		return days;
	}	
}
