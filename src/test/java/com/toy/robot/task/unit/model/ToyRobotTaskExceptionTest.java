package com.toy.robot.task.unit.model;

import com.toy.robot.model.ToyRobotTaskException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ToyRobotTaskExceptionTest {

    @Test
    public void exceptionTest() {

        ToyRobotTaskException testee = new ToyRobotTaskException("some-message");
        assertThat(testee.getMessage(), is("some-message"));
    }


}

