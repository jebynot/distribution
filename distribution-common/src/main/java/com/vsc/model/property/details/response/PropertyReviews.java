//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 08:07:16 PM IST 
//


package com.vsc.model.property.details.response;

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
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}propertyReview"/>
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
    "propertyReview"
})
@XmlRootElement(name = "propertyReviews")
public class PropertyReviews {

    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected PropertyReview propertyReview;

    /**
     * Gets the value of the propertyReview property.
     * 
     * @return
     *     possible object is
     *     {@link PropertyReview }
     *     
     */
    public PropertyReview getPropertyReview() {
        return propertyReview;
    }

    /**
     * Sets the value of the propertyReview property.
     * 
     * @param value
     *     allowed object is
     *     {@link PropertyReview }
     *     
     */
    public void setPropertyReview(PropertyReview value) {
        this.propertyReview = value;
    }

}
