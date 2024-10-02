create table member (
    id int primary key auto_increment,
    user_id varchar(255),
    course_id int,

    foreign key (course_id) references course(id)
)