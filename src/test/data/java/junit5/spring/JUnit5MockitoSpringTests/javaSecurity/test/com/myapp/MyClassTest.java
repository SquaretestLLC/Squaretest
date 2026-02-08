package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.authentication.TestingAuthenticationToken;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testGetName() {
        // Setup
        final Principal principal = new TestingAuthenticationToken("user", "pass", "ROLE_USER");

        // Run the test
        final String result = myClassUnderTest.getName(principal);

        // Verify the results
        assertEquals("result", result);
    }
}
