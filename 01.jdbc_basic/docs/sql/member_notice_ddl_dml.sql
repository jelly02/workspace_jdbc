-- master : member table
-- 테이블레벨 단일컬럼 식별키 제약 지정
-- 1단계
create table member (
	member_id varchar2(30),
	member_pw varchar2(20) not null,
	name varchar2(20),
	mobile varchar2(13) not null,
	email varchar2(30) not null,
	entry_date varchar2(10) not null,
	grade varchar2(1) not null,
	mileage number(6),
	manager varchar2(20),
	CONSTRAINT PK_MEMBER_ID PRIMARY KEY (MEMBER_ID)
);

-- detail : notice table
-- 게시글 테이블레벨 참조키 지정 	
CREATE table NOTICE (
	notice_no number(8),
    title varchar2(30) not null,
    contents varchar2(500),
    MEMBER_ID varchar2(30),
    write_date date not null,
    hit_count number(10),
    CONSTRAINT PK_NOTICE_notice_no PRIMARY KEY (notice_no),
	constraint fk_memberid foreign key (MEMBER_ID) references member(member_id)
);

-- 2단계 : 제약 추가 변경
-- master : member table
create table member (
	member_id varchar2(30),
	member_pw varchar2(20) not null,
	name varchar2(20),
	mobile varchar2(13) not null,
	email varchar2(30) not null,
	entry_date varchar2(10) not null,
	grade varchar2(1) not null,
	mileage number(6),
	manager varchar2(20)
);

-- constraint add : pk
alter table member
add constraint pk_memberid primary key (member_id);

-- constraint add : mobile 중복불가, NULL 허용
alter table member 
add constraint UK_mobile unique (mobile);

-- detail : notice table
CREATE table NOTICE (
	notice_no number(8),
    title varchar2(30) not null,
    contents varchar2(500),
    MEMBER_ID varchar2(30),
    write_date date not null,
    hit_count number(10)
);

-- constraint add : pk
alter table notice
add CONSTRAINT PK_NOTICE_notice_no PRIMARY KEY (notice_no);

-- constraint add : fk
alter table notice
add constraint fk_memberid foreign key (MEMBER_ID) references member(member_id);


-- 지정한 컬럼, 순서대로 
insert into member(member_id, member_pw, name, mobile, email, entry_date, grade, mileage)
values('user01', 'password01', '홍길동', '010-1234-1111', 'user01@work.com	', '2017.05.05', 'G', 7500);

-- 테이블 스키마 구조 순서대로 : 컬럼에 데이터가 없는 경우에는 임의의값지정, 또는 null
insert into member
values('user02', 'password02', '강감찬', '010-1234-1112', 'user02@work.com', '2017.05.06', 'G', 95000, null);

-- 회원 3명 모두 추가 진행하시기 바랍니다.	
insert into member
values('user03', 'password03', '이순신', '010-1234-1113', 'user03@work.com', '2017.05.07', 'G', 3000, null);

insert into member
values('user04', 'password04', '김유신', '010-1234-1114', 'user04@work.com', '2017.05.08', 'S', null, '송중기');

insert into member
values('user05', 'password05', '유관순', '010-1234-1115', 'user05@work.com', '2017.05.09', 'A', null, null);





-- 게시글 추가 
insert into notice
values ('1','주말과제	','회원도서관리DB설계','user05','2020.11.11','0') ; 
insert into notice
values ('2','형상관리 ','형상관리 소개','user04','2020.12.25','5') ; 
insert into notice
values ('3','주말과제','화면정의서','user05','2021.02.14','0') ; 
insert into notice
values ('4','과제제출','시간엄수','user05','2021.03.01','15') ; 
insert into notice
values ('5','WEB참고','www.w3schools.com','user01','2021.05.26','5') ; 

















