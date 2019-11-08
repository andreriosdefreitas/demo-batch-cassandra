create keyspace demoDb WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 3};

use demoDb;

CREATE TABLE Person (
    id uuid PRIMARY KEY,
    name text,
    age int
);
