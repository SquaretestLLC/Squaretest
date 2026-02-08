package com.myapp;

import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNull;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testIndexWithNoPath() {
        assertNull(myClassUnderTest.indexWithNoPath("theName", null));
    }

    @Test
    void testIndexWithSlashPath() {
        assertNull(myClassUnderTest.indexWithSlashPath("theName", null));
    }

    @Test
    void testGreeting() {
        assertNull(myClassUnderTest.greeting("name", null));
    }

    @Test
    void testGreeting1() {
        assertNull(myClassUnderTest.greeting1(Locale.US, "name", null));
    }

    @Test
    void testAuthorizedGreeting() {
        assertNull(myClassUnderTest.authorizedGreeting(new UserPrincipal("name"), "name", null));
    }

    @Test
    void testAuthorizedGreeting1() {
        assertNull(myClassUnderTest.authorizedGreeting1("name", null));
    }

    @Test
    void testAuthorizedGreeting2() {
        assertNull(myClassUnderTest.authorizedGreeting2("username", null));
    }

    @Test
    void testGreetingWithValueParam() {
        assertNull(myClassUnderTest.greetingWithValueParam("theName", null));
    }

    @Test
    void testGreetingWithPathParam() {
        assertNull(myClassUnderTest.greetingWithPathParam("theName", null));
    }

    @Test
    void testGreetingWithPathAndValue() {
        assertNull(myClassUnderTest.greetingWithPathAndValue("theName", null));
    }

    @Test
    void testGreetingWithMultiplePaths() {
        assertNull(myClassUnderTest.greetingWithMultiplePaths("name", null));
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() {
        assertNull(myClassUnderTest.greetingWithPathSetToMultiplePaths("name", null));
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() {
        assertNull(myClassUnderTest.greetingWithValueSetToMultiplePaths("name", null));
    }

    @Test
    void testGreetingWithConstant() {
        assertNull(myClassUnderTest.greetingWithConstant("name", null));
    }

    @Test
    void testGreetingWithMultipleConstant() {
        assertNull(myClassUnderTest.greetingWithMultipleConstant("name", null));
    }

    @Test
    void testGreetingWithPlaceholder() {
        assertNull(myClassUnderTest.greetingWithPlaceholder("name", null));
    }

    @Test
    void testGreetingWithPathVar() {
        assertNull(myClassUnderTest.greetingWithPathVar("userId", null));
    }

    @Test
    void testGreetingWithPathVarRegex() {
        assertNull(myClassUnderTest.greetingWithPathVarRegex("userId", null));
    }

    @Test
    void testFindPet() {
        myClassUnderTest.findPet(0, 0);
    }
}
