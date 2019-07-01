package com.rajesh.camunda;

import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkflowResource {
	
	@Autowired
	private RuntimeService runtimeService;
	
	@GetMapping(path = "/loan")
	private String initiateLoanApproval() {
		runtimeService.startProcessInstanceByKey("loanApproval");
		return "Loan Approval Initiated";
	}
	
}
