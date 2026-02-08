package com.myapp

import com.myapp.bases.FooService
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test
import org.mockito.Mock

import java.io.IOException
import java.sql.SQLException

import static org.testng.Assert.assertEquals

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private FooService theMockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(theMockFooService)
    }

    @Test
    void testGetData() throws Exception {
        // Setup
        when(theMockFooService.getData("someValue")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.getData("someValue")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetData_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getData("someValue")).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getData("someValue"))
    }

    @Test
    void testGetOtherData() throws Exception {
        // Setup
        when(theMockFooService.getOtherData("otherValue")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetOtherData_FooServiceThrowsIOException() throws Exception {
        // Setup
        when(theMockFooService.getOtherData("otherValue")).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, () -> myClassUnderTest.getOtherData("otherValue"))
    }

    @Test
    void testGetThingFromDatabase() throws Exception {
        // Setup
        when(theMockFooService.getThingFromDatabase("value")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testGetThingFromDatabase_FooServiceThrowsSQLException() throws Exception {
        // Setup
        when(theMockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class)

        // Run the test
        assertThrows(SQLException.class, () -> myClassUnderTest.getThingFromDatabase("value"))
    }

    @Test
    void testDoSomething() {
        // Setup
        when(theMockFooService.doSomething("purchaseId")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testActivateBar() {
        // Setup
        when(theMockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testActivateBar2() {
        // Setup
        when(theMockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assertEquals("result", result)
    }

    @Test
    void testActivateBar3() {
        // Setup
        when(theMockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        final String result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assertEquals("something", result)
    }
}
