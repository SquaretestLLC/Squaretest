package com.myapp;

import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Principal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testIndexWithNoPath() {
        assertEquals("indexWithNoPath", myClassUnderTest.indexWithNoPath("theName", null));
    }

    @Test
    void testIndexWithSlashPath() {
        assertEquals("indexWithSlashPath", myClassUnderTest.indexWithSlashPath("theName", null));
    }

    @Test
    void testGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting1(Locale.US, "name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testAuthorizedGreeting() {
        // Setup
        final Principal principal = new UserPrincipal("name");
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.authorizedGreeting(principal, "name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testAuthorizedGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.authorizedGreeting1("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testAuthorizedGreeting2() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.authorizedGreeting2("username", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithValueParam() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithValueParam("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithPathParam() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathParam("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithPathAndValue() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathAndValue("theName", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithMultiplePaths() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithMultiplePaths("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathSetToMultiplePaths("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithValueSetToMultiplePaths("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithConstant() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithConstant("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithMultipleConstant() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithMultipleConstant("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithPlaceholder() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPlaceholder("name", model);

        // Verify the results
        assertEquals("greeting", result);
    }

    @Test
    void testGreetingWithPathVar() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathVar("userId", model);

        // Verify the results
        assertEquals("greetingForUser", result);
    }

    @Test
    void testGreetingWithPathVarRegex() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathVarRegex("userId", model);

        // Verify the results
        assertEquals("greetingForUserRegex", result);
    }

    @Test
    void testFindPet() {
        myClassUnderTest.findPet(0, 0);
    }
}
