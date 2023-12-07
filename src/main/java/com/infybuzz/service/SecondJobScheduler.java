package com.infybuzz.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SecondJobScheduler {

	@Autowired
	JobLauncher jobLauncher;

	@Qualifier("secondJob")
	@Autowired
	Job secondJob;

//	@Scheduled(cron = "0 0/1 * 1/1 * ?")
	public void secondJobStarter() throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> params = new HashMap<>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));

		JobExecution jobExecution = null;
		JobParameters jobParameters = new JobParameters(params);

		jobExecution = jobLauncher.run(secondJob, jobParameters);

		System.out.println(jobExecution);
	}
}
