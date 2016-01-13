//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.07 at 05:30:39 PM IST 
//


package com.vsc.model.reserve.response;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vibes.neo.model.reserve.response package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReservationComment_QNAME = new QName("http://ws.vacaystayconnect.com/reserve", "reservationComment");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vibes.neo.model.reserve.response
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnitRateType }
     * 
     */
    public UnitRateType createUnitRateType() {
        return new UnitRateType();
    }

    /**
     * Create an instance of {@link Unit }
     * 
     */
    public Unit createUnit() {
        return new Unit();
    }

    /**
     * Create an instance of {@link StayDateRange }
     * 
     */
    public StayDateRange createStayDateRange() {
        return new StayDateRange();
    }

    /**
     * Create an instance of {@link GuestCounts }
     * 
     */
    public GuestCounts createGuestCounts() {
        return new GuestCounts();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

    /**
     * Create an instance of {@link VSCReserveRS }
     * 
     */
    public VSCReserveRS createVSCReserveRS() {
        return new VSCReserveRS();
    }

    /**
     * Create an instance of {@link Success }
     * 
     */
    public Success createSuccess() {
        return new Success();
    }

    /**
     * Create an instance of {@link Warnings }
     * 
     */
    public Warnings createWarnings() {
        return new Warnings();
    }

    /**
     * Create an instance of {@link Warning }
     * 
     */
    public Warning createWarning() {
        return new Warning();
    }

    /**
     * Create an instance of {@link Errors }
     * 
     */
    public Errors createErrors() {
        return new Errors();
    }

    /**
     * Create an instance of {@link Reservations }
     * 
     */
    public Reservations createReservations() {
        return new Reservations();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link ReservationIDs }
     * 
     */
    public ReservationIDs createReservationIDs() {
        return new ReservationIDs();
    }

    /**
     * Create an instance of {@link ReservationID }
     * 
     */
    public ReservationID createReservationID() {
        return new ReservationID();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.vacaystayconnect.com/reserve", name = "reservationComment")
    public JAXBElement<String> createReservationComment(String value) {
        return new JAXBElement<String>(_ReservationComment_QNAME, String.class, null, value);
    }

}
