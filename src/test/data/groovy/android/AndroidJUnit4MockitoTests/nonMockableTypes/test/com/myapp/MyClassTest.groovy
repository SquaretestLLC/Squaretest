package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(new FinalFoo(), new MyClass.StaticFoo(), EnumFoo.RED,
                ["someObjects"] as Object[], 0, "someOtherObjects")
    }

    @Test
    void testPerformWithStaticFoo() {
        // Setup
        def listener = new MyClass.StaticFoo()

        // Run the test
        myClassUnderTest.performWithStaticFoo(listener)

        // Verify the results
    }

    @Test
    void testPerformWithFinalFoo() {
        // Setup
        def finalFoo = new FinalFoo()

        // Run the test
        myClassUnderTest.performWithFinalFoo(finalFoo)

        // Verify the results
    }

    @Test
    void testPerformWithListener() {
        // Setup
        def finalFooListener = new FinalFoo()

        // Run the test
        myClassUnderTest.performWithListener(finalFooListener)

        // Verify the results
    }

    @Test
    void testPerformWithStaticListener() {
        // Setup
        def staticFooListener = new MyClass.StaticFoo()

        // Run the test
        myClassUnderTest.performWithStaticListener(staticFooListener)

        // Verify the results
    }

    @Test
    void testPerformWithCallback() {
        // Setup
        def finalFooCallback = new FinalFoo()

        // Run the test
        myClassUnderTest.performWithCallback(finalFooCallback)

        // Verify the results
    }

    @Test
    void testPerformWithArrayListener() {
        // Setup
        // Run the test
        myClassUnderTest.performWithArrayListener(["listener"] as Object[])

        // Verify the results
    }

    @Test
    void testPerformWithArbitraryListeners() {
        // Setup
        // Run the test
        myClassUnderTest.performWithArbitraryListeners("listeners")

        // Verify the results
    }
}
