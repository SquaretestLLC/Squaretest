package com.myapp;

import com.myapp.foos.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @Before
    public void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @After
    public void tearDown() throws Exception {
        mockitoCloseable.close();
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
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        simpleBean1.setMyOtherId(0L);
        simpleBean1.setMyLastName("myLastName");
        simpleBean1.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        final SimpleBean simpleBean2 = new SimpleBean();
        simpleBean2.setMyId(0L);
        simpleBean2.setMyName("myName");
        simpleBean2.setMyOtherId(0L);
        simpleBean2.setMyLastName("myLastName");
        simpleBean2.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        final SimpleBean simpleBean3 = new SimpleBean();
        simpleBean3.setMyId(0L);
        simpleBean3.setMyName("myName");
        simpleBean3.setMyOtherId(0L);
        simpleBean3.setMyLastName("myLastName");
        simpleBean3.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        final Foo1 foo1 = new Foo1(Arrays.asList(), Arrays.asList(), new Bar(Arrays.asList()),
                new HashSet<>(Arrays.asList(new Bar(Arrays.asList()))), new OtherBar(null),
                Arrays.asList(new OtherBar(null)), simpleBean, simpleBean1, simpleBean2, simpleBean3);
        when(mockFooMaker.makeFoo1()).thenReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
