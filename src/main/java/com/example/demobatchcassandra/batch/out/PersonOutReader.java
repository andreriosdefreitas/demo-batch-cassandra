package com.example.demobatchcassandra.batch.out;

import com.example.demobatchcassandra.domain.Person;
import com.example.demobatchcassandra.repository.PersonRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.List;

public class PersonOutReader implements ItemReader<List<Person>> {

    private PersonRepository personRepository;

    public PersonOutReader(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return personRepository.findAll();
    }
}
