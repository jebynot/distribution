//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 08:07:16 PM IST 
//


package com.vsc.legacy.model.property.details.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="amenityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amenityName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="amenityType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="hasAssociatedCost" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "propertyAmenity")
public class PropertyAmenity {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "amenityCode")
    protected String amenityCode;
    @XmlAttribute(name = "amenityName")
    protected String amenityName;
    @XmlAttribute(name = "amenityType")
    protected String amenityType;
    @XmlAttribute(name = "hasAssociatedCost")
    protected String hasAssociatedCost;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the amenityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityCode() {
        return amenityCode;
    }

    /**
     * Sets the value of the amenityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityCode(String value) {
        this.amenityCode = value;
    }

    /**
     * Gets the value of the amenityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityName() {
        return amenityName;
    }

    /**
     * Sets the value of the amenityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityName(String value) {
        this.amenityName = value;
    }

    /**
     * Gets the value of the amenityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmenityType() {
        return amenityType;
    }

    /**
     * Sets the value of the amenityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmenityType(String value) {
        this.amenityType = value;
    }

    /**
     * Gets the value of the hasAssociatedCost property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHasAssociatedCost() {
        return hasAssociatedCost;
    }

    /**
     * Sets the value of the hasAssociatedCost property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHasAssociatedCost(String value) {
        this.hasAssociatedCost = value;
    }

}
