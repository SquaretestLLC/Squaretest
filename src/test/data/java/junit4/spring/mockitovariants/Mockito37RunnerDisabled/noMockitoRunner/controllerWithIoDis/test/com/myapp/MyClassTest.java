package com.myapp;

import com.myapp.dtoio.*;
import com.myapp.dtoio.io.SpecialInputStream;
import com.myapp.dtoio.io.SpecialReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MyClass.class)
public class MyClassTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FooProvider mockFooProvider;

    @Test
    public void testMakeBeanWithIsAndIoArgs() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        final BeanWithIs beanWithIs = new BeanWithIs();
        beanWithIs.setId(0L);
        beanWithIs.setName("name");
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIsAndIoArgs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithIsAndIoArgsAlt() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        final BeanWithIs beanWithIs = new BeanWithIs();
        beanWithIs.setId(0L);
        beanWithIs.setName("name");
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIsAndIoArgsAlt")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithServletRequestResponseArgs() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        final BeanWithIs beanWithIs = new BeanWithIs();
        beanWithIs.setId(0L);
        beanWithIs.setName("name");
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithServletRequestResponseArgs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithIs() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithIs(...).
        final BeanWithIs beanWithIs = new BeanWithIs();
        beanWithIs.setId(0L);
        beanWithIs.setName("name");
        beanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithIs()).thenReturn(beanWithIs);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithIsAndReader() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithIsAndReader(...).
        final BeanWithIsAndReader beanWithIsAndReader = new BeanWithIsAndReader();
        beanWithIsAndReader.setId(0L);
        beanWithIsAndReader.setName("name");
        beanWithIsAndReader.setInputStream(new ByteArrayInputStream("content".getBytes()));
        beanWithIsAndReader.setReader(new StringReader("content"));
        when(mockFooProvider.makeBeanWithIsAndReader()).thenReturn(beanWithIsAndReader);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithIsAndReader")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithReader() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithReader(...).
        final BeanWithReader beanWithReader = new BeanWithReader();
        beanWithReader.setId(0L);
        beanWithReader.setName("name");
        beanWithReader.setReader(new StringReader("content"));
        when(mockFooProvider.makeBeanWithReader()).thenReturn(beanWithReader);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithReader")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithSubIs() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithSubIs(...).
        final BeanWithSubIs beanWithSubIs = new BeanWithSubIs();
        beanWithSubIs.setId(0L);
        beanWithSubIs.setName("name");
        beanWithSubIs.setInputStream(new SpecialInputStream(new ByteArrayInputStream("content".getBytes())));
        when(mockFooProvider.makeBeanWithSubIs()).thenReturn(beanWithSubIs);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubIs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithSubIsMultipleSetterOverloads() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithSubIsMultipleSetterOverloads(...).
        final BeanWithSubIsMultipleSetterOverloads beanWithSubIsMultipleSetterOverloads = new BeanWithSubIsMultipleSetterOverloads();
        beanWithSubIsMultipleSetterOverloads.setId(0L);
        beanWithSubIsMultipleSetterOverloads.setName("name");
        beanWithSubIsMultipleSetterOverloads.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeBeanWithSubIsMultipleSetterOverloads())
                .thenReturn(beanWithSubIsMultipleSetterOverloads);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubIsMultipleSetterOverloads")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithSubReader() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithSubReader(...).
        final BeanWithSubReader beanWithSubReader = new BeanWithSubReader();
        beanWithSubReader.setId(0L);
        beanWithSubReader.setName("name");
        beanWithSubReader.setSpecialReader(new SpecialReader(new StringReader("content")));
        when(mockFooProvider.makeBeanWithSubReader()).thenReturn(beanWithSubReader);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubReader")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeBeanWithSubReaderMultipleSetterOverloads() throws Exception {
        // Setup
        // Configure FooProvider.makeBeanWithSubReaderMultipleSetterOverloads(...).
        final BeanWithSubReaderMultipleSetterOverloads beanWithSubReaderMultipleSetterOverloads = new BeanWithSubReaderMultipleSetterOverloads();
        beanWithSubReaderMultipleSetterOverloads.setId(0L);
        beanWithSubReaderMultipleSetterOverloads.setName("name");
        beanWithSubReaderMultipleSetterOverloads.setSpecialReader(new StringReader("content"));
        when(mockFooProvider.makeBeanWithSubReaderMultipleSetterOverloads())
                .thenReturn(beanWithSubReaderMultipleSetterOverloads);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeBeanWithSubReaderMultipleSetterOverloads")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
    }

    @Test
    public void testMakeCloseableBeanWithIs() throws Exception {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithIs(...).
        final CloseableBeanWithIs spyCloseableBeanWithIs = spy(new CloseableBeanWithIs());
        spyCloseableBeanWithIs.setId(0L);
        spyCloseableBeanWithIs.setName("name");
        spyCloseableBeanWithIs.setInputStream(new ByteArrayInputStream("content".getBytes()));
        when(mockFooProvider.makeCloseableBeanWithIs()).thenReturn(spyCloseableBeanWithIs);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeCloseableBeanWithIs")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(spyCloseableBeanWithIs).close();
    }

    @Test
    public void testMakeCloseableBeanWithReader() throws Exception {
        // Setup
        // Configure FooProvider.makeCloseableBeanWithReader(...).
        final CloseableBeanWithReader spyCloseableBeanWithReader = spy(new CloseableBeanWithReader());
        spyCloseableBeanWithReader.setId(0L);
        spyCloseableBeanWithReader.setName("name");
        spyCloseableBeanWithReader.setReader(new StringReader("content"));
        when(mockFooProvider.makeCloseableBeanWithReader()).thenReturn(spyCloseableBeanWithReader);

        // Run the test and verify the results
        mockMvc.perform(get("/fooBeanController/makeCloseableBeanWithReader")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("{}", true));
        verify(spyCloseableBeanWithReader).close();
    }

    @Test
    public void testCopy() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test
    public void testCopy_EmptyInput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream(new byte[]{});
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        final int result = MyClass.copy(input, output);

        // Verify the results
        assertEquals(0, result);
    }

    @Test(expected = IOException.class)
    public void testCopy_BrokenInput() throws Exception {
        // Setup
        final InputStream input = new InputStream() {
            private final IOException exception = new IOException("Error");

            @Override
            public int read() throws IOException {
                throw exception;
            }

            @Override
            public int available() throws IOException {
                throw exception;
            }

            @Override
            public long skip(final long n) throws IOException {
                throw exception;
            }

            @Override
            public synchronized void reset() throws IOException {
                throw exception;
            }

            @Override
            public void close() throws IOException {
                throw exception;
            }
        };
        final OutputStream output = new ByteArrayOutputStream();

        // Run the test
        MyClass.copy(input, output);
    }

    @Test(expected = IOException.class)
    public void testCopy_BrokenOutput() throws Exception {
        // Setup
        final InputStream input = new ByteArrayInputStream("content".getBytes());
        final OutputStream output = new OutputStream() {

            private final IOException exception = new IOException("Error");

            @Override
            public void write(final int b) throws IOException {
                throw exception;
            }

            @Override
            public void flush() throws IOException {
                throw exception;
            }

            @Override
            public void close() throws IOException {
                throw exception;
            }
        };

        // Run the test
        MyClass.copy(input, output);
    }
}
