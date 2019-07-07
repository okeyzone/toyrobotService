package com.toy.robot.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static java.util.Objects.nonNull;

public class Position {

    private int x;
    private int y;
    private Direction direction;

    public Position(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void updateCoordinate(int x, int y) {
        this.x = this.x + x;
        this.y = this.y + y;
    }

    public Position(Position position) {
        this.x = position.getX();
        this.y = position.getY();
        this.direction = position.getDirection();
    }

    public Position getNextPosition() {
        if (nonNull(this.direction)) {
            Position nextPosition = new Position(this);
            switch (this.direction) {
                case NORTH:
                    nextPosition.updateCoordinate(0, 1);
                    break;
                case EAST:
                    nextPosition.updateCoordinate(1, 0);
                    break;
                case SOUTH:
                    nextPosition.updateCoordinate(0, -1);
                    break;
                case WEST:
                    nextPosition.updateCoordinate(-1, 0);
                    break;
            }
            return nextPosition;
        }
        throw new RuntimeException("Invalid robot direction"); //TODO: Change this message: Custom Exception here
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
