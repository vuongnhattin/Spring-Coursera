create table users (
    id int primary key auto_increment,
    username varchar(255),
    password varchar(255),
    first_name varchar(255),
    last_name varchar(255),
    email varchar(255),
    roles varchar(255)
);