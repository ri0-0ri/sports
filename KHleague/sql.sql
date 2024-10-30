create database sports;
use sports;
drop database sports;

drop table user;
drop table sports;
drop table team;
drop table player;
drop table sudan;
drop table goods;
drop table orders;
drop table g_will_board;
drop table g_end_board;
drop table fboard;
drop table eboard;

select * from user;
select * from sports;
select * from team;
select * from player;
select * from sudan;
select * from goods;
select * from orders;
select * from g_will_board;
select * from g_end_board;
select * from fboard;
select * from eboard;

create table user(
	userid varchar(300) primary key,
	userpw varchar(300),
   username varchar(300),
   userphone varchar(300),
   useraddr varchar(300),
   userReward varchar(300) default "5000",
   userbirth varchar(300),
   usergender varchar(300),
   userhomenum varchar(300),
   userjoin varchar(300)
);

create table sports(
	sportsnum int primary key auto_increment,
    sport varchar(300)
);

insert into sports(sport) values("축구");
insert into sports(sport) values("야구");
insert into sports(sport) values("농구");
insert into sports(sport) values("배구");

create table team(
	teamnum int primary key auto_increment,
    teamname varchar(300),
    sportsnum int,
    constraint sportsnum foreign key(sportsnum) references sports(sportsnum)
);
insert into team(teamname, sportsnum) values("FC서울", 1);

create table player(
	playernum int primary key auto_increment,
    playername varchar(300),
    playerEname varchar(300),
    playerCountry varchar(300),
    playerAge int,
    playerH varchar(300),
    playerW varchar(300),
    playerP varchar(300),
    teamnum int,
    constraint player_teamnum foreign key(teamnum) references team(teamnum)
);

create table sudan(
	sudannum int primary key
);

create table goods(
	goodsnum int primary key auto_increment,
    goodstype varchar(300),
    goodsname varchar(300),
    goodsprice bigint,
    goodsimg varchar(300)
);
select * from goods;
insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Outerwear", "24 이동복 상의(레드)", 109000, "/images/굿즈/24 이동복 상의(레드).jpg");
insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Outerwear", "JBFC 다이노스 PK 맨투맨", 69000, "/images/굿즈/JBFC 다이노스 PK 맨투맨.jpg");

insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Top", "24 연습복 긴팔 상의(블랙)", 59000, "/images/굿즈/24 연습복 긴팔 상의(블랙).jpg");
insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Top", "JBFC 아메리칸 풋볼져지 (화이트)", 99000, "/images/굿즈/JBFC 아메리칸 풋볼져지 (화이트).jpg");

insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Bottom", "TIRO 24 트레이닝 팬츠 (네이비)", 87000, "/images/굿즈/TIRO 24 트레이닝 팬츠 (네이비).jpg");
insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Bottom", "2024 스틸러스 트레이닝 팀컵 팬츠 (블랙)", 69000, "/images/굿즈/2024 스틸러스 트레이닝 팀컵 팬츠 (블랙).png");

insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Accessories", "수원FC 슈니 더블 축구공 키링", 5000, "/images/굿즈/수원FC 슈니 더블 축구공 키링.png");
insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Accessories", "JBFC 슬로건 볼캡(베이지)", 25000, "/images/굿즈/JBFC 슬로건 볼캡(베이지).jpg");

insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Other items", "수원FC 24 사인볼(남자)", 20000, "/images/굿즈/수원FC 24 사인볼(남자).jpg");
insert into goods(goodstype, goodsname, goodsprice, goodsimg) values("Other items", "2024 스틸러스 브랜딩 니트머플러", 28000, "/images/굿즈/2024 스틸러스 브랜딩 니트머플러.png");

create table orders(
	ordernum int primary key auto_increment,
    orderdatetime datetime,
    deliveryPlace varchar(300),
    deliveryMemo varchar(300),
    totalPrice bigint,
    sudannum int,
    userid varchar(300),
    goodsnum int,
    constraint sudannum foreign key(sudannum) references sudan(sudannum),
    constraint order_userid foreign key(userid) references user(userid),
    constraint goodsnum foreign key(goodsnum) references goods(goodsnum)
);

create table wishList(
	wishnum int primary key auto_increment,
    userid varchar(300),
    goodsnum int,
	constraint wish_userid foreign key(userid) references user(userid),
	constraint goodsnumWish foreign key(goodsnum) references goods(goodsnum)
);
drop table wishList;
select * from wishList;

create table buyList(
	buynum int primary key auto_increment,
    userid varchar(300),
    goodsnum int,
    size varchar(300),
    quantity int,
	constraint buy_userid foreign key(userid) references user(userid),
	constraint goodsnumBuy foreign key(goodsnum) references goods(goodsnum)
);
select * from buyList;

create table g_will_board(
	gWnum int primary key auto_increment,
    gWdate datetime,
    teamnum int,
    constraint gW_teamnum foreign key(teamnum) references team(teamnum)
);

create table g_end_board(
	gEnum int primary key auto_increment,
    gEdate datetime,
    gEscore varchar(300),
	teamnum int,
    constraint gE_teamnum foreign key(teamnum) references team(teamnum)
);

create table fboard(
	fboardnum int primary key auto_increment,
    fboardtitle varchar(300),
    fboardcontent varchar(3000),
    userid varchar(300),
    gWnum int,
	constraint fboard_userid foreign key(userid) references user(userid),
    constraint gWnum foreign key(gWnum) references g_will_board(gWnum)
);

create table eboard(
	eboardnum int primary key auto_increment,
    eproduct varchar(300),
    edate varchar(300),
    eboardcontent varchar(3000),
    fboardnum int,
    constraint fboardnum foreign key(fboardnum) references fboard(fboardnum)
);