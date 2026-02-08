package com.myapp;

import org.junit.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class MyClassTest {

    @Test
    public void testFromString1() {
        // Run the test
        final MyClass result = MyClass.fromString("uriString");
        assertThat(result.isRelative()).isFalse();
        assertThat(result.isHierarchical()).isFalse();
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result");
        assertThat(result.isAbsolute()).isFalse();
    }

    @Test
    public void testFromFile1() {
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
    public void testFromParts1() {
        // Run the test
        final MyClass result = MyClass.fromParts("scheme", "ssp", "fragment");
        assertThat(result.isRelative()).isFalse();
        assertThat(result.isHierarchical()).isFalse();
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result");
        assertThat(result.isAbsolute()).isFalse();
    }
}