package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private FooService mockBarService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass();
        myClassUnderTest.setFooService(mockFooService);
        myClassUnderTest.setBarService(mockBarService);
    }

    @Test
    public void testGetFooAndBar11() {
        // Setup
        // Configure FooService.getFoo1(...).
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");
        when(mockFooService.getFoo1("id")).thenReturn(fooData);

        // Configure FooService.getFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockBarService.getFoo1("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.getFooAndBar1("id");

        // Verify the results
    }
}