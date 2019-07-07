package com.toy.robot.task.unit.model;

import com.toy.robot.model.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.StringTokenizer;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TaskTest {

    private static final String COMMAND = "PLACE 1,2,EAST MOVE RIGHT";
    private Task testee;
    private Task anotherTestee;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setup() {
        testee = createTask();
        anotherTestee = new Task();
    }

    @Test
    public void taskPosition() {
        assertPosition(1, 2, Direction.EAST);
    }

    @Test
    public void assertGettersAndSetters() {
        Position position = new Position(1, 2, Direction.EAST);
        assertThat(testee.getPosition(), is(position));
        assertThat(testee.getCommand(), is(COMMAND));
        assertThat(testee.getSteps().size(), is(2));
        assertThat(testee.getSteps().contains(Step.MOVE), is(true));
        assertThat(testee.getSteps().contains(Step.RIGHT), is(true));
        assertThat(testee.getSteps().contains(Step.LEFT), is(false));
    }

    @Test
    public void assigningRequestWithoutCommandToTask() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Placed command is required");

        testee.setCommand(null);
    }

    @Test
    public void assigningInvalidCommandToTask() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Incomplete task. Task must contain start position (x, y) and facing direction");

        testee.setCommand("PLACE EAST WEST");
    }

    @Test
    public void requestWithInvalidXPosition() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("X must be a number");

        testee.setCommand("PLACE X,2,EAST MOVE RIGHT");
    }

    @Test
    public void requestWithInvalidYPosition() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Y must be a number");

        testee.setCommand("PLACE 3,Y,EAST MOVE RIGHT");
    }

    @Test
    public void missingCoordinateInPlaceRequest() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Incomplete task. Task must contain start position (x, y) and facing direction");

        testee.setCommand("PLACE 3,EAST MOVE RIGHT");
    }

    @Test
    public void invalidDirectionInPlaceRequest() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("Direction must be valid any of the following: NORTH, EAST, SOUTH, WEST");

        testee.setCommand("PLACE 2,3,LEFT MOVE RIGHT");
    }

    @Test
    public void equalsWhenEqual() {
        anotherTestee = createTask();
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
        anotherTestee = createTask();
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
        assertEquals(3, strTok.countTokens());
        assertTrue(testeeAsString.contains("command=<null>"));
        assertTrue(testeeAsString.contains("position=<null>"));
        assertTrue(testeeAsString.contains("steps=[]"));
    }

    private Task createTask() {
        Task task = new Task(createTaskRequest());
        return task;
    }

    private TaskRequest createTaskRequest() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand(COMMAND);
        return taskRequest;
    }

    private void assertPosition(int xPosition, int yPosition, Direction facing) {
        assertThat(testee.getPosition().getX(), is(xPosition));
        assertThat(testee.getPosition().getY(), is(yPosition));
        assertThat(testee.getPosition().getDirection(), is(facing));
    }
}

