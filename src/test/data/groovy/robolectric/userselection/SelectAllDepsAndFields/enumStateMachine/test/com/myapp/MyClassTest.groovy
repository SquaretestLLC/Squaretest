package com.myapp


import groovy.transform.CompileStatic
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@CompileStatic
@RunWith(RobolectricTestRunner.class)
class MyClassTest {

    @Test
    void testNextState() {
        assert MyClass.Submitted == MyClass.Submitted.nextState()
        assert MyClass.Submitted == MyClass.Escalated.nextState()
        assert MyClass.Submitted == MyClass.Approved.nextState()
    }

    @Test
    void testResponsiblePerson() {
        assert "result" == MyClass.Submitted.responsiblePerson()
        assert "result" == MyClass.Escalated.responsiblePerson()
        assert "result" == MyClass.Approved.responsiblePerson()
    }
}
