package com.example.demobatchcassandra.batch.out;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.Resource;

import java.util.List;

public class PersonOutWriter extends FlatFileItemWriter<List<Person>> {

    public PersonOutWriter(Resource peopleOutCSV) {
        //Set output file location
        this.setResource(peopleOutCSV);

        //All job repetitions should "append" to same output file
        this.setAppendAllowed(true);

        //Name field values sequence based on object properties
        this.setLineAggregator(new DelimitedLineAggregator() {
            {
                setDelimiter(",");
                setFieldExtractor(new BeanWrapperFieldExtractor() {
                    {
                        setNames(new String[] { "id", "name", "age" });
                    }
                });
            }
        });
    }
}
