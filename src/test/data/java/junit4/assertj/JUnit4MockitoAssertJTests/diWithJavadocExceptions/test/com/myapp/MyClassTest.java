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
import static org.mockito.Mockito.when;

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
        when(mockFooProvider.getFooFromPlace("key")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFooFromPlace("key");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFooFromPlace_ThrowingFooProviderThrowsRuntimeException() {
        // Setup
        when(mockFooProvider.getFooFromPlace("key")).thenThrow(RuntimeException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFooFromPlace("key")).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void testGetBar() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar("barName")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetBar1() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar1("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar1("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar1("barName")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetBar2() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar2("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar2("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar2("barName")).isInstanceOf(OtherException.class);
    }

    @Test
    public void testGetBar3() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getBar3("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetBar3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar3("barName")).isInstanceOf(OtherException.class);
    }

    @Test
    public void testGetBar3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.getBar3("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getBar3("barName")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithDupExceptions() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithDupExceptions("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithDupExceptions("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithDupExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithDupExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithDupExceptions("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions1() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions1("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions1("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions1_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions1("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions2() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions2("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions2("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions2_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions2("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions3() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions3("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions3("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions3_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions3("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions4() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions4("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions4("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions4_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions4("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions5() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions5("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions5("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions5_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions5("barName"))
                .isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions6() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.tryGetBarWithTwoExceptions6("barName");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsOtherException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(OtherException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions6("barName"))
                .isInstanceOf(OtherException.class);
    }

    @Test
    public void testTryGetBarWithTwoExceptions6_ThrowingFooProviderThrowsFooServiceException() {
        // Setup
        when(mockFooProvider.tryGetBarWithTwoExceptions("barName")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.tryGetBarWithTwoExceptions6("barName"))
                .isInstanceOf(FooServiceException.class);
    }
}
