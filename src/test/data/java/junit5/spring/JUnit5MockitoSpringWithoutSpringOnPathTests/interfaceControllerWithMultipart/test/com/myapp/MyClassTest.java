package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNull;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass();
    }

    @Test
    void testStoreFile() {
        assertNull(myClassUnderTest.storeFile(null));
    }

    @Test
    void testStoreFile1() {
        assertNull(myClassUnderTest.storeFile1(null));
    }

    @Test
    void testStoreFile2() {
        assertNull(myClassUnderTest.storeFile2(Mono.just(null)));
    }

    @Test
    void testStoreFile3() {
        assertNull(myClassUnderTest.storeFile3(null));
    }

    @Test
    void testStoreFile4() {
        assertNull(myClassUnderTest.storeFile4(null));
    }

    @Test
    void testStoreFile5() {
        assertNull(myClassUnderTest.storeFile5(null));
    }

    @Test
    void testStoreFile6() {
        assertNull(myClassUnderTest.storeFile6(null));
    }

    @Test
    void testStoreDataWithFiles() throws Exception {
        assertNull(
                myClassUnderTest.storeDataWithFiles("theFormParam1", "theFormParam2", new JsonDto(), Arrays.asList()));
    }

    @Test
    void testStoreDataWithFiles0() throws Exception {
        assertNull(myClassUnderTest.storeDataWithFiles0("theFormParam1", "theFormParam2", new JsonDto(),
                new MultipartFile[]{}));
    }

    @Test
    void testStoreDataWithFiles1() {
        assertNull(myClassUnderTest.storeDataWithFiles1(new FormBeanWithFile()));
    }

    @Test
    void testStoreDataWithFiles2() {
        assertNull(myClassUnderTest.storeDataWithFiles2(new FormBeanWithFileList()));
    }

    @Test
    void testStoreDataWithFiles3() {
        assertNull(myClassUnderTest.storeDataWithFiles3(new FormBeanWithFileArray()));
    }

    @Test
    void testStoreDataWithFiles4() {
        assertNull(myClassUnderTest.storeDataWithFiles4(new FormBeanWithFile(), null));
    }

    @Test
    void testSubmitFormWithNestedTypes() {
        assertNull(myClassUnderTest.submitFormWithNestedTypes(new FormWithNestedObjects()));
    }

    @Test
    void testSubmitFormWithNestedTypes1() {
        assertNull(myClassUnderTest.submitFormWithNestedTypes1(new FormWithNestedObjects()));
    }
}
