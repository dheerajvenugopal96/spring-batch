package com.infybuzz.service;

import java.util.HashMap;
import java.util.List;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.infybuzz.model.JobParamsRequest;

@Service
public class JobService {

	@Autowired
	JobLauncher jobLauncher;

	@Qualifier("firstJob")
	@Autowired
	Job firstJob;

	@Qualifier("secondJob")
	@Autowired
	Job secondJob;

	@Autowired
	SecondJobScheduler secondJobScheduler;

	@Async
	public void startJob(String jobName, List<JobParamsRequest> jobParamsRequestList)
			throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> params = new HashMap<>();
		params.put("currentTime", new JobParameter(System.currentTimeMillis()));

		jobParamsRequestList.stream().forEach(jobParamRequest -> {
			params.put(jobParamRequest.getParamKey(), new JobParameter(jobParamRequest.getParamValue()));
		});

		JobExecution jobExecution = null;
		JobParameters jobParameters = new JobParameters(params);
		if (jobName.equals("First Job")) {
			jobExecution = jobLauncher.run(firstJob, jobParameters);
		} else if (jobName.equals("Second Job")) {
			secondJobScheduler.secondJobStarter();
		}

		System.out.println(jobExecution);
	}

}
