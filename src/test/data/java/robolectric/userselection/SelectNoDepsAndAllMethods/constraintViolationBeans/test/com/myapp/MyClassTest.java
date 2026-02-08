package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class MyClassTest {

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
    }

    @Test
    public void testStoreFoo1() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo1(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo1_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo1(fooData);
    }

    @Test
    public void testStoreFoo2() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo2_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo2(fooData);
    }

    @Test
    public void testStoreFoo3() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo3(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo3_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo3(fooData);
    }

    @Test
    public void testStoreFoo4() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo4(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo4_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo4(fooData);
    }

    @Test
    public void testStoreFoo5() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo5_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo5(fooData);
    }

    @Test
    public void testStoreFoo6() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo6_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo6(fooData);
    }

    @Test
    public void testStoreFoo7() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo7(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo7_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo7_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test(expected = FooAlreadyExistsException.class)
    public void testStoreFoo7_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test
    public void testStoreFoo8() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo8(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo8_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo8_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test(expected = FooAlreadyExistsException.class)
    public void testStoreFoo8_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test
    public void testStoreFoo9() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo9(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo9_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo9_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo9_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test
    public void testStoreFoo10() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo10(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo10_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo10_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo10_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test
    public void testStoreFoo11() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo11(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo11_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo11_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test(expected = FooAlreadyExistsException.class)
    public void testStoreFoo11_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test
    public void testStoreFoo12() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo12(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo12_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo12_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo12_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test
    public void testStoreFoo13() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo13(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo13_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo13(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo13_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo13(fooData);
    }

    @Test
    public void testStoreFoo14() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo14(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo14_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo14_ThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo14_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test
    public void testGetFooById1() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFooById1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = GetFooException.class)
    public void testGetFooById1_ThrowsGetFooException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.getFooById1("id");
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testGetFooById1_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.getFooById1("id");
    }

    @Test
    public void testGetFooById2() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFooById2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = GetFooException.class)
    public void testGetFooById2_ThrowsGetFooException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.getFooById2("id");
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testGetFooById2_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.getFooById2("id");
    }

    @Test
    public void testGetFooById3() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFooById3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = GetFooException.class)
    public void testGetFooById3_ThrowsGetFooException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.getFooById3("id");
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testGetFooById3_ThrowsFooDoesNotExistException() throws Exception {
        // Setup
        // Run the test
        myClassUnderTest.getFooById3("id");
    }

    @Test
    public void testStoreFoo15() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo15(fooData);

        // Verify the results
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo15_ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo15(fooData);
    }
}
