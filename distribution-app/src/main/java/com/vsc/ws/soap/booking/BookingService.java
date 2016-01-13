package com.vsc.ws.soap.booking;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.vsc.model.Booking;

@WebService
public interface BookingService {

	@WebMethod
	public Booking getBooking(Long bookingID);
}
