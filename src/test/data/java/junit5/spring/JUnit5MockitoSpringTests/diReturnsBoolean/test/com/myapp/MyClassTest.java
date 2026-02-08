package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockMetricService);
    }

    @Test
    void testIsValid1() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.isValid1("data");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testIsValid1_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.isValid1("data");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testIsValid2() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.isValid2("data");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testIsValid2_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.isValid2("data");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testIsValid3() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.isValid3("data");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testIsValid3_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.isValid3("data");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testExists() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.exists("data");

        // Verify the results
        assertFalse(result);
    }

    @Test
    void testExists_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.exists("data");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testValidate1() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.validate1("data");

        // Verify the results
    }

    @Test
    void testValidate1_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThrows(FooNotValidException.class, () -> myClassUnderTest.validate1("data"));
    }

    @Test
    void testValidate2() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.validate2("data");

        // Verify the results
    }

    @Test
    void testValidate2_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThrows(FooNotValidException.class, () -> myClassUnderTest.validate2("data"));
    }

    @Test
    void testValidate3() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.validate3("data");

        // Verify the results
    }

    @Test
    void testValidate3_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        assertThrows(FooIsValidException.class, () -> myClassUnderTest.validate3("data"));
    }

    @Test
    void testCreateFoo1() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo1("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo1_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThrows(FooNotValidException.class, () -> myClassUnderTest.createFoo1("data"));
    }

    @Test
    void testCreateFoo1_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.createFoo1("data"));
    }

    @Test
    void testCreateFoo2() {
        // Setup
        // Run the test
        myClassUnderTest.createFoo2("data");

        // Verify the results
        verify(mockFooService).isValid("data");
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo2_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.createFoo2("data"));
        verify(mockFooService).isValid("data");
    }

    @Test
    void testCreateFoo3() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo3("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo3_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);
        when(mockFooService.shouldThrowInvalidFoo("data")).thenReturn(false);

        // Run the test
        assertThrows(GenericException.class, () -> myClassUnderTest.createFoo3("data"));
    }

    @Test
    void testCreateFoo3_FooServiceShouldThrowInvalidFooReturnsTrue() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);
        when(mockFooService.shouldThrowInvalidFoo("data")).thenReturn(true);

        // Run the test
        assertThrows(FooNotValidException.class, () -> myClassUnderTest.createFoo3("data"));
    }

    @Test
    void testCreateFoo3_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.createFoo3("data"));
    }

    @Test
    void testCreateFoo4() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.createFoo4("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo4_FooServiceExistsReturnsTrue() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.createFoo4("data"));
    }

    @Test
    void testCreateFoo4_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.createFoo4("data"));
    }

    @Test
    void testCreateFoo5() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.createFoo5("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo5_FooServiceExistsReturnsTrue() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo5("data");

        // Verify the results
    }

    @Test
    void testCreateFoo5_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.createFoo5("data"));
    }

    @Test
    void testCreateFoo6() {
        // Setup
        when(mockFooService.shouldRecordCreate("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.createFoo6("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo6_FooServiceShouldRecordCreateReturnsTrue() {
        // Setup
        when(mockFooService.shouldRecordCreate("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo6("data");

        // Verify the results
        verify(mockMetricService).recordCreateFoo("data");
        verify(mockFooService).createFoo1("data");
    }

    @Test
    void testCreateFoo6_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.shouldRecordCreate("data")).thenReturn(false);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.createFoo6("data"));
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.getFoo2("id")).thenReturn("result");
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo1_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThrows(FooNotValidException.class, () -> myClassUnderTest.getFoo1("id"));
    }

    @Test
    void testCheckCanCreate1() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.checkCanCreate1("id");

        // Verify the results
    }

    @Test
    void testCheckCanCreate1_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.getFoo2("id")).thenReturn("result");
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.checkCanCreate1("id");

        // Verify the results
    }

    @Test
    void testCheckCanCreate1_FooServiceExistsReturnsTrue() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.checkCanCreate1("id"));
    }

    @Test
    void testGetFoo2() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo2_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.shouldThrow(eq("id"), any(FooNotFoundException.class))).thenReturn(false);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo2_FooServiceShouldThrowReturnsTrue() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.shouldThrow(eq("id"), any(FooNotFoundException.class))).thenReturn(true);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo2("id"));
    }

    @Test
    void testGetFoo3() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo3_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo3("id"));
    }

    @Test
    void testGetFoo3_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThrows(OldFooFoundException.class, () -> myClassUnderTest.getFoo3("id"));
        verify(mockMetricService).recordOldFoo("id");
    }

    @Test
    void testGetFoo4() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenReturn("result");
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo4_FooServiceGetFooWithValidId1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo4("id"));
    }

    @Test
    void testGetFoo4_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo4("id"));
    }

    @Test
    void testGetFoo4_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThrows(OldFooFoundException.class, () -> myClassUnderTest.getFoo4("id"));
        verify(mockMetricService).recordOldFoo("id");
    }

    @Test
    void testGetFoo5() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo5_FooServiceGetFooWithValidId1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo5("id"));
    }

    @Test
    void testGetFoo5_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo5("id"));
    }

    @Test
    void testGetFoo5_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThrows(OldFooFoundException.class, () -> myClassUnderTest.getFoo5("id"));
        verify(mockMetricService).recordOldFoo("id");
    }

    @Test
    void testGetFoo6() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo6_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo6("id"));
    }

    @Test
    void testGetFoo7() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetFoo7_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo7("id"));
    }

    @Test
    void testGetFoo8() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenReturn("hello");
        when(mockFooService.getFoo1("id")).thenReturn("hello");

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertEquals("hello", result);
    }

    @Test
    void testGetFoo8_FooServiceGetFooWithValidId1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.getFoo8("id"));
    }

    @Test
    void testGetFoo8_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThrows(FooNotFoundException.class, () -> myClassUnderTest.getFoo8("id"));
    }

    @Test
    void testGetFoo8_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenReturn("hello");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThrows(OldFooFoundException.class, () -> myClassUnderTest.getFoo8("id"));
        verify(mockMetricService).recordOldFoo("id");
    }
}
