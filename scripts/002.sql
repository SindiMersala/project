drop table if exists notification;

create table notification (
                            id bigint auto_increment primary key,
                            user_id bigint not null,
                            vaccine_center_id bigint not null,

                            unique index pk_real(user_id, vaccine_center_id),
                            foreign key fk_user(user_id) references user(id),
                            foreign key fk_vaccineCenter(vaccine_center_id) references vaccine_center(id)
);