package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.openMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    private AutoCloseable mockitoCloseable

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this)
        myClassUnderTest = new MyClass(mockFoo)
    }

    @AfterEach
    void tearDown() {
        mockitoCloseable.close()
    }

    @Test
    void testMakeFooWithBean() {
        // Setup
        // Configure Foo.makeFooWithBean(...).
        def beanWithFoo = new BeanWithFoo()
        beanWithFoo.setId(0L)
        beanWithFoo.setName("name")
        def simpleBean = new SimpleBean()
        simpleBean.setMyId(0L)
        simpleBean.setMyName("myName")
        beanWithFoo.setFooWithBean(new FooWithBean(0L, "name", new BeanWithFoo(), simpleBean))
        def simpleBean1 = new SimpleBean()
        simpleBean1.setMyId(0L)
        simpleBean1.setMyName("myName")
        def fooWithBean = new FooWithBean(0L, "name", beanWithFoo, simpleBean1)
        when(mockFoo.makeFooWithBean()).thenReturn(fooWithBean)

        // Run the test
        def result = myClassUnderTest.makeFooWithBean()

        // Verify the results
    }
}
