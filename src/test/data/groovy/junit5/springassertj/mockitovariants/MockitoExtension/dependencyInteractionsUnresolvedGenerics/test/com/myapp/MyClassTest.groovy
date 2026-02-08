package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.ArgumentMatchers.any
import static org.mockito.Mockito.when

@ExtendWith(MockitoExtension.class)
@CompileStatic
class MyClassTest {

    @Mock
    private Producer<String> mockProducer

    private MyClass<String> myClassUnderTest

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>(mockProducer)
    }

    @Test
    void testCreateNewT1() {
        // Setup
        when(mockProducer.createNewT("theString")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewT("theString")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewT2() {
        // Setup
        when(mockProducer.createNewT("templateT")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewT("templateT")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewK1() {
        // Setup
        when(mockProducer.createNewK("templateK")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewK("templateK")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewK2() {
        assertThat(myClassUnderTest.createNewK2("templateK")).isNull()
    }

    @Test
    void testCreateNewK2() {
        // Setup
        when(mockProducer.createNewK(String.class, "key")).thenReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewK(String.class, "key")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewBar() {
        // Setup
        when(mockProducer.createNewK(any(MyClass.Bar.class))).thenReturn(new MyClass.Bar())

        // Run the test
        def result = myClassUnderTest.createNewBar()

        // Verify the results
    }

    @Test
    void testCreateNewBar2() {
        // Setup
        when(mockProducer.createNewK(MyClass.Bar.class, "bob")).thenReturn(new MyClass.Bar())

        // Run the test
        def result = myClassUnderTest.createNewBar2()

        // Verify the results
    }

    @Test
    void testCreateNewGenericBar() {
        // Setup
        def bar = new MyClass.Bar()
        when(mockProducer.createNewK(any(MyClass.GenericBar.class)))
                .thenReturn(new MyClass.GenericBar<>(new MyClass.Bar()))

        // Run the test
        def result = myClassUnderTest.createNewGenericBar(bar)

        // Verify the results
    }

    @Test
    void testCreateNewGenericBar2() {
        // Setup
        when(mockProducer.createNewK(MyClass.GenericBar.class, "bob")).thenReturn(new MyClass.GenericBar<>("t"))

        // Run the test
        def result = myClassUnderTest.createNewGenericBar2(MyClass.GenericBar.class)

        // Verify the results
    }
}
