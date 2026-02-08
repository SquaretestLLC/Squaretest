package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testFrom() {
        // Setup
        final MyData myData = new MyData("name", 0L, "path");

        // Run the test
        final MyClass result = MyClass.from(myData);
        assertThat(result.getName()).isEqualTo("name");
        assertThat(result.getId()).isEqualTo(0L);
        assertThat(result.getPath()).isEqualTo("path");
    }
}
