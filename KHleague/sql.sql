create database sports;
use sports;
-- drop database sports;

-- drop table user;
-- drop table sports;
-- drop table team;
-- drop table player;
-- drop table goods;
-- drop table orders;
-- drop table g_will_board;
-- drop table g_end_board;
-- drop table fboard;
-- drop table eboard;
-- drop table wishList;
-- drop table buyList;
-- drop table money;
-- drop table orderList;
drop table votes;
select * from user;
select * from sports;
select * from team;
select * from player;
select * from goods;
select * from orders;
select * from g_will_board;
select * from g_end_board;
select * from fboard;
select * from eboard;
select * from wishList;
select * from buyList;
select * from money;
select * from orderList;
select * from votes;
CREATE TABLE user (
    userid VARCHAR(300) PRIMARY KEY,
    userpw VARCHAR(300),
    username VARCHAR(300),
    userphone VARCHAR(300),
    useraddr VARCHAR(300),
    userReward VARCHAR(300),
    userbirth VARCHAR(300),
    usergender VARCHAR(300),
    userhomenum VARCHAR(300),
    userjoin VARCHAR(300),
    userpoint int default 0,
    role VARCHAR(50) DEFAULT 'user'
);
#관리자 계정 추가 
INSERT INTO user (userid, userpw, username, userphone, useraddr, userReward, userbirth, usergender, userhomenum, userjoin, role)
VALUES ('admin', '12345678', '관리자', '000-0000-0000', '주소', '5000', '2000-01-01', '남', '홈넘버', '2023-11-04', 'admin');

# 주의! 이 테이블은 무조건 moneyname에 적립금/포인트/충전/사용/취소/소멸 과 같은 단어가 들어가야함
# 혹시나 moneyname을 set해줄때 주의해서 넣어주세요
create table money(
   moneyId int auto_increment primary key,
    moneydate datetime default now(),
    moneytype varchar(300),
   moneyname varchar(300),
    changeMoney varchar(300),
    userid varchar(300),
    ordernum int,
   constraint useridR foreign key(userid) references user(userid) ON DELETE CASCADE
);
select * from money;

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
    teamname varchar(300) UNIQUE,
    sportsnum int,
    teamloggo varchar(300),
    constraint sportsnum foreign key(sportsnum) references sports(sportsnum)
);
insert into team(teamname, sportsnum, teamloggo) values("FC서울", 1, '');
insert into team(teamname, sportsnum, teamloggo) values("전북 현대 모터스", 1,'');
insert into team(teamname, sportsnum, teamloggo) values("삼성 라이온즈", 2,'');
insert into team(teamname, sportsnum, teamloggo) values("두산 베어스", 2,'');
insert into team(teamname, sportsnum, teamloggo) values("고양 소노", 3,'');
insert into team(teamname, sportsnum, teamloggo) values("대구 한국가스공사", 3,'');
insert into team(teamname, sportsnum, teamloggo) values("수원 한국전력", 4,'');
insert into team(teamname, sportsnum, teamloggo) values("안산 ok저축은행", 4,'');

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
    constraint player_teamnum foreign key(teamnum) references team(sportsnum)
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
#전북 현대 모터스
 INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('권창훈', 'Kwon Chang-hoon', '대한민국', 28, '175cm', '70kg', '미드필더', 1, 'image1.jpg'),
('이주용', 'Lee Joo-young', '대한민국', 29, '180cm', '75kg', '수비수', 1, 'image2.jpg'),
('김신욱', 'Kim Shin-wook', '대한민국', 35, '194cm', '92kg', '포워드', 1, 'image3.jpg'),
('이용', 'Lee Yong', '대한민국', 36, '180cm', '74kg', '수비수', 1, 'image4.jpg'),
('정영선', 'Jeong Young-sun', '대한민국', 30, '178cm', '73kg', '미드필더', 1, 'image5.jpg'),
('김민재', 'Kim Min-jae', '대한민국', 27, '185cm', '80kg', '수비수', 1, 'image6.jpg'),
('송범근', 'Song Bum-keun', '대한민국', 25, '186cm', '83kg', '골키퍼', 1, 'image7.jpg'),
('이정협', 'Lee Jeong-hyeop', '대한민국', 30, '182cm', '78kg', '포워드', 1, 'image8.jpg'),
('황인범', 'Hwang In-beom', '대한민국', 27, '177cm', '72kg', '미드필더', 1, 'image9.jpg'),
('한교원', 'Han Kyo-won', '대한민국', 30, '181cm', '76kg', '미드필더', 1, 'image10.jpg'),
('김보경', 'Kim Bo-kyung', '대한민국', 32, '175cm', '71kg', '미드필더', 1, 'image11.jpg'),
('오반석', 'Oh Ban-seok', '대한민국', 29, '180cm', '78kg', '수비수', 1, 'image12.jpg'),
('조현우', 'Jo Hyun-woo', '대한민국', 32, '185cm', '82kg', '골키퍼', 1, 'image13.jpg'),
('이승기', 'Lee Seung-ki', '대한민국', 34, '183cm', '80kg', '미드필더', 1, 'image14.jpg'),
('안재현', 'An Jae-hyun', '대한민국', 26, '179cm', '74kg', '수비수', 1, 'image15.jpg'),
('홍정호', 'Hong Jung-ho', '대한민국', 34, '182cm', '80kg', '수비수', 1, 'image16.jpg'),
('김재환', 'Kim Jae-hwan', '대한민국', 28, '176cm', '72kg', '포워드', 1, 'image17.jpg'),
('이주영', 'Lee Joo-young', '대한민국', 26, '178cm', '75kg', '미드필더', 1, 'image18.jpg'),
('변선수', 'Byeon Seon-su', '대한민국', 24, '184cm', '78kg', '미드필더', 1, 'image19.jpg'),
('김기희', 'Kim Gi-hee', '대한민국', 33, '180cm', '78kg', '수비수', 1, 'image20.jpg');
#삼성 라이온즈
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('이승엽', 'Lee Seung-yeop', '대한민국', 48, '183cm', '90kg', '1루수', 2, 'image1.jpg'),
('박해민', 'Park Hae-min', '대한민국', 32, '180cm', '75kg', '외야수', 2, 'image2.jpg'),
('강민호', 'Kang Min-ho', '대한민국', 38, '178cm', '85kg', '포수', 2, 'image3.jpg'),
('김상수', 'Kim Sang-soo', '대한민국', 34, '181cm', '80kg', '내야수', 2, 'image4.jpg'),
('최채흥', 'Choi Chae-hung', '대한민국', 29, '185cm', '90kg', '투수', 2, 'image5.jpg'),
('이창섭', 'Lee Chang-seob', '대한민국', 27, '176cm', '72kg', '내야수', 2, 'image6.jpg'),
('이재현', 'Lee Jae-hyun', '대한민국', 25, '183cm', '78kg', '외야수', 2, 'image7.jpg'),
('김지찬', 'Kim Ji-chan', '대한민국', 24, '178cm', '74kg', '내야수', 2, 'image8.jpg'),
('이용찬', 'Lee Yong-chan', '대한민국', 30, '185cm', '88kg', '투수', 2, 'image9.jpg'),
('변우혁', 'Byeon Woo-hyuk', '대한민국', 28, '179cm', '77kg', '외야수', 2, 'image10.jpg'),
('김성욱', 'Kim Sung-wook', '대한민국', 29, '182cm', '80kg', '내야수', 2, 'image11.jpg'),
('정인욱', 'Jeong In-wook', '대한민국', 32, '180cm', '76kg', '투수', 2, 'image12.jpg'),
('강준석', 'Kang Jun-seok', '대한민국', 26, '181cm', '79kg', '내야수', 2, 'image13.jpg'),
('김현수', 'Kim Hyun-soo', '대한민국', 33, '184cm', '83kg', '외야수', 2, 'image14.jpg'),
('한승주', 'Han Seung-joo', '대한민국', 24, '175cm', '70kg', '포수', 2, 'image15.jpg'),
('박민우', 'Park Min-woo', '대한민국', 31, '177cm', '72kg', '내야수', 2, 'image16.jpg'),
('이상화', 'Lee Sang-hwa', '대한민국', 29, '180cm', '75kg', '외야수', 2, 'image17.jpg'),
('조상우', 'Jo Sang-woo', '대한민국', 27, '186cm', '82kg', '투수', 2, 'image18.jpg'),
('정경호', 'Jeong Gyeong-ho', '대한민국', 34, '182cm', '78kg', '내야수', 2, 'image19.jpg'),
('김유신', 'Kim Yu-shin', '대한민국', 30, '179cm', '73kg', '외야수', 2, 'image20.jpg');

#두산 베어스 
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('김재호', 'Kim Jae-ho', '대한민국', 36, '180cm', '75kg', '내야수', 2, 'image1.jpg'),
('박건우', 'Park Gun-woo', '대한민국', 31, '181cm', '80kg', '외야수', 2, 'image2.jpg'),
('정수빈', 'Jung Soo-bin', '대한민국', 30, '178cm', '76kg', '외야수', 2, 'image3.jpg'),
('오재원', 'Oh Jae-won', '대한민국', 37, '182cm', '82kg', '내야수', 2, 'image4.jpg'),
('양의지', 'Yang Eui-ji', '대한민국', 36, '183cm', '85kg', '포수', 2, 'image5.jpg'),
('이용찬', 'Lee Yong-chan', '대한민국', 30, '185cm', '88kg', '투수', 2, 'image6.jpg'),
('최주환', 'Choi Joo-hwan', '대한민국', 29, '180cm', '77kg', '내야수', 2, 'image7.jpg'),
('김민식', 'Kim Min-sik', '대한민국', 28, '176cm', '72kg', '포수', 2, 'image8.jpg'),
('이유찬', 'Lee Yoo-chan', '대한민국', 25, '179cm', '74kg', '투수', 2, 'image9.jpg'),
('최형우', 'Choi Hyung-woo', '대한민국', 34, '182cm', '80kg', '외야수', 2, 'image10.jpg'),
('권휘', 'Kwon Hwi', '대한민국', 26, '178cm', '73kg', '내야수', 2, 'image11.jpg'),
('정진호', 'Jeong Jin-ho', '대한민국', 32, '180cm', '76kg', '내야수', 2, 'image12.jpg'),
('허경민', 'Heo Kyung-min', '대한민국', 29, '177cm', '75kg', '내야수', 2, 'image13.jpg'),
('이정용', 'Lee Jeong-yong', '대한민국', 30, '181cm', '79kg', '투수', 2, 'image14.jpg'),
('이현호', 'Lee Hyun-ho', '대한민국', 24, '182cm', '77kg', '투수', 2, 'image15.jpg'),
('김대우', 'Kim Dae-woo', '대한민국', 27, '185cm', '83kg', '외야수', 2, 'image16.jpg'),
('장원준', 'Jang Won-jun', '대한민국', 35, '180cm', '82kg', '투수', 2, 'image17.jpg'),
('권혁', 'Kwon Hyuk', '대한민국', 33, '177cm', '73kg', '투수', 2, 'image18.jpg'),
('이재원', 'Lee Jae-won', '대한민국', 26, '179cm', '74kg', '내야수', 2, 'image19.jpg'),
('윤명준', 'Yoon Myung-jun', '대한민국', 29, '180cm', '78kg', '외야수', 2, 'image20.jpg');

#고양 소노
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('김선형', 'Kim Sun-hyung', '대한민국', 32, '180cm', '75kg', '가드', 3, 'image1.jpg'),
('이대성', 'Lee Dae-sung', '대한민국', 30, '185cm', '80kg', '포워드', 3, 'image2.jpg'),
('정효근', 'Jung Hyo-geun', '대한민국', 28, '190cm', '85kg', '센터', 3, 'image3.jpg'),
('양홍석', 'Yang Hong-seok', '대한민국', 26, '188cm', '78kg', '포워드', 3, 'image4.jpg'),
('이정현', 'Lee Jung-hyun', '대한민국', 31, '182cm', '77kg', '가드', 3, 'image5.jpg'),
('장재석', 'Jang Jae-seok', '대한민국', 34, '192cm', '88kg', '센터', 3, 'image6.jpg'),
('이재도', 'Lee Jae-do', '대한민국', 29, '178cm', '73kg', '가드', 3, 'image7.jpg'),
('한호빈', 'Han Ho-bin', '대한민국', 24, '186cm', '79kg', '포워드', 3, 'image8.jpg'),
('전준범', 'Jeon Jun-beom', '대한민국', 25, '181cm', '74kg', '가드', 3, 'image9.jpg'),
('최준용', 'Choi Jun-yong', '대한민국', 27, '189cm', '80kg', '포워드', 3, 'image10.jpg'),
('김민수', 'Kim Min-soo', '대한민국', 28, '183cm', '76kg', '가드', 3, 'image11.jpg'),
('강상재', 'Kang Sang-jae', '대한민국', 30, '188cm', '82kg', '센터', 3, 'image12.jpg'),
('이상민', 'Lee Sang-min', '대한민국', 33, '179cm', '72kg', '가드', 3, 'image13.jpg'),
('안영준', 'An Young-jun', '대한민국', 26, '186cm', '75kg', '포워드', 3, 'image14.jpg'),
('유재학', 'Yoo Jae-hak', '대한민국', 34, '191cm', '87kg', '센터', 3, 'image15.jpg'),
('오세근', 'Oh Se-geun', '대한민국', 36, '194cm', '90kg', '센터', 3, 'image16.jpg'),
('김동욱', 'Kim Dong-wook', '대한민국', 29, '182cm', '78kg', '가드', 3, 'image17.jpg'),
('신지혜', 'Shin Ji-hye', '대한민국', 25, '177cm', '70kg', '가드', 3, 'image18.jpg'),
('이형석', 'Lee Hyung-seok', '대한민국', 27, '188cm', '81kg', '포워드', 3, 'image19.jpg'),
('황희찬', 'Hwang Hee-chan', '대한민국', 28, '184cm', '79kg', '포워드', 3, 'image20.jpg');

#대구 한국가스공사
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('김준일', 'Kim Joon-il', '대한민국', 29, '185cm', '80kg', '포워드', 3, 'image1.jpg'),
('이종현', 'Lee Jong-hyun', '대한민국', 28, '191cm', '90kg', '센터', 3, 'image2.jpg'),
('이현중', 'Lee Hyun-jung', '대한민국', 25, '180cm', '75kg', '가드', 3, 'image3.jpg'),
('정진훈', 'Jeong Jin-hoon', '대한민국', 30, '182cm', '78kg', '가드', 3, 'image4.jpg'),
('권혁', 'Kwon Hyuk', '대한민국', 32, '184cm', '85kg', '포워드', 3, 'image5.jpg'),
('강우람', 'Kang Woo-ram', '대한민국', 27, '190cm', '88kg', '센터', 3, 'image6.jpg'),
('박세완', 'Park Se-wan', '대한민국', 26, '179cm', '72kg', '가드', 3, 'image7.jpg'),
('홍성욱', 'Hong Seong-wook', '대한민국', 31, '178cm', '74kg', '가드', 3, 'image8.jpg'),
('오상욱', 'Oh Sang-wook', '대한민국', 29, '184cm', '82kg', '포워드', 3, 'image9.jpg'),
('이동엽', 'Lee Dong-yeop', '대한민국', 30, '187cm', '80kg', '센터', 3, 'image10.jpg'),
('서명진', 'Seo Myung-jin', '대한민국', 28, '183cm', '76kg', '가드', 3, 'image11.jpg'),
('이관희', 'Lee Kwan-hee', '대한민국', 33, '181cm', '78kg', '내야수', 3, 'image12.jpg'),
('최준호', 'Choi Jun-ho', '대한민국', 27, '179cm', '73kg', '가드', 3, 'image13.jpg'),
('유경훈', 'Yoo Kyung-hoon', '대한민국', 30, '185cm', '85kg', '포워드', 3, 'image14.jpg'),
('안상준', 'An Sang-jun', '대한민국', 26, '188cm', '80kg', '센터', 3, 'image15.jpg'),
('이상민', 'Lee Sang-min', '대한민국', 34, '190cm', '87kg', '센터', 3, 'image16.jpg'),
('김원준', 'Kim Won-joon', '대한민국', 29, '180cm', '75kg', '가드', 3, 'image17.jpg'),
('문희원', 'Moon Hee-won', '대한민국', 24, '177cm', '70kg', '가드', 3, 'image18.jpg'),
('조성민', 'Jo Sung-min', '대한민국', 27, '182cm', '78kg', '포워드', 3, 'image19.jpg'),
('신민수', 'Shin Min-soo', '대한민국', 28, '184cm', '79kg', '포워드', 3, 'image20.jpg');

# 수원 한국전력
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('서명진', 'Seo Myung-jin', '대한민국', 30, '182cm', '80kg', '가드', 4, 'image1.jpg'),
('이상민', 'Lee Sang-min', '대한민국', 34, '180cm', '78kg', '가드', 4, 'image2.jpg'),
('김지완', 'Kim Ji-wan', '대한민국', 28, '185cm', '83kg', '포워드', 4, 'image3.jpg'),
('이동관', 'Lee Dong-kwan', '대한민국', 27, '178cm', '75kg', '가드', 4, 'image4.jpg'),
('장문혁', 'Jang Mun-hyuk', '대한민국', 29, '190cm', '85kg', '센터', 4, 'image5.jpg'),
('오상욱', 'Oh Sang-wook', '대한민국', 32, '184cm', '80kg', '포워드', 4, 'image6.jpg'),
('양희종', 'Yang Hee-jong', '대한민국', 35, '187cm', '88kg', '센터', 4, 'image7.jpg'),
('이주연', 'Lee Joo-yeon', '대한민국', 25, '180cm', '75kg', '가드', 4, 'image8.jpg'),
('최진수', 'Choi Jin-soo', '대한민국', 31, '182cm', '79kg', '포워드', 4, 'image9.jpg'),
('정승원', 'Jeong Seung-won', '대한민국', 26, '179cm', '74kg', '가드', 4, 'image10.jpg'),
('이찬영', 'Lee Chan-young', '대한민국', 28, '183cm', '76kg', '가드', 4, 'image11.jpg'),
('김선기', 'Kim Sun-gi', '대한민국', 29, '186cm', '80kg', '센터', 4, 'image12.jpg'),
('박재민', 'Park Jae-min', '대한민국', 30, '178cm', '72kg', '가드', 4, 'image13.jpg'),
('이종현', 'Lee Jong-hyun', '대한민국', 33, '191cm', '90kg', '센터', 4, 'image14.jpg'),
('강성훈', 'Kang Sung-hoon', '대한민국', 24, '175cm', '70kg', '가드', 4, 'image15.jpg'),
('유재석', 'Yoo Jae-seok', '대한민국', 36, '182cm', '79kg', '포워드', 4, 'image16.jpg'),
('김태형', 'Kim Tae-hyung', '대한민국', 29, '180cm', '75kg', '가드', 4, 'image17.jpg'),
('박찬호', 'Park Chan-ho', '대한민국', 25, '184cm', '82kg', '포워드', 4, 'image18.jpg'),
('이유빈', 'Lee Yoo-bin', '대한민국', 27, '179cm', '73kg', '가드', 4, 'image19.jpg'),
('정진원', 'Jeong Jin-won', '대한민국', 28, '181cm', '78kg', '포워드', 4, 'image20.jpg');
#안산 ok저축은행
INSERT INTO player (playername, playerEname, playerCountry, playerAge, playerH, playerW, playerP, teamnum, playerimage) VALUES
('이재도', 'Lee Jae-do', '대한민국', 29, '180cm', '75kg', '가드', 4, 'image1.jpg'),
('김형빈', 'Kim Hyung-bin', '대한민국', 26, '188cm', '80kg', '포워드', 4, 'image2.jpg'),
('안영준', 'An Young-jun', '대한민국', 27, '186cm', '79kg', '센터', 4, 'image3.jpg'),
('최진호', 'Choi Jin-ho', '대한민국', 31, '182cm', '78kg', '가드', 4, 'image4.jpg'),
('서동철', 'Seo Dong-cheol', '대한민국', 28, '190cm', '85kg', '센터', 4, 'image5.jpg'),
('윤호상', 'Yoon Ho-sang', '대한민국', 30, '183cm', '76kg', '가드', 4, 'image6.jpg'),
('김지수', 'Kim Ji-soo', '대한민국', 25, '179cm', '73kg', '가드', 4, 'image7.jpg'),
('정윤성', 'Jeong Yoon-sung', '대한민국', 34, '180cm', '75kg', '포워드', 4, 'image8.jpg'),
('이민호', 'Lee Min-ho', '대한민국', 29, '187cm', '81kg', '센터', 4, 'image9.jpg'),
('한상민', 'Han Sang-min', '대한민국', 26, '182cm', '79kg', '가드', 4, 'image10.jpg'),
('오현준', 'Oh Hyun-jun', '대한민국', 32, '179cm', '74kg', '가드', 4, 'image11.jpg'),
('문준호', 'Moon Jun-ho', '대한민국', 28, '185cm', '78kg', '포워드', 4, 'image12.jpg'),
('강민재', 'Kang Min-jae', '대한민국', 27, '184cm', '80kg', '센터', 4, 'image13.jpg'),
('정석현', 'Jeong Seok-hyun', '대한민국', 29, '180cm', '75kg', '가드', 4, 'image14.jpg'),
('이병호', 'Lee Byeong-ho', '대한민국', 31, '182cm', '82kg', '포워드', 4, 'image15.jpg'),
('최규민', 'Choi Gyu-min', '대한민국', 30, '181cm', '78kg', '가드', 4, 'image16.jpg'),
('유영주', 'Yoo Young-joo', '대한민국', 25, '176cm', '70kg', '가드', 4, 'image17.jpg'),
('윤태영', 'Yoon Tae-young', '대한민국', 34, '179cm', '73kg', '포워드', 4, 'image18.jpg'),
('조현호', 'Jo Hyun-ho', '대한민국', 28, '190cm', '88kg', '센터', 4, 'image19.jpg'),
('허범수', 'Heo Beom-soo', '대한민국', 29, '182cm', '80kg', '포워드', 4, 'image20.jpg');

create table goods(
   goodsnum int primary key auto_increment,
    goodstype varchar(300),
    goodsname varchar(300),
    goodsprice bigint,
    goodsimg varchar(300)
);
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
    orderdatetime datetime default now(),
    deliveryPlace varchar(300),
    deliveryMemo varchar(300),
    totalPrice int,
    sudannum int,
    userid varchar(300),
    ordername varchar(300),
    buynum varchar(300),
    constraint order_userid foreign key(userid) references user(userid) ON DELETE CASCADE,
    state varchar(300) default'결제완료'
);
select * from orders;

create table wishList(
   wishnum int primary key auto_increment,
    userid varchar(300),
    goodsnum int,
   constraint wish_userid foreign key(userid) references user(userid) ON DELETE CASCADE,
   constraint goodsnumWish foreign key(goodsnum) references goods(goodsnum) ON DELETE CASCADE
);
select * from wishList;

create table buyList(
   buynum int primary key auto_increment,
    userid varchar(300),
    goodsnum int,
    size varchar(300),
    quantity int,
   constraint buy_userid foreign key(userid) references user(userid) ON DELETE CASCADE,
   constraint goodsnumBuy foreign key(goodsnum) references goods(goodsnum) ON DELETE CASCADE
);
select * from buyList;

create table orderList(
	orderListid int primary key auto_increment,
    ordernum int,
	buynum int ,
    userid varchar(300),
    goodsnum int,
    size varchar(300),
    quantity int,
   constraint orderlist_userid foreign key(userid) references user(userid) ON DELETE CASCADE,
   constraint goodsnumOrderlist foreign key(goodsnum) references goods(goodsnum) ON DELETE CASCADE
);
select * from orderList;

CREATE TABLE g_will_board (
    gWnum INT PRIMARY KEY AUTO_INCREMENT,
    gWdate DATETIME,
    gEdate DATETIME,
    team1name VARCHAR(300),
    team2name VARCHAR(300),
	team1score INT,
    team2score INT,
    gwtime DATETIME,
    CONSTRAINT gW_team1num FOREIGN KEY (team1name) REFERENCES team(teamname) ON DELETE CASCADE,
    CONSTRAINT gW_team2num FOREIGN KEY (team2name) REFERENCES team(teamname) ON DELETE CASCADE
);
select * from g_will_board;

CREATE TABLE fboard (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),  -- 사용자 ID
    content TEXT,          -- 채팅 내용
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- 작성 시간
    chat_type INT,         -- 1 = 왼쪽 채팅, 2 = 오른쪽 채팅
    FOREIGN KEY (user_id) REFERENCES user(userid)
);
select * from fboard;
CREATE TABLE votes (
    vote_id INT PRIMARY KEY auto_increment,    -- 경기 ID (어떤 경기인지 구별)
    team1_vote INT DEFAULT 0,    -- team1의 누적 투표 수
    team2_vote INT DEFAULT 0     -- team2의 누적 투표 수
);

INSERT INTO team_votes (team_id, votes) VALUES (1, 0);
INSERT INTO team_votes (team_id, votes) VALUES (2, 0);
-- 필요에 따라 추가

create table events(
	eventnum int primary key auto_increment,
    gwnum int,
	CONSTRAINT gwnum FOREIGN KEY (gwnum) REFERENCES g_will_board(gwnum) ON DELETE CASCADE,
    eventtype varchar(300),
    eventitem varchar(300)
);

create table eboard(
	eboardnum int primary key auto_increment,
    edate datetime default now(),
    eboardcount int,
    eboardtitle varchar(300),
    eboardcontent varchar(3000),
    eventcon varchar(300),
    eventnum int,
	CONSTRAINT eventnum FOREIGN KEY (eventnum) REFERENCES events(eventnum),
    winner varchar(300)
);
select * from eboard;
