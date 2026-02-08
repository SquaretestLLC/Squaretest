package com.myapp;

import com.google.cloud.language.v1.LanguageServiceClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private SessionFactory mockSessionFactory;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockSessionFactory);
    }

    @Test
    void testIsHappy() {
        // Setup
        // Configure SessionFactory.openSession(...).
        final LanguageServiceClient mockLanguageServiceClient = mock(LanguageServiceClient.class);
        when(mockSessionFactory.openSession()).thenReturn(mockLanguageServiceClient);

        // Run the test
        final boolean result = myClassUnderTest.isHappy("text");

        // Verify the results
        assertFalse(result);
        verify(mockLanguageServiceClient).close();
    }
}
