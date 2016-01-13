//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.26 at 08:07:16 PM IST 
//


package com.vsc.model.property.details.response;

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
 *         &lt;element name="reviewerName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewerEmailAddress" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}stayDetails"/>
 *         &lt;element name="reviewContent" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewerCity" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewerState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reviewerCountry" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemsMostLiked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="itemsLeastLiked" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="personalTips" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="reasonAccommSelected" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="purposeOfTrip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="travelledWith" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}starRatings"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}recommendTo"/>
 *         &lt;element ref="{http://ws.vacaystayconnect.com/propdetails}mgmtFollowup"/>
 *       &lt;/sequence>
 *       &lt;attribute name="language" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reviewType" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="reviexsource" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "reviewerName",
    "reviewerEmailAddress",
    "stayDetails",
    "reviewContent",
    "reviewerCity",
    "reviewerState",
    "reviewerCountry",
    "itemsMostLiked",
    "itemsLeastLiked",
    "personalTips",
    "reasonAccommSelected",
    "purposeOfTrip",
    "travelledWith",
    "starRatings",
    "recommendTo",
    "mgmtFollowup"
})
@XmlRootElement(name = "propertyReview")
public class PropertyReview {

    @XmlElement(required = true)
    protected String reviewerName;
    @XmlElement(required = true)
    protected String reviewerEmailAddress;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected StayDetails stayDetails;
    @XmlElement(required = true)
    protected String reviewContent;
    @XmlElement(required = true)
    protected String reviewerCity;
    @XmlElement(required = true)
    protected String reviewerState;
    @XmlElement(required = true)
    protected String reviewerCountry;
    @XmlElement(required = true)
    protected String itemsMostLiked;
    @XmlElement(required = true)
    protected String itemsLeastLiked;
    @XmlElement(required = true)
    protected String personalTips;
    @XmlElement(required = true)
    protected String reasonAccommSelected;
    @XmlElement(required = true)
    protected String purposeOfTrip;
    @XmlElement(required = true)
    protected String travelledWith;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected StarRatings starRatings;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected RecommendTo recommendTo;
    @XmlElement(namespace = "http://ws.vacaystayconnect.com/propdetails", required = true)
    protected MgmtFollowup mgmtFollowup;
    @XmlAttribute(name = "language")
    protected String language;
    @XmlAttribute(name = "reviewType")
    protected String reviewType;
    @XmlAttribute(name = "reviexsource")
    protected String reviexsource;

    /**
     * Gets the value of the reviewerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewerName() {
        return reviewerName;
    }

    /**
     * Sets the value of the reviewerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewerName(String value) {
        this.reviewerName = value;
    }

    /**
     * Gets the value of the reviewerEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewerEmailAddress() {
        return reviewerEmailAddress;
    }

    /**
     * Sets the value of the reviewerEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewerEmailAddress(String value) {
        this.reviewerEmailAddress = value;
    }

    /**
     * Gets the value of the stayDetails property.
     * 
     * @return
     *     possible object is
     *     {@link StayDetails }
     *     
     */
    public StayDetails getStayDetails() {
        return stayDetails;
    }

    /**
     * Sets the value of the stayDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link StayDetails }
     *     
     */
    public void setStayDetails(StayDetails value) {
        this.stayDetails = value;
    }

    /**
     * Gets the value of the reviewContent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewContent() {
        return reviewContent;
    }

    /**
     * Sets the value of the reviewContent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewContent(String value) {
        this.reviewContent = value;
    }

    /**
     * Gets the value of the reviewerCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewerCity() {
        return reviewerCity;
    }

    /**
     * Sets the value of the reviewerCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewerCity(String value) {
        this.reviewerCity = value;
    }

    /**
     * Gets the value of the reviewerState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewerState() {
        return reviewerState;
    }

    /**
     * Sets the value of the reviewerState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewerState(String value) {
        this.reviewerState = value;
    }

    /**
     * Gets the value of the reviewerCountry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewerCountry() {
        return reviewerCountry;
    }

    /**
     * Sets the value of the reviewerCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewerCountry(String value) {
        this.reviewerCountry = value;
    }

    /**
     * Gets the value of the itemsMostLiked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemsMostLiked() {
        return itemsMostLiked;
    }

    /**
     * Sets the value of the itemsMostLiked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemsMostLiked(String value) {
        this.itemsMostLiked = value;
    }

    /**
     * Gets the value of the itemsLeastLiked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getItemsLeastLiked() {
        return itemsLeastLiked;
    }

    /**
     * Sets the value of the itemsLeastLiked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setItemsLeastLiked(String value) {
        this.itemsLeastLiked = value;
    }

    /**
     * Gets the value of the personalTips property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonalTips() {
        return personalTips;
    }

    /**
     * Sets the value of the personalTips property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonalTips(String value) {
        this.personalTips = value;
    }

    /**
     * Gets the value of the reasonAccommSelected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonAccommSelected() {
        return reasonAccommSelected;
    }

    /**
     * Sets the value of the reasonAccommSelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonAccommSelected(String value) {
        this.reasonAccommSelected = value;
    }

    /**
     * Gets the value of the purposeOfTrip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurposeOfTrip() {
        return purposeOfTrip;
    }

    /**
     * Sets the value of the purposeOfTrip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurposeOfTrip(String value) {
        this.purposeOfTrip = value;
    }

    /**
     * Gets the value of the travelledWith property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTravelledWith() {
        return travelledWith;
    }

    /**
     * Sets the value of the travelledWith property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTravelledWith(String value) {
        this.travelledWith = value;
    }

    /**
     * Gets the value of the starRatings property.
     * 
     * @return
     *     possible object is
     *     {@link StarRatings }
     *     
     */
    public StarRatings getStarRatings() {
        return starRatings;
    }

    /**
     * Sets the value of the starRatings property.
     * 
     * @param value
     *     allowed object is
     *     {@link StarRatings }
     *     
     */
    public void setStarRatings(StarRatings value) {
        this.starRatings = value;
    }

    /**
     * Gets the value of the recommendTo property.
     * 
     * @return
     *     possible object is
     *     {@link RecommendTo }
     *     
     */
    public RecommendTo getRecommendTo() {
        return recommendTo;
    }

    /**
     * Sets the value of the recommendTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecommendTo }
     *     
     */
    public void setRecommendTo(RecommendTo value) {
        this.recommendTo = value;
    }

    /**
     * Gets the value of the mgmtFollowup property.
     * 
     * @return
     *     possible object is
     *     {@link MgmtFollowup }
     *     
     */
    public MgmtFollowup getMgmtFollowup() {
        return mgmtFollowup;
    }

    /**
     * Sets the value of the mgmtFollowup property.
     * 
     * @param value
     *     allowed object is
     *     {@link MgmtFollowup }
     *     
     */
    public void setMgmtFollowup(MgmtFollowup value) {
        this.mgmtFollowup = value;
    }

    /**
     * Gets the value of the language property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Sets the value of the language property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguage(String value) {
        this.language = value;
    }

    /**
     * Gets the value of the reviewType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviewType() {
        return reviewType;
    }

    /**
     * Sets the value of the reviewType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviewType(String value) {
        this.reviewType = value;
    }

    /**
     * Gets the value of the reviexsource property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReviexsource() {
        return reviexsource;
    }

    /**
     * Sets the value of the reviexsource property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReviexsource(String value) {
        this.reviexsource = value;
    }

}
