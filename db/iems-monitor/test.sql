create table test
(
  id   int auto_increment
    primary key,
  loginName varchar(30) not null
);

INSERT INTO test(loginName) values ('Jesse');
SELECT * FROM test;

