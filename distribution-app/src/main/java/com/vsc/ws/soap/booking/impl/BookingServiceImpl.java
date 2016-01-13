package com.vsc.ws.soap.booking.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.vsc.model.Booking;
import com.vsc.ws.soap.booking.BookingService;

@WebService(endpointInterface="com.vsc.ws.soap.booking.BookingService", serviceName="BookingService")
public class BookingServiceImpl implements BookingService {

    @WebMethod
	public Booking getBooking(Long bookingID) {
		Booking booking = new Booking();
		booking.setBookingID(bookingID);
		booking.setBookingName("VSC");
		return booking;
	}
}
