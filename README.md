# KH LEAGUE

이 플랫폼은 스포츠 통합 플랫폼으로써 관리자와 사용자 두 가지 주요 역할을 지원합니다.
관리자는 경기 관리, 유저 관리, 이벤트 관리 등을 통해 플랫폼을 운영합니다.
사용자는 생성된 경기에 투표하거나 응원를 통해 이벤트에 참여하고, 다양한 커뮤니케이션 활동을 할 수 있으며 각 구단의 굿즈를 구매할 수 있습니다.

**기간** : 2024. 10. 22 ~ 2024. 11. 26

**개발인원** : 2명

🙋🏻‍♂️ 윤경수 <https://github.com/YoonKyungSoo> 

🙋🏻‍♀️ 윤혜정 <https://github.com/ri0-0ri>

**PPT** : [PPT Link](https://www.canva.com/design/DAGXFJdj3OE/E2RdD2Gw7qWNvD6plQ80wQ/view?utm_content=DAGXFJdj3OE&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=h44cc8678c8)

---
# 🛠️ 개발목표
1) 스포츠 팬들에게 경기 일정, 팀 정보, 굿즈 구매 기능을 제공하여 사용자 편의성 증가
2) 팬들 간의 소통을 강화하고, 이벤트 참여를 유도하여 커뮤니티 활성화 도모
3) 통합 플랫폼을 통해 스포츠 문화를 더욱 풍부하게 만들어, 팬들이 경기를 더 재미있게 즐길 수 있는 환경 조성
---
# 🛠️ 사용기술 / 개발환경
- **운영체제**

  <img src="https://img.shields.io/badge/Windows-0078D4?style=for-the-badge&logo=Windows&logoColor=white">  <img src="https://img.shields.io/badge/macOs-000000?style=for-the-badge&logo=macOs&logoColor=white">
- **사용언어**

  <img src="https://img.shields.io/badge/JAVA-DC8236?style=for-the-badge&logo=JAVA&logoColor=white">  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">  <img src="https://img.shields.io/badge/Ajax-000000?style=for-the-badge&logo=Ajax&logoColor=white">  <img src="https://img.shields.io/badge/JSP & Servlet-08C2FF?style=for-the-badge&logo=JSP/Servlet&logoColor=white">
  
- **FrameWork / Library**

  <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">  <img src="https://img.shields.io/badge/jquery-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> 
  
- **DB**

  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  
- **WAS**

  <img src="https://img.shields.io/badge/Apache Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black"> 
  
- **Tool**

  <img src="https://img.shields.io/badge/VS Code-3478C6?style=for-the-badge&logo=vscode&logoColor=white">  <img src="https://img.shields.io/badge/STS-6DB33F?style=for-the-badge&logo=spring&logoColor=white">  <img src="https://img.shields.io/badge/GitHub-181717?style=for-the-badge&logo=github&logoColor=white">
  
- **Collaboration**

  <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=notion&logoColor=white">  <img src="https://img.shields.io/badge/Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white">  <img src="https://img.shields.io/badge/ERD CLOUD-9188F4?style=for-the-badge&logo=erd&logoColor=white">
  
- **API** : Tosspayments test API, 다음 주소 API
---
# 🛠️ 기능
## Folder Structure

```bash
📦SeSAC-green-way-Seoul
 ┣ 📂src
 ┃ ┣ 📂api  // 오픈 API 및 Mock-Data 저장 폴더
 ┃ ┣ 📂assets  // 이미지, 아이콘, 로고로 구분하여 저장
 ┃ ┃ ┣ 📂icon
 ┃ ┃ ┣ 📂img
 ┃ ┃ ┣ 📂logo
      // 페이지별 폴더 관리 
 ┃ ┣ 📂bookmark // 기능별로 폴더를 구분하여 html, css, js 저장
 ┃ ┣ 📂join
 ┃ ┣ 📂login
 ┃ ┣ 📂main
 ┃ ┣ 📂mypage
 ┃ ┣ 📂search
 ┃ ┣ 📂styles // 공통 style css를 저장   
 ┃ ┗ 📂utils  // 공통 부분 js 및 css 저장
 ┃ ┃ ┣ 📂header
 ┃ ┃ ┣ 📂tabBar
 ┃ ┃ ┗ 📜utils.js
 ┣ 📜index.html  // 첫 화면에 로딩 될 html 
 ┣ 📜netlify.toml  // netlify 배포를 위한 환경변수 파일
 ┣ 📜README.md
 ┗ 📜_redirects //  netlify 배포를 위한 환경변수 파일
---
## 💡 DB 설계
> ![erd1](https://github.com/user-attachments/assets/3c7e9907-aac7-4bfe-aba4-70bfcec7d017)
![erd2](https://github.com/user-attachments/assets/ff81a176-caf3-47e0-bef7-8887fc073297)
![erd3](https://github.com/user-attachments/assets/99deabe9-ceab-4ca8-a228-c297f756ed1a)
![erd4](https://github.com/user-attachments/assets/fb506a97-7592-4d64-8fdc-4753ed2d18e6)

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
