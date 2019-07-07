NB: The Summary, URLs and sample requests below describe how to place a task, retrieve report(s) and delete report(s)
####Input is via a REST call to the endpoints. The task request payload must be in this format:
{
   PLACE X,Y,FACING STEP1 STEP2 STEP2 ...STEPn
}

While STEP = MOVE, LEFT, RIGHT

Summary: Sending a robot task request to the service
Request Method: POST
URL: localhost:8080/toy-robot-service/v1/tasks

SAMPLE PAYLOAD-1:
{
   "command": " PLACE 1,2,EAST MOVE MOVE LEFT MOVE"
}

SAMPLE PAYLOAD-2:
{
   "command": "PLACE 3,4,EAST MOVE MOVE MOVE MOVE RIGHT  MOVE  MOVE MOVE MOVE MOVE RIGHT MOVE MOVE LEFT MOVE MOVE"
}

Response:
{
    "reportId": "9b72449f-3626-4c8d-97d0-93f9ca254419"
}

NB: When a task is place, a reportId is returned after the task. The reportId can be used to retrieve the output of the task be calling the GET Report endpoints below:
-------------------------------------------------------------------
Summary: Getting the report of a robot task - Retrieves a single report
Method: GET
URL: localhost:8080/toy-robot-service/v1/tasks/reports/{reportId}

Example: localhost:8080/toy-robot-service/v1/tasks/reports/9b72449f-3626-4c8d-97d0-93f9ca254419
{
    "id": "9b72449f-3626-4c8d-97d0-93f9ca254419",
    "output": "3,3,NORTH"
}

Summary: Getting all reports - Retrieves all reports
Method: GET
URL: localhost:8080/toy-robot-service/v1/tasks/reports


NB: Reports can be deleted by calling the delete report endpoint below:
-------------------------------------------------------------------


Summary: Delete a report - Deletes the specified report
Method: DELETE
URL: localhost:8080/toy-robot-service/v1/tasks/reports/{reportId}

Summary: Delete all reports - Deletes all the reports
Method: DELETE
URL: localhost:8080/toy-robot-service/v1/tasks/reports

-------------------------------------------------------------------
There are unit tests coverage and integration tests. The Service can also be testing using Postman or similar application
