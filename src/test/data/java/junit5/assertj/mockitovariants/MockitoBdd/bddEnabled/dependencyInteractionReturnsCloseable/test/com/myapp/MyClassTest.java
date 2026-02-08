package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.spy;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private Foo mockFoo;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFoo);
    }

    @Test
    void testMakeString() throws Exception {
        // Setup
        // Configure Foo.openCloseableFoo(...).
        final CloseableFoo spyCloseableFoo = spy(new CloseableFoo(new ByteArrayInputStream("content".getBytes())));
        given(mockFoo.openCloseableFoo("source")).willReturn(spyCloseableFoo);

        // Run the test
        final String result = myClassUnderTest.makeString();

        // Verify the results
        assertThat(result).isEqualTo("result");
        then(spyCloseableFoo).should().close();
    }
}
