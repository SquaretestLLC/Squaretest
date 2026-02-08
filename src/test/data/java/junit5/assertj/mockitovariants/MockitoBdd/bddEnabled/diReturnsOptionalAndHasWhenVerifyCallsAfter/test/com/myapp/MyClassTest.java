package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private CustomOptionalFooProvider mockOptionalFooProvider;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockOptionalFooProvider);
    }

    @Test
    void testGetTheStr() {
        // Setup
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final Optional<String> result = myClassUnderTest.getTheStr();

        // Verify the results
        assertThat(result).isEqualTo(Optional.of("value"));
    }

    @Test
    void testGetTheStr_CustomOptionalFooProviderReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.empty());

        // Run the test
        final Optional<String> result = myClassUnderTest.getTheStr();

        // Verify the results
        assertThat(result).isEmpty();
    }

    @Test
    void testSubmitString() {
        // Setup
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString1() {
        // Setup
        given(mockOptionalFooProvider.getTheDouble()).willReturn(OptionalDouble.of(0.0));
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString1("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString1_CustomOptionalFooProviderGetTheDoubleReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheDouble()).willReturn(OptionalDouble.empty());
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString1("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString1_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheDouble()).willReturn(OptionalDouble.of(0.0));
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString1("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString2() {
        // Setup
        given(mockOptionalFooProvider.getTheInt()).willReturn(OptionalInt.of(0));
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString2("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString2_CustomOptionalFooProviderGetTheIntReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheInt()).willReturn(OptionalInt.empty());
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString2("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString2_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheInt()).willReturn(OptionalInt.of(0));
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString2("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString3() {
        // Setup
        given(mockOptionalFooProvider.getTheLong()).willReturn(OptionalLong.of(0));
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString3("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString3_CustomOptionalFooProviderGetTheLongReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheLong()).willReturn(OptionalLong.empty());
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString3("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }

    @Test
    void testSubmitString3_CustomOptionalFooProviderGetTheStrReturnsAbsent() {
        // Setup
        given(mockOptionalFooProvider.getTheLong()).willReturn(OptionalLong.of(0));
        given(mockOptionalFooProvider.getTheStr()).willReturn(Optional.empty());

        // Run the test
        final String result = myClassUnderTest.submitString3("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(mockOptionalFooProvider).should().submitTheStr("valueToSubmit");
    }
}
