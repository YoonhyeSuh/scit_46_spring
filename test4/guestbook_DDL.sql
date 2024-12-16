-- 2024년 12월 16일
use scit;

drop table if exists guestbook;

create table guestbook(
	seqno int auto_increment primary key,
    guest_name varchar(50) not null,
    guest_pwd varchar(50) not null,
    content varchar(1000) not null,
    regdate datetime default current_timestamp
);

commit;
select * from guestbook;