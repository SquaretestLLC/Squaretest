package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    @Test
    void testAtLeast() {
        assertThat(MyClass.JAVA_0_9.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_1.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_2.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_3.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_4.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_5.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_6.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_7.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_8.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_9.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_9.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_10.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_11.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_12.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_13.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_14.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_15.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_16.atLeast(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_RECENT.atLeast(MyClass.JAVA_0_9)).isFalse();
    }

    @Test
    void testAtMost() {
        assertThat(MyClass.JAVA_0_9.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_1.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_2.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_3.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_4.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_5.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_6.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_7.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_8.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_1_9.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_9.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_10.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_11.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_12.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_13.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_14.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_15.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_16.atMost(MyClass.JAVA_0_9)).isFalse();
        assertThat(MyClass.JAVA_RECENT.atMost(MyClass.JAVA_0_9)).isFalse();
    }

    @Test
    void testGetJavaVersion() {
        assertThat(MyClass.getJavaVersion("nom")).isEqualTo(MyClass.JAVA_0_9);
    }

    @Test
    void testGet() {
        assertThat(MyClass.get("nom")).isEqualTo(MyClass.JAVA_0_9);
    }

    @Test
    void testToString() {
        assertThat(MyClass.JAVA_0_9.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_1.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_2.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_3.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_4.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_5.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_6.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_7.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_8.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_9.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_9.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_10.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_11.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_12.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_13.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_14.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_15.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_16.toString()).isEqualTo("name");
        assertThat(MyClass.JAVA_RECENT.toString()).isEqualTo("name");
    }

    @Test
    void testName() {
        assertThat(MyClass.JAVA_0_9.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_1.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_2.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_3.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_4.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_5.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_6.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_7.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_8.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_1_9.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_9.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_10.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_11.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_12.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_13.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_14.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_15.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_16.name()).isEqualTo("name");
        assertThat(MyClass.JAVA_RECENT.name()).isEqualTo("name");
    }

    @Test
    void testOrdinal() {
        assertThat(MyClass.JAVA_0_9.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_1.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_2.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_3.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_4.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_5.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_6.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_7.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_8.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_9.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_9.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_10.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_11.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_12.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_13.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_14.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_15.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_16.ordinal()).isEqualTo(0);
        assertThat(MyClass.JAVA_RECENT.ordinal()).isEqualTo(0);
    }

    @Test
    void testEquals() {
        assertThat(MyClass.JAVA_0_9.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_1.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_2.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_3.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_4.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_5.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_6.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_7.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_8.equals("other")).isFalse();
        assertThat(MyClass.JAVA_1_9.equals("other")).isFalse();
        assertThat(MyClass.JAVA_9.equals("other")).isFalse();
        assertThat(MyClass.JAVA_10.equals("other")).isFalse();
        assertThat(MyClass.JAVA_11.equals("other")).isFalse();
        assertThat(MyClass.JAVA_12.equals("other")).isFalse();
        assertThat(MyClass.JAVA_13.equals("other")).isFalse();
        assertThat(MyClass.JAVA_14.equals("other")).isFalse();
        assertThat(MyClass.JAVA_15.equals("other")).isFalse();
        assertThat(MyClass.JAVA_16.equals("other")).isFalse();
        assertThat(MyClass.JAVA_RECENT.equals("other")).isFalse();
    }

    @Test
    void testHashCode() {
        assertThat(MyClass.JAVA_0_9.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_1.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_2.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_3.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_4.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_5.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_6.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_7.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_8.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_1_9.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_9.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_10.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_11.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_12.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_13.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_14.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_15.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_16.hashCode()).isEqualTo(0);
        assertThat(MyClass.JAVA_RECENT.hashCode()).isEqualTo(0);
    }

    @Test
    void testCompareTo() {
        assertThat(MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
        assertThat(MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9)).isEqualTo(0);
    }

    @Test
    void testCompareTo_ThrowsNullPointerException() {
        assertThatThrownBy(() -> MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9)).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    void testCompareTo_ThrowsClassCastException() {
        assertThatThrownBy(() -> MyClass.JAVA_0_9.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_1.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_2.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_3.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_4.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_5.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_6.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_7.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_8.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_1_9.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_9.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_10.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_11.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_12.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_13.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_14.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_15.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_16.compareTo(MyClass.JAVA_0_9)).isInstanceOf(ClassCastException.class);
        assertThatThrownBy(() -> MyClass.JAVA_RECENT.compareTo(MyClass.JAVA_0_9))
                .isInstanceOf(ClassCastException.class);
    }

    @Test
    void testGetDeclaringClass() {
        assertThat(MyClass.JAVA_0_9.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_1.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_2.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_3.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_4.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_5.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_6.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_7.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_8.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_1_9.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_9.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_10.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_11.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_12.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_13.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_14.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_15.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_16.getDeclaringClass()).isEqualTo(MyClass.class);
        assertThat(MyClass.JAVA_RECENT.getDeclaringClass()).isEqualTo(MyClass.class);
    }

    @Test
    void testValueOf() {
        assertThat(Enum.valueOf(MyClass.class, "name")).isEqualTo(MyClass.JAVA_0_9);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> Enum.valueOf(MyClass.class, "name")).isInstanceOf(NullPointerException.class);
    }
}
