package com.myapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    private AutoCloseable mockitoCloseable;

    @BeforeEach
    void setUp() {
        mockitoCloseable = openMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockitoCloseable.close();
    }

    @Test
    void testGetTheInts() {
        // Setup
        when(mockFooCreator.getTheInts()).thenReturn(new int[]{0});

        // Run the test
        final int[] result = myClassUnderTest.getTheInts();

        // Verify the results
        assertThat(result).isEqualTo(new int[]{0});
    }

    @Test
    void testGetTheInts_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheInts()).thenReturn(new int[]{});

        // Run the test
        final int[] result = myClassUnderTest.getTheInts();

        // Verify the results
        assertThat(result).isEqualTo(new int[]{});
    }

    @Test
    void testGetTheIntCube() {
        // Setup
        final int[][][] expectedResult = new int[][][]{{{0}}};
        when(mockFooCreator.getTheIntCube()).thenReturn(new int[][][]{{{0}}});

        // Run the test
        final int[][][] result = myClassUnderTest.getTheIntCube();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTheIntCube_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheIntCube()).thenReturn(new int[][][]{});

        // Run the test
        final int[][][] result = myClassUnderTest.getTheIntCube();

        // Verify the results
        assertThat(result).isEqualTo(new int[][][]{});
    }

    @Test
    void testGetTheIntArrays() {
        // Setup
        when(mockFooCreator.getTheIntArrays()).thenReturn(Arrays.asList(new int[]{0}));

        // Run the test
        final List<int[]> result = myClassUnderTest.getTheIntArrays();

        // Verify the results
    }

    @Test
    void testGetTheIntArrays_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheIntArrays()).thenReturn(Collections.emptyList());

        // Run the test
        final List<int[]> result = myClassUnderTest.getTheIntArrays();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTheTables() {
        // Setup
        when(mockFooCreator.getTheTables()).thenReturn(Arrays.asList(new String[][]{{"value"}}));

        // Run the test
        final List<String[][]> result = myClassUnderTest.getTheTables();

        // Verify the results
    }

    @Test
    void testGetTheTables_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheTables()).thenReturn(Collections.emptyList());

        // Run the test
        final List<String[][]> result = myClassUnderTest.getTheTables();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTheBeans() {
        // Setup
        // Configure FooCreator.getTheBeans(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final Bean[] beans = new Bean[]{bean};
        when(mockFooCreator.getTheBeans()).thenReturn(beans);

        // Run the test
        final Bean[] result = myClassUnderTest.getTheBeans();

        // Verify the results
    }

    @Test
    void testGetTheBeans_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheBeans()).thenReturn(new Bean[]{});

        // Run the test
        final Bean[] result = myClassUnderTest.getTheBeans();

        // Verify the results
        assertThat(result).isEqualTo(new Bean[]{});
    }

    @Test
    void testGetTheBeanMatrix() {
        // Setup
        // Configure FooCreator.getTheBeanMatrix(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final Bean[][] beans = new Bean[][]{{bean}};
        when(mockFooCreator.getTheBeanMatrix()).thenReturn(beans);

        // Run the test
        final Bean[][] result = myClassUnderTest.getTheBeanMatrix();

        // Verify the results
    }

    @Test
    void testGetTheBeanMatrix_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheBeanMatrix()).thenReturn(new Bean[][]{});

        // Run the test
        final Bean[][] result = myClassUnderTest.getTheBeanMatrix();

        // Verify the results
        assertThat(result).isEqualTo(new Bean[][]{});
    }

    @Test
    void testGetTheBeanArrays() {
        // Setup
        // Configure FooCreator.getTheBeanArrays(...).
        final Bean bean = new Bean();
        final FooData fooData = new FooData();
        fooData.setPurchaseId("purchaseId");
        fooData.setNameOnTheLicense("nameOnTheLicense");
        fooData.setOtherData(new OtherData("dataName"));
        bean.setFooData(fooData);
        bean.setTheString("theString");
        final List<Bean[]> beans = Arrays.asList(new Bean[]{bean});
        when(mockFooCreator.getTheBeanArrays()).thenReturn(beans);

        // Run the test
        final List<Bean[]> result = myClassUnderTest.getTheBeanArrays();

        // Verify the results
    }

    @Test
    void testGetTheBeanArrays_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheBeanArrays()).thenReturn(Collections.emptyList());

        // Run the test
        final List<Bean[]> result = myClassUnderTest.getTheBeanArrays();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTheByteArray() {
        // Setup
        when(mockFooCreator.getTheByteArray()).thenReturn("content".getBytes());

        // Run the test
        final byte[] result = myClassUnderTest.getTheByteArray();

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testGetTheByteMatrix() {
        // Setup
        final byte[][] expectedResult = new byte[][]{};
        when(mockFooCreator.getTheByteMatrix()).thenReturn(new byte[][]{});

        // Run the test
        final byte[][] result = myClassUnderTest.getTheByteMatrix();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTheByteMatrix_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheByteMatrix()).thenReturn(new byte[][]{});

        // Run the test
        final byte[][] result = myClassUnderTest.getTheByteMatrix();

        // Verify the results
        assertThat(result).isEqualTo(new byte[][]{});
    }

    @Test
    void testGetTyeByteArrays() {
        // Setup
        when(mockFooCreator.getTheByteArrays()).thenReturn(Arrays.asList("content".getBytes()));

        // Run the test
        final List<byte[]> result = myClassUnderTest.getTyeByteArrays();

        // Verify the results
    }

    @Test
    void testGetTyeByteArrays_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheByteArrays()).thenReturn(Collections.emptyList());

        // Run the test
        final List<byte[]> result = myClassUnderTest.getTyeByteArrays();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTyeByteMatricies() {
        // Setup
        when(mockFooCreator.getTheByteMatricies()).thenReturn(Arrays.asList(new byte[][]{}));

        // Run the test
        final List<byte[][]> result = myClassUnderTest.getTyeByteMatricies();

        // Verify the results
    }

    @Test
    void testGetTyeByteMatricies_FooCreatorReturnsNoItems() {
        // Setup
        when(mockFooCreator.getTheByteMatricies()).thenReturn(Collections.emptyList());

        // Run the test
        final List<byte[][]> result = myClassUnderTest.getTyeByteMatricies();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
