package com.toy.robot.task.unit.application;

import com.toy.robot.application.RobotApplicationImpl;
import com.toy.robot.model.*;
import com.toy.robot.repository.RobotRepository;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static java.util.Collections.singletonList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.ReflectionTestUtils.setField;

public class RobotApplicationImplTest {

    private static final String REPORT_ID = "12342322";
    @InjectMocks
    private RobotApplicationImpl testee;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private RobotRepository robotRepository;

    @Mock
    private Report report;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        setTableSize(0, 4);
    }

    @Test
    public void performTaskIsSuccessful() {
        when(robotRepository.createReport(any(Robot.class))).thenReturn(REPORT_ID);
        TaskResponse taskResponse = testee.performTask(createTask());
        assertThat(taskResponse.getReportId(), is(REPORT_ID));
        verify(robotRepository).createReport(any(Robot.class));
    }

    @Test
    public void getReportById() {
        when(robotRepository.getReportById(anyString())).thenReturn(report);
        Report retrievedReport = testee.getReportById(REPORT_ID);
        assertThat(retrievedReport, is(report));
        verify(robotRepository).getReportById(anyString());
    }

    @Test
    public void getAllReports() {
        when(robotRepository.getAllReports()).thenReturn(singletonList(report));
        List<Report> retrievedReports = testee.getAllReports();
        assertThat(retrievedReports, is(singletonList(report)));
        verify(robotRepository).getAllReports();
    }

    @Test
    public void removeReport() {
        doNothing().when(robotRepository).removeReport(anyString());
        testee.removeReport(REPORT_ID);
        verify(robotRepository).removeReport(anyString());
    }

    @Test
    public void removeReportAll() {
        doNothing().when(robotRepository).removeReportAll();
        testee.removeReportAll();
        verify(robotRepository).removeReportAll();
    }

    @Test
    public void performingTaskWithBeyondTableSize() {
        exception.expect(ToyRobotTaskException.class);
        exception.expectMessage("Starting point is outside the table. X and Y should between 0 and 4 inclusive");

        testee.performTask(createInvalidCoordinateTask());
    }

    private Task createTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,EAST MOVE RIGHT");
        Task task = new Task(taskRequest);
        return task;
    }

    private Task createInvalidCoordinateTask() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 4,8,EAST MOVE RIGHT");
        Task task = new Task(taskRequest);
        return task;
    }

    private void setTableSize(int min, int max) {
        setField(testee, "minTablePosition", min);
        setField(testee, "maxTablePosition", max);
    }
}

