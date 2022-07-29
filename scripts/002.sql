drop table if exists request;

create table request (
                            id bigint auto_increment primary key,
                            user_id bigint not null ,
                            vaccine_id bigint not null,

                            unique  index pk_real(user_id, vaccine_id),
                            foreign key fk_user(user_id) references user(id),
                            foreign key fk_vaccine(vaccine_id) references vaccine(id)

);