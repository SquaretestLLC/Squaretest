package com.myapp;

import org.apache.commons.io.input.BrokenInputStream;
import org.apache.commons.io.output.BrokenOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private InputProvider mockInputProvider;
    @Mock
    private OutputProvider mockOutputProvider;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockInputProvider, mockOutputProvider, mockMetricService);
    }

    @Test
    void testDoCopy1() throws Exception {
        // Setup
        // Configure InputProvider.openInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockInputProvider.openInputStream()).thenReturn(spyInputStream);

        // Configure OutputProvider.openOutputStream(...).
        final OutputStream spyOutputStream = spy(new ByteArrayOutputStream());
        when(mockOutputProvider.openOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        myClassUnderTest.doCopy1();

        // Verify the results
        verify(spyInputStream).close();
        verify(spyOutputStream).close();
        verify(mockMetricService).recordCopySuccessful();
    }

    @Test
    void testDoCopy1_InputProviderReturnsNull() throws Exception {
        // Setup
        when(mockInputProvider.openInputStream()).thenReturn(null);

        // Configure OutputProvider.openOutputStream(...).
        final OutputStream spyOutputStream = spy(new ByteArrayOutputStream());
        when(mockOutputProvider.openOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        myClassUnderTest.doCopy1();

        // Verify the results
        verify(spyOutputStream).close();
        verify(mockMetricService).recordInputOrOutputEqualsNull();
    }

    @Test
    void testDoCopy1_InputProviderReturnsNoContent() throws Exception {
        // Setup
        // Configure InputProvider.openInputStream(...).
        final InputStream spyInputStream = spy(InputStream.nullInputStream());
        when(mockInputProvider.openInputStream()).thenReturn(spyInputStream);

        // Configure OutputProvider.openOutputStream(...).
        final OutputStream spyOutputStream = spy(new ByteArrayOutputStream());
        when(mockOutputProvider.openOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        myClassUnderTest.doCopy1();

        // Verify the results
        verify(spyInputStream).close();
        verify(spyOutputStream).close();
        verify(mockMetricService).recordCopySuccessful();
    }

    @Test
    void testDoCopy1_InputProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure InputProvider.openInputStream(...).
        final InputStream spyInputStream = spy(new BrokenInputStream());
        when(mockInputProvider.openInputStream()).thenReturn(spyInputStream);

        // Configure OutputProvider.openOutputStream(...).
        final OutputStream spyOutputStream = spy(new ByteArrayOutputStream());
        when(mockOutputProvider.openOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        assertThrows(BrokenIOException.class, () -> myClassUnderTest.doCopy1());
        verify(spyInputStream).close();
        verify(spyOutputStream).close();
        verify(mockMetricService).recordIOException(any(IOException.class));
    }

    @Test
    void testDoCopy1_OutputProviderReturnsNull() throws Exception {
        // Setup
        // Configure InputProvider.openInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockInputProvider.openInputStream()).thenReturn(spyInputStream);

        when(mockOutputProvider.openOutputStream()).thenReturn(null);

        // Run the test
        myClassUnderTest.doCopy1();

        // Verify the results
        verify(spyInputStream).close();
        verify(mockMetricService).recordInputOrOutputEqualsNull();
    }

    @Test
    void testDoCopy1_OutputProviderReturnsBrokenIo() throws Exception {
        // Setup
        // Configure InputProvider.openInputStream(...).
        final InputStream spyInputStream = spy(new ByteArrayInputStream("content".getBytes()));
        when(mockInputProvider.openInputStream()).thenReturn(spyInputStream);

        // Configure OutputProvider.openOutputStream(...).
        final OutputStream spyOutputStream = spy(new BrokenOutputStream());
        when(mockOutputProvider.openOutputStream()).thenReturn(spyOutputStream);

        // Run the test
        assertThrows(BrokenIOException.class, () -> myClassUnderTest.doCopy1());
        verify(spyInputStream).close();
        verify(spyOutputStream).close();
        verify(mockMetricService).recordIOException(any(IOException.class));
    }
}
