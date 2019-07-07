package com.toy.robot.task.integrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.robot.model.Message;
import com.toy.robot.model.Report;
import com.toy.robot.model.TaskRequest;
import com.toy.robot.model.TaskResponse;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.io.IOException;
import java.util.List;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RobotTaskIntegrationTests {

	private static String PERFORM_TASK_URL = "/toy-robot-service/v1/tasks";
	private static String REPORT_URL = PERFORM_TASK_URL + "/reports";

	@Autowired
	private TestRestTemplate testRestTemplate;
    ObjectMapper objectMapper = new ObjectMapper();

	@Test
    public void performingShortRobotTaskShouldBeSuccessful() {
        String expectedOutput = "0,1,NORTH";
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 0,0,NORTH MOVE");

        HttpEntity<Object> taskRequestEntity = getHttpEntity(taskRequest);

        ResponseEntity<String> restResponse = testRestTemplate.postForEntity(PERFORM_TASK_URL, taskRequestEntity, String.class);
        String reportId = getReportId(restResponse.getBody());
        Assert.assertNotNull(reportId);

        ResponseEntity<String> reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(reportId), String.class);
        Report report = getReport(reportResponseEntity.getBody());
        Assert.assertEquals(expectedOutput, report.getOutput());
        Assert.assertEquals(reportId, report.getId());
        Assert.assertEquals(HttpStatus.OK, reportResponseEntity.getStatusCode());
    }

    @Test
    public void performingMediumRobotTaskShouldBeSuccessful() {
        String expectedOutput = "3,3,NORTH";
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,EAST MOVE MOVE LEFT MOVE");

        HttpEntity<Object> taskRequestEntity = getHttpEntity(taskRequest);

        ResponseEntity<String> restResponse = testRestTemplate.postForEntity(PERFORM_TASK_URL, taskRequestEntity, String.class);
        String reportId = getReportId(restResponse.getBody());
        Assert.assertNotNull(reportId);

        ResponseEntity<String> reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(reportId), String.class);
        Report report = getReport(reportResponseEntity.getBody());
        Assert.assertEquals(expectedOutput, report.getOutput());
        Assert.assertEquals(reportId, report.getId());
        Assert.assertEquals(HttpStatus.OK, reportResponseEntity.getStatusCode());
    }

    @Test
    public void performingExtendedRobotTaskShouldBeSuccessful() {
        String expectedOutput = "2,0,SOUTH";
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 3,4,EAST MOVE MOVE MOVE MOVE RIGHT  MOVE  MOVE MOVE MOVE MOVE RIGHT MOVE MOVE LEFT MOVE MOVE");

        HttpEntity<Object> taskRequestEntity = getHttpEntity(taskRequest);

        ResponseEntity<String> restResponse = testRestTemplate.postForEntity(PERFORM_TASK_URL, taskRequestEntity, String.class);
        String reportId = getReportId(restResponse.getBody());
        Assert.assertNotNull(reportId);

        ResponseEntity<String> reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(reportId), String.class);
        Report report = getReport(reportResponseEntity.getBody());
        Assert.assertEquals(expectedOutput, report.getOutput());
        Assert.assertEquals(reportId, report.getId());
        Assert.assertEquals(HttpStatus.OK, reportResponseEntity.getStatusCode());
    }

    @Test
    public void deleteOneOrAllReportsShouldBeSuccessful() {
        String expectedOutput = "0,1,NORTH";
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 0,0,NORTH MOVE");
        testRestTemplate.delete(getReportUrl(null));
        String reportId = null;
        int count = 3;
        while (count > 0) {
            HttpEntity<Object> taskRequestEntity = getHttpEntity(taskRequest);
            ResponseEntity<String> restResponse = testRestTemplate.postForEntity(PERFORM_TASK_URL, taskRequestEntity, String.class);
            reportId = getReportId(restResponse.getBody());
            Assert.assertNotNull(reportId);
            count--;
        }
        ResponseEntity<String> reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(reportId), String.class);
        Report report = getReport(reportResponseEntity.getBody());
        Assert.assertEquals(expectedOutput, report.getOutput());
        Assert.assertEquals(reportId, report.getId());
        Assert.assertEquals(HttpStatus.OK, reportResponseEntity.getStatusCode());

        testRestTemplate.delete(getReportUrl(reportId));
        reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(reportId), String.class);
        Assert.assertTrue(isBlank(reportResponseEntity.getBody()));
        Assert.assertEquals(HttpStatus.OK, reportResponseEntity.getStatusCode());

        reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(null), String.class);
        List<Report> reports = getAllReports(reportResponseEntity.getBody());
        Assert.assertEquals(2, reports.size());

        testRestTemplate.delete(getReportUrl(null));
        reportResponseEntity = testRestTemplate.getForEntity(getReportUrl(null), String.class);
        reports = getAllReports(reportResponseEntity.getBody());
        Assert.assertTrue(reports.isEmpty());
    }

    @Test
    public void sendingIncompleteTaskReturnsBadRequestMessage() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setCommand("PLACE 1,2,RIGHT MOVE");

        HttpEntity<Object> taskRequestEntity = getHttpEntity(taskRequest);

        ResponseEntity<String> restResponse = testRestTemplate.postForEntity(PERFORM_TASK_URL, taskRequestEntity, String.class);
        Assert.assertNotNull(restResponse.getBody());
        Message message = getMessage(restResponse.getBody());
        Assert.assertEquals("invalid.task.request", message.getCode());
        Assert.assertEquals("Direction must be valid any of the following: NORTH, EAST, SOUTH, WEST", message.getDescription());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, restResponse.getStatusCode());
    }

	private HttpEntity<Object> getHttpEntity(Object body) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(APPLICATION_JSON);
		return new HttpEntity<>(body, headers);
	}

	private String getReportId(String body){

		return convert(body, TaskResponse.class).getReportId();
	}

	private Report getReport(String body){

		return convert(body, Report.class);
	}

    private List<Report> getAllReports(String body){

        return convert(body, List.class);
    }

    private Message getMessage(String body){

        return convert(body, Message.class);
    }

	private <T> T convert(String body, Class<T> clazz){
		try {
			return objectMapper.readValue(body, clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getReportUrl(String reportId){
        StringBuilder builder = new StringBuilder().append(REPORT_URL);
        if(StringUtils.isNotBlank(reportId)) {
            builder.append("/").append(reportId);
        }
        return builder.toString();
    }
}
