//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.05 at 02:19:16 PM IST 
//


package com.vsc.legacy.model.availability.response;

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
 *         &lt;element ref="{http://ws.vacaystayconnect.com/avail}units"/>
 *       &lt;/sequence>
 *       &lt;attribute name="rateTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="rateTypeName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	"rateTypeCode",
	"rateTypeName",
    "units"
})
@XmlRootElement(name = "unitRateType")
public class UnitRateType {

    @XmlElement(required = true)
    protected Units units;
    @XmlAttribute(name = "rateTypeCode")
    protected String rateTypeCode;
    @XmlAttribute(name = "rateTypeName")
    protected String rateTypeName;

    /**
     * Gets the value of the units property.
     * 
     * @return
     *     possible object is
     *     {@link Units }
     *     
     */
    public Units getUnits() {
        return units;
    }

    /**
     * Sets the value of the units property.
     * 
     * @param value
     *     allowed object is
     *     {@link Units }
     *     
     */
    public void setUnits(Units value) {
        this.units = value;
    }

    /**
     * Gets the value of the rateTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateTypeCode() {
        return rateTypeCode;
    }

    /**
     * Sets the value of the rateTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateTypeCode(String value) {
        this.rateTypeCode = value;
    }

    /**
     * Gets the value of the rateTypeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateTypeName() {
        return rateTypeName;
    }

    /**
     * Sets the value of the rateTypeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateTypeName(String value) {
        this.rateTypeName = value;
    }

}