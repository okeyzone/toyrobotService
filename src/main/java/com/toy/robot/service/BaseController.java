package com.toy.robot.service;

import com.toy.robot.model.Message;
import com.toy.robot.model.ToyRobotTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

    private static final String CODE = "invalid.task.request";

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message handleIllegalArgumentException(IllegalArgumentException e) {

        return new Message(CODE, e.getMessage());
    }

    @ExceptionHandler(ToyRobotTaskException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Message handleToyRobotTaskException(ToyRobotTaskException e) {

        return new Message(CODE, e.getMessage());
    }
}
