package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
import static org.mockito.Mockito.spy
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeMethod
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
        assert "result" == result
        then(spyCloseableFoo).should().close()
    }
}
