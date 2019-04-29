drop database if exists vbank2;
create database vbank2;
use vbank2;
-- 权限控制表
create table roles(
`username` varchar(30) not null,
`password` varchar(100) not null,
`role` varchar(10) not null,
primary key(`username`))charset=utf8;

create index role_idx on roles(`username`);

-- 余额表
create table card_balance(
`cardId` char(20) not null,
`balance` decimal(20,2) not null,
primary key(`cardId`));

create index cardbalance_idx on card_balance(`cardId`);

# record model
#create table records(
#`cardId` char(20) not null,
#`money` decimal(20,2) not null,
#`date` datetime default current_timestamp,
#`comment` varchar(100));
#

-- 用户表
create table users(
`username` varchar(30) not null,
`uid` char(18) not null,
`email` varchar(30) not null unique,
`cardId` char(20) not null,
`init` datetime default current_timestamp,
primary key(`uid`)) charset=utf8;

create index uid_idx on users(`uid`);
create index email_idx on users(`email`);
create index cardid_idx on users(`cardId`);

-- 员工表
create table employees(
`workId` char(8) not null,
`salary` decimal(20,2) not null,
`name` varchar(30) not null,
`init` datetime default current_timestamp,
`university` varchar(30));

create index wid_idx on employees(`workId`);

-- 被冻结的卡的表
create table frozen(
`cardId` char(20),
primary key (cardId));

create  index fro_cid_idx on frozen(`cardId`);