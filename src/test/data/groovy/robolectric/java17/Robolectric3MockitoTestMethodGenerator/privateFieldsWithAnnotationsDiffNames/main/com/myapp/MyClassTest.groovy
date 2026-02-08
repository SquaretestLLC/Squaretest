package com.myapp

import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.util.ReflectionTestUtils

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

class MyClassTest {

    @Mock
    private FooService theMockFooService
    @Mock
    private BarService theMockBarService

    @InjectMocks
    private MyClass myClassUnderTest

    @Before
    void setUp() {
        initMocks(this)
        ReflectionTestUtils.setField(myClassUnderTest, "defaultBarId", "defaultBarId")
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData()
        fooData.setId("id")
        fooData.setName("name")
        when(theMockFooService.getFoo1("id")).thenReturn(fooData)

        // Configure BarService.getDefaultBar1(...).
        final BarData barData = new BarData()
        barData.setId("id")
        barData.setName("name")
        when(theMockBarService.getDefaultBar1("defaultBarId")).thenReturn(barData)

        // Run the test
        final FooAndBar result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }
}
