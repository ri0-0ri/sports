# KH LEAGUE

이 플랫폼은 관리자와 사용자 두 가지 주요 역할을 지원합니다.
관리자는 경기 관리, 유저 관리, 이벤트 관리 등을 통해 플랫폼을 운영합니다.
사용자는 생성된 경기에 투표하거나 응원를 통해 이벤트에 참여하고, 다양한 커뮤니케이션 활동을 할 수 있으며 각 구단의 굿즈를 구매할 수 있습니다.

**기간** : 2024. 10. 22 ~ 2024. 11. 26

**개발인원** : 2명

    윤경수 <https://github.com/YoonKyungSoo> 

    윤혜정 <https://github.com/ri0-0ri>

---
# 🛠️ 개발목표
1) 스포츠 팬들에게 경기 일정, 팀 정보, 굿즈 구매 기능을 제공하여 사용자 편의성 증가
2) 팬들 간의 소통을 강화하고, 이벤트 참여를 유도하여 커뮤니티 활성화 도모
3) 통합 플랫폼을 통해 스포츠 문화를 더욱 풍부하게 만들어, 팬들이 경기를 더 재미있게 즐길 수 있는 환경 조성
---
# 🛠️ 사용기술 / 개발환경
- **운영체제** : Windows, MacOS
- **사용언어** : Java, Javascript, HTML5, CSS3, Ajax, JSP&Servlet
- **FrameWork / Library** : Spring, jQuery
- **DB** : MySQL
- **WAS** : Tomcat
- **Tool** : Visual Studio Code, STS (Spring Tool Suite), GitHub
- **Collaboration** : Notion, Figma, ERD Cloud
- **API** : Tosspayments test API, 다음 주소 API
---
# 🛠️ 기능

## 💡 DB 설계
> 이미지

## 💡 주요 기능 설명
**로그인 / 회원가입페이지**
> - 로그인 및 회원가입‬
‭

**관리자 페이지‬**
> - 유저 관리 기능 : 회원 조회/삭제‬
> - 경기 일정 관리 : 팀과 날짜 선택 후 경기 생성‬  
> - 이벤트 기능 : 이벤트 생성 및 당첨자 추첨 후 공지


**구단정보 및 선수 페이지**
> - 축구 / 야구 / 농구 / 배구 종목별 구단 정보와 각 팀의 선수 확인‬
‭ (다른 기능을 위한 데이터 페이지로서 축구-FC서울만 구현)‬


**응원 게시판**
> - 시작 예정 경기 최신순 3개 조회‬
> - 가장 최신 경기에 팀별 투표, 응원 댓글 작성‬
‭

**경기 정보 게시판**
> - 월별 필터링, 예정 및 종료된 경기 조회‬
‭

**경품 SHOP**
> - 카테고리별 경품 조회(아우터/상의/하의/악세서리/기타)‬
> - 인기순, 최신순, 오래된순 필터링‬
> - 상품 상세 : 옵션 선택 후 바로구매/장바구니 추가/위시리스트 추가‬
‭

**당첨자 SHOP**
> - 당첨 경품 조회‬‭
> - 경품 선택 수령 : 적립금 수령 시 안내창(10일동안 유효), 상품 수령 시 배송 페이지로 이동‬
‭

**이벤트 게시판 / 작성 페이지**
> - 이벤트 글 조회
> - 글‬‭ 작성‬‭ 및‬‭ 수정‬‭ :‬‭ 관리자만‬‭ 작성‬‭ 가능,‬‭ 생성된‬‭ 이벤트를‬‭ 알리는‬‭ [이벤트],‬‭ 당첨자를‬‭ 알리는‬ [공지사항]으로 나누어 작성‬


**마이 페이지**
> - 사용자 정보 : 프로필 확인 및 수정‬
> - 구매내역 : 구매정보 및 배송조회, 환불요청 기능
> - 위시리스트 : 바로구매/장바구니 추가/위시리스트에서 삭제
> - 장바구니 : 옵션 변경/장바구니에서 삭제/구매할 상품 선택 후 결제 페이지 이동
> - 적립금/포인트 : 카테고리별 조회(적립금/포인트/사용/취소/소멸)
‭

**결제 / 환불 / 배송 페이지**
> - 배송지 : 주소 선택(주소찾기 API), 상세주소, 배송메모 작성‬
> - 결제수단 : 포인트 결제, 간편결제(토스페이먼츠 결제 API), 적립금 결제‬
> - 환불사유 선택(단순 변심/상품 불만/타 상품/배송 지연/기타)‬
> - 사용자 포인트, 적립금 변경 : 결제 시 10% 적립금 추가 및 보유 포인트/적립금 변경‬
