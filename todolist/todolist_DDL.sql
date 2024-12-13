-- 2024년 12월 12일 Todo List 프로젝트
create database scit;
use scit;

create table todolist(
		seqno int auto_increment primary key,
        regdate datetime default current_timestamp,
        status varchar(10) check(status in ('완료', '진행', '지연')),
        importance varchar(10) check(importance in ('높음', '보통', '낮음')),
        categories varchar(6) default '개인',
        todo varchar(3000)
);

drop table if exists todolist;

select * from todolist;
commit;
