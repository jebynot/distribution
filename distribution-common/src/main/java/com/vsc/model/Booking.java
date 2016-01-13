package com.vsc.model;

import java.io.Serializable;

public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long bookingID;
	private String bookingName;
	
	public Long getBookingID() {
		return bookingID;
	}
	public void setBookingID(Long bookingID) {
		this.bookingID = bookingID;
	}
	public String getBookingName() {
		return bookingName;
	}
	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
	
}
