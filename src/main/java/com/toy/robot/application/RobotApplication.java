package com.toy.robot.application;

import com.toy.robot.model.Report;
import com.toy.robot.model.Task;
import com.toy.robot.model.TaskResponse;

import java.util.List;

public interface RobotApplication {

    TaskResponse performTask(Task task);
    List<Report> getAllReports();
    Report getReportById(String id);
    void removeReport(String id);
    void removeReportAll();
}
