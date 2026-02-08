package com.myapp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MyClassTest {

    @Test
    public void testStoreFoo1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo1(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo1_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo1(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo2() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo2_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo2(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo3() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo3(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo3_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo3(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo4() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo4(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo4_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo4(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo5() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo5_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo5(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo6() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo6_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo6(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo7() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo7(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo7_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo7(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo7_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo7(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo7(fooData)).isInstanceOf(FooAlreadyExistsException.class);
    }

    @Test
    public void testStoreFoo8() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo8(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo8_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo8(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo8_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo8(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo8_ThrowsFooAlreadyExistsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo8(fooData)).isInstanceOf(FooAlreadyExistsException.class);
    }

    @Test
    public void testStoreFoo9() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo9(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo9_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo9(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo9_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo9(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo9_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo9(fooData)).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testStoreFoo10() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo10(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo10_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo10(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo10_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo10(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo10_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo10(fooData)).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testStoreFoo11() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo11(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo11_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo11(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo11_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo11(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo11_ThrowsFooAlreadyExistsException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo11(fooData)).isInstanceOf(FooAlreadyExistsException.class);
    }

    @Test
    public void testStoreFoo12() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo12(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo12_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo12(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo12_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo12(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo12_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo12(fooData)).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testStoreFoo13() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo13(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo13_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo13(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo13_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo13(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo14() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo14(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo14_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo14(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testStoreFoo14_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo14(fooData)).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testStoreFoo14_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo14(fooData)).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testGetFooById1() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFooById1("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFooById1_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById1("id")).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testGetFooById1_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById1("id")).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testGetFooById2() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFooById2("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFooById2_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById2("id")).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testGetFooById2_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById2("id")).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testGetFooById3() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData expectedResult = new FooData("id", "name");

        // Run the test
        final FooData result = myClassUnderTest.getFooById3("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFooById3_ThrowsGetFooException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById3("id")).isInstanceOf(GetFooException.class);
    }

    @Test
    public void testGetFooById3_ThrowsFooDoesNotExistException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooById3("id")).isInstanceOf(FooDoesNotExistException.class);
    }

    @Test
    public void testStoreFoo15() throws Exception {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        myClassUnderTest.storeFoo15(fooData);

        // Verify the results
    }

    @Test
    public void testStoreFoo15_ThrowsFooServiceException() {
        // Setup
        final MyClass myClassUnderTest = new MyClass(null, null, null, new UnmockableMetrics());
        final FooData fooData = new FooData("id", "name");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.storeFoo15(fooData)).isInstanceOf(FooServiceException.class);
    }
}
