package com.myapp;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    @Test
    void testFromString() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertThat(result.isRelative()).isFalse();
        assertThat(result.isHierarchical()).isFalse();
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result");
        assertThat(result.isAbsolute()).isFalse();
    }

    @Test
    void testFromFile() {
        // Setup
        final File file = new File("filename.txt");

        // Run the test
        final MyClass result = MyClass.fromFile(file);
        assertThat(result.isRelative()).isFalse();
        assertThat(result.isHierarchical()).isFalse();
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result");
        assertThat(result.isAbsolute()).isFalse();
    }

    @Test
    void testFromParts() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertThat(result.isRelative()).isFalse();
        assertThat(result.isHierarchical()).isFalse();
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result");
        assertThat(result.isAbsolute()).isFalse();
    }
}
