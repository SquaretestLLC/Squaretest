package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Validator mockValidator;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockValidator);
    }

    @Test
    void testDoSomething1() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.doSomething1("input");

        // Verify the results
        assertEquals("result", result);
        verify(mockValidator).validate("input");
    }

    @Test
    void testDoSomething1_ValidatorThrowsValidationException() {
        // Setup
        when(mockValidator.validate("input")).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.doSomething1("input"));
    }

    @Test
    void testDoSomething2() {
        // Setup
        when(mockValidator.validate("input")).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.doSomething2("input");

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testDoSomething2_ValidatorThrowsValidationException() {
        // Setup
        when(mockValidator.validate("input")).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.doSomething2("input"));
    }
}
