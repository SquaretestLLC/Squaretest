package com.myapp

import com.myapp.other.GenericInstanceMethodFoo
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.verify
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private GenericInstanceMethodFoo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeMethod
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterMethod
    void tearDown() {
        mockitoCloseable.close()
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
