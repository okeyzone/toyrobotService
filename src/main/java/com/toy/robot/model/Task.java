package com.toy.robot.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;
import static java.util.Collections.unmodifiableList;
import static org.apache.commons.lang3.EnumUtils.isValidEnum;
import static org.apache.commons.lang3.StringUtils.*;
import static org.springframework.util.Assert.isTrue;

public class Task {

    private static final String PLACE_COMMAND = "PLACE";
    private String command;
    private Position position;
    private static final String COMMA = ",";
    private static final String SINGLE_SPACE = " ";
    private List<Step> steps = new ArrayList<>();

    public Task(){}
    public Task(TaskRequest request){
        isTrue(isNotBlank(request.getCommand()), "Placed command is required");
        this.command = upperCase(request.getCommand());
        validateAndExtractCommand();
    }
    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
        validateAndExtractCommand();
    }

    public Position getPosition() {
        return position;
    }

    public List<Step> getSteps() {
        return unmodifiableList(steps);
    }

    private void validateAndExtractCommand(){
        isTrue(isNotBlank(command), "Placed command is required");
        String trimmedCommand = StringUtils.trim(command);
        isTrue(trimmedCommand.startsWith(PLACE_COMMAND), "Task must begin with PLACE command");
        String[] startPosition = substringBeforeLast(trimmedCommand, COMMA).replace(PLACE_COMMAND, "").trim().split(COMMA);
        isTrue(startPosition.length == 2, "Incomplete task. Task must contain start position (x, y) and facing direction");
        isTrue(isNumeric(startPosition[0]), "X must be a number");
        isTrue(isNumeric(startPosition[1]), "Y must be a number");

        String[] commandSteps = StringUtils.substringAfterLast(trimmedCommand, COMMA).split(SINGLE_SPACE);
        isTrue(isValidEnum(Direction.class, commandSteps[0]), "Direction must be valid any of the following: NORTH, EAST, SOUTH, WEST");
        position = new Position(parseInt(startPosition[0]), parseInt(startPosition[1]), Direction.valueOf(commandSteps[0]));

        for(int i=0; i <= commandSteps.length - 1; i++){
            if(isValidEnum(Step.class, commandSteps[i])){
                steps.add(Step.valueOf(commandSteps[i]));
            }
        }
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
