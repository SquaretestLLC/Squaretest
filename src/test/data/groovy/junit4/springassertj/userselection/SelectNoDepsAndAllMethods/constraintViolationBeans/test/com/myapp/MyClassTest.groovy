package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy

@CompileStatic
class MyClassTest {

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
    }

    @Test
    void testStoreFoo1() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo1(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo1_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo1(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo2() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo2(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo2_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo2(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo3() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo3(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo3_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo3(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo4() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo4(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo4_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo4(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo5() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo5(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo5_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo5(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo6() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo6(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo6_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo6(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo7() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo7(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo7_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo7_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(FooAlreadyExistsException.class)
    }

    @Test
    void testStoreFoo8() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo8(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo8_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo8_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo8_ThrowsFooAlreadyExistsException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(FooAlreadyExistsException.class)
    }

    @Test
    void testStoreFoo9() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo9(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo9_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo9_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo9_ThrowsFooDoesNotExistException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testStoreFoo10() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo10(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo10_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo10_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo10_ThrowsFooDoesNotExistException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testStoreFoo11() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo11(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo11_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo11_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo11_ThrowsFooAlreadyExistsException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(FooAlreadyExistsException.class)
    }

    @Test
    void testStoreFoo12() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo12(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo12_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo12_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo12_ThrowsFooDoesNotExistException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testStoreFoo13() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo13(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo13_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo13(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo13_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo13(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo14() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo14(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo14_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo14_ThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo14_ThrowsFooDoesNotExistException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testGetFooById1() {
        // Setup
        def expectedResult = new FooData("id", "name")

        // Run the test
        def result = myClassUnderTest.getFooById1("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooById1_ThrowsGetFooException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById1("id")
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testGetFooById1_ThrowsFooDoesNotExistException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById1("id")
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testGetFooById2() {
        // Setup
        def expectedResult = new FooData("id", "name")

        // Run the test
        def result = myClassUnderTest.getFooById2("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooById2_ThrowsGetFooException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById2("id")
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testGetFooById2_ThrowsFooDoesNotExistException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById2("id")
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testGetFooById3() {
        // Setup
        def expectedResult = new FooData("id", "name")

        // Run the test
        def result = myClassUnderTest.getFooById3("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooById3_ThrowsGetFooException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById3("id")
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testGetFooById3_ThrowsFooDoesNotExistException() {
        // Setup
        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById3("id")
        }).isInstanceOf(FooDoesNotExistException.class)
    }

    @Test
    void testStoreFoo15() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo15(fooData)

        // Verify the results
    }

    @Test
    void testStoreFoo15_ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo15(fooData)
        }).isInstanceOf(FooServiceException.class)
    }
}
