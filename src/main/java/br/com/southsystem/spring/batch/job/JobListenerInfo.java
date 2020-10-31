package br.com.southsystem.spring.batch.job;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListenerInfo implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Iniciou o Job " + jobExecution.getJobInstance() + " (" + jobExecution.getStatus().name() + ")");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Finalizou o Job " + jobExecution.getJobInstance() + " (" + jobExecution.getStatus().name() + ")");
	}
}
