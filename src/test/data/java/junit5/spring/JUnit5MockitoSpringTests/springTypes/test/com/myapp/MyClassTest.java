package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new MockMultipartFile("name", "content".getBytes()), new MockEnvironment());
    }

    @Test
    void testGetInfo1() {
        // Setup
        final MultipartFile multipartFileParam = new MockMultipartFile("name", "content".getBytes());
        final Environment environmentParam = new MockEnvironment();

        // Run the test
        final String result = myClassUnderTest.getInfo1(multipartFileParam, environmentParam);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    void testGetDefaultInfo() {
        // Setup
        // Run the test
        final String result = myClassUnderTest.getDefaultInfo();

        // Verify the results
        assertEquals("result", result);
    }
}
