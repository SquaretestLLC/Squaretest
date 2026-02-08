package com.myapp;

import com.myapp.libs.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
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
    }

    @Test
    void testSubmitString() {
        // Setup
        when(mockOptionalFooProvider.getTheStr()).thenReturn(Optional.of("value"));

        // Run the test
        final String result = myClassUnderTest.submitString("theData");

        // Verify the results
        assertThat(result).isEqualTo("result");
        verify(mockOptionalFooProvider).submitTheStr("valueToSubmit");
    }
}
