/*2024년 12월 30일 온라인 차량 대여(카쉐어링)*/
use scit;
drop table if exists scit.sharing_user;
drop table if exists scit.sharing_car;
drop table if exists scit.sharing_order;

-- 1) 회원 테이블
create table scit.sharing_user(
	user_id varchar(50),
	user_pw varchar(100) not null,
	user_nm varchar(50) not null,
	roles varchar(50) default 'ROLE_USER',
	constraint sharinguser_userid primary key(user_id),
	constraint sharinguser_roles check(roles in('ROLE_USER', 'ROLE_ADMIN'))
);

commit;
select * from sharing_user;

-- 2) 차량정보 테이블
create table scit.sharing_car(
	car_seq int auto_increment,
	car_type varchar(50) not null,
	car_status char(1) default '0',
	constraint sharingcar_carseq primary key(car_seq),
	constraint sharingcar_carstatus check(car_status in('1', '0'))
);

commit;
select * from sharing_car;

insert into sharing_car (car_type) values ('모닝'); 
insert into sharing_car (car_type) values ('레이'); 
insert into sharing_car (car_type) values ('코나'); 
insert into sharing_car (car_type) values ('투싼'); 
insert into sharing_car (car_type) values ('K3'); 
insert into sharing_car (car_type) values ('K5'); 
insert into sharing_car (car_type) values ('아반떼');
insert into sharing_car (car_type) values ('쏘나타'); 

-- 3) 주문정보 테이블
create table scit.sharing_order(
	order_seq int auto_increment,
	user_id varchar(20),
	car_id int,
	sharing_status char(1) default '0',
	sharing_date datetime default current_timestamp,
	constraint sharingorder_orderseq primary key(order_seq),
	constraint sharingorder_userid foreign key (user_id) references scit.sharing_user(user_id) on delete cascade,
	constraint sharingorder_carid foreign key (car_id) references scit.sharing_car(car_seq) on delete cascade, CHECK (sharing_status IN ('0', '1'))
);

commit;
select * from sharing_order;