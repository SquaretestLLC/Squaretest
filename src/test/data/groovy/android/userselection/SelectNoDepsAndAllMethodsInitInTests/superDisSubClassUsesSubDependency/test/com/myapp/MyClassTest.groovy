package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.bases.SubFooService
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Test
    void testGetFoo() {
        // Setup
        def myClassUnderTest = new MyClass(new SubFooService())

        // Run the test
        def result = myClassUnderTest.getFoo("key")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(new SubFooService())

        // Run the test
        def result = myClassUnderTest.getFoo2("key")

        // Verify the results
        assert "result" == result
    }
}
