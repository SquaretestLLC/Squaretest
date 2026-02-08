package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Currency;

import static org.mockito.Mockito.when;
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
        final Foo1 foo1 = new Foo1();
        final SimpleBean simpleBean1 = new SimpleBean();
        simpleBean1.setMyId(0L);
        simpleBean1.setMyName("myName");
        simpleBean1.setMyOtherId(0L);
        simpleBean1.setMyLastName("myLastName");
        simpleBean1.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean1.setMyAddressLine1("myAddressLine1");
        simpleBean1.setMyAddressLine2("myAddressLine2");
        simpleBean1.setMyZipCode("myZipCode");
        simpleBean1.setMyCountry("myCountry");
        simpleBean1.setMyCurrency(Currency.getInstance("USD"));
        foo1.setSimpleBean1(simpleBean1);
        final SimpleBean simpleBean2 = new SimpleBean();
        simpleBean2.setMyId(0L);
        simpleBean2.setMyName("myName");
        simpleBean2.setMyOtherId(0L);
        simpleBean2.setMyLastName("myLastName");
        simpleBean2.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean2.setMyAddressLine1("myAddressLine1");
        simpleBean2.setMyAddressLine2("myAddressLine2");
        simpleBean2.setMyZipCode("myZipCode");
        simpleBean2.setMyCountry("myCountry");
        simpleBean2.setMyCurrency(Currency.getInstance("USD"));
        foo1.setSimpleBean2(simpleBean2);
        when(mockFooMaker.makeFoo1()).thenReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
