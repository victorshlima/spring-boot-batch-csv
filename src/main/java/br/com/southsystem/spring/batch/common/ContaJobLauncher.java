package br.com.southsystem.spring.batch.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
<<<<<<< HEAD
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
=======
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
>>>>>>> 0014
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Component
public class ContaJobLauncher {

<<<<<<< HEAD
    private static final Logger LOGGER = LoggerFactory.getLogger(ContaJobLauncher.class);

    private final Job job;
    private final JobLauncher jobLauncher;
	private ScheduledFuture scheduledFuture;
    @Autowired
    public ContaJobLauncher(Job job, JobLauncher jobLauncher) {
        this.job = job;
        this.jobLauncher = jobLauncher;
    }

   @Scheduled(initialDelay = 1 , fixedDelay=Long.MAX_VALUE)
    public void runSpringBatchContaJob() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        LOGGER.info("Conta job iniciou");
        jobLauncher.run(job, newExecution());
        LOGGER.info("Conta job parou");
        LOGGER.info("Processo concluído encerrando...Scheduler ");
       scheduledFuture.cancel(true);
       LOGGER.info("Processo encerrado!!! ");
    }
    private JobParameters newExecution() {
        Map<String, JobParameter> parameters = new HashMap<>();
        JobParameter parameter = new JobParameter(new Date());
        parameters.put("currentTime", parameter);
        return new JobParameters(parameters);
    }
=======

	
	@Autowired
	private Job job;
	
	@Autowired
	private JobLauncher jobLauncher;	       
    
	public JobExecution execute() throws Exception, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		return jobLauncher.run(job, params);
	}
    
>>>>>>> 0014
}
