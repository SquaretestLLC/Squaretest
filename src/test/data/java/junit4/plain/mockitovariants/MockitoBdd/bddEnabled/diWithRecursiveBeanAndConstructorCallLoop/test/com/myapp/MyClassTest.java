package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.BDDMockito.given;

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
    public void testMakeFooWithBean() {
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
        final FooWithBean fooWithBean = new FooWithBean(0L, "name", beanWithFoo, simpleBean1);
        given(mockFoo.makeFooWithBean()).willReturn(fooWithBean);

        // Run the test
        final FooWithBean result = myClassUnderTest.makeFooWithBean();

        // Verify the results
    }
}
