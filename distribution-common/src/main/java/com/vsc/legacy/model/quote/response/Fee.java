//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.12.22 at 02:11:56 PM IST 
//


package com.vsc.legacy.model.quote.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="feeName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="feeAmount" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="applicability" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="feeTotal" use="required" type="{http://www.w3.org/2001/XMLSchema}float" />
 *       &lt;attribute name="mandatory" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="taxInclusive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="refundable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="displayOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "fee")
public class Fee {

    @XmlAttribute(name = "feeName", required = true)
    protected String feeName;
    @XmlAttribute(name = "feeAmount", required = true)
    protected float feeAmount;
    @XmlAttribute(name = "applicability", required = true)
    protected String applicability;
    @XmlAttribute(name = "feeTotal", required = true)
    protected float feeTotal;
    @XmlAttribute(name = "mandatory")
    protected Boolean mandatory;
    @XmlAttribute(name = "taxInclusive")
    protected Boolean taxInclusive;
    @XmlAttribute(name = "refundable")
    protected Boolean refundable;
    @XmlAttribute(name = "displayOnly")
    protected Boolean displayOnly;

    /**
     * Gets the value of the feeName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeName() {
        return feeName;
    }

    /**
     * Sets the value of the feeName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeName(String value) {
        this.feeName = value;
    }

    /**
     * Gets the value of the feeAmount property.
     * 
     */
    public float getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets the value of the feeAmount property.
     * 
     */
    public void setFeeAmount(float value) {
        this.feeAmount = value;
    }

    /**
     * Gets the value of the applicability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplicability() {
        return applicability;
    }

    /**
     * Sets the value of the applicability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplicability(String value) {
        this.applicability = value;
    }

    /**
     * Gets the value of the feeTotal property.
     * 
     */
    public float getFeeTotal() {
        return feeTotal;
    }

    /**
     * Sets the value of the feeTotal property.
     * 
     */
    public void setFeeTotal(float value) {
        this.feeTotal = value;
    }

    /**
     * Gets the value of the mandatory property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatory() {
        return mandatory;
    }

    /**
     * Sets the value of the mandatory property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatory(Boolean value) {
        this.mandatory = value;
    }

    /**
     * Gets the value of the taxInclusive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTaxInclusive() {
        return taxInclusive;
    }

    /**
     * Sets the value of the taxInclusive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTaxInclusive(Boolean value) {
        this.taxInclusive = value;
    }

    /**
     * Gets the value of the refundable property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRefundable() {
        return refundable;
    }

    /**
     * Sets the value of the refundable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRefundable(Boolean value) {
        this.refundable = value;
    }

    /**
     * Gets the value of the displayOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDisplayOnly() {
        return displayOnly;
    }

    /**
     * Sets the value of the displayOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDisplayOnly(Boolean value) {
        this.displayOnly = value;
    }

}
