package com.myapp

import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testStoreFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo1(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo1_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo1(fooData)
    }

    @Test
    void testStoreFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo2(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo2_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo2(fooData)
    }

    @Test
    void testStoreFoo3() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo3(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo3_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo3(fooData)
    }

    @Test
    void testStoreFoo4() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo4(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo4_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo4(fooData)
    }

    @Test
    void testStoreFoo5() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo5(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo5_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo5(fooData)
    }

    @Test
    void testStoreFoo6() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo6(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo6_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo6(fooData)
    }

    @Test
    void testStoreFoo7() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo7(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo7_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo7_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test(expected = FooAlreadyExistsException.class)
    void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test
    void testStoreFoo8() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo8(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo8_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo8_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test(expected = FooAlreadyExistsException.class)
    void testStoreFoo8_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test
    void testStoreFoo9() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo9(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo9_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo9_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test(expected = FooDoesNotExistException.class)
    void testStoreFoo9_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test
    void testStoreFoo10() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo10(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo10_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo10_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test(expected = FooDoesNotExistException.class)
    void testStoreFoo10_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test
    void testStoreFoo11() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo11(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo11_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo11_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test(expected = FooAlreadyExistsException.class)
    void testStoreFoo11_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test
    void testStoreFoo12() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo12(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo12_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo12_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test(expected = FooDoesNotExistException.class)
    void testStoreFoo12_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test
    void testStoreFoo13() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo13(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo13_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo13(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo13_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo13(fooData)
    }

    @Test
    void testStoreFoo14() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo14(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo14_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test(expected = GetFooException.class)
    void testStoreFoo14_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test(expected = FooDoesNotExistException.class)
    void testStoreFoo14_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test
    void testGetFooById1() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def expectedResult = new FooData("id", "name")

        // Run the test
        def result = myClassUnderTest.getFooById1("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = GetFooException.class)
    void testGetFooById1_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        myClassUnderTest.getFooById1("id")
    }

    @Test(expected = FooDoesNotExistException.class)
    void testGetFooById1_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        myClassUnderTest.getFooById1("id")
    }

    @Test
    void testGetFooById2() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def expectedResult = new FooData("id", "name")

        // Run the test
        def result = myClassUnderTest.getFooById2("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = GetFooException.class)
    void testGetFooById2_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        myClassUnderTest.getFooById2("id")
    }

    @Test(expected = FooDoesNotExistException.class)
    void testGetFooById2_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        myClassUnderTest.getFooById2("id")
    }

    @Test
    void testGetFooById3() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def expectedResult = new FooData("id", "name")

        // Run the test
        def result = myClassUnderTest.getFooById3("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expected = GetFooException.class)
    void testGetFooById3_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        myClassUnderTest.getFooById3("id")
    }

    @Test(expected = FooDoesNotExistException.class)
    void testGetFooById3_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        myClassUnderTest.getFooById3("id")
    }

    @Test
    void testStoreFoo15() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo15(fooData)

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    void testStoreFoo15_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo15(fooData)
    }
}
