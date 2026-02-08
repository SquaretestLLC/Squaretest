package com.myapp

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
        assert Optional.of("value") == result
    }

    @Test
    void testGetTheStr_CustomOptionalFooProviderReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.getTheStr()

        // Verify the results
        assert Optional.empty() == result
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

    @Test
    void testSubmitString_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.submitString("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString1() {
        // Setup
        when(mockOptionalFooProvider.getTheDouble()).thenReturn(OptionalDouble.of(0.0))
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString1("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString1_CustomOptionalFooProviderGetTheDoubleReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheDouble()).thenReturn(OptionalDouble.empty())
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString1("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString1_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheDouble()).thenReturn(OptionalDouble.of(0.0))
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.submitString1("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString2() {
        // Setup
        when(mockOptionalFooProvider.getTheInt()).thenReturn(OptionalInt.of(0))
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString2("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString2_CustomOptionalFooProviderGetTheIntReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheInt()).thenReturn(OptionalInt.empty())
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString2("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString2_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheInt()).thenReturn(OptionalInt.of(0))
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.submitString2("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString3() {
        // Setup
        when(mockOptionalFooProvider.getTheLong()).thenReturn(OptionalLong.of(0))
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString3("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString3_CustomOptionalFooProviderGetTheLongReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheLong()).thenReturn(OptionalLong.empty())
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"))

        // Run the test
        def result = myClassUnderTest.submitString3("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }

    @Test
    void testSubmitString3_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheLong()).thenReturn(OptionalLong.of(0))
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty())

        // Run the test
        def result = myClassUnderTest.submitString3("theData")

        // Verify the results
        assert "result" == result
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit")
    }
}
