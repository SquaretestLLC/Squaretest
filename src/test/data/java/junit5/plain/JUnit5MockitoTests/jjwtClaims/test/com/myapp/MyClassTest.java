package com.myapp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.impl.DefaultClaims;
import io.jsonwebtoken.impl.DefaultHeader;
import io.jsonwebtoken.impl.DefaultJwt;
import io.jsonwebtoken.impl.FixedClock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(new DefaultClaims(),
                new FixedClock(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime()),
                new DefaultJwt<>(new DefaultHeader<>(), "value"), mockFooService);
    }

    @Test
    void testGetInfo1() {
        // Setup
        final Claims claimsParam = new DefaultClaims();

        // Run the test
        final String result = myClassUnderTest.getInfo1(claimsParam);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getDefaultInfo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetInfo2() {
        // Setup
        // Run the test
        final Date result = myClassUnderTest.getInfo2();

        // Verify the results
        assertEquals(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(), result);
    }

    @Test
    void testGetInfo3() {
        // Setup
        final Jwt<Header, String> input = new DefaultJwt<>(new DefaultHeader<>(), "value");

        // Run the test
        final String result = myClassUnderTest.getInfo3(input);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo2() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getDefaultInfo2();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFooData1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name");

        // Configure FooService.getFoo1(...).
        final Jwt<Header, FooData> headerFooDataJwt = new DefaultJwt<>(new DefaultHeader<>(),
                new FooData("id", "name"));
        when(mockFooService.getFoo1("id")).thenReturn(headerFooDataJwt);

        // Run the test
        final FooData result = myClassUnderTest.getFooData1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name"));

        // Configure FooService.getFoo2(...).
        final Jwt<Header, List<FooData>> headerListJwt = new DefaultJwt<>(new DefaultHeader<>(),
                Arrays.asList(new FooData("id", "name")));
        when(mockFooService.getFoo2("id")).thenReturn(headerListJwt);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData2_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoo2(...).
        final Jwt<Header, List<FooData>> headerListJwt = new DefaultJwt<>(new DefaultHeader<>(),
                Collections.emptyList());
        when(mockFooService.getFoo2("id")).thenReturn(headerListJwt);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFooData2("id");

        // Verify the results
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testGetFooData3() {
        // Setup
        final Optional<FooData> expectedResult = Optional.of(new FooData("id", "name"));

        // Configure FooService.getFoo3(...).
        final Jwt<Header, Optional<FooData>> headerOptionalJwt = new DefaultJwt<>(new DefaultHeader<>(),
                Optional.of(new FooData("id", "name")));
        when(mockFooService.getFoo3("id")).thenReturn(headerOptionalJwt);

        // Run the test
        final Optional<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData3_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getFoo3("id")).thenReturn(new DefaultJwt<>(new DefaultHeader<>(), Optional.empty()));

        // Run the test
        final Optional<FooData> result = myClassUnderTest.getFooData3("id");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooData4() {
        // Setup
        final Optional<List<FooData>> expectedResult = Optional.of(Arrays.asList(new FooData("id", "name")));

        // Configure FooService.getFoo4(...).
        final Jwt<Header, Optional<List<FooData>>> headerOptionalJwt = new DefaultJwt<>(new DefaultHeader<>(),
                Optional.of(Arrays.asList(new FooData("id", "name"))));
        when(mockFooService.getFoo4("id")).thenReturn(headerOptionalJwt);

        // Run the test
        final Optional<List<FooData>> result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooData4_FooServiceReturnsNoItem() {
        // Setup
        when(mockFooService.getFoo4("id")).thenReturn(new DefaultJwt<>(new DefaultHeader<>(), Optional.empty()));

        // Run the test
        final Optional<List<FooData>> result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testGetFooData4_FooServiceReturnsNoItems() {
        // Setup
        // Configure FooService.getFoo4(...).
        final Jwt<Header, Optional<List<FooData>>> headerOptionalJwt = new DefaultJwt<>(new DefaultHeader<>(),
                Optional.of(Collections.emptyList()));
        when(mockFooService.getFoo4("id")).thenReturn(headerOptionalJwt);

        // Run the test
        final Optional<List<FooData>> result = myClassUnderTest.getFooData4("id");

        // Verify the results
        assertEquals(Optional.of(Collections.emptyList()), result);
    }
}
