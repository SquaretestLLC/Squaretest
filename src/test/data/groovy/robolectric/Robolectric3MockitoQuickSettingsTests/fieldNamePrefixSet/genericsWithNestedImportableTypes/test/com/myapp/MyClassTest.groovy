package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

import java.text.SimpleDateFormat

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(
                [new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime(): ["value": new SimpleDateFormat(
                        "yyyy-MM-dd'T'HH:mm'Z'", Locale.US)]])
    }

    @Test
    void testDoSomethingWithMultimap() {
        // Setup
        def idToSocketMap = ["value": [new Socket("host", 80)]]

        // Run the test
        myClassUnderTest.doSomethingWithMultimap(idToSocketMap)

        // Verify the results
    }

    @Test
    void testDoSomethingWithListOfCurrencies() {
        // Setup
        def theMap = ["value": Currency.getInstance("USD")]

        // Run the test
        def result = MyClass.doSomethingWithListOfCurrencies(theMap)

        // Verify the results
    }
}
