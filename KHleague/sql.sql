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
   userReward varchar(300),
   userbirth varchar(300),
   usergender varchar(300),
   userhomenum varchar(300),
   userjoin varchar(300)
);

create table sports(
	sportsnum int primary key auto_increment,
    sport varchar(300)
);

create table team(
	teamnum int primary key auto_increment,
    teamname varchar(300),
    sportsnum int,
    constraint sportsnum foreign key(sportsnum) references sports(sportsnum)
);

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
    goodsSize varchar(300),
    goodsprice bigint,
    teamnum int,
    constraint goods_teamnum foreign key(teamnum) references team(teamnum)
);

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
-- 이벤트 당첨 게시판
create table eboard(
	eboardnum int primary key auto_increment,
    eproduct varchar(300),
    edate varchar(300),
    eboardcontent varchar(3000),
    fboardnum int,
    constraint fboardnum foreign key(fboardnum) references fboard(fboardnum)
);