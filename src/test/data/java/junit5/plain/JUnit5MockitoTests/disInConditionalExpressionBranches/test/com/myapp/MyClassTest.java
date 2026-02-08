package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        final WrappedFoo expectedResult = new WrappedFoo(new FooData("id", "name"), "otherData");
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));
        when(mockFooService.getData1("id")).thenReturn("otherData");
        when(mockFooService.getData2("id")).thenReturn("otherData");

        // Run the test
        final WrappedFoo result = myClassUnderTest.getFoo1("id", false);

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFoo1_FooServiceGetData1ReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));
        when(mockFooService.getData1("id")).thenReturn(null);

        // Run the test
        assertThrows(OtherDataNotFoundException.class, () -> myClassUnderTest.getFoo1("id", false));
    }

    @Test
    void testGetFoo1_FooServiceGetData2ReturnsNull() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn(new FooData("id", "name"));
        when(mockFooService.getData2("id")).thenReturn(null);

        // Run the test
        assertThrows(OtherDataNotFoundException.class, () -> myClassUnderTest.getFoo1("id", false));
    }
}
