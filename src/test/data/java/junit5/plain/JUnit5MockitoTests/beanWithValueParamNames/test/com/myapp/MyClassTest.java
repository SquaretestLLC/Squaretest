package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testConvert1() {
        // Setup
        final FooData1 fooData1 = new FooData1();
        fooData1.setId("id");
        fooData1.setName("name");
        fooData1.setOther("other");

        // Run the test
        final OtherData1 result = myClassUnderTest.convert1(fooData1);

        // Verify the results
    }

    @Test
    void testConvert2() {
        // Setup
        final FooData2 fooData2 = new FooData2();
        fooData2.setId("id");
        fooData2.setName("name");
        fooData2.setOther("other");

        // Configure FooService.convertTo(...).
        final OtherData2 otherData2 = new OtherData2();
        otherData2.setId("id");
        otherData2.setName("name");
        otherData2.setOther("other");
        when(mockFooService.convertTo(any(Object.class), eq(OtherData2.class))).thenReturn(otherData2);

        // Run the test
        final OtherData2 result = myClassUnderTest.convert2(fooData2);

        // Verify the results
    }

    @Test
    void testConvert3() {
        // Setup
        final FooData3 fooData3 = new FooData3();
        fooData3.setId("id");
        fooData3.setName("name");
        fooData3.setOther("other");

        // Run the test
        final OtherData3 result = myClassUnderTest.convert3(fooData3);

        // Verify the results
    }
}
