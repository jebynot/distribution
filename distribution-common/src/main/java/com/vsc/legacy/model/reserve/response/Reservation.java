//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.29 at 01:02:25 PM IST 
//


package com.vsc.legacy.model.reserve.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;sequence>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/reserve}reservationIDs"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="resStatus" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"resStatus",
    "reservationIDs"
})
@XmlRootElement(name = "reservation")
public class Reservation {

    @XmlElement(required = true)
    protected ReservationIDs reservationIDs;
    @XmlAttribute(name = "resStatus")
    protected String resStatus;

    /**
     * Gets the value of the reservationIDs property.
     * 
     * @return
     *     possible object is
     *     {@link ReservationIDs }
     *     
     */
    public ReservationIDs getReservationIDs() {
        return reservationIDs;
    }

    /**
     * Sets the value of the reservationIDs property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReservationIDs }
     *     
     */
    public void setReservationIDs(ReservationIDs value) {
        this.reservationIDs = value;
    }

    /**
     * Gets the value of the resStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResStatus() {
        return resStatus;
    }

    /**
     * Sets the value of the resStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResStatus(String value) {
        this.resStatus = value;
    }

}
