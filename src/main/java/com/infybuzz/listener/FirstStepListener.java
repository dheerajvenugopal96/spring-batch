package com.infybuzz.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class FirstStepListener implements StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("this is before step" + stepExecution.getStepName());
		System.out.println("job execution context " + stepExecution.getJobExecution().getExecutionContext());
		System.out.println("step execution context " + stepExecution.getExecutionContext());
		stepExecution.getExecutionContext().put("new name", "Dheeru Bhai");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {

		System.out.println("this is after step" + stepExecution.getStepName());
		System.out.println("job execution context " + stepExecution.getJobExecution().getExecutionContext());
		System.out.println("step execution context " + stepExecution.getExecutionContext());
		return null;
	}

}
