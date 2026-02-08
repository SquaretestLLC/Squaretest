package com.myapp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
