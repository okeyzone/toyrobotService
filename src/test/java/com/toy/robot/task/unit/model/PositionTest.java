package com.toy.robot.task.unit.model;

import com.toy.robot.model.Direction;
import com.toy.robot.model.Position;
import com.toy.robot.task.unit.TestUtil;
import org.junit.Before;
import org.junit.Test;


public class PositionTest {

    private Position testee;
    private Position anotherTestee;

    @Before
    public void setUp() {

        testee = new Position(0,4, Direction.EAST);
        anotherTestee = new Position(0,4, Direction.WEST);
    }
    @Test
    public void getSetCoverage() {

        TestUtil.assertEnums(Direction.class);
    }
}

