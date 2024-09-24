create table module (
    id int primary key auto_increment,
    name varchar(50),
    module_no int,
    course_id int,

    foreign key (course_id) references course(id),
    unique (module_no, course_id)
)