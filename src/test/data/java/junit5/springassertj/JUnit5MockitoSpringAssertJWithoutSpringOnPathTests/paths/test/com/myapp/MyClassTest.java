package com.myapp;

import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.Principal;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testIndexWithNoPath() {
        assertThat(myClassUnderTest.indexWithNoPath("theName", null)).isEqualTo("indexWithNoPath");
    }

    @Test
    void testIndexWithSlashPath() {
        assertThat(myClassUnderTest.indexWithSlashPath("theName", null)).isEqualTo("indexWithSlashPath");
    }

    @Test
    void testGreeting() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greeting1(Locale.US, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testAuthorizedGreeting() {
        // Setup
        final Principal principal = new UserPrincipal("name");
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.authorizedGreeting(principal, "name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testAuthorizedGreeting1() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.authorizedGreeting1("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testAuthorizedGreeting2() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.authorizedGreeting2("username", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithValueParam() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithValueParam("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithPathParam() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathParam("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithPathAndValue() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathAndValue("theName", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithMultiplePaths() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithMultiplePaths("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathSetToMultiplePaths("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithValueSetToMultiplePaths("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithConstant() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithConstant("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithMultipleConstant() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithMultipleConstant("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithPlaceholder() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPlaceholder("name", model);

        // Verify the results
        assertThat(result).isEqualTo("greeting");
    }

    @Test
    void testGreetingWithPathVar() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathVar("userId", model);

        // Verify the results
        assertThat(result).isEqualTo("greetingForUser");
    }

    @Test
    void testGreetingWithPathVarRegex() {
        // Setup
        final Model model = null;

        // Run the test
        final String result = myClassUnderTest.greetingWithPathVarRegex("userId", model);

        // Verify the results
        assertThat(result).isEqualTo("greetingForUserRegex");
    }

    @Test
    void testFindPet() {
        myClassUnderTest.findPet(0, 0);
    }
}
