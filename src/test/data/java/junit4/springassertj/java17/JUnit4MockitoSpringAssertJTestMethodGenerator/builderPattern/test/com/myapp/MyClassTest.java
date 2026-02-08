package com.myapp;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = null;
    }

    @Test
    public void testGetFullName1() {
        assertThat(myClassUnderTest.getFullName()).isEqualTo("result");
    }

    @Test
    public void testGetFullNameWithSuffix1() {
        assertThat(myClassUnderTest.getFullNameWithSuffix()).isEqualTo("result");
    }
}