insert into application_role(id, name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into user(id, email, first_name, last_name, password, id_card, age, city, state, address, application_role_id)
values (1, 'admin@project.com', 'admin', 'admin', 'admin123', 'J123456I',31,'Tirana','Albania','QemalStafa', 1),
       (2, 'l@gmail.com', 'leca',  'mersa', 'leca123', 'J131156I', 21,'Tirana','Albania','QemalStafa', 2);

