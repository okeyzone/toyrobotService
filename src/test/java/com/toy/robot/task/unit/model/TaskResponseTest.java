package com.toy.robot.task.unit.model;

import com.toy.robot.model.TaskResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskResponseTest {

    private TaskResponse testee;

    @Before
    public void setUp() {
        testee = new TaskResponse();
        testee.setReportId("id233");
    }

    @Test
    public void assertGettersAndSetters() {
        Assert.assertEquals("id233", testee.getReportId());
    }
}

