package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MetricService mockMetricService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooService, mockMetricService);
    }

    @Test
    public void testIsValid1() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.isValid1("data");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testIsValid1_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.isValid1("data");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testIsValid2() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.isValid2("data");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testIsValid2_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.isValid2("data");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testIsValid3() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.isValid3("data");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testIsValid3_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.isValid3("data");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testExists() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        final boolean result = myClassUnderTest.exists("data");

        // Verify the results
        assertThat(result).isFalse();
    }

    @Test
    public void testExists_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        final boolean result = myClassUnderTest.exists("data");

        // Verify the results
        assertThat(result).isTrue();
    }

    @Test
    public void testValidate1() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.validate1("data");

        // Verify the results
    }

    @Test
    public void testValidate1_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.validate1("data")).isInstanceOf(FooNotValidException.class);
    }

    @Test
    public void testValidate2() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.validate2("data");

        // Verify the results
    }

    @Test
    public void testValidate2_FooServiceReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.validate2("data")).isInstanceOf(FooNotValidException.class);
    }

    @Test
    public void testValidate3() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.validate3("data");

        // Verify the results
    }

    @Test
    public void testValidate3_FooServiceReturnsTrue() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.validate3("data")).isInstanceOf(FooIsValidException.class);
    }

    @Test
    public void testCreateFoo1() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo1("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo1_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo1("data")).isInstanceOf(FooNotValidException.class);
    }

    @Test
    public void testCreateFoo1_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo1("data")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testCreateFoo2() {
        // Setup
        // Run the test
        myClassUnderTest.createFoo2("data");

        // Verify the results
        verify(mockFooService).isValid("data");
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo2_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo2("data")).isInstanceOf(FooServiceException.class);
        verify(mockFooService).isValid("data");
    }

    @Test
    public void testCreateFoo3() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo3("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo3_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);
        when(mockFooService.shouldThrowInvalidFoo("data")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo3("data")).isInstanceOf(GenericException.class);
    }

    @Test
    public void testCreateFoo3_FooServiceShouldThrowInvalidFooReturnsTrue() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(false);
        when(mockFooService.shouldThrowInvalidFoo("data")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo3("data")).isInstanceOf(FooNotValidException.class);
    }

    @Test
    public void testCreateFoo3_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValid("data")).thenReturn(true);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo3("data")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testCreateFoo4() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.createFoo4("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo4_FooServiceExistsReturnsTrue() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo4("data")).isInstanceOf(FooAlreadyExistsException.class);
    }

    @Test
    public void testCreateFoo4_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo4("data")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testCreateFoo5() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.createFoo5("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo5_FooServiceExistsReturnsTrue() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo5("data");

        // Verify the results
    }

    @Test
    public void testCreateFoo5_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.exists("data")).thenReturn(false);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo5("data")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testCreateFoo6() {
        // Setup
        when(mockFooService.shouldRecordCreate("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.createFoo6("data");

        // Verify the results
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo6_FooServiceShouldRecordCreateReturnsTrue() {
        // Setup
        when(mockFooService.shouldRecordCreate("data")).thenReturn(true);

        // Run the test
        myClassUnderTest.createFoo6("data");

        // Verify the results
        verify(mockMetricService).recordCreateFoo("data");
        verify(mockFooService).createFoo1("data");
    }

    @Test
    public void testCreateFoo6_FooServiceCreateFoo1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.shouldRecordCreate("data")).thenReturn(false);
        doThrow(FooServiceException.class).when(mockFooService).createFoo1("data");

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.createFoo6("data")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetFoo1() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo1_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.getFoo2("id")).thenReturn("result");
        when(mockFooService.isValid("data")).thenReturn(true);

        // Run the test
        final String result = myClassUnderTest.getFoo1("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo1_FooServiceIsValidReturnsFalse() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.isValid("data")).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo1("id")).isInstanceOf(FooNotValidException.class);
    }

    @Test
    public void testCheckCanCreate1() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.checkCanCreate1("id");

        // Verify the results
    }

    @Test
    public void testCheckCanCreate1_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.getFoo2("id")).thenReturn("result");
        when(mockFooService.exists("data")).thenReturn(false);

        // Run the test
        myClassUnderTest.checkCanCreate1("id");

        // Verify the results
    }

    @Test
    public void testCheckCanCreate1_FooServiceExistsReturnsTrue() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.exists("data")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.checkCanCreate1("id")).isInstanceOf(FooAlreadyExistsException.class);
    }

    @Test
    public void testGetFoo2() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo2("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo2_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.shouldThrow(eq("id"), any(FooNotFoundException.class))).thenReturn(false);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo2("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo2_FooServiceShouldThrowReturnsTrue() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);
        when(mockFooService.shouldThrow(eq("id"), any(FooNotFoundException.class))).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo2("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo3() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo3("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo3_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo3("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo3_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo3("id")).isInstanceOf(OldFooFoundException.class);
        verify(mockMetricService).recordOldFoo("id");
    }

    @Test
    public void testGetFoo4() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenReturn("result");
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo4("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo4_FooServiceGetFooWithValidId1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo4("id")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetFoo4_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo4("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo4_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo4("id")).isInstanceOf(OldFooFoundException.class);
        verify(mockMetricService).recordOldFoo("id");
    }

    @Test
    public void testGetFoo5() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo5("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo5_FooServiceGetFooWithValidId1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo5("id")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetFoo5_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo5("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo5_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenReturn("result");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo5("id")).isInstanceOf(OldFooFoundException.class);
        verify(mockMetricService).recordOldFoo("id");
    }

    @Test
    public void testGetFoo6() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo6("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo6_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo6("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo7() {
        // Setup
        when(mockFooService.getFoo1("id")).thenReturn("result");

        // Run the test
        final String result = myClassUnderTest.getFoo7("id");

        // Verify the results
        assertThat(result).isEqualTo("result");
    }

    @Test
    public void testGetFoo7_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo7("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo8() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenReturn("hello");
        when(mockFooService.getFoo1("id")).thenReturn("hello");

        // Run the test
        final String result = myClassUnderTest.getFoo8("id");

        // Verify the results
        assertThat(result).isEqualTo("hello");
    }

    @Test
    public void testGetFoo8_FooServiceGetFooWithValidId1ThrowsFooServiceException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFooWithValidId1("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo8("id")).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testGetFoo8_FooServiceGetFoo1ThrowsFooNotFoundException() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenThrow(FooNotFoundException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo8("id")).isInstanceOf(FooNotFoundException.class);
    }

    @Test
    public void testGetFoo8_FooServiceShouldRecordOldFooReturnsTrue() {
        // Setup
        when(mockFooService.isValidId("id")).thenReturn(false);
        when(mockFooService.getFoo1("id")).thenReturn("hello");
        when(mockFooService.shouldRecordOldFoo("id")).thenReturn(true);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.getFoo8("id")).isInstanceOf(OldFooFoundException.class);
        verify(mockMetricService).recordOldFoo("id");
    }
}
