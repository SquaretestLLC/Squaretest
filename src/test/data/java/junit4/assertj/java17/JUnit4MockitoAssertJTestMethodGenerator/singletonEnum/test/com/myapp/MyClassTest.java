package com.myapp;

import org.junit.Test;

import java.lang.constant.ClassDesc;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testCreateNewConnection1() throws Exception {
        assertThat(MyClass.INSTANCE.createNewConnection()).isNull();
    }

    @Test
    public void testDoSomething1() {
        MyClass.INSTANCE.doSomething();
    }

    @Test
    public void testName() {
        // Setup
        // Run the test
        final String result = MyClass.INSTANCE.name();

        // Verify the results
        assertThat(result).isEqualTo("name");
    }

    @Test
    public void testOrdinal() {
        // Setup
        // Run the test
        final int result = MyClass.INSTANCE.ordinal();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testToString() {
        // Setup
        // Run the test
        final String result = MyClass.INSTANCE.toString();

        // Verify the results
        assertThat(result).isEqualTo("name");
    }

    @Test
    public void testEquals() {
        // Setup
        // Run the test
        final boolean result = MyClass.INSTANCE.equals("other");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testHashCode() {
        // Setup
        // Run the test
        final int result = MyClass.INSTANCE.hashCode();

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCompareTo() {
        // Setup
        // Run the test
        final int result = MyClass.INSTANCE.compareTo(MyClass.INSTANCE);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }

    @Test
    public void testCompareTo_ThrowsNullPointerException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.INSTANCE.compareTo(MyClass.INSTANCE)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCompareTo_ThrowsClassCastException() {
        // Run the test
        assertThatThrownBy(() -> MyClass.INSTANCE.compareTo(MyClass.INSTANCE)).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testGetDeclaringClass() {
        // Setup
        // Run the test
        final Class<MyClass> result = MyClass.INSTANCE.getDeclaringClass();

        // Verify the results
        assertThat(result).isEqualTo(MyClass.class);
    }

    @Test
    public void testDescribeConstable() {
        // Setup
        final Optional<Enum.EnumDesc<MyClass>> expectedResult = Optional.of(
                Enum.EnumDesc.of(ClassDesc.of("name"), "name"));

        // Run the test
        final Optional<Enum.EnumDesc<MyClass>> result = MyClass.INSTANCE.describeConstable();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.INSTANCE);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(NullPointerException.class);
    }
}