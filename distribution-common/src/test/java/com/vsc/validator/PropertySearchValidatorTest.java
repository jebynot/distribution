package com.vsc.validator;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.StringWriter;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.vsc.model.search.request.Country;
import com.vsc.model.search.request.GuestCount;
import com.vsc.model.search.request.GuestCounts;
import com.vsc.model.search.request.Location;
import com.vsc.model.search.request.Pos;
import com.vsc.model.search.request.SearchRequest;
import com.vsc.model.search.request.Source;
import com.vsc.model.search.request.StateProvince;
import com.vsc.model.search.request.StayDateRange;
import com.vsc.model.search.request.VSCSearchRQ;
import com.vsc.model.search.response.Error;
import com.vsc.model.search.response.Errors;
import com.vsc.validation.validator.PropertySearchValidator;

/**
 * Created by jebynot on 8/24/15.
 */
@RunWith(JUnit4.class)
public class PropertySearchValidatorTest {
    private static Validator validator;
    private static PropertySearchValidator propertySearchValidator;

    @BeforeClass
    public static void runBeforeClass() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        propertySearchValidator = new PropertySearchValidator(validator);
    }

    @Test
    public void testSuccessRequest() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertNull(errors);

    }

    @Test
    public void testNonMandatoryFieldsNoTRequired() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().setPostalCode(null);
        vscSearchRQ.getSearchRequest().setProperties(null);
        vscSearchRQ.getSearchRequest().setPets(null);
        vscSearchRQ.getSearchRequest().setDetailsInclusive(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertNull(errors);

    }

    @Test
    public void testPosMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.setPos(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
//            assertTrue("POS should be missing", error.getErrorCode().toString().equals("1101"));
        }
    }

    @Test
    public void testSourcesMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
//        vscSearchRQ.getPos().setSources(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
//            assertTrue("Sources should be missing", error.getErrorCode().toString().equals("1102"));
        }
    }

    @Test
    public void testSourcesSourceMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
//        vscSearchRQ.getPos().getSources().setSource(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
//            assertTrue("Sources should be missing", error.getErrorCode().toString().equals("1103"));
        }
    }

    @Test
    public void testIdMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        Pos pos = vscSearchRQ.getPos();
        pos.getSource().setID(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("ID missing", error.getCode().toString().equals("1105"));
        }
    }

    @Test
    public void testTypeMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        Pos pos = vscSearchRQ.getPos();
        pos.getSource().setType(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Type missing", error.getCode().toString().equals("1104"));
        }
    }

    @Test
    public void testPasswordMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        Pos pos = vscSearchRQ.getPos();
        pos.getSource().setPassword(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Password missing", error.getCode().toString().equals("1106"));
        }
    }

    @Test
    public void testSearchRequestMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.setSearchRequest(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Password missing", error.getCode().toString().equals("1110"));
        }
    }

    @Test
    public void testLocationMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().setLocation(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Password missing", error.getCode().toString().equals("1107"));
        }
    }

    @Test
	public void testCityNameAndStateProvinceMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().setCityName(null);
        vscSearchRQ.getSearchRequest().getLocation().setStateProvince(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Password missing", error.getCode().toString().equals("1120"));
        }

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().setCityName(null);
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertNull("There is no errors", errors);

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().setStateProvince(null);
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertNull("There is no errors", errors);

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().setCityName(null);
        vscSearchRQ.getSearchRequest().getLocation().getStateProvince().setName(null);
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertNotNull("There is no errors", errors);

    }

    @Test
    public void testCountryMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().setCountry(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Country missing", error.getCode().toString().equals("1121"));
        }

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getLocation().getCountry().setCode(null);
        vscSearchRQ.getSearchRequest().getLocation().getCountry().setName(null);
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Country missing", error.getCode().toString().equals("1121"));
        }
    }

    @Test
    public void testStayDateRange() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().setStayDateRange(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("StayDateRange missing", error.getCode().toString().equals("1108"));
        }

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckIn(null);
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckOut(null);
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Checkin & Checkout dates missing", error.getCode().toString().equals("1116"));
        }

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckIn(getDateCalendar("2015-07-10"));
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckOut(getDateCalendar("2015-07-15"));
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Dates are in the past", error.getCode().toString().equals("1119"));
        }

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckIn(getDateCalendar("2016-10-10"));
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckOut(getDateCalendar("2016-10-15"));
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Dates are beyond 1 year", error.getCode().toString().equals("1122"));
        }

        vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckIn(getDateCalendar("2015-10-10"));
        vscSearchRQ.getSearchRequest().getStayDateRange().setCheckOut(getDateCalendar("2015-11-15"));
        errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Stay date range is more than 30 days", error.getCode().toString().equals("1118"));
        }
    }

    @Test
    public void testGuestCountsMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().setGuestCounts(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Password missing", error.getCode().toString().equals("1109"));
        }
    }

    @Test
    public void testGuestCountsGuestCountMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getGuestCounts().getGuestCount().add(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Guest count missing", error.getCode().toString().equals("1111"));
        }
    }

    @Test
    public void testGuestCountsGuestCountCountMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getGuestCounts().getGuestCount().get(0).setCount(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("GuestCount count missing", error.getCode().toString().equals("1114"));
        }
    }

    @Test
    public void testGuestCountsGuestCountAgeQualifyingCodeMissing() throws Exception {
        VSCSearchRQ vscSearchRQ = buildSearchRQ();
        vscSearchRQ.getSearchRequest().getGuestCounts().getGuestCount().get(0).setAgeQualifyingCode(null);
        Errors errors = propertySearchValidator.validate(vscSearchRQ);
        assertTrue("There is only 1 error", errors.getError().size() == 1);
        for (Error error : errors.getError()) {
            assertTrue("Age Qualifying code missing", error.getCode().toString().equals("1115"));
        }
    }



    private VSCSearchRQ buildSearchRQ() throws Exception {
        VSCSearchRQ vscSearchRQ = new VSCSearchRQ();
        vscSearchRQ.setTimeStamp("test");
        Pos pos = new Pos();
        Source source = new Source();
        source.setID("234");
        source.setPassword("testing");
        source.setType("Test");
        pos.setSource(source);
        vscSearchRQ.setPos(pos);
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.setDetailsInclusive("yes");
        StayDateRange stayDateRange = new StayDateRange();
        stayDateRange.setCheckIn(getDateCalendar("2015-12-10"));
        stayDateRange.setCheckOut(getDateCalendar("2015-12-15"));
        searchRequest.setStayDateRange(stayDateRange);
        GuestCounts guestCounts = new GuestCounts();
        GuestCount guestCount = new GuestCount();
        guestCount.setAgeQualifyingCode("10");
        guestCount.setCount(BigInteger.valueOf(Long.valueOf("2")));
        guestCounts.getGuestCount().add(guestCount);
        searchRequest.setGuestCounts(guestCounts);
        Location location = new Location();
        location.setCityName("Chicago");
        StateProvince stateProvince = new StateProvince();
        stateProvince.setCode("IL");
        stateProvince.setName("Illinois");
        location.setStateProvince(stateProvince);
        Country country = new Country();
        country.setName("United States");
        country.setCode("US");
        location.setCountry(country);
        location.setPostalCode("60661");
        searchRequest.setLocation(location);
        vscSearchRQ.setSearchRequest(searchRequest);
        JAXBContext jaxbContext = JAXBContext.newInstance(VSCSearchRQ.class);
        Marshaller marshaller= jaxbContext.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal( vscSearchRQ, writer);
        String xmlRequestString = writer.toString();
        System.out.println(xmlRequestString);

        return vscSearchRQ;

    }
    private XMLGregorianCalendar getDateCalendar(String sDate)  throws Exception {
        Date date= new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
        GregorianCalendar calender = new GregorianCalendar();
        calender.setTime(date);
        XMLGregorianCalendar xmlCalender = DatatypeFactory.newInstance().newXMLGregorianCalendar(calender);
        return xmlCalender;
    }
}
