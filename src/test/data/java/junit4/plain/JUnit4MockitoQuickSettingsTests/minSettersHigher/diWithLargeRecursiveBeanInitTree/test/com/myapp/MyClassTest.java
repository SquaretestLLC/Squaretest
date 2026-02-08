package com.myapp;

import com.myapp.foos.Foo1;
import com.myapp.foos.Foo2;
import com.myapp.foos.FooMaker;
import com.myapp.foos.SimpleBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Currency;

import static org.mockito.Mockito.when;

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
        final Foo2 foo21 = new Foo2();
        final SimpleBean simpleBean11 = new SimpleBean();
        simpleBean11.setMyId(0L);
        simpleBean11.setMyName("myName");
        simpleBean11.setMyOtherId(0L);
        simpleBean11.setMyLastName("myLastName");
        simpleBean11.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean11.setMyAddressLine1("myAddressLine1");
        simpleBean11.setMyAddressLine2("myAddressLine2");
        simpleBean11.setMyZipCode("myZipCode");
        simpleBean11.setMyCountry("myCountry");
        simpleBean11.setMyCurrency(Currency.getInstance("USD"));
        foo21.setSimpleBean1(simpleBean11);
        final SimpleBean simpleBean21 = new SimpleBean();
        simpleBean21.setMyId(0L);
        simpleBean21.setMyName("myName");
        simpleBean21.setMyOtherId(0L);
        simpleBean21.setMyLastName("myLastName");
        simpleBean21.setMyCreationDate(LocalDateTime.of(2020, 1, 1, 0, 0, 0, 0).toInstant(ZoneOffset.UTC));
        simpleBean21.setMyAddressLine1("myAddressLine1");
        simpleBean21.setMyAddressLine2("myAddressLine2");
        simpleBean21.setMyZipCode("myZipCode");
        simpleBean21.setMyCountry("myCountry");
        simpleBean21.setMyCurrency(Currency.getInstance("USD"));
        foo21.setSimpleBean2(simpleBean21);
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
        foo21.setSimpleBean3(simpleBean3);
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
        foo21.setSimpleBean4(simpleBean4);
        final SimpleBean simpleBean5 = new SimpleBean();
        simpleBean5.setMyId(0L);
        simpleBean5.setMyName("myName");
        foo21.setSimpleBean5(simpleBean5);
        foo1.setFoo21(foo21);
        when(mockFooMaker.makeFoo1()).thenReturn(foo1);

        // Run the test
        final Foo1 result = myClassUnderTest.makeFoo1();

        // Verify the results
    }
}
