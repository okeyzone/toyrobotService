package com.toy.robot.model;

public enum Step {

    MOVE, LEFT, RIGHT;

    public boolean isMove() {
        return this == MOVE;
    }

    public boolean isLeft() {
        return this == LEFT;
    }

    public boolean isRight() {
        return this == RIGHT;
    }
}
