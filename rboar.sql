-- web table만들기
create table users(
	no integer auto_increment primary key
    ,id varchar(20) unique not null
    ,password varchar(20) not null
    ,name varchar(20)
    ,gender varchar(10)
);

select *
from users;

drop table guestbook;

insert into users
values(null, "aaa","123","jws","male");

-- 로그인할때 : pw는 굳이 메모리에 안남겨두는게 좋아서 안가져오는게 좋다.
select no
		,name
from users
where id='aaa'
and password='1234';

-- guestbook table만들기

create table guestbook(
	no integer auto_increment primary key
  ,name varchar(80)
  ,password varchar(20)
  ,content varchar(2000)
  ,reg_date datetime
);

select no
		,name
       ,password
       ,content
       ,reg_date
from guestbook;

insert into guestbook
values(null, "aaa" ,"aaa","방문하고갑니다.",now());

delete
from guestbook
where no=3
and password="ccc";

-- board table만들기(user table fk로 연결
create table board(
	no integer auto_increment primary key
  ,title varchar(500) not null
  ,content varchar(4000)
  ,hit int
  ,reg_date datetime
  ,user_no int not null
  ,constraint board_fk foreign key(user_no)
  references users(no)
);

-- users랑 연결한 board에서 필요한 정보 불러오기(join)
select b.no
		,title
       ,u.name
       ,hit
       ,b.reg_date
from board b join users u
where b.user_no = u.no ;

insert into board
values(null, "보리최강미묘고영","보리는 진짜 전설이다." ,0, now(), 1);
insert into board
values(null, "몬베베","몽스탁스" ,0, now(), 2);
insert into board
values(null, "이민혁","미녀쿠" ,0, now(), 3);
insert into board
values(null, "유기현","키키" ,0, now(), 4);
insert into board
values(null, "임창균","균주" ,0, now(), 5);

delete
from board
where user_no=3;

select b.no
		,title
       ,u.name
       ,hit
       ,b.reg_date
       ,content
       ,b.user_no
from board b join users u
where b.user_no = u.no
and b.no=4;

insert into board
values(null, "보리최강미묘고영","보리는 진짜 전설이다." ,0, now(), 1);

select *
from boausersrd;

update board 
set title = '유기현'
	,content = '키키트리'
where no= 4;

insert into users
values(null , "jh","jh","jh","male");

select *
from users;

select password
		,name
		,gender
from users
where no = 8;

update users
set password ="lll"
	,name="lll"
	,gender="male"
where no=8;

select no
		,name
        ,reg_date
        ,content
from guestbook;

select *
from guestbook;

insert into guestbook
values(null, "ccc","ccc","방문하고갑니다." , now());

delete from guestbook
where no=3 
and password = "ccc";

select *
from board;

select b.no
	  ,title
	  ,name
      ,hit
      ,reg_date
      ,user_no
from board b join users u
where b.user_no = u.no;

select b.no
		,title
       ,u.name
       ,hit
       ,b.reg_date
       ,content
       ,b.user_no
from board b join users u
where b.user_no = u.no
and b.no=4;

insert into board
values(null, "gambler", "gambler", 0, now(), 5);

select name
	  ,hit
      ,reg_date
      ,title
      ,content
from board b join users u
where b.user_no = u.no
and b.no=4;

select *
from board;

delete 
from board
where no=8;

insert into board
values(null, "test" ,"test", 0, now(), 5);

update board
set title="만두"
	,content="맨두"
where no= 12;

select no
		,name
        ,password
        ,content
        ,reg_date
from guestbook;

insert into guestbook
values(null, "vvv", "vvv", "방문", now());

delete from guestbook
where no= 12
and password="zzz";

-- rboard table만들기
create table rboard(
	no integer auto_increment primary key
    ,user_no int not null
	,title varchar(500) not null
	,content text
	,hit int
	,reg_date datetime
    ,group_no int 
    ,order_no int 
	,depth int
	,constraint rboard_fk foreign key(user_no)
  references users(no)
);

select * 
from rboard;

select * 
from board;

select * 
from users;

insert into rboard
values(null, 1, "보리세최묘", "동해물과백두산이", 0,now(),1,1,0);
insert into rboard
values(null, 2, "보리최고", "떴다 비행기", 0,now(),2,1,0);
insert into rboard
values(null, 3, "보리", "푸른하늘 은하수", 0,now(),3,1,0);

select  r.no
		,title
        ,name
        ,hit
        ,reg_date
		,group_no
		,order_no
		,depth
from rboard r join users u
group  by group_no;

select  r.no
		,title
        ,name
        ,hit
        ,reg_date
		,group_no
		,order_no
		,depth
from rboard r join users u
where r.user_no = u.no
order by group_no desc, order_no desc;

update rboard
set order_no=order_no+1
where depth=1
and group_no=80;

insert into rboard
values(null, 1, "우리나라만세","우리나라만세", 0, now(), 1,1,1 );

select*from rboard;


update rboard
set order_no=3
where no=88;

delete from rboard 
where no=97;

