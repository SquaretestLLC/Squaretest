package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    private MyClass<String, String> myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass<>();
    }

    @Test
    void testLeftGetterAndSetter() {
        final String left = "setKeyParam";
        myClassUnderTest.setKey(left);
        assertThat(myClassUnderTest.getKey()).isEqualTo(left);
    }

    @Test
    void testGetValue() {
        assertThat(myClassUnderTest.getValue()).isEqualTo("result");
    }

    @Test
    void testSetValue() {
        assertThat(myClassUnderTest.setValue("setValueParam")).isEqualTo("result");
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam"))
                .isInstanceOf(UnsupportedOperationException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam")).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam")).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam"))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> myClassUnderTest.setValue("setValueParam")).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void testComparingByKey1() {
        // Setup
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByKey();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testComparingByValue1() {
        // Setup
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByValue();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testComparingByKey2() {
        // Setup
        final Comparator<? super String> cmp = Comparator.comparing(Object::toString);
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByKey(cmp);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testComparingByValue2() {
        // Setup
        final Comparator<? super String> cmp = Comparator.comparing(Object::toString);
        final Comparator<Map.Entry<String, String>> expectedResult = Comparator.comparing(Object::toString);

        // Run the test
        final Comparator<Map.Entry<String, String>> result = Map.Entry.comparingByValue(cmp);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }
}
