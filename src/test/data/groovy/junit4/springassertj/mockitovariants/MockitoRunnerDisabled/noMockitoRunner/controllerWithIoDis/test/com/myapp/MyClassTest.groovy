package com.myapp

import com.myapp.dtoio.*
import com.myapp.dtoio.io.SpecialInputStream
import com.myapp.dtoio.io.SpecialReader
import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc

import static org.assertj.core.api.Assertions.assertThat
import static org.assertj.core.api.Assertions.assertThatThrownBy
import static org.mockito.Mockito.*
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
@CompileStatic
class MyClassTest {

    @Autowired
    private MockMvc mockMvc

    @MockBean
    private FooProvider mockFooProvider

    @Test
    void testMakeBeanWithIsAndIoArgs() {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        def beanWithIs = new BeanWithIs()
        beanWithIs.setId(0L)
        beanWithIs.setName("name")
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIsAndIoArgs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithIsAndIoArgsAlt() {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        def beanWithIs = new BeanWithIs()
        beanWithIs.setId(0L)
        beanWithIs.setName("name")
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIsAndIoArgsAlt")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithServletRequestResponseArgs() {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        def beanWithIs = new BeanWithIs()
        beanWithIs.setId(0L)
        beanWithIs.setName("name")
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithServletRequestResponseArgs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithIs() {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        def beanWithIs = new BeanWithIs()
        beanWithIs.setId(0L)
        beanWithIs.setName("name")
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
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
        when(mockFooProvider.makeBeanWithIsAndReader()).thenReturn(beanWithIsAndReader)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIsAndReader")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithReader() {
        // Setup
        // Configure FooProvider.makeBeanWithReader(...).
        def beanWithReader = new BeanWithReader()
        beanWithReader.setId(0L)
        beanWithReader.setName("name")
        beanWithReader.setReader(new StringReader("content"))
        when(mockFooProvider.makeBeanWithReader()).thenReturn(beanWithReader)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithReader")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithSubIs() {
        // Setup
        // Configure FooProvider.makeBeanWithSubIs(...).
        def beanWithSubIs = new BeanWithSubIs()
        beanWithSubIs.setId(0L)
        beanWithSubIs.setName("name")
        beanWithSubIs.setInputStream(new SpecialInputStream(new ByteArrayInputStream("content".getBytes())))
        when(mockFooProvider.makeBeanWithSubIs()).thenReturn(beanWithSubIs)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubIs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithSubIsMultipleSetterOverloads() {
        // Setup
        // Configure FooProvider.makeBeanWithSubIsMultipleSetterOverloads(...).
        def beanWithSubIsMultipleSetterOverloads = new BeanWithSubIsMultipleSetterOverloads()
        beanWithSubIsMultipleSetterOverloads.setId(0L)
        beanWithSubIsMultipleSetterOverloads.setName("name")
        beanWithSubIsMultipleSetterOverloads.setInputStream(new ByteArrayInputStream("content".getBytes()))
        when(mockFooProvider.makeBeanWithSubIsMultipleSetterOverloads())
                .thenReturn(beanWithSubIsMultipleSetterOverloads)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubIsMultipleSetterOverloads")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithSubReader() {
        // Setup
        // Configure FooProvider.makeBeanWithSubReader(...).
        def beanWithSubReader = new BeanWithSubReader()
        beanWithSubReader.setId(0L)
        beanWithSubReader.setName("name")
        beanWithSubReader.setSpecialReader(new SpecialReader(new StringReader("content")))
        when(mockFooProvider.makeBeanWithSubReader()).thenReturn(beanWithSubReader)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubReader")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeBeanWithSubReaderMultipleSetterOverloads() {
        // Setup
        // Configure FooProvider.makeBeanWithSubReaderMultipleSetterOverloads(...).
        def beanWithSubReaderMultipleSetterOverloads = new BeanWithSubReaderMultipleSetterOverloads()
        beanWithSubReaderMultipleSetterOverloads.setId(0L)
        beanWithSubReaderMultipleSetterOverloads.setName("name")
        beanWithSubReaderMultipleSetterOverloads.setSpecialReader(new StringReader("content"))
        when(mockFooProvider.makeBeanWithSubReaderMultipleSetterOverloads())
                .thenReturn(beanWithSubReaderMultipleSetterOverloads)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubReaderMultipleSetterOverloads")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
    }

    @Test
    void testMakeCloseableBeanWithIs() {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithIs(...).
        def spyCloseableBeanWithIs = spy(new CloseableBeanWithIs())
        spyCloseableBeanWithIs.setId(0L)
        spyCloseableBeanWithIs.setName("name")
        spyCloseableBeanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()))
        when(mockFooProvider.makeCloseableBeanWithIs()).thenReturn(spyCloseableBeanWithIs)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeCloseableBeanWithIs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(spyCloseableBeanWithIs).close()
    }

    @Test
    void testMakeCloseableBeanWithReader() {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithReader(...).
        def spyCloseableBeanWithReader = spy(new CloseableBeanWithReader())
        spyCloseableBeanWithReader.setId(0L)
        spyCloseableBeanWithReader.setName("name")
        spyCloseableBeanWithReader.setReader(new StringReader("content"))
        when(mockFooProvider.makeCloseableBeanWithReader()).thenReturn(spyCloseableBeanWithReader)

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeCloseableBeanWithReader")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true))
        verify(spyCloseableBeanWithReader).close()
    }

    @Test
    void testCopy() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCopy_EmptyInput() {
        // Setup
        def input = new ByteArrayInputStream(new byte[]{})
        def output = new ByteArrayOutputStream()

        // Run the test
        def result = MyClass.copy(input, output)

        // Verify the results
        assertThat(result).isEqualTo(0)
    }

    @Test
    void testCopy_BrokenInput() {
        // Setup
        def input = new InputStream() {
            private final IOException exception = new IOException("Error")

            @Override
            public int read() throws IOException {
                throw exception
            }

            @Override
            public int available() throws IOException {
                throw exception
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }
        def output = new ByteArrayOutputStream()

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }

    @Test
    void testCopy_BrokenOutput() {
        // Setup
        def input = new ByteArrayInputStream("content".getBytes())
        def output = new OutputStream() {

            private final IOException exception = new IOException("Error")

            @Override
            public void write(final int b) throws IOException {
                throw exception
            }

            @Override
            public void flush() throws IOException {
                throw exception
            }

            @Override
            public void close() throws IOException {
                throw exception
            }
        }

        // Run the test
        assertThatThrownBy({
            MyClass.copy(input, output)
        }).isInstanceOf(IOException.class)
    }
}
