alter table member
drop foreign key member_ibfk_1;

alter table member
add constraint member_course
foreign key (course_id) references course(id) on delete cascade;

alter table material
drop foreign key material_ibfk_1;

alter table material
add constraint material_module
foreign key (module_id) references module(id) on delete cascade;

alter table module
drop foreign key module_ibfk_1;

alter table module
add constraint module_course
foreign key (course_id) references course(id) on delete cascade;