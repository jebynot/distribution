//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.29 at 07:13:03 PM IST 
//


package com.vsc.model.reserve.cancel.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *           &lt;element ref="{http://ws.vacaystayconnect.com/cancel}cancellationDate"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/cancel}cancellationReason"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="resSource" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="resID" type="{http://www.w3.org/2001/XMLSchema}string" />
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
    "cancellationDate",
    "cancellationReason"
})
@XmlRootElement(name = "reservation")
public class Reservation {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cancellationDate;
    @XmlElement(required = true)
    protected CancellationReason cancellationReason;
    @XmlAttribute(name = "resSource")
    protected String resSource;
    @XmlAttribute(name = "resID")
    protected String resID;
    @XmlAttribute(name = "resStatus")
    protected String resStatus;

    /**
     * Gets the value of the cancellationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCancellationDate() {
        return cancellationDate;
    }

    /**
     * Sets the value of the cancellationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCancellationDate(XMLGregorianCalendar value) {
        this.cancellationDate = value;
    }

    /**
     * Gets the value of the cancellationReason property.
     * 
     * @return
     *     possible object is
     *     {@link CancellationReason }
     *     
     */
    public CancellationReason getCancellationReason() {
        return cancellationReason;
    }

    /**
     * Sets the value of the cancellationReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CancellationReason }
     *     
     */
    public void setCancellationReason(CancellationReason value) {
        this.cancellationReason = value;
    }

    /**
     * Gets the value of the resSource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResSource() {
        return resSource;
    }

    /**
     * Sets the value of the resSource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResSource(String value) {
        this.resSource = value;
    }

    /**
     * Gets the value of the resID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResID() {
        return resID;
    }

    /**
     * Sets the value of the resID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResID(String value) {
        this.resID = value;
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
