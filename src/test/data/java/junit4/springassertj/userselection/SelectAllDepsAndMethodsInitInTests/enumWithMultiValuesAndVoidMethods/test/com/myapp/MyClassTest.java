package com.myapp;

import org.apache.commons.lang3.mutable.MutableInt;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testNormalize() {
        // Setup
        final MutableInt theIntToNormalize = new MutableInt(0);

        // Run the test
        MyClass.FirstSpace.normalize(theIntToNormalize);
        MyClass.SecondSpace.normalize(theIntToNormalize);
        MyClass.ThirdSpace.normalize(theIntToNormalize);
        MyClass.FourthSpace.normalize(theIntToNormalize);

        // Verify the results
    }

    @Test
    public void testNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.normalizeNoArg();
        MyClass.SecondSpace.normalizeNoArg();
        MyClass.ThirdSpace.normalizeNoArg();
        MyClass.FourthSpace.normalizeNoArg();

        // Verify the results
    }

    @Test
    public void testTryNormalize() {
        // Setup
        final MutableInt someInt = new MutableInt(0);

        // Run the test
        MyClass.FirstSpace.tryNormalize(someInt);
        MyClass.SecondSpace.tryNormalize(someInt);
        MyClass.ThirdSpace.tryNormalize(someInt);
        MyClass.FourthSpace.tryNormalize(someInt);

        // Verify the results
    }

    @Test
    public void testTryNormalize_ThrowsRuntimeException() {
        assertThatThrownBy(() -> MyClass.FirstSpace.tryNormalize(new MutableInt(0)))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.SecondSpace.tryNormalize(new MutableInt(0)))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.ThirdSpace.tryNormalize(new MutableInt(0)))
                .isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.FourthSpace.tryNormalize(new MutableInt(0)))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testTryNormalizeNoArg() {
        // Run the test
        MyClass.FirstSpace.tryNormalizeNoArg();
        MyClass.SecondSpace.tryNormalizeNoArg();
        MyClass.ThirdSpace.tryNormalizeNoArg();
        MyClass.FourthSpace.tryNormalizeNoArg();

        // Verify the results
    }

    @Test
    public void testTryNormalizeNoArg_ThrowsRuntimeException() {
        assertThatThrownBy(() -> MyClass.FirstSpace.tryNormalizeNoArg()).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.SecondSpace.tryNormalizeNoArg()).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.ThirdSpace.tryNormalizeNoArg()).isInstanceOf(RuntimeException.class);
        assertThatThrownBy(() -> MyClass.FourthSpace.tryNormalizeNoArg()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testName() {
        assertThat(MyClass.FirstSpace.name()).isEqualTo("name");
        assertThat(MyClass.SecondSpace.name()).isEqualTo("name");
        assertThat(MyClass.ThirdSpace.name()).isEqualTo("name");
        assertThat(MyClass.FourthSpace.name()).isEqualTo("name");
    }

    @Test
    public void testOrdinal() {
        assertThat(MyClass.FirstSpace.ordinal()).isEqualTo(0);
        assertThat(MyClass.SecondSpace.ordinal()).isEqualTo(0);
        assertThat(MyClass.ThirdSpace.ordinal()).isEqualTo(0);
        assertThat(MyClass.FourthSpace.ordinal()).isEqualTo(0);
    }

    @Test
    public void testToString() {
        assertThat(MyClass.FirstSpace.toString()).isEqualTo("name");
        assertThat(MyClass.SecondSpace.toString()).isEqualTo("name");
        assertThat(MyClass.ThirdSpace.toString()).isEqualTo("name");
        assertThat(MyClass.FourthSpace.toString()).isEqualTo("name");
    }

    @Test
    public void testEquals() {
        assertThat(MyClass.FirstSpace.equals("other")).isFalse();
        assertThat(MyClass.SecondSpace.equals("other")).isFalse();
        assertThat(MyClass.ThirdSpace.equals("other")).isFalse();
        assertThat(MyClass.FourthSpace.equals("other")).isFalse();
    }

    @Test
    public void testHashCode() {
        assertThat(MyClass.FirstSpace.hashCode()).isEqualTo(0);
        assertThat(MyClass.SecondSpace.hashCode()).isEqualTo(0);
        assertThat(MyClass.ThirdSpace.hashCode()).isEqualTo(0);
        assertThat(MyClass.FourthSpace.hashCode()).isEqualTo(0);
    }

    @Test
    public void testCompareTo() {
        assertThat(MyClass.FirstSpace.compareTo(MyClass.FirstSpace)).isEqualTo(0);
        assertThat(MyClass.SecondSpace.compareTo(MyClass.FirstSpace)).isEqualTo(0);
        assertThat(MyClass.ThirdSpace.compareTo(MyClass.FirstSpace)).isEqualTo(0);
        assertThat(MyClass.FourthSpace.compareTo(MyClass.FirstSpace)).isEqualTo(0);
    }

    @Test
    public void testCompareTo_ThrowsNullPointerException() {
        assertThatThrownBy(() -> MyClass.FirstSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.SecondSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.ThirdSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.FourthSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCompareTo_ThrowsClassCastException() {
        assertThatThrownBy(() -> MyClass.FirstSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.SecondSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.ThirdSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.FourthSpace.compareTo(MyClass.FirstSpace))
                .isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testGetDeclaringClass() {
        assertThat(MyClass.FirstSpace.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.SecondSpace.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.ThirdSpace.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.FourthSpace.getDeclaringClass()).isEqualTo(MyClass.class);
    }

    @Test
    public void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.FirstSpace);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(NullPointerException.class);
    }
}
