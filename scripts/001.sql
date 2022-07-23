insert into application_role(id, name)
values
    (1, 'ROLE_ADMIN'),
    (2, 'ROLE_USER');

insert into user(id, email, first_name, last_name, password, id_card, city, state, address, application_role_id)
values (1, 'admin@project.com', 'admin', 'admin', 'admin123', 'J123456I','Tirana','Albania','QemalStafa', 1);
