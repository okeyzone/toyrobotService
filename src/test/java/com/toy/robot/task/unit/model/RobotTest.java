package com.toy.robot.task.unit.model;

import com.toy.robot.model.*;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;

import java.util.StringTokenizer;

import static org.junit.Assert.*;

public class RobotTest {

    private Robot testee;
    private Robot anotherTestee;

    @Before
    public void setup() {
        testee = createRobot();
        anotherTestee = new Robot();
    }

    @Test
    public void robotStepsAndPositioning() {

        assertPosition(1, 2, Direction.EAST);
        testee.takeStep(Step.MOVE);
        assertPosition(2, 2, Direction.EAST);
        testee.takeStep(Step.LEFT);
        assertPosition(2, 2, Direction.NORTH);
        testee.takeStep(Step.MOVE);
        assertPosition(2, 3, Direction.NORTH);
        testee.takeStep(Step.RIGHT);
        assertPosition(2, 3, Direction.EAST);
        testee.takeStep(Step.RIGHT);
        assertPosition(2, 3, Direction.SOUTH);
        testee.takeStep(Step.MOVE);
        assertPosition(2, 2, Direction.SOUTH);
    }

    private void assertPosition(int xPosition, int yPosition, Direction facing) {

        assertThat(testee.getPosition().getX(), Is.is(xPosition));
        assertThat(testee.getPosition().getY(), Is.is(yPosition));
        assertThat(testee.getPosition().getDirection(), Is.is(facing));
    }

    @Test
    public void assertGettersAndSetters() {
        Task task = createTask();
        assertEquals(task, testee.getTask());
        assertEquals(task.getPosition(), testee.getPosition());
    }

    @Test
    public void equalsWhenEqual() {
        anotherTestee = createRobot();
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
        anotherTestee = createRobot();
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
        assertEquals(4, strTok.countTokens());
        assertTrue(testeeAsString.contains("position=<null>"));
        assertTrue(testeeAsString.contains("task=<null>"));
        assertTrue(testeeAsString.contains("minPosition=0"));
        assertTrue(testeeAsString.contains("minPosition=0"));
    }

    private Robot createRobot() {
        Robot robot = new Robot(createTask(), 2, 4);
        return robot;
    }

    private Task createTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,EAST");
        Task task = new Task(taskRequest);
        return task;
    }

}

