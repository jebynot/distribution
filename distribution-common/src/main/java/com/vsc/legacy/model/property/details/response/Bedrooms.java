//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 08:07:16 PM IST 
//


package com.vsc.legacy.model.property.details.response;

import java.math.BigInteger;
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
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}bedroom"/>
 *       &lt;/sequence>
 *       &lt;attribute name="totalCount" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "bedroom"
})
@XmlRootElement(name = "bedrooms")
public class Bedrooms {

    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected Bedroom bedroom;
    @XmlAttribute(name = "totalCount")
    protected BigInteger totalCount;

    /**
     * Gets the value of the bedroom property.
     * 
     * @return
     *     possible object is
     *     {@link Bedroom }
     *     
     */
    public Bedroom getBedroom() {
        return bedroom;
    }

    /**
     * Sets the value of the bedroom property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bedroom }
     *     
     */
    public void setBedroom(Bedroom value) {
        this.bedroom = value;
    }

    /**
     * Gets the value of the totalCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalCount(BigInteger value) {
        this.totalCount = value;
    }

}