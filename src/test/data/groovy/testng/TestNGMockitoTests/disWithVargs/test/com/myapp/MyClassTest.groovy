package com.myapp

import groovy.transform.CompileStatic
import org.mockito.Mock
import org.testng.annotations.BeforeMethod
import org.testng.annotations.Test

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.eq
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when
import static org.mockito.MockitoAnnotations.initMocks
import static org.testng.Assert.assertNull

@CompileStatic
class MyClassTest {

    @Mock
    private FooService mockFooService

    private MyClass myClassUnderTest

    @BeforeMethod
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooService)
    }

    @Test
    void testGetFoos1() {
        // Setup
        def expectedResult = new FooData("id", "name", FooStatus.Creating)
        when(mockFooService.getFoos1("id")).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos1("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos1("id")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoos2() {
        // Setup
        def expectedResult = new FooData("id", "name", FooStatus.Creating)
        when(mockFooService.getFoos1()).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos2()

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1()).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos2()

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoos3() {
        // Setup
        def expectedResult = new FooData("id", "name", FooStatus.Creating)
        when(mockFooService.getFoos1("id1")).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos3("id1")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id1")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos3("id1")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoos4() {
        // Setup
        def expectedResult = new FooData("id", "name", FooStatus.Creating)
        when(mockFooService.getFoos1("id")).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos4("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos4("id")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoos5() {
        // Setup
        def expectedResult = new FooData("id", "name", FooStatus.Creating)
        when(mockFooService.getFoos1("id")).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos5("id")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos5("id")

        // Verify the results
        assertNull(result)
    }

    @Test
    void testGetFoos6() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos2("prefix")).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos6("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos6("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos7() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos2("prefix", FooStatus.Creating))
                .thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos7("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos7_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos7("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos8() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos2("prefix", FooStatus.Creating))
                .thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos8("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos8_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos8("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos9() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos2("prefix", FooStatus.Creating))
                .thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos9("prefix", FooStatus.Creating)

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos9_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos9("prefix", FooStatus.Creating)

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos10() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos2("prefix", FooStatus.Creating))
                .thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos10("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos10_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos10("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos11() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos3("prefix")).thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos11("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos11_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3("prefix")).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos11("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos12() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class)))
                .thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos12("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos12_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class))).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos12("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testGetFoos13() {
        // Setup
        def expectedResult = [new FooData("id", "name", FooStatus.Creating)]
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class)))
                .thenReturn([new FooData("id", "name", FooStatus.Creating)])

        // Run the test
        def result = myClassUnderTest.getFoos13("prefix")

        // Verify the results
        assert expectedResult == result
    }

    @Test
    void testGetFoos13_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class))).thenReturn([])

        // Run the test
        def result = myClassUnderTest.getFoos13("prefix")

        // Verify the results
        assert [] == result
    }

    @Test
    void testStoreFoo1() {
        // Setup
        // Run the test
        myClassUnderTest.storeFoo1()

        // Verify the results
        verify(mockFooService).storeFoos1()
    }

    @Test
    void testStoreFoo2() {
        // Setup
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo2(fooData)

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo3() {
        // Setup
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo3(fooData)

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo4() {
        // Setup
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo4(fooData)

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo5() {
        // Setup
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo5(fooData)

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo6() {
        // Setup
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo6(fooData)

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo7() {
        // Setup
        def mainFoo = new FooData("id", "name", FooStatus.Creating)
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo7(mainFoo, fooData)

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo8() {
        // Setup
        def fooData = new FooData("id", "name", FooStatus.Creating)

        // Run the test
        myClassUnderTest.storeFoo8(fooData)

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo9() {
        // Setup
        def fooDatas = [new FooData("id", "name", FooStatus.Creating)]

        // Run the test
        myClassUnderTest.storeFoo9(fooDatas)

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating))
    }

    @Test
    void testStoreFoo10() {
        // Setup
        def fooDatas = [new FooData("id", "name", FooStatus.Creating)]

        // Run the test
        myClassUnderTest.storeFoo10(fooDatas)

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating))
    }
}
