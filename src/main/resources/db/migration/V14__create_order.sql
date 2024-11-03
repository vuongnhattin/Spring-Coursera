create table orders (
    id int primary key auto_increment,
    user_id int not null,
    course_id int not null,
    token varchar(255) not null,
    foreign key (user_id) references users(id) on delete cascade,
    foreign key (course_id) references course(id) on delete cascade
)