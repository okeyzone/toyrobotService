package com.toy.robot.repository;

import com.toy.robot.model.Report;
import com.toy.robot.model.Robot;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class RobotRepository implements Repository {

    Map<String, Report> reports = new HashMap<>();

    @Override
    public String createReport(Robot robot) {
        String robotId = UUID.randomUUID().toString();
        reports.put(robotId, new Report(robotId, robot));
        return robotId;
    }

    @Override
    public List<Report> getAllReports() {
        return new ArrayList<>(reports.values());
    }

    @Override
    public Report getReportById(String id) {
        return reports.get(id);
    }

    @Override
    public void removeReport(String id) {
        reports.remove(id);
    }

    @Override
    public void removeReportAll() {
        reports.clear();
    }
}
