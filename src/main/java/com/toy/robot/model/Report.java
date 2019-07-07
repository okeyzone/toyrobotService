package com.toy.robot.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Report {
    private String id;
    private String output;

    public Report(){}
    public Report(String id, Robot robot){
        this.id = id;
        output = new StringBuilder()
                .append(robot.getPosition().getX())
                .append(",")
                .append(robot.getPosition().getY())
                .append(",")
                .append(robot.getPosition().getDirection().name())
                .toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
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
