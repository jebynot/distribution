//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.10.27 at 10:52:52 AM IST 
//


package com.vsc.model.search.request;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.validator.constraints.NotEmpty;


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
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}pos"/>
 *           &lt;element ref="{http://ws.vacaystayconnect.com/search}searchRequest"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="timeStamp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "pos",
    "searchRequest"
})
@XmlRootElement(name = "VSC_SearchRQ")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VSCSearchRQ {

    @XmlElement(required = true)
    @NotNull(message = "{009}")
	@Valid
    protected Pos pos;
    @XmlElement(required = true)
    @Valid
	@NotNull(message = "{009}")
    protected SearchRequest searchRequest;
    @XmlAttribute(name = "timeStamp")
    @NotEmpty
    protected String timeStamp;

    /**
     * Gets the value of the pos property.
     * 
     * @return
     *     possible object is
     *     {@link Pos }
     *     
     */
    public Pos getPos() {
        return pos;
    }

    /**
     * Sets the value of the pos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Pos }
     *     
     */
    public void setPos(Pos value) {
        this.pos = value;
    }

    /**
     * Gets the value of the searchRequest property.
     * 
     * @return
     *     possible object is
     *     {@link SearchRequest }
     *     
     */
    public SearchRequest getSearchRequest() {
        return searchRequest;
    }

    /**
     * Sets the value of the searchRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link SearchRequest }
     *     
     */
    public void setSearchRequest(SearchRequest value) {
        this.searchRequest = value;
    }

    /**
     * Gets the value of the timeStamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the value of the timeStamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTimeStamp(String value) {
        this.timeStamp = value;
    }

}
