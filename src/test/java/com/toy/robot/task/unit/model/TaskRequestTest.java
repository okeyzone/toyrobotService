package com.toy.robot.task.unit.model;

import com.toy.robot.model.TaskRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskRequestTest {

    private TaskRequest testee;

    @Before
    public void setUp() {
        testee = new TaskRequest();
        testee.setCommand("PLACE X,Y,WEST");
    }

    @Test
    public void assertGettersAndSetters() {
        Assert.assertEquals("PLACE X,Y,WEST", testee.getCommand());
    }
}

