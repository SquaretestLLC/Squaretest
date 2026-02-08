package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;
import java.util.Optional;

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
    void testGetFoo1() throws Exception {
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
        when(mockFooService.createException1("id")).thenReturn(new FooServiceException("message"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo1("id"));
    }

    @Test
    void testGetFoo2() throws Exception {
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
        when(mockFooService.getExceptionMessage1("id")).thenReturn("result");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo2_FooServiceGetExceptionMessage1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo3() throws Exception {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.createException1("id")).thenReturn(new FooServiceException("message"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo3("id"));
    }

    @Test
    void testGetFoo4() throws Exception {
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
        when(mockFooService.createException2()).thenReturn(new FooServiceException("message"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo4("id"));
    }

    @Test
    void testGetFoo5() throws Exception {
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
        when(mockFooService.createException2()).thenReturn(new FooServiceException("message"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo5("id"));
    }

    @Test
    void testGetFoo6() throws Exception {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn("result");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo6("id"));
    }

    @Test
    void testGetFoo6_FooServiceGetExceptionMessage1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo6("id"));
    }

    @Test
    void testGetFoo7() throws Exception {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo7_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn("result");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo7("id"));
    }

    @Test
    void testGetFoo7_FooServiceGetExceptionMessage1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn(null);

        // Run the test
        assertThrows(NoSuchElementException.class, () -> myClassUnderTest.getFoo7("id"));
    }

    @Test
    void testGetFoo8() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo8_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn("result");

        // Run the test
        assertThrows(FooServiceRuntimeException.class, () -> myClassUnderTest.getFoo8("id"));
    }

    @Test
    void testGetFoo8_FooServiceGetExceptionMessage1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceRuntimeException.class, () -> myClassUnderTest.getFoo8("id"));
    }

    @Test
    void testGetFoo9() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo9("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo9_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getFoo9("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo9_FooServiceGetExceptionMessage1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.getExceptionMessage1("id")).thenReturn(null);

        // Run the test
        assertThrows(FooServiceRuntimeException.class, () -> myClassUnderTest.getFoo9("id"));
    }

    @Test
    void testGetFoo10() throws Exception {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo10("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo10_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.createException3("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo10("id"));
    }

    @Test
    void testGetFoo10_FooServiceCreateException3ReturnsFailure() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.createException3("id")).thenReturn(Optional.of(new FooServiceException("message")));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo10("id"));
    }

    @Test
    void testGetFoo11() throws Exception {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo11("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo11_FooServiceGetFooAsString1ReturnsNull() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.createException3("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo11("id"));
    }

    @Test
    void testGetFoo11_FooServiceCreateException3ReturnsFailure() {
        // Setup
        when(mockFooService.getFooAsString1("id")).thenReturn(null);
        when(mockFooService.createException3("id")).thenReturn(Optional.of(new FooServiceException("message")));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo11("id"));
    }
}
