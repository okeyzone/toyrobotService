package com.toy.robot.application;

import com.toy.robot.model.*;
import com.toy.robot.repository.RobotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RobotApplicationImpl implements RobotApplication {

    @Value("${task.table.size.row:0}")
    private int minTablePosition;

    @Value("${task.table.size.column:4}")
    private int maxTablePosition;

    @Autowired
    private RobotRepository robotRepository;

    @Override
    public TaskResponse performTask(Task task) {
        validateTaskCoordinates(task);
        Robot robot = new Robot(task, minTablePosition, maxTablePosition);
        robot.getTask().getSteps().forEach(step -> robot.takeStep(step));
        String robotId = robotRepository.createReport(robot);
        return new TaskResponse(robotId);
    }

    @Override
    public List<Report> getAllReports() {
        return robotRepository.getAllReports();
    }

    @Override
    public Report getReportById(String id) {
        return robotRepository.getReportById(id);
    }

    @Override
    public void removeReport(String id) {
        robotRepository.removeReport(id);
    }

    @Override
    public void removeReportAll() {
        robotRepository.removeReportAll();
    }

    private void validateTaskCoordinates(Task task){

        if(!isOnTable(task)){
            throw new ToyRobotTaskException(String.format("Starting point is outside the table. X and Y should between %s and %s inclusive", minTablePosition, maxTablePosition));
        }
    }

    private boolean isOnTable(Task task){

       return isOnTable(task.getPosition().getX()) && isOnTable(task.getPosition().getY());
    }


    private boolean isOnTable(int point){

        return point >= minTablePosition && point <= maxTablePosition;
    }

}
