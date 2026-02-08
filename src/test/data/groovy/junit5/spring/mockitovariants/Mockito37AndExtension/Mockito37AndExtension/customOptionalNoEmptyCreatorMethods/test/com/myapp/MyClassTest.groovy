package com.myapp

import com.myapp.libs.Optional
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private CustomOptionalFooProvider mockOptionalFooProvider

    private MyClass myClassUnderTest

    @BeforeEach
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
}
