package com.myapp;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", new HashMap<>());
        myClassUnderTest.betaService = null;
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }

    @Test
    void testGetAlpha1() {
        // Setup
        // Run the test
        final AlphaData result = myClassUnderTest.getAlpha1("id");

        // Verify the results
    }

    @Test
    void testGetBeta1() {
        // Setup
        // Run the test
        final BetaData result = myClassUnderTest.getBeta1("id");

        // Verify the results
    }

    @Test
    void testGetGamma1() {
        // Setup
        // Run the test
        final GammaData result = myClassUnderTest.getGamma1("id");

        // Verify the results
    }

    @Test
    void testGetGamma2() {
        // Setup
        // Run the test
        final GammaData result = myClassUnderTest.getGamma2();

        // Verify the results
    }

    @Test
    void testGetEpsilon1() {
        // Setup
        // Run the test
        final EpsilonData result = MyClass.getEpsilon1("id");

        // Verify the results
    }

    @Test
    void testGetZetaData1() {
        // Setup
        // Run the test
        final ZetaData result = MyClass.getZetaData1("id");

        // Verify the results
    }

    @Test
    void testSetFooService() {
        final FooService fooService = null;
        myClassUnderTest.setFooService(fooService);
    }

    @Test
    void testSetEpsilonService() {
        // Setup
        final EpsilonService value = null;

        // Run the test
        MyClass.setEpsilonService(value);

        // Verify the results
    }
}
