package com.myapp

import com.myapp.bases.FooService
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import java.sql.SQLException

import static org.junit.jupiter.api.Assertions.assertThrows
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService theMockFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(theMockFooService)
    }

    @Test
    void testGetData1() {
        // Setup
        when(theMockFooService.getData("someValue")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getData("someValue")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetData_FooServiceThrowsIOException1() {
        // Setup
        when(theMockFooService.getData("someValue")).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getData("someValue")
        })
    }

    @Test
    void testGetOtherData1() {
        // Setup
        when(theMockFooService.getOtherData("otherValue")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetOtherData_FooServiceThrowsIOException1() {
        // Setup
        when(theMockFooService.getOtherData("otherValue")).thenThrow(IOException.class)

        // Run the test
        assertThrows(IOException.class, {
            myClassUnderTest.getOtherData("otherValue")
        })
    }

    @Test
    void testGetThingFromDatabase1() {
        // Setup
        when(theMockFooService.getThingFromDatabase("value")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testGetThingFromDatabase_FooServiceThrowsSQLException1() {
        // Setup
        when(theMockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class)

        // Run the test
        assertThrows(SQLException.class, {
            myClassUnderTest.getThingFromDatabase("value")
        })
    }

    @Test
    void testDoSomething1() {
        // Setup
        when(theMockFooService.doSomething("purchaseId")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar1() {
        // Setup
        when(theMockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar21() {
        // Setup
        when(theMockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assert "result" == result
    }

    @Test
    void testActivateBar31() {
        // Setup
        when(theMockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assert "something" == result
    }
}