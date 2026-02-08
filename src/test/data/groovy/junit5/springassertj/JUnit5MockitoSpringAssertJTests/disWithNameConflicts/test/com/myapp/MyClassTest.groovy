package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockMainFooService
    @Mock
    private FooService mockOldFooService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockMainFooService, mockOldFooService)
    }

    @Test
    void testGetFooData1() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockOldFooService.getFooData("id")).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData1("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooData1_OldFooServiceThrowsFooServiceException() {
        // Setup
        when(mockOldFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData1("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData1_MainFooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData1("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData2() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData2("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooData2_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData2("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData2_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData2("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData3() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData(FooType.Normal)).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData3("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooData3_FooServiceGetFooData1ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData(FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData3("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData3_FooServiceGetFooData2ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData3("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData3_FooServiceGetFooData3ThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id", FooType.Normal)).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData3("id")
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testGetFooData4() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockMainFooService.getFooData("id")).thenReturn(new FooData("id", "name"))

        // Run the test
        def result = myClassUnderTest.getFooData4("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooData4_FooServiceThrowsFooServiceException() {
        // Setup
        when(mockMainFooService.getFooData("id")).thenThrow(FooServiceException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooData4("id")
        }).isInstanceOf(FooServiceException.class)
    }
}
