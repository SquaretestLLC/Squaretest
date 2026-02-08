package com.myapp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
    public void testGetFoos1() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooStatus.Creating);

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos1_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final FooData result = myClassUnderTest.getFoos1("id");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoos2() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooStatus.Creating);

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos1()).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoos2();

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos2_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1()).thenReturn(Collections.emptyList());

        // Run the test
        final FooData result = myClassUnderTest.getFoos2();

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoos3() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooStatus.Creating);

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos1("id1")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoos3("id1");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos3_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id1")).thenReturn(Collections.emptyList());

        // Run the test
        final FooData result = myClassUnderTest.getFoos3("id1");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoos4() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooStatus.Creating);

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos4_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final FooData result = myClassUnderTest.getFoos4("id");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoos5() {
        // Setup
        final FooData expectedResult = new FooData("id", "name", FooStatus.Creating);

        // Configure FooService.getFoos1(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos1("id")).thenReturn(fooData);

        // Run the test
        final FooData result = myClassUnderTest.getFoos5("id");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos5_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos1("id")).thenReturn(Collections.emptyList());

        // Run the test
        final FooData result = myClassUnderTest.getFoos5("id");

        // Verify the results
        assertThat(result).isNull();
    }

    @Test
    public void testGetFoos6() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos2("prefix")).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos6("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos6_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos6("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos7() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos7("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos7_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos7("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos8() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos8("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos8_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos8("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos9() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos9("prefix", FooStatus.Creating);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos9_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos9("prefix", FooStatus.Creating);

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos10() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos2(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos10("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos10_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos2("prefix", FooStatus.Creating)).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos10("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos11() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos3(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos3("prefix")).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos11("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos11_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3("prefix")).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos11("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos12() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos3(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class))).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos12("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos12_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos12("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testGetFoos13() {
        // Setup
        final List<FooData> expectedResult = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Configure FooService.getFoos3(...).
        final List<FooData> fooData = Arrays.asList(new FooData("id", "name", FooStatus.Creating));
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class))).thenReturn(fooData);

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos13("prefix");

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    public void testGetFoos13_FooServiceReturnsNoItems() {
        // Setup
        when(mockFooService.getFoos3(eq("prefix"), any(FooFilter.class))).thenReturn(Collections.emptyList());

        // Run the test
        final List<FooData> result = myClassUnderTest.getFoos13("prefix");

        // Verify the results
        assertThat(result).isEqualTo(Collections.emptyList());
    }

    @Test
    public void testStoreFoo1() {
        // Setup
        // Run the test
        myClassUnderTest.storeFoo1();

        // Verify the results
        verify(mockFooService).storeFoos1();
    }

    @Test
    public void testStoreFoo2() {
        // Setup
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo2(fooData);

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo3() {
        // Setup
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo3(fooData);

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo4() {
        // Setup
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo4(fooData);

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo5() {
        // Setup
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo5(fooData);

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo6() {
        // Setup
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo6(fooData);

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo7() {
        // Setup
        final FooData mainFoo = new FooData("id", "name", FooStatus.Creating);
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo7(mainFoo, fooData);

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo8() {
        // Setup
        final FooData fooData = new FooData("id", "name", FooStatus.Creating);

        // Run the test
        myClassUnderTest.storeFoo8(fooData);

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo9() {
        // Setup
        final List<FooData> fooDatas = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Run the test
        myClassUnderTest.storeFoo9(fooDatas);

        // Verify the results
        verify(mockFooService).storeFoos1(new FooData("id", "name", FooStatus.Creating));
    }

    @Test
    public void testStoreFoo10() {
        // Setup
        final List<FooData> fooDatas = Arrays.asList(new FooData("id", "name", FooStatus.Creating));

        // Run the test
        myClassUnderTest.storeFoo10(fooDatas);

        // Verify the results
        verify(mockFooService).storeFoos2(new FooData("id", "name", FooStatus.Creating),
                new FooData("id", "name", FooStatus.Creating));
    }
}
