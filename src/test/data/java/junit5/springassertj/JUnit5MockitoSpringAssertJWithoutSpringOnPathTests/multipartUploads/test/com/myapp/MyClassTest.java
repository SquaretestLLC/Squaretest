package com.myapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyClassTest {

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        myClassUnderTest = new MyClass(new ObjectMapper());
    }

    @Test
    void testStoreFile() {
        assertThat(myClassUnderTest.storeFile(null)).isEqualTo("storeFileConfirmation");
    }

    @Test
    void testStoreFile1() {
        assertThat(myClassUnderTest.storeFile1(null)).isEqualTo("storeFile1Confirmation");
    }

    @Test
    void testStoreFile2() {
        assertThat(myClassUnderTest.storeFile2(Mono.just(null))).isEqualTo("storeFile2Confirmation");
    }

    @Test
    void testStoreFile3() {
        assertThat(myClassUnderTest.storeFile3(null)).isEqualTo("storeFile3Confirmation");
    }

    @Test
    void testStoreFile4() {
        assertThat(myClassUnderTest.storeFile4(null)).isEqualTo("storeFile4Confirmation");
    }

    @Test
    void testStoreFile5() {
        assertThat(myClassUnderTest.storeFile5(null)).isEqualTo("storeFile5Confirmation");
    }

    @Test
    void testStoreFile6() {
        assertThat(myClassUnderTest.storeFile6(null)).isEqualTo("storeFile6Confirmation");
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
        assertThat(result).isEqualTo("storeDataWithFilesConfirmation");
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
        assertThatThrownBy(() -> myClassUnderTest.storeDataWithFiles("theFormParam1", "theFormParam2", jsonFile,
                files)).isInstanceOf(Exception.class);
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
        assertThat(result).isEqualTo("storeDataWithFilesConfirmation");
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
        assertThatThrownBy(() -> myClassUnderTest.storeDataWithFiles0("theFormParam1", "theFormParam2", jsonFile,
                files)).isInstanceOf(Exception.class);
    }

    @Test
    void testStoreDataWithFiles1() {
        assertThat(myClassUnderTest.storeDataWithFiles1(new FormBeanWithFile()))
                .isEqualTo("storeDataWithFiles1Confirmation1");
    }

    @Test
    void testStoreDataWithFiles2() {
        assertThat(myClassUnderTest.storeDataWithFiles2(new FormBeanWithFileList()))
                .isEqualTo("storeDataWithFiles1Confirmation2");
    }

    @Test
    void testStoreDataWithFiles3() {
        assertThat(myClassUnderTest.storeDataWithFiles3(new FormBeanWithFileArray()))
                .isEqualTo("storeDataWithFiles1Confirmation3");
    }

    @Test
    void testStoreDataWithFiles4() {
        assertThat(myClassUnderTest.storeDataWithFiles4(new FormBeanWithFile(), null))
                .isEqualTo("storeDataWithFiles4Confirmation1");
    }

    @Test
    void testSubmitFormWithNestedTypes() {
        assertThat(myClassUnderTest.submitFormWithNestedTypes(new FormWithNestedObjects()))
                .isEqualTo("submitFormWithNestedTypesConfirmation");
    }

    @Test
    void testSubmitFormWithNestedTypes1() {
        assertThat(myClassUnderTest.submitFormWithNestedTypes1(new FormWithNestedObjects()))
                .isEqualTo("submitFormWithNestedTypesConfirmation1");
    }
}
