package com.myapp

import android.support.test.filters.SmallTest
import android.support.test.runner.AndroidJUnit4
import com.myapp.other.GenericInstanceMethodFoo
import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
@RunWith(AndroidJUnit4.class)
@SmallTest
class MyClassTest {

    @Mock
    private GenericInstanceMethodFoo mockFoo

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testDoLoadWithVal() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithVal("key")

        // Verify the results
        verify(mockFoo).load("key")
    }

    @Test
    void testDoLoadWithClassAndKey() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithClassAndKey("key")

        // Verify the results
        verify(mockFoo).load(String.class, "key")
    }

    @Test
    void testDoLoadWithNull() {
        // Setup
        // Run the test
        myClassUnderTest.doLoadWithNull("key")

        // Verify the results
        verify(mockFoo).load(any(T.class))
    }

    @Test
    void testDoSaveWithNull() {
        // Setup
        // Run the test
        myClassUnderTest.doSaveWithNull("obj")

        // Verify the results
        verify(mockFoo).save(any(T.class))
    }

    @Test
    void testDoLoad() {
        // Setup
        // Run the test
        myClassUnderTest.doLoad("key")

        // Verify the results
        verify(mockFoo).load("key")
    }

    @Test
    void testDoSave() {
        // Setup
        // Run the test
        myClassUnderTest.doSave("obj")

        // Verify the results
        verify(mockFoo).save(any(Object.class))
    }
}
