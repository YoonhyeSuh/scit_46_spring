-- 2024년 12월 2일 전화번호부 프로젝트

drop database scit;
create database scit;

use scit;

drop table if exists phonebook;

create table phonebook
(
	id int auto_increment primary key,
    fname varchar(50) not null,
    phone varchar(50),
    birthday datetime,
    ftype char(1)
);

commit;
select * from phonebook;