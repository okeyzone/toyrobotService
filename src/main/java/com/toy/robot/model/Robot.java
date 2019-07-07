package com.toy.robot.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static java.util.Objects.nonNull;

public class Robot {

    private Position position;
    private Task task;
    private int minPosition;
    private int maxPosition;

    public Robot(){}{}

    public Robot(Task task, int minPosition, int maxPosition) {
        this.task = task;
        position = task.getPosition();
        this.minPosition = minPosition;
        this.maxPosition = maxPosition;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Task getTask() {
        return task;
    }

    public void takeStep(Step step) {
        if (step.isMove()) {
            move(position.getNextPosition());
        } else if (step.isLeft()) {
            leftTurn();
        } else if (step.isRight()) {
            rightTurn();
        }
    }

    private void move(Position newPosition) {
        if (isValidPosition(newPosition)) {
            this.position = newPosition;
        }
    }

    private boolean isValidPosition(Position position) {
        if (nonNull(position)) {
            switch (position.getDirection()) {
                case NORTH:
                    return isMaxPositionExceeded(position.getY());
                case EAST:
                    return isMaxPositionExceeded(position.getX());
                case WEST:
                    return isMinPositionExceeded(position.getX());
                case SOUTH:
                    return isMinPositionExceeded(position.getY());
            }
        }
        return false;
    }

    private boolean isMaxPositionExceeded(int xyPoint) {
        return xyPoint <= maxPosition;
    }

    private boolean isMinPositionExceeded(int xyPoint) {
        return xyPoint >= minPosition;
    }

    private boolean leftTurn() {
        if (nonNull(position.getDirection())) {
            position.setDirection(position.getDirection().leftDirection());
            return true;
        }
        return false;
    }

    private boolean rightTurn() {
        if (nonNull(position.getDirection())) {
            position.setDirection(position.getDirection().rightDirection());
            return true;
        }
        return false;
    }

    @Override
    public String toString() {

        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public boolean equals(Object obj) {

        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public int hashCode() {

        return HashCodeBuilder.reflectionHashCode(this);
    }
}
