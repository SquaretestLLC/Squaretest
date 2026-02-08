package com.myapp

import com.google.common.base.Optional
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.BDDMockito.given
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
    void testTryMakeSimpleBean() {
        // Setup
        // Configure Foo.tryMakeSimpleBean(...).
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        def simpleBeanOptional = Optional.of(simpleBean)
        given(mockFoo.tryMakeSimpleBean("name")).willReturn(simpleBeanOptional)

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
    }

    @Test
    void testTryMakeSimpleBean_FooReturnsAbsent() {
        // Setup
        given(mockFoo.tryMakeSimpleBean("name")).willReturn(Optional.absent())

        // Run the test
        def result = myClassUnderTest.tryMakeSimpleBean("name")

        // Verify the results
        assertThat(result).isEqualTo(Optional.absent())
    }
}
