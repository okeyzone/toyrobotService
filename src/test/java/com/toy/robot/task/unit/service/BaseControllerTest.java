package com.toy.robot.task.unit.service;

import com.toy.robot.model.Message;
import com.toy.robot.model.ToyRobotTaskException;
import com.toy.robot.service.BaseController;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BaseControllerTest {

    private BaseController testee;
    @Before
    public void setUp(){
        testee = new BaseController();
    }
    @Test
    public void handleIllegalArgumentException() {
        IllegalArgumentException ex = new IllegalArgumentException("Some exception message");
        Message response = testee.handleIllegalArgumentException(ex);
        assertThat(response.getCode(), is("invalid.task.request"));
        assertThat(response.getDescription(), is("Some exception message"));
    }

    @Test
    public void handleToyRobotTaskException() {
        ToyRobotTaskException ex = new ToyRobotTaskException("Some exception message");
        Message response = testee.handleToyRobotTaskException(ex);
        assertThat(response.getCode(), is("invalid.task.request"));
        assertThat(response.getDescription(), is("Some exception message"));
    }

}

