package com.myapp;

import com.myapp.foos.Bar;
import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        simpleBean.setMyOtherId(0L);
        simpleBean.setMyLastName("myLastName");
        simpleBean.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        final Foo1 foo1 = new Foo1(new Bar(Arrays.asList()), simpleBean);
        given(mockFooMaker.makeFoo1()).willReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
