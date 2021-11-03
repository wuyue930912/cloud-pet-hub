create database pet_hub;
use pet_hub;

drop table if exists sys_user;

create table sys_user
(
    id             varchar(64)  null,
    user_name      varchar(64)  null,
    user_pwd       varchar(128) null,
    user_nick_name varchar(64)  null,
    user_icon      varchar(128) null,
    user_phone     varchar(16)  null,
    user_email     varchar(64)  null,
    create_time    timestamp    null,
    create_user    varchar(64)  null,
    modify_time    timestamp    null,
    modify_user    varchar(64)  null,
    delete_flag    tinyint(1)   null,
    constraint sys_user_pk
        primary key (id)
) engine = innoDB
  default charset = utf8;

create unique index sys_user_user_name_uindex on sys_user (user_name);
