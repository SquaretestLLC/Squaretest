package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testAtLeast() {
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
    public void testAtMost() {
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
    public void testGetJavaVersion() {
        assertThat(MyClass.getJavaVersion("nom")).isEqualTo(MyClass.JAVA_0_9);
    }

    @Test
    public void testGet() {
        assertThat(MyClass.get("nom")).isEqualTo(MyClass.JAVA_0_9);
    }

    @Test
    public void testToString() {
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
}
