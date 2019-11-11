package com.example.demobatchcassandra.batch.in;

import com.example.demobatchcassandra.batch.PersonMapper;
import com.example.demobatchcassandra.domain.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.core.io.Resource;

public class PersonReader extends FlatFileItemReader<Person> {

    public PersonReader(Resource peopleCSV) {
        super();
        this.setResource(peopleCSV);
        this.setLineMapper(createPersonMapper());
    }

    private LineMapper<Person> createPersonMapper() {
        DefaultLineMapper<Person> personDefaultLineMapper = new DefaultLineMapper<>();
        personDefaultLineMapper.setLineTokenizer(createPersonLineTokenizer());
        personDefaultLineMapper.setFieldSetMapper(new PersonMapper());
        return personDefaultLineMapper;
    }

    private LineTokenizer createPersonLineTokenizer() {
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(",");
        delimitedLineTokenizer.setNames("name", "age");
        return delimitedLineTokenizer;
    }
}
