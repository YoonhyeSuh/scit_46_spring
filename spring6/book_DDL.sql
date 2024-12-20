/* 2024/12/20 (개인 독서 노트)*/
/*OneToOne*/
use scit;

drop table if exists scit.reading_note;
drop table if exists scit.book;

create table scit.book(
	book_seq int auto_increment primary key,
	title varchar(200) not null,
	writer varchar(200) not null,
	publisher varchar(200) not null,
	purchase_date datetime default current_timestamp,
	price int default 0
);

create table reading_note(
	reading_seq int auto_increment primary key,
	read_status varchar(50) check(read_status in ('읽는 중', '읽음')),
	read_date datetime,
	book_review varchar(1000),
	book_seq int references book(book_seq) on delete cascade
);
 
commit;

select * from book;
select * from reading_note;