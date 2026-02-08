package com.myapp;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class MyClassTest {

    @Test
    public void testNextState() {
        assertEquals(MyClass.Submitted, MyClass.Submitted.nextState());
        assertEquals(MyClass.Submitted, MyClass.Escalated.nextState());
        assertEquals(MyClass.Submitted, MyClass.Approved.nextState());
    }

    @Test
    public void testResponsiblePerson() {
        assertEquals("result", MyClass.Submitted.responsiblePerson());
        assertEquals("result", MyClass.Escalated.responsiblePerson());
        assertEquals("result", MyClass.Approved.responsiblePerson());
    }
}
