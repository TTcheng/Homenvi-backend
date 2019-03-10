create table test
(
  id   int auto_increment
    primary key,
  name varchar(30) not null
);

INSERT INTO test(name) values ('Jesse');
SELECT * FROM test;

