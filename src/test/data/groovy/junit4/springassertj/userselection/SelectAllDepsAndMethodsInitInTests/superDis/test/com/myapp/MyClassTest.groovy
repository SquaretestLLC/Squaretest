package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import java.sql.SQLException

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.when

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    @Test
    void testGetData() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("someValue")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getData("someValue")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetData_FooServiceThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getData("someValue")).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getData("someValue")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetOtherData() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getOtherData("otherValue")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getOtherData("otherValue")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetOtherData_FooServiceThrowsIOException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getOtherData("otherValue")).thenThrow(IOException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getOtherData("otherValue")
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testGetThingFromDatabase() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getThingFromDatabase("value")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.getThingFromDatabase("value")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testGetThingFromDatabase_FooServiceThrowsSQLException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.getThingFromDatabase("value")).thenThrow(SQLException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getThingFromDatabase("value")
        }).isInstanceOf(SQLException.class)
    }

    @Test
    void testDoSomething() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.doSomething("purchaseId")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.doSomething("purchaseId")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.activateBar("computeCodeForOrder")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.activateBar2("something")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testActivateBar3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService)
        when(mockFooService.activateBar("computeCodeForOrder")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.activateBar3("something")

        // Verify the results
        assertThat(result).isEqualTo("something")
    }
}
