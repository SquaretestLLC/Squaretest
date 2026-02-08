package com.myapp

import groovy.transform.CompileStatic
import org.junit.jupiter.api.Test

import static org.assertj.core.api.Assertions.assertThat

@CompileStatic
class MyClassTest {

    @Test
    void testFromString() {
        // Run the test
        def result = MyClass.fromString("uriString")
        assertThat(result.isRelative()).isFalse()
        assertThat(result.isHierarchical()).isFalse()
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.isAbsolute()).isFalse()
    }

    @Test
    void testFromFile() {
        // Setup
        def file = new File("filename.txt")

        // Run the test
        def result = MyClass.fromFile(file)
        assertThat(result.isRelative()).isFalse()
        assertThat(result.isHierarchical()).isFalse()
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.isAbsolute()).isFalse()
    }

    @Test
    void testFromParts() {
        // Run the test
        def result = MyClass.fromParts("scheme", "ssp", "fragment")
        assertThat(result.isRelative()).isFalse()
        assertThat(result.isHierarchical()).isFalse()
        assertThat(result.getSchemeSpecificPart()).isEqualTo("result")
        assertThat(result.isAbsolute()).isFalse()
    }
}
