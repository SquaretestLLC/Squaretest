package com.myapp;

import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Map;

public class MyClassTest {

    @Test
    public void testGetFooAndBar11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", Map.ofEntries(Map.entry("value", "value")));
        myClassUnderTest.betaService = null;

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }

    @Test
    public void testGetAlpha11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", Map.ofEntries(Map.entry("value", "value")));
        myClassUnderTest.betaService = null;

        // Run the test
        final AlphaData result = myClassUnderTest.getAlpha1("id");

        // Verify the results
    }

    @Test
    public void testGetBeta11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", Map.ofEntries(Map.entry("value", "value")));
        myClassUnderTest.betaService = null;

        // Run the test
        final BetaData result = myClassUnderTest.getBeta1("id");

        // Verify the results
    }

    @Test
    public void testGetGamma11() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", Map.ofEntries(Map.entry("value", "value")));
        myClassUnderTest.betaService = null;

        // Run the test
        final GammaData result = myClassUnderTest.getGamma1("id");

        // Verify the results
    }

    @Test
    public void testGetGamma21() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", Map.ofEntries(Map.entry("value", "value")));
        myClassUnderTest.betaService = null;

        // Run the test
        final GammaData result = myClassUnderTest.getGamma2();

        // Verify the results
    }

    @Test
    public void testGetEpsilon11() {
        // Setup
        // Run the test
        final EpsilonData result = MyClass.getEpsilon1("id");

        // Verify the results
    }

    @Test
    public void testGetZetaData11() {
        // Setup
        // Run the test
        final ZetaData result = MyClass.getZetaData1("id");

        // Verify the results
    }

    @Test
    public void testSetFooService1() {
        final MyClass myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", Map.ofEntries(Map.entry("value", "value")));
        myClassUnderTest.betaService = null;
        final FooService fooService = null;
        myClassUnderTest.setFooService(fooService);
    }

    @Test
    public void testSetEpsilonService1() {
        // Setup
        final EpsilonService epsilonService = null;

        // Run the test
        MyClass.setEpsilonService(epsilonService);

        // Verify the results
    }
}