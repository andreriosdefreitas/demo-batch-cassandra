package com.example.demobatchcassandra.batch;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class PersonMapper implements FieldSetMapper<Person> {

    @Override
    public Person mapFieldSet(FieldSet fieldSet) throws BindException {
        Person person = new Person();
        person.setName(fieldSet.readString("name"));
        person.setAge(fieldSet.readInt("age"));
        return person;
    }
}
