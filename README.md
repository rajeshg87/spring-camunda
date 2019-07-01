http://localhost:8080/rest/engine/default/task/?processDefinitionKey=Opportunity

Start Process:

POST

http://localhost:8080/rest/engine/default/process-definition/key/Opportunity/start
{
	"businessKey": 101,
	"variables":
	{
		"assignee": {
			"value": "Rajesh"
		} 
	}
}

Complete Task:

POST

http://localhost:8080/rest/engine/default/task/8367e4d6-9b9f-11e9-8368-e2cdba48c6ba/complete

{
	"variables":
	{
		"approver": {
			"value": "Rajesh"
		} 
	}
}
