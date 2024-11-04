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
    playerimage varchar(300),
    constraint player_teamnum foreign key(teamnum) references team(teamnum)
);


#FC 서울 팀 20명 (이미지는 넣어야함 )
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('김용대', 'Kim Yong-dae', '대한민국', 37, '185cm', '80kg', '골키퍼', 1, 'image1.jpg'),
('이청용', 'Lee Chung-yong', '대한민국', 35, '175cm', '70kg', '미드필더', 1, 'image2.jpg'),
('박주영', 'Park Ju-young', '대한민국', 38, '182cm', '77kg', '포워드', 1, 'image3.jpg'),
('오스마르', 'Osmar', '스페인', 32, '180cm', '75kg', '미드필더', 1, 'image4.jpg'),
('이승기', 'Lee Seung-ki', '대한민국', 34, '183cm', '78kg', '수비수', 1, 'image5.jpg'),
('주세종', 'Joo Se-jong', '대한민국', 29, '176cm', '72kg', '미드필더', 1, 'image6.jpg'),
('한동민', 'Han Dong-min', '대한민국', 27, '177cm', '73kg', '수비수', 1, 'image7.jpg'),
('문서원', 'Moon Seo-won', '대한민국', 25, '180cm', '80kg', '미드필더', 1, 'image8.jpg'),
('이태석', 'Lee Tae-seok', '대한민국', 28, '185cm', '85kg', '수비수', 1, 'image9.jpg'),
('조영욱', 'Cho Young-wook', '대한민국', 24, '182cm', '76kg', '포워드', 1, 'image10.jpg'),
('고요한', 'Ko Yo-han', '대한민국', 30, '177cm', '73kg', '수비수', 1, 'image11.jpg'),
('이진현', 'Lee Jin-hyun', '대한민국', 29, '179cm', '74kg', '미드필더', 1, 'image12.jpg'),
('세징야', 'Sejingya', '브라질', 32, '174cm', '70kg', '미드필더', 1, 'image13.jpg'),
('박재현', 'Park Jae-hyun', '대한민국', 26, '180cm', '76kg', '미드필더', 1, 'image14.jpg'),
('김신욱', 'Kim Shin-wook', '대한민국', 35, '194cm', '92kg', '포워드', 1, 'image15.jpg'),
('여름', 'Yeoreum', '대한민국', 24, '178cm', '72kg', '미드필더', 1, 'image16.jpg'),
('신진호', 'Shin Jin-ho', '대한민국', 33, '179cm', '75kg', '미드필더', 1, 'image17.jpg'),
('한승규', 'Han Seung-kyu', '대한민국', 26, '182cm', '77kg', '수비수', 1, 'image18.jpg'),
('송진우', 'Song Jin-woo', '대한민국', 31, '186cm', '82kg', '골키퍼', 1, 'image19.jpg'),
('이용', 'Lee Yong', '대한민국', 36, '180cm', '74kg', '수비수', 1, 'image20.jpg');
 
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