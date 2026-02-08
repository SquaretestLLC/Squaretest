package com.myapp

import groovy.transform.CompileStatic
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner.class)
@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService
    @Mock
    private MyValidator mockValidator
    @Mock
    private MetricsAdapter mockMetrics
    @Mock
    private UnmockableMetrics mockUnmockableMetrics

    private MyClass myClassUnderTest

    @Before
    void setUp() {
        myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
    }

    @Test
    void testStoreFoo1() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo1(fooData)

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo1_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo1(fooData)
        }).isInstanceOf(FooDataValidationException.class)
    }

    @Test
    void testStoreFoo1_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo1(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo2() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo2(fooData)

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo2_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo2(fooData)
        }).isInstanceOf(FooDataValidationException.class)
    }

    @Test
    void testStoreFoo2_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo2(fooData)
        }).isInstanceOf(FooServiceException.class)
    }

    @Test
    void testStoreFoo3() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo3(fooData)

        // Verify the results
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo3_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo3(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo3_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo3(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo4() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo4(fooData)

        // Verify the results
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo4_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo4(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo4_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo4(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo5() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo5(fooData)

        // Verify the results
        verify(mockUnmockableMetrics).recordMethodEnter()
        verify(mockUnmockableMetrics).recordAfterValidatorCalled()
        verify(mockUnmockableMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockUnmockableMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo5_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo5(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockUnmockableMetrics).recordMethodEnter()
        verify(mockUnmockableMetrics).recordAfterValidatorCalled()
        verify(mockUnmockableMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo5_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo5(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockUnmockableMetrics).recordMethodEnter()
        verify(mockUnmockableMetrics).recordAfterValidatorCalled()
        verify(mockUnmockableMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo6() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo6(fooData)

        // Verify the results
        verify(mockUnmockableMetrics).recordMethodEnter()
        verify(mockUnmockableMetrics).recordAfterValidatorCalled()
        verify(mockUnmockableMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockUnmockableMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo6_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo6(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockUnmockableMetrics).recordMethodEnter()
        verify(mockUnmockableMetrics).recordAfterValidatorCalled()
        verify(mockUnmockableMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo6_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo6(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockUnmockableMetrics).recordMethodEnter()
        verify(mockUnmockableMetrics).recordAfterValidatorCalled()
        verify(mockUnmockableMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(FooAlreadyExistsException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo7_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo7_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.storeFoo7(fooData)

        // Verify the results
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo7_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(GetFooException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo7_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo7(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo8_ThrowsFooAlreadyExistsException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(FooAlreadyExistsException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo8_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo8_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.storeFoo8(fooData)

        // Verify the results
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo8_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(GetFooException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo8_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo8(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo9() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        myClassUnderTest.storeFoo9(fooData)

        // Verify the results
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo9_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo9_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo9_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(GetFooException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo9_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo9(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo10() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        myClassUnderTest.storeFoo10(fooData)

        // Verify the results
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
        verify(mockMetrics).recordAfterStoreFoo1Called()
    }

    @Test
    void testStoreFoo10_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultNotEmpty()
    }

    @Test
    void testStoreFoo10_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo10_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(GetFooException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo10_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo10(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordMethodEnter()
        verify(mockMetrics).recordAfterValidatorCalled()
        verify(mockMetrics).recordValidatorResultIsEmpty()
    }

    @Test
    void testStoreFoo11_ThrowsFooAlreadyExistsException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(FooAlreadyExistsException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockMetrics).recordExistingFooPresent()
    }

    @Test
    void testStoreFoo11_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo11(fooData)

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo11_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo11_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testStoreFoo11_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo11(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testStoreFoo12() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo12(fooData)

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo12_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockMetrics).recordExistingFooAbsent()
    }

    @Test
    void testStoreFoo12_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo12_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testStoreFoo12_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo12(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testStoreFoo13() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo13(fooData)

        // Verify the results
        verify(mockFooService).getFooById("id")
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo13_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo13(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo13_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo13(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockFooService).getFooById("id")
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testStoreFoo13_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo13(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockFooService).getFooById("id")
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testStoreFoo14() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo14(fooData)

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo14_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockMetrics).recordExistingFooAbsent()
    }

    @Test
    void testStoreFoo14_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo14_MyValidatorReturnsFailure() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(FooDataValidationException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockMetrics).recordValidationFailure()
    }

    @Test
    void testStoreFoo14_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo14(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockMetrics).recordGetFooByIdCalled()
    }

    @Test
    void testGetFooById1() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooById1("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooById1_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById1("id")
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordExistingFooAbsent()
    }

    @Test
    void testGetFooById1_FooServiceThrowsGetFooException() {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById1("id")
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testGetFooById2() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooById2("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooById2_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById2("id")
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordExistingFooAbsent()
    }

    @Test
    void testGetFooById2_FooServiceThrowsGetFooException() {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById2("id")
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testGetFooById3() {
        // Setup
        def expectedResult = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooById3("id")

        // Verify the results
        assertThat(result).isEqualTo(expectedResult)
    }

    @Test
    void testGetFooById3_FooServiceReturnsAbsent() {
        // Setup
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById3("id")
        }).isInstanceOf(FooDoesNotExistException.class)
        verify(mockMetrics).recordExistingFooAbsent()
    }

    @Test
    void testGetFooById3_FooServiceThrowsGetFooException() {
        // Setup
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.getFooById3("id")
        }).isInstanceOf(GetFooException.class)
    }

    @Test
    void testStoreFoo15() {
        // Setup
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo15(fooData)

        // Verify the results
        verify(mockValidator).validate(new FooData("id", "name"))
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test
    void testStoreFoo15_FooServiceThrowsFooServiceException() {
        // Setup
        def fooData = new FooData("id", "name")
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        assertThatThrownBy({
            myClassUnderTest.storeFoo15(fooData)
        }).isInstanceOf(FooServiceException.class)
        verify(mockValidator).validate(new FooData("id", "name"))
    }
}
