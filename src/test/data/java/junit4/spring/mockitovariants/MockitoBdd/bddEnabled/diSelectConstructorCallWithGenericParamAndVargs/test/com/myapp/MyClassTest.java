package com.myapp;

import com.myapp.foos.Foo;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @Test
    public void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        simpleBean.setMyOtherId(0L);
        simpleBean.setMyLastName("myLastName");
        simpleBean.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        final Foo<SimpleBean> simpleBeanFoo = new Foo<>(0, simpleBean);
        given(mockFooMaker.makeFoo1()).willReturn(simpleBeanFoo);

        // Run the test
        final Foo<SimpleBean> result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
