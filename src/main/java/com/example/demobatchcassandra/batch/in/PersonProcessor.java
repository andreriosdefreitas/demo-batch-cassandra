package com.example.demobatchcassandra.batch.in;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.batch.item.ItemProcessor;

import java.util.UUID;

public class PersonProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(Person person) throws Exception {
        person.setId(UUID.randomUUID());
        return person;
    }
}
