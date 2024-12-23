/* 2024년 12월 23일 회원관리 */
use scit;
drop table if exists scit.tbl_user;

create table scit.tbl_user(
	seq int auto_increment primary key,
	user_id varchar(255) unique,
	user_pwd varchar(255) not null,
	user_name varchar(255) not null,
	role varchar(255) default 'role_user' check(role in ('role_user','role_admin'))
);

select * from tbl_user;
commit;