package com.toy.robot.repository;

import com.toy.robot.model.Report;
import com.toy.robot.model.Robot;

import java.util.List;

public interface Repository {

    String createReport(Robot robot);
    List<Report> getAllReports();
    Report getReportById(String id);
    void removeReport(String id);
    void removeReportAll();
}
