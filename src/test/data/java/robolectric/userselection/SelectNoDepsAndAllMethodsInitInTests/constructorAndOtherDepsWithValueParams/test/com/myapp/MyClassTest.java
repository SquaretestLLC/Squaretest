package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(null, null, null, "defaultBarId");
        myClassUnderTest.setDeltaService(null);
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", new HashMap<>());
        myClassUnderTest.betaService = null;
    }

    @Test
    public void testGetFooAndBar1() {
        // Setup
        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }

    @Test
    public void testGetAlpha1() {
        // Setup
        // Run the test
        final AlphaData result = myClassUnderTest.getAlpha1("id");

        // Verify the results
    }

    @Test
    public void testGetBeta1() {
        // Setup
        // Run the test
        final BetaData result = myClassUnderTest.getBeta1("id");

        // Verify the results
    }

    @Test
    public void testGetGamma1() {
        // Setup
        // Run the test
        final GammaData result = myClassUnderTest.getGamma1("id");

        // Verify the results
    }

    @Test
    public void testGetGamma2() {
        // Setup
        // Run the test
        final GammaData result = myClassUnderTest.getGamma2();

        // Verify the results
    }

    @Test
    public void testGetEpsilon1() {
        // Setup
        // Run the test
        final EpsilonData result = MyClass.getEpsilon1("id");

        // Verify the results
    }

    @Test
    public void testGetZetaData1() {
        // Setup
        // Run the test
        final ZetaData result = MyClass.getZetaData1("id");

        // Verify the results
    }

    @Test
    public void testSetFooService() {
        final FooService fooService = null;
        myClassUnderTest.setFooService(fooService);
    }

    @Test
    public void testSetEpsilonService() {
        // Setup
        final EpsilonService value = null;

        // Run the test
        MyClass.setEpsilonService(value);

        // Verify the results
    }
}
