package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testStoreFile() {
        assertThat(myClassUnderTest.storeFile(null)).isNull();
    }

    @Test
    void testStoreFile1() {
        assertThat(myClassUnderTest.storeFile1(null)).isNull();
    }

    @Test
    void testStoreFile2() {
        assertThat(myClassUnderTest.storeFile2(Mono.just(null))).isNull();
    }

    @Test
    void testStoreFile3() {
        assertThat(myClassUnderTest.storeFile3(null)).isNull();
    }

    @Test
    void testStoreFile4() {
        assertThat(myClassUnderTest.storeFile4(null)).isNull();
    }

    @Test
    void testStoreFile5() {
        assertThat(myClassUnderTest.storeFile5(null)).isNull();
    }

    @Test
    void testStoreFile6() {
        assertThat(myClassUnderTest.storeFile6(null)).isNull();
    }

    @Test
    void testStoreDataWithFiles() throws Exception {
        assertThat(myClassUnderTest.storeDataWithFiles("theFormParam1", "theFormParam2", new JsonDto(),
                Arrays.asList())).isNull();
    }

    @Test
    void testStoreDataWithFiles0() throws Exception {
        assertThat(myClassUnderTest.storeDataWithFiles0("theFormParam1", "theFormParam2", new JsonDto(),
                new MultipartFile[]{})).isNull();
    }

    @Test
    void testStoreDataWithFiles1() {
        assertThat(myClassUnderTest.storeDataWithFiles1(new FormBeanWithFile())).isNull();
    }

    @Test
    void testStoreDataWithFiles2() {
        assertThat(myClassUnderTest.storeDataWithFiles2(new FormBeanWithFileList())).isNull();
    }

    @Test
    void testStoreDataWithFiles3() {
        assertThat(myClassUnderTest.storeDataWithFiles3(new FormBeanWithFileArray())).isNull();
    }

    @Test
    void testStoreDataWithFiles4() {
        assertThat(myClassUnderTest.storeDataWithFiles4(new FormBeanWithFile(), null)).isNull();
    }

    @Test
    void testSubmitFormWithNestedTypes() {
        assertThat(myClassUnderTest.submitFormWithNestedTypes(new FormWithNestedObjects())).isNull();
    }

    @Test
    void testSubmitFormWithNestedTypes1() {
        assertThat(myClassUnderTest.submitFormWithNestedTypes1(new FormWithNestedObjects())).isNull();
    }
}
