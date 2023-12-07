package com.infybuzz.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("this is before job" + jobExecution.getJobInstance().getJobName());
		System.out.println("job params " + jobExecution.getJobParameters());
		System.out.println("job execution context " + jobExecution.getExecutionContext());

		jobExecution.getExecutionContext().put("name", "Dheeraj");

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("this is after job" + jobExecution.getJobInstance().getJobName());
		System.out.println("job params " + jobExecution.getJobParameters());
		System.out.println("job execution context " + jobExecution.getExecutionContext());

	}

}
