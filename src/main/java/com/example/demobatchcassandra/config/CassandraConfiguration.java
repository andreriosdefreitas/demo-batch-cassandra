package com.example.demobatchcassandra.config;

import com.example.demobatchcassandra.domain.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;

import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Configuration
@EnableCassandraRepositories(basePackages = {"com.example.demobatchcassandra.repository"})
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    /*
     * Provide a contact point to the configuration.
     */
    public String getContactPoints() {
        return "localhost";
    }

    @Override
    public int getPort() {
        return 32769;
    }

    @Override
    protected boolean getMetricsEnabled() {
        return false;
    }

    /*
     * Provide a keyspace name to the configuration.
     */

    @Override
    protected String getKeyspaceName() {
        return "demodb";
    }

    @Override
    protected List<String> getStartupScripts() {
        String script = "create keyspace IF NOT EXISTS demodb WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};";
        return Arrays.asList(script);
    }

    //@PostConstruct
    public void init() {
        CassandraOperations template = new CassandraTemplate(Objects.requireNonNull(session().getObject()));
        Person jonDoe = template.insert(newPerson("Jon Doe", 40));
    }

    protected static Person newPerson(String name, int age) {
        UUID uuid = UUID.randomUUID();
        return newPerson(uuid, name, age);
    }

    protected static Person newPerson(UUID id, String name, int age) {
        Person person = new Person();
        person.setId(id);
        person.setName(name);
        person.setAge(age);
        return person;
    }
}
