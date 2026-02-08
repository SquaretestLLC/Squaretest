package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooCreator mockFooCreator;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooCreator);
    }

    @Test
    void testGetTheInts() {
        // Setup
        given(mockFooCreator.getTheInts()).willReturn(new int[]{0});

        // Run the test
        final int[] result = myClassUnderTest.getTheInts();

        // Verify the results
        assertThat(result).isEqualTo(new int[]{0});
    }

    @Test
    void testGetTheInts_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheInts()).willReturn(new int[]{});

        // Run the test
        final int[] result = myClassUnderTest.getTheInts();

        // Verify the results
        assertThat(result).isEqualTo(new int[]{});
    }

    @Test
    void testGetTheIntCube() {
        // Setup
        final int[][][] expectedResult = new int[][][]{{{0}}};
        given(mockFooCreator.getTheIntCube()).willReturn(new int[][][]{{{0}}});

        // Run the test
        final int[][][] result = myClassUnderTest.getTheIntCube();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTheIntCube_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheIntCube()).willReturn(new int[][][]{});

        // Run the test
        final int[][][] result = myClassUnderTest.getTheIntCube();

        // Verify the results
        assertThat(result).isEqualTo(new int[][][]{});
    }

    @Test
    void testGetTheIntArrays() {
        // Setup
        given(mockFooCreator.getTheIntArrays()).willReturn(Arrays.asList(new int[]{0}));

        // Run the test
        final List<int[]> result = myClassUnderTest.getTheIntArrays();

        // Verify the results
    }

    @Test
    void testGetTheIntArrays_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheIntArrays()).willReturn(Collections.emptyList());

        // Run the test
        final List<int[]> result = myClassUnderTest.getTheIntArrays();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTheTables() {
        // Setup
        given(mockFooCreator.getTheTables()).willReturn(Arrays.asList(new String[][]{{"value"}}));

        // Run the test
        final List<String[][]> result = myClassUnderTest.getTheTables();

        // Verify the results
    }

    @Test
    void testGetTheTables_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheTables()).willReturn(Collections.emptyList());

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
        given(mockFooCreator.getTheBeans()).willReturn(beans);

        // Run the test
        final Bean[] result = myClassUnderTest.getTheBeans();

        // Verify the results
    }

    @Test
    void testGetTheBeans_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheBeans()).willReturn(new Bean[]{});

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
        given(mockFooCreator.getTheBeanMatrix()).willReturn(beans);

        // Run the test
        final Bean[][] result = myClassUnderTest.getTheBeanMatrix();

        // Verify the results
    }

    @Test
    void testGetTheBeanMatrix_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheBeanMatrix()).willReturn(new Bean[][]{});

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
        given(mockFooCreator.getTheBeanArrays()).willReturn(beans);

        // Run the test
        final List<Bean[]> result = myClassUnderTest.getTheBeanArrays();

        // Verify the results
    }

    @Test
    void testGetTheBeanArrays_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheBeanArrays()).willReturn(Collections.emptyList());

        // Run the test
        final List<Bean[]> result = myClassUnderTest.getTheBeanArrays();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTheByteArray() {
        // Setup
        given(mockFooCreator.getTheByteArray()).willReturn("content".getBytes());

        // Run the test
        final byte[] result = myClassUnderTest.getTheByteArray();

        // Verify the results
        assertThat(result).isEqualTo("content".getBytes());
    }

    @Test
    void testGetTheByteMatrix() {
        // Setup
        final byte[][] expectedResult = new byte[][]{};
        given(mockFooCreator.getTheByteMatrix()).willReturn(new byte[][]{});

        // Run the test
        final byte[][] result = myClassUnderTest.getTheByteMatrix();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTheByteMatrix_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheByteMatrix()).willReturn(new byte[][]{});

        // Run the test
        final byte[][] result = myClassUnderTest.getTheByteMatrix();

        // Verify the results
        assertThat(result).isEqualTo(new byte[][]{});
    }

    @Test
    void testGetTyeByteArrays() {
        // Setup
        given(mockFooCreator.getTheByteArrays()).willReturn(Arrays.asList("content".getBytes()));

        // Run the test
        final List<byte[]> result = myClassUnderTest.getTyeByteArrays();

        // Verify the results
    }

    @Test
    void testGetTyeByteArrays_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheByteArrays()).willReturn(Collections.emptyList());

        // Run the test
        final List<byte[]> result = myClassUnderTest.getTyeByteArrays();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    void testGetTyeByteMatricies() {
        // Setup
        given(mockFooCreator.getTheByteMatricies()).willReturn(Arrays.asList(new byte[][]{}));

        // Run the test
        final List<byte[][]> result = myClassUnderTest.getTyeByteMatricies();

        // Verify the results
    }

    @Test
    void testGetTyeByteMatricies_FooCreatorReturnsNoItems() {
        // Setup
        given(mockFooCreator.getTheByteMatricies()).willReturn(Collections.emptyList());

        // Run the test
        final List<byte[][]> result = myClassUnderTest.getTyeByteMatricies();

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }
}
