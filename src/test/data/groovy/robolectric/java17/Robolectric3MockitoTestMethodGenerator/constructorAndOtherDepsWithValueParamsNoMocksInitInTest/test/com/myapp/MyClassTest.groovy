package com.myapp


import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.springframework.test.util.ReflectionTestUtils

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testGetFooAndBar11() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, "defaultBarId")
        myClassUnderTest.setDeltaService(null)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", ["value": "value"])
        myClassUnderTest.betaService = null

        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetAlpha11() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, "defaultBarId")
        myClassUnderTest.setDeltaService(null)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", ["value": "value"])
        myClassUnderTest.betaService = null

        // Run the test
        def result = myClassUnderTest.getAlpha1("id")

        // Verify the results
    }

    @Test
    void testGetBeta11() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, "defaultBarId")
        myClassUnderTest.setDeltaService(null)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", ["value": "value"])
        myClassUnderTest.betaService = null

        // Run the test
        def result = myClassUnderTest.getBeta1("id")

        // Verify the results
    }

    @Test
    void testGetGamma11() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, "defaultBarId")
        myClassUnderTest.setDeltaService(null)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", ["value": "value"])
        myClassUnderTest.betaService = null

        // Run the test
        def result = myClassUnderTest.getGamma1("id")

        // Verify the results
    }

    @Test
    void testGetGamma21() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, "defaultBarId")
        myClassUnderTest.setDeltaService(null)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", ["value": "value"])
        myClassUnderTest.betaService = null

        // Run the test
        def result = myClassUnderTest.getGamma2()

        // Verify the results
    }

    @Test
    void testGetEpsilon11() {
        // Setup
        // Run the test
        def result = MyClass.getEpsilon1("id")

        // Verify the results
    }

    @Test
    void testGetZetaData11() {
        // Setup
        // Run the test
        def result = MyClass.getZetaData1("id")

        // Verify the results
    }

    @Test
    void testSetFooService1() {
        def myClassUnderTest = new MyClass(null, null, null, "defaultBarId")
        myClassUnderTest.setDeltaService(null)
        ReflectionTestUtils.setField(myClassUnderTest, "specialMap", ["value": "value"])
        myClassUnderTest.betaService = null
        def fooService = null
        myClassUnderTest.setFooService(fooService)
    }

    @Test
    void testSetEpsilonService1() {
        // Setup
        def value = null

        // Run the test
        MyClass.setEpsilonService(value)

        // Verify the results
    }
}