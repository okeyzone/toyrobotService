package com.toy.robot.service;

import com.toy.robot.application.RobotApplication;
import com.toy.robot.model.Report;
import com.toy.robot.model.Task;
import com.toy.robot.model.TaskRequest;
import com.toy.robot.model.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("toy-robot-service/v1/tasks")
public class RobotController extends BaseController {

    @Autowired
    private RobotApplication robotApplication;

    @PostMapping
    @ResponseBody
    public HttpEntity<TaskResponse> performTask(@RequestBody TaskRequest taskRequest) {
        return new HttpEntity(robotApplication.performTask(new Task(taskRequest)));
    }

    @GetMapping(value = "/reports")
    @ResponseBody
    public HttpEntity<Collection<Report>> getAllReports() {
        return new HttpEntity(robotApplication.getAllReports());
    }

    @GetMapping(value = "/reports/{id}")
    @ResponseBody
    public HttpEntity<Report> getReportById(@PathVariable("id") String id) {
        return new HttpEntity(robotApplication.getReportById(id));
    }

    @DeleteMapping(value = "/reports/{id}")
    public void removeReport(@PathVariable("id") String id) {
        robotApplication.removeReport(id);
    }

    @DeleteMapping(value = "/reports")
    public void removeAllReports() {
        robotApplication.removeReportAll();
    }

}
