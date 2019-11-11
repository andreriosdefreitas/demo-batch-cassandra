package com.example.demobatchcassandra.batch.out;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.batch.item.ItemProcessor;

import java.util.List;

public class PersonOutProcessor implements ItemProcessor<List<Person>, List<Person>> {
    @Override
    public List<Person> process(List<Person> people) throws Exception {
        people.forEach(person -> person.setName(person.getName().toUpperCase()));
        return people;
    }
}
