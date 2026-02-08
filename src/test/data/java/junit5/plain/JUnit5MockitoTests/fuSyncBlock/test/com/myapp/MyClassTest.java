package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
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
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo3() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString3("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString3("id")).thenReturn("result");
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString3("id")).thenReturn("result");
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo4() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo5() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo5_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo5_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo6() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFooAsString4ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.empty());
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo7() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo7_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo7_FooServiceGetFooAsString4ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.empty());
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo7_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertNull(result);
    }

    @Test
    void testGetFoo8() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo8_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo8_FooServiceGetFooAsString4ReturnsAbsent() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.empty());
        when(mockFooService.getFooAsString2("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo8_FooServiceGetFooAsString2ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getFooAsString4("id")).thenReturn(Optional.of("value"));
        when(mockFooService.getFooAsString2("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertNull(result);
    }
}
