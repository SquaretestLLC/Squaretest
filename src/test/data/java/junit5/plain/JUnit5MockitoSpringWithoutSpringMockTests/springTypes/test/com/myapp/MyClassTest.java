package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private MultipartFile mockMultipartFileDep;
    @Mock
    private Environment mockEnvironmentDep;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockMultipartFileDep, mockEnvironmentDep);
    }

    @Test
    void testGetInfo1() {
        // Setup
        final MultipartFile mockMultipartFileParam = mock(MultipartFile.class);
        final Environment mockEnvironmentParam = mock(Environment.class);

        // Run the test
        final String result = myClassUnderTest.getInfo1(mockMultipartFileParam, mockEnvironmentParam);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo() {
        // Setup
        when(mockMultipartFileDep.getName()).thenReturn("result");
        when(mockEnvironmentDep.getProperty("PROP1")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getDefaultInfo();

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo_EnvironmentReturnsNull() {
        // Setup
        when(mockMultipartFileDep.getName()).thenReturn("result");
        when(mockEnvironmentDep.getProperty("PROP1")).thenReturn(null);

        // Run the test
        final String result = myClassUnderTest.getDefaultInfo();

        // Verify the results
        assertEquals("result", result);
    }
}
