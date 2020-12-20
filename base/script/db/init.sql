create database pethub;
use pethub;

create table sys_user
(
    id varchar(64) null,
    user_name varchar(64) null,
    user_pwd varchar(128) null,
    user_nick_name varchar(64) null,
    user_icon varchar(128) null,
    user_phone varchar(16) null,
    user_email varchar(64) null,
    create_time timestamp null,
    create_user varchar(64) null,
    modify_time timestamp null,
    modify_user varchar(64) null,
    is_delete int null,
    constraint sys_user_pk
        primary key (id)
)engine=innoDB default charset=utf8;

create unique index sys_user_user_name_uindex
	on sys_user (user_name);

create table sys_user_role
(
    id varchar(64) null,
    user_id varchar(64) null,
    role_id varchar(64) null,
    constraint sys_user_role_pk
        primary key (id)
)engine=innoDB default charset=utf8;

create table sys_role
(
    id varchar(64) null,
    role_name varchar(64) null,
    role_describe varchar(64) null,
    create_time timestamp null,
    create_user varchar(64) null,
    modify_time timestamp null,
    modify_user varchar(64) null,
    is_delete int null,
    constraint sys_role_pk
        primary key (id)
)engine=innoDB default charset=utf8;

create table sys_role_right
(
    id varchar(64) null,
    role_id varchar(64) null,
    right_id varchar(64) null,
    constraint sys_role_right_pk
        primary key (id)
)engine=innoDB default charset=utf8;

create table sys_right
(
    id varchar(64) null,
    right_name varchar(64) null,
    right_icon varchar(64) null,
    right_url varchar(64) null,
    create_time timestamp null,
    create_user varchar(64) null,
    modify_time timestamp null,
    modify_user varchar(64) null,
    is_delete int null,
    constraint sys_right_pk
        primary key (id)
)engine=innoDB default charset=utf8;

create table sys_log
(
    id varchar(64) null,
    log_describe varchar(64) null,
    create_time timestamp null,
    create_user varchar(64) null,
    constraint sys_log_pk
        primary key (id)
)engine=innoDB default charset=utf8;
