package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
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
    void testMakeFooWithBean() {
        // Setup
        // Configure Foo.makeFooWithBean(...).
        final BeanWithFoo beanWithFoo = new BeanWithFoo();
        beanWithFoo.setId(0L);
        beanWithFoo.setName("name");
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        beanWithFoo.setFooWithBean(new FooWithBean(0L, "name", new BeanWithFoo(), simpleBean));
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        beanWithFoo.setSimpleBean(simpleBean1);
        final SimpleBean simpleBean2 = new SimpleBean();
        simpleBean2.setMyId(0L);
        simpleBean2.setMyName("myName");
        final FooWithBean fooWithBean = new FooWithBean(0L, "name", beanWithFoo, simpleBean2);
        when(mockFoo.makeFooWithBean()).thenReturn(fooWithBean);

        // Run the test
        final FooWithBean result = myClassUnderTest.makeFooWithBean();

        // Verify the results
    }
}
