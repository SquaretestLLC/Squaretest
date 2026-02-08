package com.myapp;

import com.sun.security.auth.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        assertThat(myClassUnderTest.indexWithNoPath("theName", null)).isNull();
    }

    @Test
    void testIndexWithSlashPath() {
        assertThat(myClassUnderTest.indexWithSlashPath("theName", null)).isNull();
    }

    @Test
    void testGreeting() {
        assertThat(myClassUnderTest.greeting("name", null)).isNull();
    }

    @Test
    void testGreeting1() {
        assertThat(myClassUnderTest.greeting1(Locale.US, "name", null)).isNull();
    }

    @Test
    void testAuthorizedGreeting() {
        assertThat(myClassUnderTest.authorizedGreeting(new UserPrincipal("name"), "name", null)).isNull();
    }

    @Test
    void testAuthorizedGreeting1() {
        assertThat(myClassUnderTest.authorizedGreeting1("name", null)).isNull();
    }

    @Test
    void testAuthorizedGreeting2() {
        assertThat(myClassUnderTest.authorizedGreeting2("username", null)).isNull();
    }

    @Test
    void testGreetingWithValueParam() {
        assertThat(myClassUnderTest.greetingWithValueParam("theName", null)).isNull();
    }

    @Test
    void testGreetingWithPathParam() {
        assertThat(myClassUnderTest.greetingWithPathParam("theName", null)).isNull();
    }

    @Test
    void testGreetingWithPathAndValue() {
        assertThat(myClassUnderTest.greetingWithPathAndValue("theName", null)).isNull();
    }

    @Test
    void testGreetingWithMultiplePaths() {
        assertThat(myClassUnderTest.greetingWithMultiplePaths("name", null)).isNull();
    }

    @Test
    void testGreetingWithPathSetToMultiplePaths() {
        assertThat(myClassUnderTest.greetingWithPathSetToMultiplePaths("name", null)).isNull();
    }

    @Test
    void testGreetingWithValueSetToMultiplePaths() {
        assertThat(myClassUnderTest.greetingWithValueSetToMultiplePaths("name", null)).isNull();
    }

    @Test
    void testGreetingWithConstant() {
        assertThat(myClassUnderTest.greetingWithConstant("name", null)).isNull();
    }

    @Test
    void testGreetingWithMultipleConstant() {
        assertThat(myClassUnderTest.greetingWithMultipleConstant("name", null)).isNull();
    }

    @Test
    void testGreetingWithPlaceholder() {
        assertThat(myClassUnderTest.greetingWithPlaceholder("name", null)).isNull();
    }

    @Test
    void testGreetingWithPathVar() {
        assertThat(myClassUnderTest.greetingWithPathVar("userId", null)).isNull();
    }

    @Test
    void testGreetingWithPathVarRegex() {
        assertThat(myClassUnderTest.greetingWithPathVarRegex("userId", null)).isNull();
    }

    @Test
    void testFindPet() {
        myClassUnderTest.findPet(0, 0);
    }
}
