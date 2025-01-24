-- MovieReview
USE scit;

-- 테이블 삭제
DROP TABLE IF EXISTS scit.movie;
DROP TABLE IF EXISTS scit.review;

-- 테이블 생성
create table scit.movie(
	movie_num int auto_increment,
	genre varchar(50) not null,
	movie_name varchar(50) not null,
	movie_summary varchar(2000) not null,
	movie_date datetime default current_timestamp,
	constraint movie_movie_num_pk primary key(movie_num),
	constraint movie_movie_name unique(movie_name)
);

commit;
select * from movie;
	
create table scit.review(
	review_num int auto_increment,
	reviewer_nickname varchar(50) not null,
	movie_num int not null,
	review_text varchar(2000) not null,
	score int default 0,
	review_date datetime default current_timestamp,
	constraint review_review_num_pk primary key(review_num),
	constraint review_movie_num_fk foreign key(movie_num) references scit.movie(movie_num) on delete cascade,
	constraint review_score_ck check (score >= 0 AND score <= 10)
);

commit;
select * from review;