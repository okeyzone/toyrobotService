package com.toy.robot.task.unit.model;

import com.toy.robot.model.Message;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.StringTokenizer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class MessageTest {

    private Message testee;
    private Message anotherTestee;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        testee = new Message("error.code", "error.description");
        anotherTestee = new Message();
    }

    @Test
    public void assertGettersAndSetters() {

        anotherTestee.setCode("new.code");
        anotherTestee.setDescription("new.description");
        assertThat(anotherTestee.getCode(), is("new.code"));
        assertThat(anotherTestee.getDescription(), is("new.description"));
    }

    @Test
    public void equalsWhenEqual() {
        testee = new Message();
        assertTrue(testee.equals(testee));
        assertTrue(testee.equals(anotherTestee));
    }

    @Test
    public void equalsWhenNotEqual() {
        assertFalse(testee.equals(null));
        assertFalse(testee.equals(new Object()));
        assertFalse(testee.equals(anotherTestee));
    }

    @Test
    public void hashCodeSameWhenEqual() {
        testee = new Message();
        assertEquals(testee.hashCode(), anotherTestee.hashCode());
    }

    @Test
    public void hashCodeDifferentWhenNotEqual() {
        assertNotEquals(testee.hashCode(), anotherTestee.hashCode());
    }

    @Test
    public void toStringFields() {
        String testeeAsString = anotherTestee.toString();

        StringTokenizer strTok = new StringTokenizer(testeeAsString, ",");
        assertEquals(2, strTok.countTokens());
        assertTrue(testeeAsString.contains("code=<null>"));
        assertTrue(testeeAsString.contains("description=<null>"));
    }
}

