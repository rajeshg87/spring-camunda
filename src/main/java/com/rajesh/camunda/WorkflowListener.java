package com.rajesh.camunda;

import org.camunda.bpm.application.impl.event.ProcessApplicationEventListenerPlugin;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.camunda.bpm.spring.boot.starter.SpringBootProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class WorkflowListener  extends SpringBootProcessApplication{
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Bean
	ProcessApplicationEventListenerPlugin processApplicationEventListenerPlugin() {
	    return new ProcessApplicationEventListenerPlugin();
	}
	
	@Override
	public ExecutionListener getExecutionListener() {
		return new ExecutionListener() {
			
			public void notify(DelegateExecution execution) throws Exception {
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%"+execution.getBusinessKey());
				
			}
		};
	}
	
	@Override
	public TaskListener getTaskListener() {
		return new TaskListener() {
			public void notify(DelegateTask delegateTask) {
				System.out.println("Task================================" + delegateTask.getTaskDefinitionKey()
						+ " --- " + delegateTask.getEventName());
				if(delegateTask.getEventName().equals("create")) {
					delegateTask.setAssignee("Rajesh");
				}
				
			}
		};
	}

	@EventListener
	private void processPostDeploy(PostDeployEvent event) {
		runtimeService.startProcessInstanceByKey("loanApproval");
	}

}
