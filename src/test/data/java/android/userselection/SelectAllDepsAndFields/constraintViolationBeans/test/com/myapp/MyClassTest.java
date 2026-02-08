package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private MyValidator mockValidator;
    @Mock
    private MetricsAdapter mockMetrics;
    @Mock
    private UnmockableMetrics mockUnmockableMetrics;

    private MyClass myClassUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics);
    }

    @Test
    public void testStoreFoo1() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo1(fooData);

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo1_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo1(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo1_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo1(fooData);
    }

    @Test
    public void testStoreFoo2() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo2_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo2(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo2_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo2(fooData);
    }

    @Test
    public void testStoreFoo3() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo3(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo3_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo3(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo3_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo3(fooData);
    }

    @Test
    public void testStoreFoo4() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo4(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo4_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo4(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo4_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo4(fooData);
    }

    @Test
    public void testStoreFoo5() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
        verify(mockUnmockableMetrics).recordMethodEnter();
        verify(mockUnmockableMetrics).recordAfterValidatorCalled();
        verify(mockUnmockableMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockUnmockableMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo5_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo5(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo5_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo5(fooData);
    }

    @Test
    public void testStoreFoo6() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
        verify(mockUnmockableMetrics).recordMethodEnter();
        verify(mockUnmockableMetrics).recordAfterValidatorCalled();
        verify(mockUnmockableMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockUnmockableMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo6_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo6(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo6_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo6(fooData);
    }

    @Test(expected = FooAlreadyExistsException.class)
    public void testStoreFoo7_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo7_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test
    public void testStoreFoo7_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo7(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo7_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo7_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo7(fooData);
    }

    @Test(expected = FooAlreadyExistsException.class)
    public void testStoreFoo8_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo8_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test
    public void testStoreFoo8_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo8(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo8_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo8_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo8(fooData);
    }

    @Test
    public void testStoreFoo9() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        myClassUnderTest.storeFoo9(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo9_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo9_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo9_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo9_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo9(fooData);
    }

    @Test
    public void testStoreFoo10() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        myClassUnderTest.storeFoo10(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo10_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo10_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo10_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo10_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo10(fooData);
    }

    @Test(expected = FooAlreadyExistsException.class)
    public void testStoreFoo11_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test
    public void testStoreFoo11_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo11(fooData);

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo11_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo11_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo11_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo11(fooData);
    }

    @Test
    public void testStoreFoo12() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo12(fooData);

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo12_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo12_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo12_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo12_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo12(fooData);
    }

    @Test
    public void testStoreFoo13() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo13(fooData);

        // Verify the results
        verify(mockFooService).getFooById("id");
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo13_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo13(fooData);
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo13_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo13(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo13_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo13(fooData);
    }

    @Test
    public void testStoreFoo14() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo14(fooData);

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testStoreFoo14_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test(expected = GetFooException.class)
    public void testStoreFoo14_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test(expected = FooDataValidationException.class)
    public void testStoreFoo14_MyValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Configure MyValidator.validate(...).
        final DetailInfo detailInfo = new DetailInfo();
        detailInfo.setId("id");
        detailInfo.setDescription("description");
        detailInfo.setErrorMessage("errorMessage");
        final Set<MyConstraintViolation<FooData>> myConstraintViolations = new HashSet<>(
                Arrays.asList(MyConstraintViolation.of(new FooData("id", "name"), detailInfo)));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations);

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo14_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo14(fooData);
    }

    @Test
    public void testGetFooById1() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooById1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testGetFooById1_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.getFooById1("id");
    }

    @Test(expected = GetFooException.class)
    public void testGetFooById1_FooServiceThrowsGetFooException() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.getFooById1("id");
    }

    @Test
    public void testGetFooById2() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooById2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testGetFooById2_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.getFooById2("id");
    }

    @Test(expected = GetFooException.class)
    public void testGetFooById2_FooServiceThrowsGetFooException() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        myClassUnderTest.getFooById2("id");
    }

    @Test
    public void testGetFooById3() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooById3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test(expected = FooDoesNotExistException.class)
    public void testGetFooById3_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        myClassUnderTest.getFooById3("id");
    }

    @Test(expected = GetFooException.class)
    public void testGetFooById3_FooServiceThrowsGetFooException() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

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
        verify(mockValidator).validate(new FooData("id", "name"));
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test(expected = FooServiceException.class)
    public void testStoreFoo15_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        myClassUnderTest.storeFoo15(fooData);
    }
}
