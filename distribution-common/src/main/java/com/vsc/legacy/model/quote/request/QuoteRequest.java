//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.22 at 02:16:34 PM IST 
//


package com.vsc.legacy.model.quote.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element ref="{http://ws.vacaystayconnect.com/quote}unitRateType"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/quote}stayDateRange"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/quote}guestCounts"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "unitRateType",
    "stayDateRange",
    "guestCounts"
})
@XmlRootElement(name = "quoteRequest")
public class QuoteRequest {

    @XmlElement(required = true)
    @Valid
    @NotNull(message = "{010}")
    protected UnitRateType unitRateType;
    @XmlElement(required = true)
    @Valid
    @NotNull(message = "{010}")
    protected StayDateRange stayDateRange;
    @XmlElement(required = true)
    @Valid
    @NotNull(message = "{010}")
    protected GuestCounts guestCounts;

    /**
     * Gets the value of the unitRateType property.
     * 
     * @return
     *     possible object is
     *     {@link UnitRateType }
     *     
     */
    public UnitRateType getUnitRateType() {
        return unitRateType;
    }

    /**
     * Sets the value of the unitRateType property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitRateType }
     *     
     */
    public void setUnitRateType(UnitRateType value) {
        this.unitRateType = value;
    }

    /**
     * Gets the value of the stayDateRange property.
     * 
     * @return
     *     possible object is
     *     {@link StayDateRange }
     *     
     */
    public StayDateRange getStayDateRange() {
        return stayDateRange;
    }

    /**
     * Sets the value of the stayDateRange property.
     * 
     * @param value
     *     allowed object is
     *     {@link StayDateRange }
     *     
     */
    public void setStayDateRange(StayDateRange value) {
        this.stayDateRange = value;
    }

    /**
     * Gets the value of the guestCounts property.
     * 
     * @return
     *     possible object is
     *     {@link GuestCounts }
     *     
     */
    public GuestCounts getGuestCounts() {
        return guestCounts;
    }

    /**
     * Sets the value of the guestCounts property.
     * 
     * @param value
     *     allowed object is
     *     {@link GuestCounts }
     *     
     */
    public void setGuestCounts(GuestCounts value) {
        this.guestCounts = value;
    }

}
