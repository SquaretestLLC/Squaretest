package com.myapp;

import com.myapp.dtoio.*;
import com.myapp.dtoio.io.SpecialInputStream;
import com.myapp.dtoio.io.SpecialReader;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MyClassTest {

    @Mock
    private FooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @Test
    public void testMakeBeanWithIs() {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        final BeanWithIs beanWithIs = new BeanWithIs();
        beanWithIs.setId(0L);
        beanWithIs.setName("name");
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs);

        // Run the test
        final BeanWithIs result = myClassUnderTest.makeBeanWithIs();

        // Verify the results
    }

    @Test
    public void testMakeBeanWithIsAndReader() {
        // Setup
        // Configure FooProvider.makeBeanWithIsAndReader(...).
        final BeanWithIsAndReader beanWithIsAndReader = new BeanWithIsAndReader();
        beanWithIsAndReader.setId(0L);
        beanWithIsAndReader.setName("name");
        beanWithIsAndReader.setInputStream(new ByteArrayInputStream("content".getBytes()));
        beanWithIsAndReader.setReader(new StringReader("content"));
        when(mockFooProvider.makeBeanWithIsAndReader()).thenReturn(beanWithIsAndReader);

        // Run the test
        final BeanWithIsAndReader result = myClassUnderTest.makeBeanWithIsAndReader();

        // Verify the results
    }

    @Test
    public void testMakeBeanWithReader() {
        // Setup
        // Configure FooProvider.makeBeanWithReader(...).
        final BeanWithReader beanWithReader = new BeanWithReader();
        beanWithReader.setId(0L);
        beanWithReader.setName("name");
        beanWithReader.setReader(new StringReader("content"));
        when(mockFooProvider.makeBeanWithReader()).thenReturn(beanWithReader);

        // Run the test
        final BeanWithReader result = myClassUnderTest.makeBeanWithReader();

        // Verify the results
    }

    @Test
    public void testMakeBeanWithSubIs() {
        // Setup
        // Configure FooProvider.makeBeanWithSubIs(...).
        final BeanWithSubIs beanWithSubIs = new BeanWithSubIs();
        beanWithSubIs.setId(0L);
        beanWithSubIs.setName("name");
        beanWithSubIs.setInputStream(new SpecialInputStream(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeBeanWithSubIs()).thenReturn(beanWithSubIs);

        // Run the test
        final BeanWithSubIs result = myClassUnderTest.makeBeanWithSubIs();

        // Verify the results
    }

    @Test
    public void testMakeBeanWithSubIsMultipleSetterOverloads() {
        // Setup
        // Configure FooProvider.makeBeanWithSubIsMultipleSetterOverloads(...).
        final BeanWithSubIsMultipleSetterOverloads beanWithSubIsMultipleSetterOverloads = new BeanWithSubIsMultipleSetterOverloads();
        beanWithSubIsMultipleSetterOverloads.setId(0L);
        beanWithSubIsMultipleSetterOverloads.setName("name");
        beanWithSubIsMultipleSetterOverloads.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithSubIsMultipleSetterOverloads())
                .thenReturn(beanWithSubIsMultipleSetterOverloads);

        // Run the test
        final BeanWithSubIsMultipleSetterOverloads result = myClassUnderTest.makeBeanWithSubIsMultipleSetterOverloads();

        // Verify the results
    }

    @Test
    public void testMakeBeanWithSubReader() {
        // Setup
        // Configure FooProvider.makeBeanWithSubReader(...).
        final BeanWithSubReader beanWithSubReader = new BeanWithSubReader();
        beanWithSubReader.setId(0L);
        beanWithSubReader.setName("name");
        beanWithSubReader.setSpecialReader(new SpecialReader(new StringReader("content")));
        when(mockFooProvider.makeBeanWithSubReader()).thenReturn(beanWithSubReader);

        // Run the test
        final BeanWithSubReader result = myClassUnderTest.makeBeanWithSubReader();

        // Verify the results
    }

    @Test
    public void testMakeBeanWithSubReaderMultipleSetterOverloads() {
        // Setup
        // Configure FooProvider.makeBeanWithSubReaderMultipleSetterOverloads(...).
        final BeanWithSubReaderMultipleSetterOverloads beanWithSubReaderMultipleSetterOverloads = new BeanWithSubReaderMultipleSetterOverloads();
        beanWithSubReaderMultipleSetterOverloads.setId(0L);
        beanWithSubReaderMultipleSetterOverloads.setName("name");
        beanWithSubReaderMultipleSetterOverloads.setSpecialReader(new StringReader("content"));
        when(mockFooProvider.makeBeanWithSubReaderMultipleSetterOverloads())
                .thenReturn(beanWithSubReaderMultipleSetterOverloads);

        // Run the test
        final BeanWithSubReaderMultipleSetterOverloads result = myClassUnderTest.makeBeanWithSubReaderMultipleSetterOverloads();

        // Verify the results
    }

    @Test
    public void testMakeCloseableBeanWithIs() throws Exception {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithIs(...).
        final CloseableBeanWithIs spyCloseableBeanWithIs = spy(new CloseableBeanWithIs());
        spyCloseableBeanWithIs.setId(0L);
        spyCloseableBeanWithIs.setName("name");
        spyCloseableBeanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeCloseableBeanWithIs()).thenReturn(spyCloseableBeanWithIs);

        // Run the test
        final CloseableBeanWithIs result = myClassUnderTest.makeCloseableBeanWithIs();

        // Verify the results
        verify(spyCloseableBeanWithIs).close();
    }

    @Test
    public void testMakeCloseableBeanWithReader() throws Exception {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithReader(...).
        final CloseableBeanWithReader spyCloseableBeanWithReader = spy(new CloseableBeanWithReader());
        spyCloseableBeanWithReader.setId(0L);
        spyCloseableBeanWithReader.setName("name");
        spyCloseableBeanWithReader.setReader(new StringReader("content"));
        when(mockFooProvider.makeCloseableBeanWithReader()).thenReturn(spyCloseableBeanWithReader);

        // Run the test
        final CloseableBeanWithReader result = myClassUnderTest.makeCloseableBeanWithReader();

        // Verify the results
        verify(spyCloseableBeanWithReader).close();
    }
}
