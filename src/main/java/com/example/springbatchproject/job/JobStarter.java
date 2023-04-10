package com.example.springbatchproject.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

@Configuration
@RequiredArgsConstructor
public class JobStarter {

    private final JobLauncher jobLauncher;
    private final Job myJobTaskletOne_Job1;
    private final Job myJobTwo_Job1;

    @Scheduled(fixedDelay = 5000)
    public void TaskletStart() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = new JobParametersBuilder()
                .addString("requestDate", LocalDateTime.now().toString())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(myJobTaskletOne_Job1, parameters);
    }

    @Scheduled(fixedDelay = 3000)
    public void JobStart() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = new JobParametersBuilder()
                .addString("requestDate", LocalDateTime.now().toString())
                .toJobParameters();

        JobExecution execution = jobLauncher.run(myJobTwo_Job1, parameters);
    }
}
