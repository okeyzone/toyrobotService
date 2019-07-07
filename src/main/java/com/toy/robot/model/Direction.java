package com.toy.robot.model;

import java.util.HashMap;
import java.util.Map;

public enum Direction {

    NORTH(0), EAST(1), SOUTH(2), WEST(3);
    private static final int LEFT = -1;
    private static final int RIGHT = 1;
    private static Map<Integer, Direction> directionMap = new HashMap<Integer, Direction>();

    static {
        for (Direction direction : Direction.values()) {
            directionMap.put(direction.index, direction);
        }
    }

    private int index;

    Direction(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public static Direction valueOf(int directionNum) {
        return directionMap.get(directionNum);
    }

    public Direction leftDirection() {
        return changeDirection(LEFT);
    }

    public Direction rightDirection() {
        return changeDirection(RIGHT);
    }

    private Direction changeDirection(int step) {
        return Direction.valueOf((this.index + step) < 0 ? directionMap.size() - 1 : (this.index + step) % directionMap.size());
    }

}
