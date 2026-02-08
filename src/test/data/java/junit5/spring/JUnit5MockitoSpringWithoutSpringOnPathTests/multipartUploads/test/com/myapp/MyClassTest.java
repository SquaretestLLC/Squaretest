package com.myapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new ObjectMapper());
    }

    @Test
    void testStoreFile() {
        assertEquals("storeFileConfirmation", myClassUnderTest.storeFile(null));
    }

    @Test
    void testStoreFile1() {
        assertEquals("storeFile1Confirmation", myClassUnderTest.storeFile1(null));
    }

    @Test
    void testStoreFile2() {
        assertEquals("storeFile2Confirmation", myClassUnderTest.storeFile2(Mono.just(null)));
    }

    @Test
    void testStoreFile3() {
        assertEquals("storeFile3Confirmation", myClassUnderTest.storeFile3(null));
    }

    @Test
    void testStoreFile4() {
        assertEquals("storeFile4Confirmation", myClassUnderTest.storeFile4(null));
    }

    @Test
    void testStoreFile5() {
        assertEquals("storeFile5Confirmation", myClassUnderTest.storeFile5(null));
    }

    @Test
    void testStoreFile6() {
        assertEquals("storeFile6Confirmation", myClassUnderTest.storeFile6(null));
    }

    @Test
    void testStoreDataWithFiles() throws Exception {
        // Setup
        final JsonDto jsonFile = new JsonDto();
        jsonFile.setName("name");
        jsonFile.setDisplayName("displayName");
        jsonFile.setId(0L);

        final List<MultipartFile> files = Arrays.asList();

        // Run the test
        final String result = myClassUnderTest.storeDataWithFiles("theFormParam1", "theFormParam2", jsonFile, files);

        // Verify the results
        assertEquals("storeDataWithFilesConfirmation", result);
    }

    @Test
    void testStoreDataWithFiles_ThrowsException() {
        // Setup
        final JsonDto jsonFile = new JsonDto();
        jsonFile.setName("name");
        jsonFile.setDisplayName("displayName");
        jsonFile.setId(0L);

        final List<MultipartFile> files = Arrays.asList();

        // Run the test
        assertThrows(Exception.class,
                () -> myClassUnderTest.storeDataWithFiles("theFormParam1", "theFormParam2", jsonFile, files));
    }

    @Test
    void testStoreDataWithFiles0() throws Exception {
        // Setup
        final JsonDto jsonFile = new JsonDto();
        jsonFile.setName("name");
        jsonFile.setDisplayName("displayName");
        jsonFile.setId(0L);

        final MultipartFile[] files = new MultipartFile[]{};

        // Run the test
        final String result = myClassUnderTest.storeDataWithFiles0("theFormParam1", "theFormParam2", jsonFile, files);

        // Verify the results
        assertEquals("storeDataWithFilesConfirmation", result);
    }

    @Test
    void testStoreDataWithFiles0_ThrowsException() {
        // Setup
        final JsonDto jsonFile = new JsonDto();
        jsonFile.setName("name");
        jsonFile.setDisplayName("displayName");
        jsonFile.setId(0L);

        final MultipartFile[] files = new MultipartFile[]{};

        // Run the test
        assertThrows(Exception.class,
                () -> myClassUnderTest.storeDataWithFiles0("theFormParam1", "theFormParam2", jsonFile, files));
    }

    @Test
    void testStoreDataWithFiles1() {
        assertEquals("storeDataWithFiles1Confirmation1", myClassUnderTest.storeDataWithFiles1(new FormBeanWithFile()));
    }

    @Test
    void testStoreDataWithFiles2() {
        assertEquals("storeDataWithFiles1Confirmation2",
                myClassUnderTest.storeDataWithFiles2(new FormBeanWithFileList()));
    }

    @Test
    void testStoreDataWithFiles3() {
        assertEquals("storeDataWithFiles1Confirmation3",
                myClassUnderTest.storeDataWithFiles3(new FormBeanWithFileArray()));
    }

    @Test
    void testStoreDataWithFiles4() {
        assertEquals("storeDataWithFiles4Confirmation1",
                myClassUnderTest.storeDataWithFiles4(new FormBeanWithFile(), null));
    }

    @Test
    void testSubmitFormWithNestedTypes() {
        assertEquals("submitFormWithNestedTypesConfirmation",
                myClassUnderTest.submitFormWithNestedTypes(new FormWithNestedObjects()));
    }

    @Test
    void testSubmitFormWithNestedTypes1() {
        assertEquals("submitFormWithNestedTypesConfirmation1",
                myClassUnderTest.submitFormWithNestedTypes1(new FormWithNestedObjects()));
    }
}
