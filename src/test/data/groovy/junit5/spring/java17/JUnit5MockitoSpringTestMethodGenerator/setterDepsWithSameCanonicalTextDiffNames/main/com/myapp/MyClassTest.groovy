package com.myapp

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private FooService theMockFooService
    @Mock
    private FooService theMockBarService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass()
        myClassUnderTest.setFooService(theMockFooService)
        myClassUnderTest.setBarService(theMockBarService)
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(theMockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData()
        fooData1.setId("id")
        fooData1.setName("name")
        when(theMockBarService.getFoo1("id")).thenReturn(fooData1)

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
