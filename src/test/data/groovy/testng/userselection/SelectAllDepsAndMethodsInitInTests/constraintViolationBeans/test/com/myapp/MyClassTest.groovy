package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.Mockito.*
import static org.mockito.MockitoAnnotations.initMocks

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

    @BeforeMethod
    void setUp() {
        initMocks(this)
    }

    @Test
    void testStoreFoo1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo1(fooData)

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo1_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo1(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo1_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo1(fooData)
    }

    @Test
    void testStoreFoo2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo2(fooData)

        // Verify the results
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo2_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo2(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo2_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo2(fooData)
    }

    @Test
    void testStoreFoo3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo3_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo3(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo3_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo3(fooData)
    }

    @Test
    void testStoreFoo4() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo4_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo4(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo4_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo4(fooData)
    }

    @Test
    void testStoreFoo5() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo5_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo5(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo5_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo5(fooData)
    }

    @Test
    void testStoreFoo6() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo6_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo6(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo6_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo6(fooData)
    }

    @Test(expectedExceptions = [FooAlreadyExistsException.class])
    void testStoreFoo7_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo7_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test
    void testStoreFoo7_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo7_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo7_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo7(fooData)
    }

    @Test(expectedExceptions = [FooAlreadyExistsException.class])
    void testStoreFoo8_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo8_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test
    void testStoreFoo8_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo8_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo8_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo8(fooData)
    }

    @Test
    void testStoreFoo9() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo9_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testStoreFoo9_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo9_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo9_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo9(fooData)
    }

    @Test
    void testStoreFoo10() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo10_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testStoreFoo10_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo10_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo10_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo10(fooData)
    }

    @Test(expectedExceptions = [FooAlreadyExistsException.class])
    void testStoreFoo11_ThrowsFooAlreadyExistsException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test
    void testStoreFoo11_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo11(fooData)

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo11_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo11_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo11_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo11(fooData)
    }

    @Test
    void testStoreFoo12() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo12(fooData)

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testStoreFoo12_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo12_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo12_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo12_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo12(fooData)
    }

    @Test
    void testStoreFoo13() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo13(fooData)

        // Verify the results
        verify(mockFooService).getFooById("id")
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo13_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo13(fooData)
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo13_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Configure MyValidator.validate(...).
        def detailInfo = new DetailInfo()
        detailInfo.setId("id")
        detailInfo.setDescription("description")
        detailInfo.setErrorMessage("errorMessage")
        def myConstraintViolations = new HashSet<>([MyConstraintViolation.of(new FooData("id", "name"), detailInfo)])
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(myConstraintViolations)

        // Run the test
        myClassUnderTest.storeFoo13(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo13_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo13(fooData)
    }

    @Test
    void testStoreFoo14() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())

        // Run the test
        myClassUnderTest.storeFoo14(fooData)

        // Verify the results
        verify(mockMetrics).recordGetFooByIdCalled()
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testStoreFoo14_FooServiceGetFooByIdReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testStoreFoo14_FooServiceGetFooByIdThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test(expectedExceptions = [FooDataValidationException.class])
    void testStoreFoo14_MyValidatorReturnsFailure() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
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
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo14_FooServiceStoreFoo1ThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))
        when(mockValidator.validate(new FooData("id", "name"))).thenReturn(Collections.emptySet())
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo14(fooData)
    }

    @Test
    void testGetFooById1() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def expectedResult = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooById1("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testGetFooById1_FooServiceReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getFooById1("id")
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testGetFooById1_FooServiceThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.getFooById1("id")
    }

    @Test
    void testGetFooById2() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def expectedResult = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooById2("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testGetFooById2_FooServiceReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getFooById2("id")
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testGetFooById2_FooServiceThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.getFooById2("id")
    }

    @Test
    void testGetFooById3() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def expectedResult = new FooData("id", "name")
        when(mockFooService.getFooById("id")).thenReturn(Optional.of(new FooData("id", "name")))

        // Run the test
        def result = myClassUnderTest.getFooById3("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test(expectedExceptions = [FooDoesNotExistException.class])
    void testGetFooById3_FooServiceReturnsAbsent() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        when(mockFooService.getFooById("id")).thenReturn(Optional.empty())

        // Run the test
        myClassUnderTest.getFooById3("id")
    }

    @Test(expectedExceptions = [GetFooException.class])
    void testGetFooById3_FooServiceThrowsGetFooException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        when(mockFooService.getFooById("id")).thenThrow(GetFooException.class)

        // Run the test
        myClassUnderTest.getFooById3("id")
    }

    @Test
    void testStoreFoo15() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")

        // Run the test
        myClassUnderTest.storeFoo15(fooData)

        // Verify the results
        verify(mockValidator).validate(new FooData("id", "name"))
        verify(mockFooService).storeFoo1(new FooData("id", "name"))
    }

    @Test(expectedExceptions = [FooServiceException.class])
    void testStoreFoo15_FooServiceThrowsFooServiceException() {
        // Setup
        def myClassUnderTest = new MyClass(mockFooService, mockValidator, mockMetrics, mockUnmockableMetrics)
        def fooData = new FooData("id", "name")
        doThrow(FooServiceException.class).when(mockFooService).storeFoo1(new FooData("id", "name"))

        // Run the test
        myClassUnderTest.storeFoo15(fooData)
    }
}
