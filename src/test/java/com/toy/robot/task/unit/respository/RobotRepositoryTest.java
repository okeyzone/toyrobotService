package com.toy.robot.task.unit.respository;

import com.toy.robot.model.Report;
import com.toy.robot.model.Robot;
import com.toy.robot.model.Task;
import com.toy.robot.model.TaskRequest;
import com.toy.robot.repository.RobotRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class RobotRepositoryTest {

    @InjectMocks
    private RobotRepository testee;

    @Mock
    private Report report;

    private String reportId;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        reportId = testee.createReport(createRobot());
     }

    @Test
    public void performTaskIsSuccessful() {
        assertThat(isNotBlank(reportId), is(true));
    }

    @Test
    public void getReportById() {
        Report retrievedRobot = testee.getReportById(reportId);
        assertThat(retrievedRobot, is(expectedReport()));
    }

    @Test
    public void getAllReports() {
        List<Report> retrievedReports = testee.getAllReports();
        assertThat(retrievedReports, is(singletonList(expectedReport())));
    }

    @Test
    public void removeReport() {
        testee.removeReport(reportId);
        assertThat(testee.getAllReports().isEmpty(), is(true));
    }

    @Test
    public void removeReportAll() {
        testee.removeReportAll();
        assertThat(testee.getAllReports().isEmpty(), is(true));
    }

    private Robot createRobot() {
        Robot robot = new Robot(createTask(), 2, 4);
        return robot;
    }

    private Task createTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,EAST");
        Task task = new Task(taskRequest);
        return task;
    }

    private Report expectedReport(){

        Report report = new Report();
        report.setOutput("1,2,EAST");
        report.setId(reportId);
        return report;
    }
}

