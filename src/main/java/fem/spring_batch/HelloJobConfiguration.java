package fem.spring_batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class HelloJobConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .start(helloStep())
                .next(nextHelloStep())
                .build();
    }

    @Bean
    public Step helloStep() {
        return stepBuilderFactory.get("helloStep")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(" >> Hello Spring Batch");
                    return RepeatStatus.FINISHED;
                })
                .build();
    }

    @Bean
    public Step nextHelloStep() {
        return stepBuilderFactory.get("nextHelloStep")
                .tasklet((stepContribution, chunkContext) -> {
                    log.info(" >> Next Hello Batch");
                    return RepeatStatus.FINISHED;
                })

                .build();
    }
}
