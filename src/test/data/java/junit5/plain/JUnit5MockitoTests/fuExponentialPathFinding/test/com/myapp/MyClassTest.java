package com.myapp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class MyClassTest {

    @Mock
    private FooService mockFooService;

    private MyClass myClassUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
        myClassUnderTest = new MyClass(mockFooService);
    }

    @Test
    void testGetFoo1() {
        // Setup
        when(mockFooService.getFoo1("fooId")).thenReturn("result");
        when(mockFooService.getFoo2("fooId")).thenReturn("result");
        when(mockFooService.getFoo3("fooId")).thenReturn("result");
        when(mockFooService.getFoo4("fooId")).thenReturn("result");
        when(mockFooService.getFoo5("fooId")).thenReturn("result");
        when(mockFooService.getFoo6("fooId")).thenReturn("result");
        when(mockFooService.getFoo7("fooId")).thenReturn("result");
        when(mockFooService.getFoo8("fooId")).thenReturn("result");
        when(mockFooService.getFoo9("fooId")).thenReturn("result");
        when(mockFooService.getFoo10("fooId")).thenReturn("result");
        when(mockFooService.getFoo11("fooId")).thenReturn("result");
        when(mockFooService.getFoo12("fooId")).thenReturn("result");
        when(mockFooService.getFoo13("fooId")).thenReturn("result");
        when(mockFooService.getFoo14("fooId")).thenReturn("result");
        when(mockFooService.getFoo15("fooId")).thenReturn("result");
        when(mockFooService.getFoo16("fooId")).thenReturn("result");
        when(mockFooService.getFoo17("fooId")).thenReturn("result");
        when(mockFooService.getFoo18("fooId")).thenReturn("result");
        when(mockFooService.getFoo19("fooId")).thenReturn("result");
        when(mockFooService.getFoo20("fooId")).thenReturn("result");
        when(mockFooService.getFoo21("fooId")).thenReturn("result");
        when(mockFooService.getFoo22("fooId")).thenReturn("result");
        when(mockFooService.getFoo23("fooId")).thenReturn("result");
        when(mockFooService.getFoo24("fooId")).thenReturn("result");
        when(mockFooService.getFoo25("fooId")).thenReturn("result");
        when(mockFooService.getFoo26("fooId")).thenReturn("result");
        when(mockFooService.getFoo27("fooId")).thenReturn("result");
        when(mockFooService.getFoo28("fooId")).thenReturn("result");
        when(mockFooService.getFoo29("fooId")).thenReturn("result");
        when(mockFooService.getFoo30("fooId")).thenReturn("result");
        when(mockFooService.getFoo31("fooId")).thenReturn("result");
        when(mockFooService.getFoo32("fooId")).thenReturn("result");
        when(mockFooService.getFoo33("fooId")).thenReturn("result");
        when(mockFooService.getFoo34("fooId")).thenReturn("result");
        when(mockFooService.getFoo35("fooId")).thenReturn("result");
        when(mockFooService.getFoo36("fooId")).thenReturn("result");
        when(mockFooService.getFoo37("fooId")).thenReturn("result");
        when(mockFooService.getFoo38("fooId")).thenReturn("result");
        when(mockFooService.getFoo39("fooId")).thenReturn("result");
        when(mockFooService.getFoo40("fooId")).thenReturn("result");
        when(mockFooService.getFoo41("fooId")).thenReturn("result");
        when(mockFooService.getFoo42("fooId")).thenReturn("result");
        when(mockFooService.getFoo43("fooId")).thenReturn("result");
        when(mockFooService.getFoo44("fooId")).thenReturn("result");
        when(mockFooService.getFoo45("fooId")).thenReturn("result");
        when(mockFooService.getFoo46("fooId")).thenReturn("result");
        when(mockFooService.getFoo47("fooId")).thenReturn("result");
        when(mockFooService.getFoo48("fooId")).thenReturn("result");
        when(mockFooService.getFoo49("fooId")).thenReturn("result");
        when(mockFooService.getFoo50("fooId")).thenReturn("result");
        when(mockFooService.getFoo51("fooId")).thenReturn("result");
        when(mockFooService.getFoo52("fooId")).thenReturn("result");
        when(mockFooService.getFoo53("fooId")).thenReturn("result");
        when(mockFooService.getFoo54("fooId")).thenReturn("result");
        when(mockFooService.getFoo55("fooId")).thenReturn("result");
        when(mockFooService.getFoo56("fooId")).thenReturn("result");
        when(mockFooService.getFoo57("fooId")).thenReturn("result");
        when(mockFooService.getFoo58("fooId")).thenReturn("result");
        when(mockFooService.getFoo59("fooId")).thenReturn("result");
        when(mockFooService.getFoo60("fooId")).thenReturn("result");
        when(mockFooService.getFoo61("fooId")).thenReturn("result");
        when(mockFooService.getFoo62("fooId")).thenReturn("result");
        when(mockFooService.getFoo63("fooId")).thenReturn("result");
        when(mockFooService.getFoo64("fooId")).thenReturn("result");
        when(mockFooService.getFoo65("fooId")).thenReturn("result");
        when(mockFooService.getFoo66("fooId")).thenReturn("result");
        when(mockFooService.getFoo67("fooId")).thenReturn("result");
        when(mockFooService.getFoo68("fooId")).thenReturn("result");
        when(mockFooService.getFoo69("fooId")).thenReturn("result");
        when(mockFooService.getFoo70("fooId")).thenReturn("result");
        when(mockFooService.getFoo71("fooId")).thenReturn("result");
        when(mockFooService.getFoo72("fooId")).thenReturn("result");
        when(mockFooService.getFoo73("fooId")).thenReturn("result");
        when(mockFooService.getFoo74("fooId")).thenReturn("result");
        when(mockFooService.getFoo75("fooId")).thenReturn("result");
        when(mockFooService.getFoo76("fooId")).thenReturn("result");
        when(mockFooService.getFoo77("fooId")).thenReturn("result");
        when(mockFooService.getFoo78("fooId")).thenReturn("result");
        when(mockFooService.getFoo79("fooId")).thenReturn("result");
        when(mockFooService.getFoo80("fooId")).thenReturn("result");
        when(mockFooService.getFoo81("fooId")).thenReturn("result");
        when(mockFooService.getFoo82("fooId")).thenReturn("result");
        when(mockFooService.getFoo83("fooId")).thenReturn("result");
        when(mockFooService.getFoo84("fooId")).thenReturn("result");
        when(mockFooService.getFoo85("fooId")).thenReturn("result");
        when(mockFooService.getFoo86("fooId")).thenReturn("result");
        when(mockFooService.getFoo87("fooId")).thenReturn("result");
        when(mockFooService.getFoo88("fooId")).thenReturn("result");
        when(mockFooService.getFoo89("fooId")).thenReturn("result");
        when(mockFooService.getFoo90("fooId")).thenReturn("result");
        when(mockFooService.getFoo91("fooId")).thenReturn("result");
        when(mockFooService.getFoo92("fooId")).thenReturn("result");
        when(mockFooService.getFoo93("fooId")).thenReturn("result");
        when(mockFooService.getFoo94("fooId")).thenReturn("result");
        when(mockFooService.getFoo95("fooId")).thenReturn("result");
        when(mockFooService.getFoo96("fooId")).thenReturn("result");
        when(mockFooService.getFoo97("fooId")).thenReturn("result");
        when(mockFooService.getFoo98("fooId")).thenReturn("result");
        when(mockFooService.getFoo99("fooId")).thenReturn("result");
        when(mockFooService.getFoo100("fooId")).thenReturn("result");
        when(mockFooService.getOtherThing1("fooId")).thenReturn(Optional.of("value"));

        // Run the test
        final List<String> result = myClassUnderTest.getFoo1("fooId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }

    @Test
    void testGetFoo1_FooServiceGetOtherThing1ReturnsAbsent() {
        // Setup
        when(mockFooService.getOtherThing1("fooId")).thenReturn(Optional.empty());

        // Run the test
        final List<String> result = myClassUnderTest.getFoo1("fooId");

        // Verify the results
        assertEquals(Arrays.asList("value"), result);
    }
}
