package com.example.demobatchcassandra.repository;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PersonRepository extends CassandraRepository<Person, UUID> {

}
