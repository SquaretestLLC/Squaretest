package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testMyClassGetFoo1() {
        // Setup
        final ServiceDTO1 expectedResult = new ServiceDTO1("serviceDTO1Id", "serviceDTO1Name",
                Arrays.asList(new ServiceDTO1.ServiceDTO1Part("ServiceDTO1PartId", "ServiceDTO1PartName")),
                new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L);

        // Configure FooService.getFoo1(...).
        final ServiceResponse1 serviceResponse1 = new ServiceResponse1("serviceResponse1Id", "serviceResponse1Name",
                Arrays.asList(new ServiceResponse1.ServiceResponse1Part("serviceResponse1PartId",
                        "serviceResponse1PartName")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L);
        when(mockFooService.getFoo1("myClassGetFoo1Id")).thenReturn(serviceResponse1);

        // Run the test
        final ServiceDTO1 result = myClassUnderTest.myClassGetFoo1("myClassGetFoo1Id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testMyClassGetFoo2() {
        // Setup
        final ServiceDTO2 expectedResult = new ServiceDTO2();
        expectedResult.setServiceDTO2Id("serviceDTO2Id");
        expectedResult.setServiceDTO2Name("serviceDTO2Name");
        final ServiceDTO2.ServiceDTO2Part serviceDTO2Part = new ServiceDTO2.ServiceDTO2Part();
        serviceDTO2Part.setServiceDTO2PartId("serviceResponse2PartId");
        serviceDTO2Part.setServiceDTO2PartName("serviceResponse2PartName");
        expectedResult.setServiceDTO2Parts(Arrays.asList(serviceDTO2Part));
        expectedResult.setServiceDTO2CreatedDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setServiceDTO2LongValue(0L);

        // Configure FooService.getFoo2(...).
        final ServiceResponse2 serviceResponse2 = new ServiceResponse2("serviceResponse2Id", "serviceResponse2Name",
                Arrays.asList(new ServiceResponse2.ServiceResponse2Part("serviceResponse2PartId",
                        "serviceResponse2PartName")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L);
        when(mockFooService.getFoo2("myClassGetFoo2Id")).thenReturn(serviceResponse2);

        // Run the test
        final ServiceDTO2 result = myClassUnderTest.myClassGetFoo2("myClassGetFoo2Id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testDoesFooExist1() {
        // Setup
        when(mockFooService.fooExists1(
                new ServiceRequest1("doesFooExistParamBucketName1", "doesFooExistParamId1"))).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.doesFooExist1("doesFooExistParamBucketName1", "doesFooExistParamId1");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testDoesFooExist1_FooServiceReturnsFailure() {
        // Setup
        when(mockFooService.fooExists1(
                new ServiceRequest1("doesFooExistParamBucketName1", "doesFooExistParamId1"))).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.doesFooExist1("doesFooExistParamBucketName1", "doesFooExistParamId1");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testDoesFooExist2() {
        // Setup
        final FooInfo1 fooInfo1 = new FooInfo1();
        fooInfo1.setFooInfo1BucketName("serviceRequest2BucketName");
        fooInfo1.setFooInfo1Id("serviceRequest2Path");

        when(mockFooService.fooExists2(
                new ServiceRequest2("serviceRequest2BucketName", "serviceRequest2Path"))).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.doesFooExist2(fooInfo1);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testDoesFooExist2_FooServiceReturnsFailure() {
        // Setup
        final FooInfo1 fooInfo1 = new FooInfo1();
        fooInfo1.setFooInfo1BucketName("serviceRequest2BucketName");
        fooInfo1.setFooInfo1Id("serviceRequest2Path");

        when(mockFooService.fooExists2(
                new ServiceRequest2("serviceRequest2BucketName", "serviceRequest2Path"))).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.doesFooExist2(fooInfo1);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testDoesFooExist3() {
        // Setup
        final FooInfo2 fooInfo2 = new FooInfo2("fooInfo2BucketNameParam", "fooInfo2IdParam");
        when(mockFooService.fooExists3(new ServiceRequest3("fooInfo2BucketNameParam", "fooInfo2IdParam")))
                .thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.doesFooExist3(fooInfo2);

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testDoesFooExist3_FooServiceReturnsFailure() {
        // Setup
        final FooInfo2 fooInfo2 = new FooInfo2("fooInfo2BucketNameParam", "fooInfo2IdParam");
        when(mockFooService.fooExists3(new ServiceRequest3("fooInfo2BucketNameParam", "fooInfo2IdParam")))
                .thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.doesFooExist3(fooInfo2);

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testMyClassGetFoo3() {
        // Setup
        final ServiceDTO3 expectedResult = new ServiceDTO3();
        expectedResult.setServiceDTO3Id("serviceDTO3Id");
        expectedResult.setServiceDTO3Name("serviceDTO3Name");
        final ServiceDTO3.ServiceDTO3Part serviceDTO3Part = new ServiceDTO3.ServiceDTO3Part();
        serviceDTO3Part.setServiceDTO3PartId("serviceDTO3PartId");
        serviceDTO3Part.setServiceDTO3PartName("serviceDTO3PartName");
        expectedResult.setServiceDTO3Parts(Arrays.asList(serviceDTO3Part));
        expectedResult.setServiceDTO3CreatedDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        expectedResult.setServiceDTO3LongValue(0L);

        // Configure FooService.getFoo3(...).
        final ServiceResponse3 serviceResponse3 = new ServiceResponse3("serviceResponse3Id", "serviceResponse3Name",
                Arrays.asList(new ServiceResponse3.ServiceResponse3Part("serviceResponse3PartId",
                        "serviceResponse3PartName")), new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L);
        when(mockFooService.getFoo3("myClassGetFoo3Id")).thenReturn(serviceResponse3);

        // Run the test
        final ServiceDTO3 result = myClassUnderTest.myClassGetFoo3("myClassGetFoo3Id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testMyClassGetFoo4() {
        // Setup
        final ServiceDTOItem serviceDTOItem = new ServiceDTOItem();
        serviceDTOItem.setServiceDTOItemId("serviceResponseDataItemId");
        serviceDTOItem.setServiceDTOItemName("serviceResponseDataItemName");
        serviceDTOItem.setServiceDTOItemCreatedDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        serviceDTOItem.setServiceDTOItemLongValue(0L);
        final List<ServiceDTOItem> expectedResult = Arrays.asList(serviceDTOItem);

        // Configure FooService.getFoo4(...).
        final List<ServiceResponseDataItem> serviceResponseDataItems = Arrays.asList(
                new ServiceResponseDataItem("serviceResponseDataItemId", "serviceResponseDataItemName",
                        new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), 0L));
        when(mockFooService.getFoo4("myClassGetFoo4Id")).thenReturn(serviceResponseDataItems);

        // Run the test
        final List<ServiceDTOItem> result = myClassUnderTest.myClassGetFoo4("myClassGetFoo4Id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testMyClassGetFoo4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoo4("myClassGetFoo4Id")).thenReturn(Collections.emptyList());

        // Run the test
        final List<ServiceDTOItem> result = myClassUnderTest.myClassGetFoo4("myClassGetFoo4Id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testQuote() {
        assertEquals("str", MyClass.quote("str"));
    }

    @Test
    void testQuote2() {
        assertEquals("str", MyClass.quote2("str"));
    }

    @Test
    void testQuote3() {
        assertEquals("str", MyClass.quote3("str"));
    }

    @Test
    void testQuote4() {
        assertEquals("result", MyClass.quote4("str"));
    }

    @Test
    void testCap() {
        assertEquals("result", MyClass.cap("theStr"));
    }
}
