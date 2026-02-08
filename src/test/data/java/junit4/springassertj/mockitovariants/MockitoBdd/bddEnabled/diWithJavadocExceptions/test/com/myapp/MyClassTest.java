package com.myapp;

import com.myapp.javadocthrows.FooServiceException;
import com.myapp.javadocthrows.OtherException;
import com.myapp.javadocthrows.ThrowingFooProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private ThrowingFooProvider mockFooProvider;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooProvider);
    }

    @Test
    public void testGetFooFromPlace() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooFromPlace("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        given(mockFooProvider.getFooFromPlace("key")).willThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooFromPlace("key")).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testGetBar() {
        // Setup
        given(mockFooProvider.getBar("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar("barName")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetBar1() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar1("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar1("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar1("barName")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetBar2() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar2("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar2("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar2("barName")).isInstanceOf(OtherException.class);
    }

    @Test
    public void testGetBar3() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar3("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar3("barName")).isInstanceOf(OtherException.class);
    }

    @Test
    public void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.getBar3("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar3("barName")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithDupExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithDupExceptions("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithDupExceptions("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithDupExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithDupExceptions("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions1() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions1("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions1("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions2() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions2("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions2("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions3() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions3("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions3("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions4() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions4("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions4("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions5() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions5("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions5("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions6() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions6("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        given(mockFooProvider.tryGetBarWithTwoExceptions("barName")).willThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions6("barName"))
                .isInstanceOf(FooServiceException.class);
    }
}
