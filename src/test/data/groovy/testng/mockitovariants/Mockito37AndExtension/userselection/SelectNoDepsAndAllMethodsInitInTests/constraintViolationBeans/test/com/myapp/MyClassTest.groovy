package com.myapp

import groovy.transform.CompileStatic
import org.testng.annotations.Test

import static org.testng.Assert.assertThrows

@CompileStatic
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

    @Test
    void testStoreFoo1_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo1(fooData)
        })
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

    @Test
    void testStoreFoo2_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo2(fooData)
        })
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

    @Test
    void testStoreFoo3_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo3(fooData)
        })
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

    @Test
    void testStoreFoo4_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo4(fooData)
        })
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

    @Test
    void testStoreFoo5_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo5(fooData)
        })
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

    @Test
    void testStoreFoo6_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo6(fooData)
        })
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

    @Test
    void testStoreFoo7_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo7(fooData)
        })
    }

    @Test
    void testStoreFoo7_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo7(fooData)
        })
    }

    @Test
    void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooAlreadyExistsException.class, {
            myClassUnderTest.storeFoo7(fooData)
        })
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

    @Test
    void testStoreFoo8_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo8(fooData)
        })
    }

    @Test
    void testStoreFoo8_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo8(fooData)
        })
    }

    @Test
    void testStoreFoo8_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooAlreadyExistsException.class, {
            myClassUnderTest.storeFoo8(fooData)
        })
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

    @Test
    void testStoreFoo9_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo9(fooData)
        })
    }

    @Test
    void testStoreFoo9_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo9(fooData)
        })
    }

    @Test
    void testStoreFoo9_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.storeFoo9(fooData)
        })
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

    @Test
    void testStoreFoo10_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo10(fooData)
        })
    }

    @Test
    void testStoreFoo10_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo10(fooData)
        })
    }

    @Test
    void testStoreFoo10_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.storeFoo10(fooData)
        })
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

    @Test
    void testStoreFoo11_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo11(fooData)
        })
    }

    @Test
    void testStoreFoo11_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo11(fooData)
        })
    }

    @Test
    void testStoreFoo11_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooAlreadyExistsException.class, {
            myClassUnderTest.storeFoo11(fooData)
        })
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

    @Test
    void testStoreFoo12_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo12(fooData)
        })
    }

    @Test
    void testStoreFoo12_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo12(fooData)
        })
    }

    @Test
    void testStoreFoo12_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.storeFoo12(fooData)
        })
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

    @Test
    void testStoreFoo13_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo13(fooData)
        })
    }

    @Test
    void testStoreFoo13_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo13(fooData)
        })
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

    @Test
    void testStoreFoo14_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo14(fooData)
        })
    }

    @Test
    void testStoreFoo14_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.storeFoo14(fooData)
        })
    }

    @Test
    void testStoreFoo14_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.storeFoo14(fooData)
        })
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

    @Test
    void testGetFooById1_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.getFooById1("id")
        })
    }

    @Test
    void testGetFooById1_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.getFooById1("id")
        })
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

    @Test
    void testGetFooById2_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.getFooById2("id")
        })
    }

    @Test
    void testGetFooById2_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.getFooById2("id")
        })
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

    @Test
    void testGetFooById3_ThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        assertThrows(GetFooException.class, {
            myClassUnderTest.getFooById3("id")
        })
    }

    @Test
    void testGetFooById3_ThrowsFooDoesNotExistException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())

        // Run the test
        assertThrows(FooDoesNotExistException.class, {
            myClassUnderTest.getFooById3("id")
        })
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

    @Test
    void testStoreFoo15_ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics())
        def fooData = new FooData("id", "name")

        // Run the test
        assertThrows(FooServiceException.class, {
            myClassUnderTest.storeFoo15(fooData)
        })
    }
}
