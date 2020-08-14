package com.example.demobatchcassandra.batch.out;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.FieldExtractor;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.core.io.Resource;

import java.util.List;

public class PersonOutWriter extends FlatFileItemWriter<Person> {

    public PersonOutWriter(Resource peopleOutCSV) {
        //Set output file location
        this.setResource(peopleOutCSV);

        //All job repetitions should "append" to same output file
        this.setAppendAllowed(true);

        this.setLineAggregator(createLineAggregator());
    }

    private LineAggregator<Person> createLineAggregator() {
        DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(",");

        FieldExtractor<Person> fieldExtractor = createFieldExtractor();
        lineAggregator.setFieldExtractor(fieldExtractor);
        return lineAggregator;
    }

    private FieldExtractor<Person> createFieldExtractor() {
        BeanWrapperFieldExtractor<Person> extractor = new BeanWrapperFieldExtractor<>();
        extractor.setNames(new String[] {"id", "name", "age"});
        return extractor;
    }
}
