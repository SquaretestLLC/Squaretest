package com.myapp;

import com.myapp.foos.Foo;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private FooMaker mockFooMaker;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooMaker);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testMakeFoo1() {
        // Setup
        // Configure FooMaker.makeFoo1(...).
        final SimpleBean simpleBeans = new SimpleBean();
        simpleBeans.setMyId(0L);
        simpleBeans.setMyName("myName");
        simpleBeans.setMyOtherId(0L);
        simpleBeans.setMyLastName("myLastName");
        simpleBeans.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        final Foo<SimpleBean> simpleBeanFoo = new Foo<>(0, simpleBeans);
        when(mockFooMaker.makeFoo1()).thenReturn(simpleBeanFoo);

        // Run the test
        final Foo<SimpleBean> result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
