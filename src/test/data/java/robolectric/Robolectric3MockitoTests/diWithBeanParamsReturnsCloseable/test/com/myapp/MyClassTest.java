package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.io.Closeable;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testDoSomething1() throws Exception {
        // Setup
        // Configure FooService.getCloseable1(...).
        final SpecialCloseable mockSpecialCloseable = mock(SpecialCloseable.class);
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        when(mockFooService.getCloseable1(fooParams)).thenReturn(mockSpecialCloseable);

        // Run the test
        myClassUnderTest.doSomething1();

        // Verify the results
        verify(mockSpecialCloseable).close();
    }

    @Test
    public void testDoSomething2() throws Exception {
        // Setup
        // Configure FooService.getCloseable2(...).
        final SpecialCloseable mockSpecialCloseable = mock(SpecialCloseable.class);
        when(mockFooService.getCloseable2()).thenReturn(mockSpecialCloseable);

        // Run the test
        myClassUnderTest.doSomething2();

        // Verify the results
        verify(mockSpecialCloseable).close();
    }

    @Test
    public void testDoSomething3() throws Exception {
        // Setup
        // Configure FooService.getCloseable3(...).
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        final CloseableWithConstructor spyCloseableWithConstructor = spy(
                new CloseableWithConstructor("content", fooParams));
        final FooParams fooParams1 = new FooParams();
        fooParams1.setId("id");
        fooParams1.setName("name");
        when(mockFooService.getCloseable3(fooParams1)).thenReturn(spyCloseableWithConstructor);

        // Run the test
        myClassUnderTest.doSomething3();

        // Verify the results
        verify(spyCloseableWithConstructor).close();
    }

    @Test
    public void testDoSomething4() throws Exception {
        // Setup
        // Configure FooService.getCloseable4(...).
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        final CloseableWithConstructor spyCloseableWithConstructor = spy(
                new CloseableWithConstructor("content", fooParams));
        when(mockFooService.getCloseable4()).thenReturn(spyCloseableWithConstructor);

        // Run the test
        myClassUnderTest.doSomething4();

        // Verify the results
        verify(spyCloseableWithConstructor).close();
    }

    @Test
    public void testDoSomething5() throws Exception {
        // Setup
        // Configure FooService.getCloseable1(...).
        final SpecialCloseable mockSpecialCloseable = mock(SpecialCloseable.class);
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        when(mockFooService.getCloseable1(fooParams)).thenReturn(mockSpecialCloseable);

        // Run the test
        myClassUnderTest.doSomething5();

        // Verify the results
        verify(mockSpecialCloseable).close();
    }

    @Test
    public void testDoSomething6() throws Exception {
        // Setup
        // Configure FooService.getCloseable2(...).
        final SpecialCloseable mockSpecialCloseable = mock(SpecialCloseable.class);
        when(mockFooService.getCloseable2()).thenReturn(mockSpecialCloseable);

        // Run the test
        myClassUnderTest.doSomething6();

        // Verify the results
        verify(mockSpecialCloseable).close();
    }

    @Test
    public void testDoSomething7() throws Exception {
        // Setup
        // Configure FooService.getCloseable3(...).
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        final CloseableWithConstructor spyCloseableWithConstructor = spy(
                new CloseableWithConstructor("content", fooParams));
        final FooParams fooParams1 = new FooParams();
        fooParams1.setId("id");
        fooParams1.setName("name");
        when(mockFooService.getCloseable3(fooParams1)).thenReturn(spyCloseableWithConstructor);

        // Run the test
        myClassUnderTest.doSomething7();

        // Verify the results
        verify(spyCloseableWithConstructor).close();
    }

    @Test
    public void testDoSomething8() throws Exception {
        // Setup
        // Configure FooService.getCloseable4(...).
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        final CloseableWithConstructor spyCloseableWithConstructor = spy(
                new CloseableWithConstructor("content", fooParams));
        when(mockFooService.getCloseable4()).thenReturn(spyCloseableWithConstructor);

        // Run the test
        myClassUnderTest.doSomething8();

        // Verify the results
        verify(spyCloseableWithConstructor).close();
    }

    @Test
    public void testDoSomething9() throws Exception {
        // Setup
        // Configure FooService.getCloseable1(...).
        final SpecialCloseable mockSpecialCloseable = mock(SpecialCloseable.class);
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        when(mockFooService.getCloseable1(fooParams)).thenReturn(mockSpecialCloseable);

        // Run the test
        final SpecialCloseable result = myClassUnderTest.doSomething9();

        // Verify the results
        verify(mockSpecialCloseable).close();
    }

    @Test
    public void testDoSomething10() throws Exception {
        // Setup
        // Configure FooService.getCloseable2(...).
        final SpecialCloseable mockSpecialCloseable = mock(SpecialCloseable.class);
        when(mockFooService.getCloseable2()).thenReturn(mockSpecialCloseable);

        // Run the test
        final SpecialCloseable result = myClassUnderTest.doSomething10();

        // Verify the results
        verify(mockSpecialCloseable).close();
    }

    @Test
    public void testDoSomething11() throws Exception {
        // Setup
        // Configure FooService.getCloseable3(...).
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        final CloseableWithConstructor spyCloseableWithConstructor = spy(
                new CloseableWithConstructor("content", fooParams));
        final FooParams fooParams1 = new FooParams();
        fooParams1.setId("id");
        fooParams1.setName("name");
        when(mockFooService.getCloseable3(fooParams1)).thenReturn(spyCloseableWithConstructor);

        // Run the test
        final Closeable result = myClassUnderTest.doSomething11();

        // Verify the results
        verify(spyCloseableWithConstructor).close();
    }

    @Test
    public void testDoSomething12() throws Exception {
        // Setup
        // Configure FooService.getCloseable4(...).
        final FooParams fooParams = new FooParams();
        fooParams.setId("id");
        fooParams.setName("name");
        final CloseableWithConstructor spyCloseableWithConstructor = spy(
                new CloseableWithConstructor("content", fooParams));
        when(mockFooService.getCloseable4()).thenReturn(spyCloseableWithConstructor);

        // Run the test
        final Closeable result = myClassUnderTest.doSomething12();

        // Verify the results
        verify(spyCloseableWithConstructor).close();
    }
}
