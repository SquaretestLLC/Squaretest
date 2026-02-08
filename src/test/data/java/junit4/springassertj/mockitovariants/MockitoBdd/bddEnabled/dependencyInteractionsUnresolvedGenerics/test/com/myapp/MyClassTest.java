package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private Producer<String> mockProducer;

    private MyClass<String> myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass<>(mockProducer);
    }

    @Test
    public void testCreateNewT1() {
        // Setup
        given(mockProducer.createNewT("theString")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("theString");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCreateNewT2() {
        // Setup
        given(mockProducer.createNewT("templateT")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewT("templateT");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCreateNewK1() {
        // Setup
        given(mockProducer.createNewK("templateK")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK("templateK");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCreateNewK2() {
        assertThat(myClassUnderTest.createNewK2("templateK")).isNull();
    }

    @Test
    public void testCreateNewK2() {
        // Setup
        given(mockProducer.createNewK(String.class, "key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.createNewK(String.class, "key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testCreateNewBar() {
        // Setup
        given(mockProducer.createNewK(any(MyClass.Bar.class))).willReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar();

        // Verify the results
    }

    @Test
    public void testCreateNewBar2() {
        // Setup
        given(mockProducer.createNewK(MyClass.Bar.class, "bob")).willReturn(new MyClass.Bar());

        // Run the test
        final MyClass.Bar result = myClassUnderTest.createNewBar2();

        // Verify the results
    }

    @Test
    public void testCreateNewGenericBar() {
        // Setup
        final MyClass.Bar bar = new MyClass.Bar();
        given(mockProducer.createNewK(any(MyClass.GenericBar.class)))
                .willReturn(new MyClass.GenericBar<>(new MyClass.Bar()));

        // Run the test
        final MyClass.GenericBar<MyClass.Bar> result = myClassUnderTest.createNewGenericBar(bar);

        // Verify the results
    }

    @Test
    public void testCreateNewGenericBar2() {
        // Setup
        given(mockProducer.createNewK(MyClass.GenericBar.class, "bob")).willReturn(new MyClass.GenericBar<>("t"));

        // Run the test
        final MyClass.GenericBar result = myClassUnderTest.createNewGenericBar2(MyClass.GenericBar.class);

        // Verify the results
    }
}
