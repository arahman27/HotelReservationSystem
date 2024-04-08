module HotelReservationSystem {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires org.xerial.sqlitejdbc;
	requires javafx.base;
	requires java.desktop;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.controllers to javafx.fxml;
	exports application.controllers to javafx.fxml;
}
