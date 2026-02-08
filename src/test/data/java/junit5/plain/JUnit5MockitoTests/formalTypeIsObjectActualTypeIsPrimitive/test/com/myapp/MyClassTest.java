package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

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
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFoo1() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load1(FooData.class, "id", 0)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load1(FooData.class, "id", "theKey")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo3() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load1(FooData.class, "id", 0)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo3("id", 0);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo4() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load1(FooData.class, "id", 0L)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo4("id", 0L);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo5() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load1(FooData.class, "id", 5)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo6() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load1(FooData.class, "id", 6L)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo7() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load2(FooData.class, "id", 1)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo8() {
        // Setup
        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.load2(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.load2(FooData.class, "id", 8)).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
