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
 *         &lt;element name="followupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="followupNotes" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "followupDate",
    "followupNotes"
})
@XmlRootElement(name = "mgmtFollowup")
public class MgmtFollowup {

    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar followupDate;
    @XmlElement(required = true)
    protected String followupNotes;

    /**
     * Gets the value of the followupDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFollowupDate() {
        return followupDate;
    }

    /**
     * Sets the value of the followupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFollowupDate(XMLGregorianCalendar value) {
        this.followupDate = value;
    }

    /**
     * Gets the value of the followupNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFollowupNotes() {
        return followupNotes;
    }

    /**
     * Sets the value of the followupNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFollowupNotes(String value) {
        this.followupNotes = value;
    }

}
