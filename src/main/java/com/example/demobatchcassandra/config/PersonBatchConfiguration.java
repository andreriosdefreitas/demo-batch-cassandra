package com.example.demobatchcassandra.config;

import com.example.demobatchcassandra.batch.in.PersonProcessor;
import com.example.demobatchcassandra.batch.in.PersonReader;
import com.example.demobatchcassandra.batch.in.PersonWriter;
import com.example.demobatchcassandra.batch.out.PersonOutProcessor;
import com.example.demobatchcassandra.batch.out.PersonOutReader;
import com.example.demobatchcassandra.batch.out.PersonOutWriter;
import com.example.demobatchcassandra.domain.Person;
import com.example.demobatchcassandra.repository.PersonRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@EnableBatchProcessing
public class PersonBatchConfiguration extends DefaultBatchConfigurer {

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final PersonRepository personRepository;

    @Value("classpath:csv/people.csv")
    private Resource peopleCSV;

    @Value("classpath:csv/peopleOut.csv")
    private Resource peopleOutCSV;


    public PersonBatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, PersonRepository personRepository) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.personRepository = personRepository;
    }

    @Bean
    public Job personJob(Step insertPerson) {
        return jobBuilderFactory.get("personJob").start(personOut()).build();
    }

    @Bean
    public Step insertPerson() {
        return stepBuilderFactory
                .get("insertPerson")
                .<Person, Person>chunk(1000)
                .reader(new PersonReader(peopleCSV))
                .processor(new PersonProcessor())
                .writer(new PersonWriter(personRepository))
                .build();
    }

    @Bean
    public Step personOut() {
        return stepBuilderFactory
                .get("personOut")
                .<List<Person>, List<Person>> chunk(1000)
                .reader(new PersonOutReader(personRepository))
                .processor(new PersonOutProcessor())
                .writer(new PersonOutWriter(peopleOutCSV))
                .build();
    }

}
