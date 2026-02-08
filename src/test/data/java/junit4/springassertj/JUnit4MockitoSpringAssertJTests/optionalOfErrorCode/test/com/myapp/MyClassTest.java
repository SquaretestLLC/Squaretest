package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    public void testPutFoo1() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo1(fooData);

        // Verify the results
    }

    @Test
    public void testPutFoo1_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo1(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo2() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo2(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo2_FooServicePutFoo1ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo2(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo2_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo2(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo3_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo3(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo3_FooServicePutFoo1ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo3(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo3_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo1(fooData1)).thenReturn(Optional.of(ErrorCode.BadId));

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo3(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo4() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo4(fooData);

        // Verify the results
        // Confirm FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo1(fooData1);
    }

    @Test
    public void testPutFoo5() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo5(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm FooService.putFoo1(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo1(fooData2);
    }

    @Test
    public void testPutFoo5_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo5(fooData)).isInstanceOf(FooServiceException.class);

        // Confirm FooService.putFoo1(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo1(fooData1);
    }

    @Test
    public void testPutFoo6() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo6(fooData);

        // Verify the results
    }

    @Test
    public void testPutFoo6_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo6(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo7() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo7(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo7_FooServicePutFoo2ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo7(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo7_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo7(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo8_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo8(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo8_FooServicePutFoo2ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo8(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo8_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo2(...).
        final ErrorInfo errorInfo1 = new ErrorInfo();
        errorInfo1.setCode("code");
        errorInfo1.setDescription("description");
        final Optional<ErrorInfo> errorInfo = Optional.of(errorInfo1);
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo2(fooData1)).thenReturn(errorInfo);

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo8(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo9() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo9(fooData);

        // Verify the results
        // Confirm FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo2(fooData1);
    }

    @Test
    public void testPutFoo10() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo10(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm FooService.putFoo2(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo2(fooData2);
    }

    @Test
    public void testPutFoo10_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo10(fooData)).isInstanceOf(FooServiceException.class);

        // Confirm FooService.putFoo2(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo2(fooData1);
    }

    @Test
    public void testPutFoo11() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo11(fooData);

        // Verify the results
    }

    @Test
    public void testPutFoo11_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo11(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo12() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo12(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo12_FooServicePutFoo3ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo12(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo12_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo12(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo13_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo13(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo13_FooServicePutFoo3ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo13(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo13_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo3(fooData1)).thenReturn(Optional.of(Error.NotFound));

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo13(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo14() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo14(fooData);

        // Verify the results
        // Confirm FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo3(fooData1);
    }

    @Test
    public void testPutFoo15() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo15(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm FooService.putFoo3(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo3(fooData2);
    }

    @Test
    public void testPutFoo15_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo15(fooData)).isInstanceOf(FooServiceException.class);

        // Confirm FooService.putFoo3(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo3(fooData1);
    }

    @Test
    public void testPutFoo16() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo16(fooData);

        // Verify the results
    }

    @Test
    public void testPutFoo16_FooServiceReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo16(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo17() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo17(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo17_FooServicePutFoo4ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo17(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo17_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo17(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo18_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo18(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo18_FooServicePutFoo4ReturnsError() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo18(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo18_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo4(...).
        final ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setCode("code");
        errorInfo.setDescription("description");
        final Optional<FailureInfo> failureInfo = Optional.of(new FailureInfo(Arrays.asList(errorInfo)));
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo4(fooData1)).thenReturn(failureInfo);

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo18(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo19() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo19(fooData);

        // Verify the results
        // Confirm FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo4(fooData1);
    }

    @Test
    public void testPutFoo20() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo20(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm FooService.putFoo4(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo4(fooData2);
    }

    @Test
    public void testPutFoo20_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo20(fooData)).isInstanceOf(FooServiceException.class);

        // Confirm FooService.putFoo4(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo4(fooData1);
    }

    @Test
    public void testPutFoo21() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo21(fooData);

        // Verify the results
    }

    @Test
    public void testPutFoo21_FooServiceReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo21(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo22() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo22(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo22_FooServicePutFoo5ReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo22(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo22_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo22(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo23_ThrowsFooStoreException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.empty());

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo23(fooData)).isInstanceOf(FooStoreException.class);
    }

    @Test
    public void testPutFoo23_FooServicePutFoo5ReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        // Configure FooService.getFoo(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData2);

        // Run the test
        final FooData result = myClassUnderTest.putFoo23(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testPutFoo23_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo5(fooData1)).thenReturn(Optional.of(new FooServiceException("message")));

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo23(fooData)).isInstanceOf(FooServiceException.class);
    }

    @Test
    public void testPutFoo24() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Run the test
        myClassUnderTest.putFoo24(fooData);

        // Verify the results
        // Confirm FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo5(fooData1);
    }

    @Test
    public void testPutFoo25() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        final FooData expectedResult = new FooData();
        expectedResult.setId("id");
        expectedResult.setName("name");

        // Configure FooService.getFoo(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.getFoo("id")).thenReturn(fooData1);

        // Run the test
        final FooData result = myClassUnderTest.putFoo25(fooData);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);

        // Confirm FooService.putFoo5(...).
        final FooData fooData2 = new FooData();
        fooData2.setId("id");
        fooData2.setName("name");
        verify(mockFooService).putFoo5(fooData2);
    }

    @Test
    public void testPutFoo25_FooServiceGetFooThrowsFooServiceException() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        when(mockFooService.getFoo("id")).thenThrow(FooServiceException.class);

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo25(fooData)).isInstanceOf(FooServiceException.class);

        // Confirm FooService.putFoo5(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        verify(mockFooService).putFoo5(fooData1);
    }

    @Test
    public void testPutFoo26() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo6(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo6(fooData1)).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.putFoo26(fooData);

        // Verify the results
    }

    @Test
    public void testPutFoo26_FooServiceReturnsFailure() {
        // Setup
        final FooData fooData = new FooData();
        fooData.setId("id");
        fooData.setName("name");

        // Configure FooService.putFoo6(...).
        final FooData fooData1 = new FooData();
        fooData1.setId("id");
        fooData1.setName("name");
        when(mockFooService.putFoo6(fooData1)).thenReturn(Optional.of(new Exception("message")));

        // Run the test
        assertThatThrownBy(() -> myClassUnderTest.putFoo26(fooData)).isInstanceOf(FooStoreException.class);
    }
}
