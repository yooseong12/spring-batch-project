package com.example.springbatchproject.job;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MyJobTaskletOne {

    /**
     * Tasklet : 사용자의 Custom 작업단위로써 Reader, Processor, Writer를 하나로 묶어서 처리하고자 사용합니다
     *
     * [Reader, Processor, Writer]
     * - Reader : chunk 단위로 Reader가 db로부터 데이터를 읽습니다
     * - Processor : Reader가 읽어 들인 데이터를 특정형태 데이터로 가공합니다
     * - Writer : Processor가 가공한 데이터를 chunk단위로 DB 혹은 파일에 저장할 수 있으며,
     * chunk단위로 처리하니 DB의 경우 전체를 롤백하는 낭비를 줄일 수 있습니다.
     */

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean(name = "myJobTaskletOne_Job1")
    public Job myJobTaskletOne_Job1() {
        return this.jobBuilderFactory.get("myJobTaskletOne_Job1")
                .start(myJobTaskletOne_Job1_Step1())
                .next(myJobTaskletOne_Job1_Step2())
                .build();
    }

    @Bean
    public Step myJobTaskletOne_Job1_Step1() {
        return stepBuilderFactory.get("myJobTaskletOne_Job1_Step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("myJobTaskletOne_Job1_Step1");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step myJobTaskletOne_Job1_Step2() {
        return stepBuilderFactory.get("myJobTaskletOne_Job1_Step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("myJobTaskletOne_Job1_Step2");
                        return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
