package com.toy.robot.task.unit.model;

import com.toy.robot.model.Step;
import com.toy.robot.task.unit.TestUtil;
import org.junit.Assert;
import org.junit.Test;

public class StepTest {

    @Test
    public void enumTest() {
        TestUtil.assertEnums(Step.class);
    }

    @Test
    public void isMove() {
        Assert.assertTrue(Step.MOVE.isMove());
        Assert.assertFalse(Step.LEFT.isMove());
        Assert.assertFalse(Step.RIGHT.isMove());
    }

    @Test
    public void isLeft() {
        Assert.assertTrue(Step.LEFT.isLeft());
        Assert.assertFalse(Step.RIGHT.isLeft());
        Assert.assertFalse(Step.MOVE.isLeft());
    }

    @Test
    public void isRight() {
        Assert.assertTrue(Step.RIGHT.isRight());
        Assert.assertFalse(Step.LEFT.isRight());
        Assert.assertFalse(Step.MOVE.isRight());    }
}

