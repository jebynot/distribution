//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.30 at 11:13:03 AM IST 
//


package com.vsc.legacy.model.search.response;

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
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}propertyChainCode"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}propertyManager"/>
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
    "propertyChainCode",
    "propertyManager"
})
@XmlRootElement(name = "propertyOwnership")
public class PropertyOwnership {

    @XmlElement(required = true)
    protected String propertyChainCode;
    @XmlElement(required = true)
    protected String propertyManager;

    /**
     * Gets the value of the propertyChainCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyChainCode() {
        return propertyChainCode;
    }

    /**
     * Sets the value of the propertyChainCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyChainCode(String value) {
        this.propertyChainCode = value;
    }

    /**
     * Gets the value of the propertyManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyManager() {
        return propertyManager;
    }

    /**
     * Sets the value of the propertyManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyManager(String value) {
        this.propertyManager = value;
    }

}