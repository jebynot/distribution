//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.27 at 10:52:52 AM IST 
//


package com.vsc.legacy.model.search.request;

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
 *         &lt;sequence>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}location"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}properties"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}chainCode"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}stayDateRange"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}guestCounts"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}pets"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}detailsInclusive"/>
 *         &lt;/sequence>
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
    "location",
    "properties",
    "chainCode",
    "stayDateRange",
    "guestCounts",
    "pets",
    "detailsInclusive"
})
@XmlRootElement(name = "searchRequest")
public class SearchRequest {

    @XmlElement(required = true)
	@Valid
    protected Location location;
    @XmlElement(required = true)
    protected Properties properties;
    @XmlElement(required = true)
    protected String chainCode;
    @XmlElement(required = true)
    @NotNull(message = "{009}")
	@Valid
    protected StayDateRange stayDateRange;
    @XmlElement(required = true)
    @NotNull(message = "{009}")
	@Valid
    protected GuestCounts guestCounts;
    @XmlElement(required = true)
    @NotNull(message = "{009}")
    @Valid
    protected Pets pets;
    @NotNull(message = "{009}")
    protected String detailsInclusive;

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the properties property.
     * 
     * @return
     *     possible object is
     *     {@link Properties }
     *     
     */
    public Properties getProperties() {
        return properties;
    }

    /**
     * Sets the value of the properties property.
     * 
     * @param value
     *     allowed object is
     *     {@link Properties }
     *     
     */
    public void setProperties(Properties value) {
        this.properties = value;
    }

    /**
     * Gets the value of the chainCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChainCode() {
        return chainCode;
    }

    /**
     * Sets the value of the chainCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChainCode(String value) {
        this.chainCode = value;
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

    /**
     * Gets the value of the pets property.
     * 
     * @return
     *     possible object is
     *     {@link Pets }
     *     
     */
    public Pets getPets() {
        return pets;
    }

    /**
     * Sets the value of the pets property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pets }
     *     
     */
    public void setPets(Pets value) {
        this.pets = value;
    }

    /**
     * Gets the value of the detailsInclusive property.
     * 
     */
    public String isDetailsInclusive() {
        return detailsInclusive;
    }

    /**
     * Sets the value of the detailsInclusive property.
     * 
     */
    public void setDetailsInclusive(String value) {
        this.detailsInclusive = value;
    }

}
