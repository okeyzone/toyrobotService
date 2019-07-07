package com.toy.robot.task.unit.model;

import com.toy.robot.model.Direction;
import com.toy.robot.task.unit.TestUtil;
import org.junit.Assert;
import org.junit.Test;


public class DirectionTest {

    @Test
    public void enumTest() {
        TestUtil.assertEnums(Direction.class);
    }

    @Test
    public void getIndex() {

        Assert.assertEquals(3, Direction.WEST.getIndex());
    }

    @Test
    public void leftDirection() {

        Assert.assertEquals(Direction.WEST, Direction.NORTH.leftDirection());
    }

    @Test
    public void rightDirection() {

        Assert.assertEquals(Direction.EAST, Direction.NORTH.rightDirection());
    }
}

