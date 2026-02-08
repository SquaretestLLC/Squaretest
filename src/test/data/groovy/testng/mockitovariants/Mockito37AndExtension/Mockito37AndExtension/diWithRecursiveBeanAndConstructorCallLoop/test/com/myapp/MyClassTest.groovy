package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.mockito.testng.MockitoTestNGListener
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Listeners
import org.testng.annotations.Test

import static org.mockito.Mockito.when

@CompileStatic
@Listeners(MockitoTestNGListener.class)
class MyClassTest {

    @Mock
    private Foo mockFoo

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        myClassUnderTest = new MyClass(mockFoo)
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
