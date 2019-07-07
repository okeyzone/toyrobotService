package com.toy.robot.task.unit.model;

import com.toy.robot.model.Report;
import com.toy.robot.model.Robot;
import com.toy.robot.model.Task;
import com.toy.robot.model.TaskRequest;
import org.junit.Test;

import java.util.StringTokenizer;

import static org.junit.Assert.*;

public class ReportTest {

    private Report testee;
    private Report anotherTestee;

    @Test
    public void reportConstructor() {
        Robot robot = createRobot();
        testee = new Report("5556", robot);
        String output = "1,2,EAST";
        assertEquals("5556", testee.getId());
        assertEquals(output, testee.getOutput());
    }

    @Test
    public void assertGettersAndSetters() {
        testee = new Report();
        testee.setId("1234");
        String output = "0,3, WEST";
        testee.setOutput(output);
        assertEquals("1234", testee.getId());
        assertEquals(output, testee.getOutput());
    }

    @Test
    public void equalsWhenEqual() {
        testee = new Report();
        anotherTestee = new Report();
        assertTrue(testee.equals(testee));
        assertTrue(testee.equals(anotherTestee));
    }

    @Test
    public void equalsWhenNotEqual() {
        testee = new Report();
        testee.setId("123232");
        anotherTestee = new Report();
        assertFalse(testee.equals(null));
        assertFalse(testee.equals(new Object()));
        assertFalse(testee.equals(anotherTestee));
    }

    @Test
    public void hashCodeSameWhenEqual() {
        testee = new Report();
        anotherTestee = new Report();
        assertEquals(testee.hashCode(), anotherTestee.hashCode());
    }

    @Test
    public void hashCodeDifferentWhenNotEqual() {
        testee = new Report();
        testee.setId("123232");
        anotherTestee = new Report();
        assertNotEquals(testee.hashCode(), anotherTestee.hashCode());
    }

    @Test
    public void toStringFields() {
        testee = new Report();
        String testeeAsString = testee.toString();

        StringTokenizer strTok = new StringTokenizer(testeeAsString, ",");
        assertEquals(2, strTok.countTokens());
        assertTrue(testeeAsString.contains("id=<null>"));
        assertTrue(testeeAsString.contains("output=<null>"));
    }

    private Robot createRobot() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,EAST");
        Task task = new Task(taskRequest);
        Robot robot = new Robot(task, 2, 4);
        return robot;
    }

}

