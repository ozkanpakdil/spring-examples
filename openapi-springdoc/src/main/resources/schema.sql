create table if not exists employee (
id serial primary key,
first_name varchar(255) not null,
last_name varchar(255) not null
);