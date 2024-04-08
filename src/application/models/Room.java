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

public class Room {
	private final IntegerProperty roomId;
	private final StringProperty roomType;
	private Double rate;
	private String availability;
	
	public Room(int id, String type, Double rate, String availability) {
		this.roomId = new SimpleIntegerProperty(id);
		this.roomType = new SimpleStringProperty(type);
		this.rate = rate;
		this.availability = availability;
		
	}
	
	
	public final IntegerProperty getRoomIdProperty() {
		return this.roomId;
		
	}
	
	public final StringProperty getRoomTypeProperty() {
		return this.roomType;
		
	}

	public int getRoomId() {
		return roomId.get();
		
	}

	public String getRoomType() {
		return roomType.get();
		
	}

	public Double getRate() {
		return rate;
		
	}

	public String getAvailability() {
		return availability;
		
	}
}
