package com.myapp

import com.myapp.libs.Optional
import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private CustomOptionalFooProvider mockOptionalFooProvider

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(mockOptionalFooProvider)
    }

    @Test
    void testGetTheStr() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.getTheStr()

        // Verify the results
    }

    @Test
    void testSubmitString() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }
}
