package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.Foo2;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
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
        final SimpleBean simpleBean = new SimpleBean();
        simpleBean.setMyId(0L);
        simpleBean.setMyName("myName");
        simpleBean.setMyOtherId(0L);
        simpleBean.setMyLastName("myLastName");
        simpleBean.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean.setMyAddressLine1("myAddressLine1");
        simpleBean.setMyAddressLine2("myAddressLine2");
        simpleBean.setMyZipCode("myZipCode");
        simpleBean.setMyCountry("myCountry");
        simpleBean.setMyCurrency(Currency.getInstance("USD"));
        foo1.setSimpleBean1(Arrays.asList(simpleBean));
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
        foo1.setSimpleBean2(Arrays.asList(simpleBean1));
        final Foo2 foo2 = new Foo2();
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
        foo2.setSimpleBean1(simpleBean1);
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
        foo2.setSimpleBean2(simpleBean2);
        final SimpleBean simpleBean3 = new SimpleBean();
        simpleBean3.setMyId(0L);
        simpleBean3.setMyName("myName");
        simpleBean3.setMyOtherId(0L);
        simpleBean3.setMyLastName("myLastName");
        simpleBean3.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean3.setMyAddressLine1("myAddressLine1");
        simpleBean3.setMyAddressLine2("myAddressLine2");
        simpleBean3.setMyZipCode("myZipCode");
        simpleBean3.setMyCountry("myCountry");
        simpleBean3.setMyCurrency(Currency.getInstance("USD"));
        foo2.setSimpleBean3(simpleBean3);
        final SimpleBean simpleBean4 = new SimpleBean();
        simpleBean4.setMyId(0L);
        simpleBean4.setMyName("myName");
        simpleBean4.setMyOtherId(0L);
        simpleBean4.setMyLastName("myLastName");
        simpleBean4.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean4.setMyAddressLine1("myAddressLine1");
        simpleBean4.setMyAddressLine2("myAddressLine2");
        simpleBean4.setMyZipCode("myZipCode");
        simpleBean4.setMyCountry("myCountry");
        simpleBean4.setMyCurrency(Currency.getInstance("USD"));
        foo2.setSimpleBean4(simpleBean4);
        final SimpleBean simpleBean5 = new SimpleBean();
        simpleBean5.setMyId(0L);
        simpleBean5.setMyName("myName");
        foo2.setSimpleBean5(simpleBean5);
        foo1.setFoo21(Arrays.asList(foo2));
        when(mockFooMaker.makeFoo1()).thenReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
