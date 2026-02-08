package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MyClassTest {

    @Mock
    private CustomOptionalFooProvider mockOptionalFooProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(mockOptionalFooProvider);
    }

    @Test
    void testGetTheStr() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final Optional<String> result = myClassUnderTest.getTheStr();

        // Verify the results
        assertEquals(Optional.of("value"), result);
    }

    @Test
    void testGetTheStr_CustomOptionalFooProviderReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty());

        // Run the test
        final Optional<String> result = myClassUnderTest.getTheStr();

        // Verify the results
        assertEquals(Optional.empty(), result);
    }

    @Test
    void testSubmitString() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString1() {
        // Setup
        when(mockOptionalFooProvider.getTheDouble()).thenReturn(OptionalDouble.of(0.0));
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString1("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString1_CustomOptionalFooProviderGetTheDoubleReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheDouble()).thenReturn(OptionalDouble.empty());
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString1("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString1_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheDouble()).thenReturn(OptionalDouble.of(0.0));
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString1("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString2() {
        // Setup
        when(mockOptionalFooProvider.getTheInt()).thenReturn(OptionalInt.of(0));
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString2("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString2_CustomOptionalFooProviderGetTheIntReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheInt()).thenReturn(OptionalInt.empty());
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString2("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString2_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheInt()).thenReturn(OptionalInt.of(0));
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString2("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString3() {
        // Setup
        when(mockOptionalFooProvider.getTheLong()).thenReturn(OptionalLong.of(0));
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString3("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString3_CustomOptionalFooProviderGetTheLongReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheLong()).thenReturn(OptionalLong.empty());
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString3("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString3_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        when(mockOptionalFooProvider.getTheLong()).thenReturn(OptionalLong.of(0));
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString3("theData");

        // Verify the results
        assertEquals("result", result);
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }
}
