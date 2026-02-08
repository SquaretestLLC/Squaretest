package com.myapp

import com.myapp.helpers.BarServiceHelpers
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static com.myapp.helpers.CommonTestHelpers.createTestFooService
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private AlphaService mockAlphaService

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockAlphaService, createTestFooService(),
                BarServiceHelpers.createTestBarService(), "defaultBarId")
    }

    @Test
    void testGetFooAndBar1() {
        // Setup
        // Run the test
        def result = myClassUnderTest.getFooAndBar1("id")

        // Verify the results
    }

    @Test
    void testGetAlpha1() {
        // Setup
        // Configure AlphaService.getAlpha1(...).
        def alphaData = new AlphaData()
        alphaData.setId("id")
        alphaData.setName("name")
        when(mockAlphaService.getAlpha1("id")).thenReturn(alphaData)

        // Run the test
        def result = myClassUnderTest.getAlpha1("id")

        // Verify the results
    }
}
