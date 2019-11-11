package com.example.demobatchcassandra.batch.in;

import com.example.demobatchcassandra.domain.Person;
import com.example.demobatchcassandra.repository.PersonRepository;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PersonWriter implements ItemWriter<Person> {

    private PersonRepository personRepository;

    public PersonWriter(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void write(List<? extends Person> list) throws Exception {
        personRepository.saveAll(list);
    }
}
