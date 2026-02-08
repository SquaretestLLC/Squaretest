package com.myapp;

import org.junit.Test;

import java.io.IOException;
import java.lang.constant.ClassDesc;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testGetLowercaseColor1() {
        assertThat(MyClass.RED.getLowercaseColor()).isEqualTo("result");
        assertThat(MyClass.BLUE.getLowercaseColor()).isEqualTo("result");
        assertThat(MyClass.GREEN.getLowercaseColor()).isEqualTo("result");
        assertThat(MyClass.PURPLE.getLowercaseColor()).isEqualTo("result");
    }

    @Test
    public void testGetUppercaseColor1() {
        assertThat(MyClass.RED.getUppercaseColor()).isEqualTo("result");
        assertThat(MyClass.BLUE.getUppercaseColor()).isEqualTo("result");
        assertThat(MyClass.GREEN.getUppercaseColor()).isEqualTo("result");
        assertThat(MyClass.PURPLE.getUppercaseColor()).isEqualTo("result");
    }

    @Test
    public void testIsGreen1() {
        assertThat(MyClass.RED.isGreen()).isFalse();
        assertThat(MyClass.BLUE.isGreen()).isFalse();
        assertThat(MyClass.GREEN.isGreen()).isFalse();
        assertThat(MyClass.PURPLE.isGreen()).isFalse();
    }

    @Test
    public void testSomethingThatThrows1() {
        assertThatThrownBy(() -> MyClass.RED.somethingThatThrows("arg")).isInstanceOf(IOException.class);
    }

    @Test
    public void testIsSupported1() {
        assertThat(MyClass.isSupported("colorName")).isFalse();
    }

    @Test
    public void testConvertTo1() {
        assertThatThrownBy(() -> MyClass.convertTo("name")).isInstanceOf(IOException.class);
    }

    @Test
    public void testConvertToSafe1() {
        assertThat(MyClass.convertToSafe("name")).isEqualTo("");
    }

    @Test
    public void testToString1() {
        assertThat(MyClass.RED.toString()).isEqualTo("result");
        assertThat(MyClass.BLUE.toString()).isEqualTo("result");
        assertThat(MyClass.GREEN.toString()).isEqualTo("result");
        assertThat(MyClass.PURPLE.toString()).isEqualTo("result");
    }

    @Test
    public void testName() {
        assertThat(MyClass.RED.name()).isEqualTo("name");
        assertThat(MyClass.BLUE.name()).isEqualTo("name");
        assertThat(MyClass.GREEN.name()).isEqualTo("name");
        assertThat(MyClass.PURPLE.name()).isEqualTo("name");
    }

    @Test
    public void testOrdinal() {
        assertThat(MyClass.RED.ordinal()).isEqualTo(0);
        assertThat(MyClass.BLUE.ordinal()).isEqualTo(0);
        assertThat(MyClass.GREEN.ordinal()).isEqualTo(0);
        assertThat(MyClass.PURPLE.ordinal()).isEqualTo(0);
    }

    @Test
    public void testEquals() {
        assertThat(MyClass.RED.equals("other")).isFalse();
        assertThat(MyClass.BLUE.equals("other")).isFalse();
        assertThat(MyClass.GREEN.equals("other")).isFalse();
        assertThat(MyClass.PURPLE.equals("other")).isFalse();
    }

    @Test
    public void testHashCode() {
        assertThat(MyClass.RED.hashCode()).isEqualTo(0);
        assertThat(MyClass.BLUE.hashCode()).isEqualTo(0);
        assertThat(MyClass.GREEN.hashCode()).isEqualTo(0);
        assertThat(MyClass.PURPLE.hashCode()).isEqualTo(0);
    }

    @Test
    public void testCompareTo() {
        assertThat(MyClass.RED.compareTo(MyClass.RED)).isEqualTo(0);
        assertThat(MyClass.BLUE.compareTo(MyClass.RED)).isEqualTo(0);
        assertThat(MyClass.GREEN.compareTo(MyClass.RED)).isEqualTo(0);
        assertThat(MyClass.PURPLE.compareTo(MyClass.RED)).isEqualTo(0);
    }

    @Test
    public void testCompareTo_ThrowsNullPointerException() {
        assertThatThrownBy(() -> MyClass.RED.compareTo(MyClass.RED)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.BLUE.compareTo(MyClass.RED)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.GREEN.compareTo(MyClass.RED)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.PURPLE.compareTo(MyClass.RED)).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void testCompareTo_ThrowsClassCastException() {
        assertThatThrownBy(() -> MyClass.RED.compareTo(MyClass.RED)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.BLUE.compareTo(MyClass.RED)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.GREEN.compareTo(MyClass.RED)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.PURPLE.compareTo(MyClass.RED)).isInstanceOf(ClassCastException.class);
    }

    @Test
    public void testGetDeclaringClass() {
        assertThat(MyClass.RED.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.BLUE.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.GREEN.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.PURPLE.getDeclaringClass()).isEqualTo(MyClass.class);
    }

    @Test
    public void testDescribeConstable() {
        assertThat(MyClass.RED.describeConstable())
                .isEqualTo(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")));
        assertThat(MyClass.BLUE.describeConstable())
                .isEqualTo(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")));
        assertThat(MyClass.GREEN.describeConstable())
                .isEqualTo(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")));
        assertThat(MyClass.PURPLE.describeConstable())
                .isEqualTo(Optional.of(Enum.EnumDesc.of(ClassDesc.of("name"), "name")));
    }

    @Test
    public void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.RED);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(NullPointerException.class);
    }
}