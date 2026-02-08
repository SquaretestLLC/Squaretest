package com.myapp;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testFrom() {
        // Setup
        final MyData myData = new MyData("name", 0L, "path");

        // Run the test
        final MyClass result = MyClass.from(myData);
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getId()).isEqualTo(0L);
        assertThat(result.getPath()).isEqualTo("path");
    }

    @Test
    void testGetConstantVal() {
        assertThat(MyClassBase.getConstantVal()).isEqualTo("ignored");
    }
}
