package com.toy.robot.task.unit.service;

import com.toy.robot.application.RobotApplication;
import com.toy.robot.model.Report;
import com.toy.robot.model.Task;
import com.toy.robot.model.TaskRequest;
import com.toy.robot.model.TaskResponse;
import com.toy.robot.service.RobotController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;

import java.util.Collection;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class RobotControllerTest {

    private static final String REPORT_ID = "12342322";
    @InjectMocks
    private RobotController testee;

    @Mock
    private RobotApplication robotApplication;

    @Mock
    private TaskResponse taskResponse;

    @Mock
    private Report report;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
     }

    @Test
    public void performTaskIsSuccessful() {
        when(robotApplication.performTask(any(Task.class))).thenReturn(taskResponse);
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,EAST MOVE");
        HttpEntity<TaskResponse> response = testee.performTask(taskRequest);
        assertThat(response.getBody(), is(taskResponse));
        verify(robotApplication).performTask(any(Task.class));
    }

    @Test
    public void getReportById() {
        when(robotApplication.getReportById(anyString())).thenReturn(report);
        HttpEntity<Report> response = testee.getReportById(REPORT_ID);
        assertThat(response.getBody(), is(report));
        verify(robotApplication).getReportById(anyString());
    }

    @Test
    public void getAllReports() {
        when(robotApplication.getAllReports()).thenReturn(singletonList(report));
        HttpEntity<Collection<Report>> retrievedReports = testee.getAllReports();
        assertThat(retrievedReports.getBody(), is(singletonList(report)));
        verify(robotApplication).getAllReports();
    }

    @Test
    public void removeReport() {
        doNothing().when(robotApplication).removeReport(anyString());
        testee.removeReport(REPORT_ID);
        verify(robotApplication).removeReport(anyString());
    }

    @Test
    public void removeReportAll() {
        doNothing().when(robotApplication).removeReportAll();
        testee.removeAllReports();
        verify(robotApplication).removeReportAll();
    }
}

