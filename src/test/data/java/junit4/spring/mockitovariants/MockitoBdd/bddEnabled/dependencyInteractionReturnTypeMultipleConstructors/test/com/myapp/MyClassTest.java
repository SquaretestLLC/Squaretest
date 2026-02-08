package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.beans.XMLDecoder;
import java.io.ByteArrayInputStream;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    public void testGetDecoder() {
        // Setup
        // Configure Foo.getDecoder(...).
        final XMLDecoder spyXMLDecoder = spy(new XMLDecoder(new ByteArrayInputStream("content".getBytes()), "owner"));
        given(mockFoo.getDecoder("name")).willReturn(spyXMLDecoder);

        // Run the test
        final XMLDecoder result = myClassUnderTest.getDecoder("name");

        // Verify the results
        then(spyXMLDecoder).should().close();
    }
}
