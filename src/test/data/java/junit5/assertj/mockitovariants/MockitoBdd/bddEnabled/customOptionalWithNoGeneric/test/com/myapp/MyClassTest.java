package com.myapp;

import com.myapp.libs1.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.BDDMockito.given;
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
        final Optional result = myClassUnderTest.getTheStr();

        // Verify the results
    }
}
