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
 *         &lt;element name="activityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}ageGroup"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}descriptiveTexts"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}addresses"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}contactDetails"/>
 *         &lt;element name="hasAssociatedCost" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="activityCode" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isIndoor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="isOutdoor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="atFacility" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="nearby" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "activityName",
    "ageGroup",
    "descriptiveTexts",
    "addresses",
    "contactDetails",
    "hasAssociatedCost"
})
@XmlRootElement(name = "propertyActivity")
public class PropertyActivity {

    @XmlElement(required = true)
    protected String activityName;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected AgeGroup ageGroup;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected DescriptiveTexts descriptiveTexts;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected Addresses addresses;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected ContactDetails contactDetails;
    @XmlElement(required = true)
    protected String hasAssociatedCost;
    @XmlAttribute(name = "activityCode")
    protected String activityCode;
    @XmlAttribute(name = "isIndoor")
    protected String isIndoor;
    @XmlAttribute(name = "isOutdoor")
    protected String isOutdoor;
    @XmlAttribute(name = "atFacility")
    protected String atFacility;
    @XmlAttribute(name = "nearby")
    protected String nearby;

    /**
     * Gets the value of the activityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityName() {
        return activityName;
    }

    /**
     * Sets the value of the activityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityName(String value) {
        this.activityName = value;
    }

    /**
     * Gets the value of the ageGroup property.
     * 
     * @return
     *     possible object is
     *     {@link AgeGroup }
     *     
     */
    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    /**
     * Sets the value of the ageGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgeGroup }
     *     
     */
    public void setAgeGroup(AgeGroup value) {
        this.ageGroup = value;
    }

    /**
     * Gets the value of the descriptiveTexts property.
     * 
     * @return
     *     possible object is
     *     {@link DescriptiveTexts }
     *     
     */
    public DescriptiveTexts getDescriptiveTexts() {
        return descriptiveTexts;
    }

    /**
     * Sets the value of the descriptiveTexts property.
     * 
     * @param value
     *     allowed object is
     *     {@link DescriptiveTexts }
     *     
     */
    public void setDescriptiveTexts(DescriptiveTexts value) {
        this.descriptiveTexts = value;
    }

    /**
     * Gets the value of the addresses property.
     * 
     * @return
     *     possible object is
     *     {@link Addresses }
     *     
     */
    public Addresses getAddresses() {
        return addresses;
    }

    /**
     * Sets the value of the addresses property.
     * 
     * @param value
     *     allowed object is
     *     {@link Addresses }
     *     
     */
    public void setAddresses(Addresses value) {
        this.addresses = value;
    }

    /**
     * Gets the value of the contactDetails property.
     * 
     * @return
     *     possible object is
     *     {@link ContactDetails }
     *     
     */
    public ContactDetails getContactDetails() {
        return contactDetails;
    }

    /**
     * Sets the value of the contactDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContactDetails }
     *     
     */
    public void setContactDetails(ContactDetails value) {
        this.contactDetails = value;
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

    /**
     * Gets the value of the activityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * Sets the value of the activityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityCode(String value) {
        this.activityCode = value;
    }

    /**
     * Gets the value of the isIndoor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsIndoor() {
        return isIndoor;
    }

    /**
     * Sets the value of the isIndoor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsIndoor(String value) {
        this.isIndoor = value;
    }

    /**
     * Gets the value of the isOutdoor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsOutdoor() {
        return isOutdoor;
    }

    /**
     * Sets the value of the isOutdoor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsOutdoor(String value) {
        this.isOutdoor = value;
    }

    /**
     * Gets the value of the atFacility property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAtFacility() {
        return atFacility;
    }

    /**
     * Sets the value of the atFacility property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAtFacility(String value) {
        this.atFacility = value;
    }

    /**
     * Gets the value of the nearby property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNearby() {
        return nearby;
    }

    /**
     * Sets the value of the nearby property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNearby(String value) {
        this.nearby = value;
    }

}
