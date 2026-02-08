package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
import static org.mockito.Mockito.spy
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @Test
    void testMakeString() {
        // Setup
        // Configure Foo.openCloseableFoo(...).
        def spyCloseableFoo = spy(new CloseableFoo(new ByteArrayInputStream("content".getBytes())))
        given(mockFoo.openCloseableFoo("source")).willReturn(spyCloseableFoo)

        // Run the test
        def result = myClassUnderTest.makeString()

        // Verify the results
        assertThat(result).isEqualTo("result")
        then(spyCloseableFoo).should().close()
    }
}
