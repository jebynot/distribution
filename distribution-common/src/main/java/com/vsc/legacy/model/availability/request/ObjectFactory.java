//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7-b41 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.05 at 02:18:20 PM IST 
//


package com.vsc.legacy.model.availability.request;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.vsc.model.availability.request package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Source_QNAME = new QName("", "source");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.vsc.model.availability.request
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UnitRateType }
     * 
     */
    public UnitRateType createUnitRateType() {
        return new UnitRateType();
    }

    /**
     * Create an instance of {@link VSCAvailRQ }
     * 
     */
    public VSCAvailRQ createVSCAvailRQ() {
        return new VSCAvailRQ();
    }

    /**
     * Create an instance of {@link Pos }
     * 
     */
    public Pos createPos() {
        return new Pos();
    }

    /**
     * Create an instance of {@link Sources }
     * 
     */
    public Sources createSources() {
        return new Sources();
    }

    /**
     * Create an instance of {@link Language }
     * 
     */
    public Language createLanguage() {
        return new Language();
    }

    /**
     * Create an instance of {@link AvailabilityRequest }
     * 
     */
    public AvailabilityRequest createAvailabilityRequest() {
        return new AvailabilityRequest();
    }

    /**
     * Create an instance of {@link Properties }
     * 
     */
    public Properties createProperties() {
        return new Properties();
    }

    /**
     * Create an instance of {@link Property }
     * 
     */
    public Property createProperty() {
        return new Property();
    }

    /**
     * Create an instance of {@link UnitRateTypes }
     * 
     */
    public UnitRateTypes createUnitRateTypes() {
        return new UnitRateTypes();
    }

    /**
     * Create an instance of {@link Bedrooms }
     * 
     */
    public Bedrooms createBedrooms() {
        return new Bedrooms();
    }

    /**
     * Create an instance of {@link Bedroom }
     * 
     */
    public Bedroom createBedroom() {
        return new Bedroom();
    }

    /**
     * Create an instance of {@link StayDateRange }
     * 
     */
    public StayDateRange createStayDateRange() {
        return new StayDateRange();
    }

    /**
     * Create an instance of {@link GuestCounts }
     * 
     */
    public GuestCounts createGuestCounts() {
        return new GuestCounts();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "source")
    public JAXBElement<String> createSource(String value) {
        return new JAXBElement<String>(_Source_QNAME, String.class, null, value);
    }

}
