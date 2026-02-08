package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import javax.validation.ValidationException;
import javax.validation.Validator;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;
    @Mock
    private Validator mockValidator;
    @Mock
    private MetricsAdapter mockMetrics;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, new UnmockableMetrics());
    }

    @Test
    void testStoreFoo1() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo1(fooData);

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo1_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo1_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo1(fooData));
    }

    @Test
    void testStoreFoo2() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo2_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo2_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo2_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo2(fooData));
    }

    @Test
    void testStoreFoo3() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo3(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test
    void testStoreFoo3_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo3(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultNotEmpty();
    }

    @Test
    void testStoreFoo3_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo3(fooData));
        verify(mockMetrics).recordMethodEnter();
    }

    @Test
    void testStoreFoo3_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo3(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo4() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo4(fooData);

        // Verify the results
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
        verify(mockMetrics).recordAfterStoreFoo1Called();
    }

    @Test
    void testStoreFoo4_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo4(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultNotEmpty();
    }

    @Test
    void testStoreFoo4_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo4(fooData));
        verify(mockMetrics).recordMethodEnter();
    }

    @Test
    void testStoreFoo4_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo4(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo5() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo5_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo5_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo5_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo5(fooData));
    }

    @Test
    void testStoreFoo6() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo6_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo6(fooData));
    }

    @Test
    void testStoreFoo6_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo6(fooData));
    }

    @Test
    void testStoreFoo6_FooServiceThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo6(fooData));
    }

    @Test
    void testStoreFoo7_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo7(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo7_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo7(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultNotEmpty();
    }

    @Test
    void testStoreFoo7_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo7(fooData));
        verify(mockMetrics).recordMethodEnter();
    }

    @Test
    void testStoreFoo7_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
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

    @Test
    void testStoreFoo7_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo7(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo7_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo7(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo8_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo8(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo8_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo8(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultNotEmpty();
    }

    @Test
    void testStoreFoo8_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo8(fooData));
        verify(mockMetrics).recordMethodEnter();
    }

    @Test
    void testStoreFoo8_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
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

    @Test
    void testStoreFoo8_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo8(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo8_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo8(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo9() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
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

    @Test
    void testStoreFoo9_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo9(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultNotEmpty();
    }

    @Test
    void testStoreFoo9_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo9(fooData));
        verify(mockMetrics).recordMethodEnter();
    }

    @Test
    void testStoreFoo9_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.storeFoo9(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo9_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo9(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo9_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo9(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo10() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
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

    @Test
    void testStoreFoo10_ValidatorReturnsFailure() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo10(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultNotEmpty();
    }

    @Test
    void testStoreFoo10_ValidatorThrowsValidationException() {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo10(fooData));
        verify(mockMetrics).recordMethodEnter();
    }

    @Test
    void testStoreFoo10_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.storeFoo10(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo10_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo10(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo10_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo10(fooData));
        verify(mockMetrics).recordMethodEnter();
        verify(mockMetrics).recordAfterValidatorCalled();
        verify(mockMetrics).recordValidatorResultIsEmpty();
    }

    @Test
    void testStoreFoo11_ThrowsFooAlreadyExistsException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        assertThrows(FooAlreadyExistsException.class, () -> myClassUnderTest.storeFoo11(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockMetrics).recordExistingFooPresent();
    }

    @Test
    void testStoreFoo11_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo11(fooData);

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo11_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo11(fooData));
    }

    @Test
    void testStoreFoo11_ValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo11(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo11_ValidatorThrowsValidationException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo11(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo11_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo11(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo12() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo12(fooData);

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo12_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.storeFoo12(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockMetrics).recordExistingFooAbsent();
    }

    @Test
    void testStoreFoo12_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo12(fooData));
    }

    @Test
    void testStoreFoo12_ValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo12(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo12_ValidatorThrowsValidationException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo12(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo12_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo12(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo13() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo13(fooData);

        // Verify the results
        verify(mockFooService).getFooById("id");
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo13_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo13(fooData));
    }

    @Test
    void testStoreFoo13_ValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo13(fooData));
        verify(mockFooService).getFooById("id");
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo13_ValidatorThrowsValidationException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo13(fooData));
        verify(mockFooService).getFooById("id");
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo13_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo13(fooData));
        verify(mockFooService).getFooById("id");
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo14() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());

        // Run the test
        myClassUnderTest.storeFoo14(fooData);

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockFooService).storeFoo1(new FooData("id", "name"));
    }

    @Test
    void testStoreFoo14_FooServiceGetFooByIdReturnsAbsent() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.storeFoo14(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockMetrics).recordExistingFooAbsent();
    }

    @Test
    void testStoreFoo14_FooServiceGetFooByIdThrowsGetFooException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.storeFoo14(fooData));
    }

    @Test
    void testStoreFoo14_ValidatorReturnsFailure() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(new HashSet<>());

        // Run the test
        assertThrows(FooDataValidationException.class, () -> myClassUnderTest.storeFoo14(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
        verify(mockMetrics).recordValidationFailure();
    }

    @Test
    void testStoreFoo14_ValidatorThrowsValidationException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenThrow(ValidationException.class);

        // Run the test
        assertThrows(ValidationException.class, () -> myClassUnderTest.storeFoo14(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testStoreFoo14_FooServiceStoreFoo1ThrowsFooServiceException() throws Exception {
        // Setup
        final FooData fooData = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));
        when(mockValidator.validate(new FooData("id", "name"), FooData.class)).thenReturn(Collections.emptySet());
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"));

        // Run the test
        assertThrows(FooServiceException.class, () -> myClassUnderTest.storeFoo14(fooData));
        verify(mockMetrics).recordGetFooByIdCalled();
    }

    @Test
    void testGetFooById1() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooById1("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooById1_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.getFooById1("id"));
        verify(mockMetrics).recordExistingFooAbsent();
    }

    @Test
    void testGetFooById1_FooServiceThrowsGetFooException() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.getFooById1("id"));
    }

    @Test
    void testGetFooById2() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooById2("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooById2_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.getFooById2("id"));
        verify(mockMetrics).recordExistingFooAbsent();
    }

    @Test
    void testGetFooById2_FooServiceThrowsGetFooException() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.getFooById2("id"));
    }

    @Test
    void testGetFooById3() throws Exception {
        // Setup
        final FooData expectedResult = new FooData("id", "name");
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")));

        // Run the test
        final FooData result = myClassUnderTest.getFooById3("id");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    void testGetFooById3_FooServiceReturnsAbsent() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty());

        // Run the test
        assertThrows(FooDoesNotExistException.class, () -> myClassUnderTest.getFooById3("id"));
        verify(mockMetrics).recordExistingFooAbsent();
    }

    @Test
    void testGetFooById3_FooServiceThrowsGetFooException() throws Exception {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class);

        // Run the test
        assertThrows(GetFooException.class, () -> myClassUnderTest.getFooById3("id"));
    }
}
