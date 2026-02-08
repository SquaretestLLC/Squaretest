package com.myapp

import com.myapp.dtoio.*
import com.myapp.dtoio.io.SpecialInputStream
import com.myapp.dtoio.io.SpecialReader
import groovy.transform.CompileStatic
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock

import static org.mockito.BDDMockito.given
import static org.mockito.BDDMockito.then
import static org.mockito.Mockito.spy
import static org.mockito.MockitoAnnotations.initMocks

@CompileStatic
class MyClassTest {

    @Mock
    private FooProvider mockFooProvider

    private MyClass myClassUnderTest

    @BeforeEach
    void setUp() {
        initMocks(this)
        myClassUnderTest = new MyClass(mockFooProvider)
    }

    @Test
    void testMakeBeanWithIs() {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        def beanWithIs = new BeanWithIs()
        beanWithIs.setId(0L)
        beanWithIs.setName("name")
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        given(mockFooProvider.makeBeanWithIs()).willReturn(beanWithIs)

        // Run the test
        def result = myClassUnderTest.makeBeanWithIs()

        // Verify the results
    }

    @Test
    void testMakeBeanWithIsAndReader() {
        // Setup
        // Configure FooProvider.makeBeanWithIsAndReader(...).
        def beanWithIsAndReader = new BeanWithIsAndReader()
        beanWithIsAndReader.setId(0L)
        beanWithIsAndReader.setName("name")
        beanWithIsAndReader.setInputStream(new ByteArrayInputStream("content".getBytes()))
        beanWithIsAndReader.setReader(new StringReader("content"))
        given(mockFooProvider.makeBeanWithIsAndReader()).willReturn(beanWithIsAndReader)

        // Run the test
        def result = myClassUnderTest.makeBeanWithIsAndReader()

        // Verify the results
    }

    @Test
    void testMakeBeanWithReader() {
        // Setup
        // Configure FooProvider.makeBeanWithReader(...).
        def beanWithReader = new BeanWithReader()
        beanWithReader.setId(0L)
        beanWithReader.setName("name")
        beanWithReader.setReader(new StringReader("content"))
        given(mockFooProvider.makeBeanWithReader()).willReturn(beanWithReader)

        // Run the test
        def result = myClassUnderTest.makeBeanWithReader()

        // Verify the results
    }

    @Test
    void testMakeBeanWithSubIs() {
        // Setup
        // Configure FooProvider.makeBeanWithSubIs(...).
        def beanWithSubIs = new BeanWithSubIs()
        beanWithSubIs.setId(0L)
        beanWithSubIs.setName("name")
        beanWithSubIs.setInputStream(new SpecialInputStream(new ByteArrayInputStream("content".getBytes())))
        given(mockFooProvider.makeBeanWithSubIs()).willReturn(beanWithSubIs)

        // Run the test
        def result = myClassUnderTest.makeBeanWithSubIs()

        // Verify the results
    }

    @Test
    void testMakeBeanWithSubIsMultipleSetterOverloads() {
        // Setup
        // Configure FooProvider.makeBeanWithSubIsMultipleSetterOverloads(...).
        def beanWithSubIsMultipleSetterOverloads = new BeanWithSubIsMultipleSetterOverloads()
        beanWithSubIsMultipleSetterOverloads.setId(0L)
        beanWithSubIsMultipleSetterOverloads.setName("name")
        beanWithSubIsMultipleSetterOverloads.setInputStream(new ByteArrayInputStream("content".getBytes()))
        given(mockFooProvider.makeBeanWithSubIsMultipleSetterOverloads())
                .willReturn(beanWithSubIsMultipleSetterOverloads)

        // Run the test
        def result = myClassUnderTest.makeBeanWithSubIsMultipleSetterOverloads()

        // Verify the results
    }

    @Test
    void testMakeBeanWithSubReader() {
        // Setup
        // Configure FooProvider.makeBeanWithSubReader(...).
        def beanWithSubReader = new BeanWithSubReader()
        beanWithSubReader.setId(0L)
        beanWithSubReader.setName("name")
        beanWithSubReader.setSpecialReader(new SpecialReader(new StringReader("content")))
        given(mockFooProvider.makeBeanWithSubReader()).willReturn(beanWithSubReader)

        // Run the test
        def result = myClassUnderTest.makeBeanWithSubReader()

        // Verify the results
    }

    @Test
    void testMakeBeanWithSubReaderMultipleSetterOverloads() {
        // Setup
        // Configure FooProvider.makeBeanWithSubReaderMultipleSetterOverloads(...).
        def beanWithSubReaderMultipleSetterOverloads = new BeanWithSubReaderMultipleSetterOverloads()
        beanWithSubReaderMultipleSetterOverloads.setId(0L)
        beanWithSubReaderMultipleSetterOverloads.setName("name")
        beanWithSubReaderMultipleSetterOverloads.setSpecialReader(new StringReader("content"))
        given(mockFooProvider.makeBeanWithSubReaderMultipleSetterOverloads())
                .willReturn(beanWithSubReaderMultipleSetterOverloads)

        // Run the test
        def result = myClassUnderTest.makeBeanWithSubReaderMultipleSetterOverloads()

        // Verify the results
    }

    @Test
    void testMakeCloseableBeanWithIs() {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithIs(...).
        def spyCloseableBeanWithIs = spy(new CloseableBeanWithIs())
        spyCloseableBeanWithIs.setId(0L)
        spyCloseableBeanWithIs.setName("name")
        spyCloseableBeanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        given(mockFooProvider.makeCloseableBeanWithIs()).willReturn(spyCloseableBeanWithIs)

        // Run the test
        def result = myClassUnderTest.makeCloseableBeanWithIs()

        // Verify the results
        then(spyCloseableBeanWithIs).should().close()
    }

    @Test
    void testMakeCloseableBeanWithReader() {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithReader(...).
        def spyCloseableBeanWithReader = spy(new CloseableBeanWithReader())
        spyCloseableBeanWithReader.setId(0L)
        spyCloseableBeanWithReader.setName("name")
        spyCloseableBeanWithReader.setReader(new StringReader("content"))
        given(mockFooProvider.makeCloseableBeanWithReader()).willReturn(spyCloseableBeanWithReader)

        // Run the test
        def result = myClassUnderTest.makeCloseableBeanWithReader()

        // Verify the results
        then(spyCloseableBeanWithReader).should().close()
    }
}
