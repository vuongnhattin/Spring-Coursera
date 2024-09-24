create table material (
    id int primary key auto_increment,
    name text,
    file_url text,
    file_type varchar(10),
    module_id int,

    foreign key (module_id) references module(id)
)