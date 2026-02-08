package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.ArgumentMatchers.any
import static org.mockito.BDDMockito.given
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private Producer<String> mockProducer

    private MyClass<String> myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass<>(mockProducer)
    }

    @Test
    void testCreateNewT1() {
        // Setup
        given(mockProducer.createNewT("theString")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewT("theString")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewT2() {
        // Setup
        given(mockProducer.createNewT("templateT")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewT("templateT")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewK1() {
        // Setup
        given(mockProducer.createNewK("templateK")).willReturn("result")

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
        given(mockProducer.createNewK(String.class, "key")).willReturn("result")

        // Run the test
        def result = myClassUnderTest.createNewK(String.class, "key")

        // Verify the results
        assertThat(result).isEqualTo("result")
    }

    @Test
    void testCreateNewBar() {
        // Setup
        given(mockProducer.createNewK(any(MyClass.Bar.class))).willReturn(new MyClass.Bar())

        // Run the test
        def result = myClassUnderTest.createNewBar()

        // Verify the results
    }

    @Test
    void testCreateNewBar2() {
        // Setup
        given(mockProducer.createNewK(MyClass.Bar.class, "bob")).willReturn(new MyClass.Bar())

        // Run the test
        def result = myClassUnderTest.createNewBar2()

        // Verify the results
    }

    @Test
    void testCreateNewGenericBar() {
        // Setup
        def bar = new MyClass.Bar()
        given(mockProducer.createNewK(any(MyClass.GenericBar.class)))
                .willReturn(new MyClass.GenericBar<>(new MyClass.Bar()))

        // Run the test
        def result = myClassUnderTest.createNewGenericBar(bar)

        // Verify the results
    }

    @Test
    void testCreateNewGenericBar2() {
        // Setup
        given(mockProducer.createNewK(MyClass.GenericBar.class, "bob")).willReturn(new MyClass.GenericBar<>("t"))

        // Run the test
        def result = myClassUnderTest.createNewGenericBar2(MyClass.GenericBar.class)

        // Verify the results
    }
}
