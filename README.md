# today-s_house_server_benjamin

## day1 dev log (22.09.17)

### 1차까지 개발예정인  기능 도메인(server)

#### 이메일 회원가입

+ 이메일 인증 기능구현 x

+ req : 이메일, 비밀번호, 별명

#### 이메일 로그인

#### 홈

-----

## day2 dev log (22.09.18)

### 1. 기획서의 변동사항
+ 색상에 따른 가격
   + 실제 어플 : 상품의 색상(색상, 크기...)마다 가격이 다름 
   + 구현 : 색상에 따라 **가격의 차이를 두지 않음**(가격 통일) -> 추가상품만 가격 다르게 처리 

+ 상품 리뷰의 별점처리
   + 실제 어플 : 1~5점을 0.5점 단위로 줄 수 있음
   + 구현 : 1~5점을 **1점 단위**로 줄 수 있음(정수처리)

+ 집들이부분 구현 x
   + 가장 필수적인 기능인, **사진**기능 구현에 집중 : 사진 업로드 기능 구현
   + 이미지 기준 : 상품의 리뷰 / 사진 = 상품의 스타일링/ 상품이미지 -> 3가지	 

### 2. ERD 진행상황

+ ERD 설계 : 100%
   + USER
   + ITEM_CATEGORY
   + PHOTO_FILTER
   + STORE
   + ITEM
   + ADDITIONAL_ITEM
   + TAG_PHOTO
   + FOLLOW
   + CONTENT_OF_ITEM
   + PHOTO
   + CONTENT_OF_PHOTO
   + SCRAP_PHOTO
   + LIKED_PHOTO
   + SCRAP_ITEM
   + VIEW_PHOTO
   + REVIEW
   + COMMENT_PHOTO
   + ITEM_QUESTION
   + PURCHASE
   + SELLER_INFORMATION
   + QUESTION_TYPE

+ ERD 구현 : 100%

### 3. API 리스트업 진행상황 : 0%

### 4. 더미데이터 진행 상황 : 0%

-----

## day3 dev log (22.09.19)

### 1. 기획서의 변동사항
+ 사용자의 프로필 사진 등록 : 클라이언트에서 외부 서비스에 이미지 업로드한 후, 그 이미지 URL을 API에 담아 서버측으로 보내면 DB에 저장
+ 등급, 포인트 : 사용자 마이페이지에 표시할 수 있도록하기 (포인트:누적 총점만)
+ 알림 기능 : 사용자가 주문완료시 띄우기 (클라이언트측에게 상품관련정보 모두 넘겨주기)
+ '마이페이지 - 프로필'의 '인테리어시공 상담내역'기능 구현x
+ '마이페이지 - 나의쇼핑'의 '진행중인 주문'에서 입금대기,결제완료 2가지만 기능구현
+ AQueryTool용량으로 인하여 '마이페이지 - 프로필'의 '내 쿠폰' 기능 구현 불가능 (1인당 1쿠폰만 가능) -> 1인당 다량의 쿠폰등록이 가능해야하므로, 프론트에서 뷰만 구현 
+ '둘러보기 - 질문과 답변' 구현x
+ '마이페이지 - 프로필'의 '리뷰쓰기' : 결제완료된 상품들만 리뷰쓰기에 띄우기

### 2. ERD 진행상황
+ ERD 설계 : 100% (30/30)
   + USER
   + ITEM_CATEGORY
   + PHOTO_FILTER
   + STORE
   + ITEM
   + ADDITIONAL
   + TAG_PHOTO
   + FOLLOW
   + CONTENT_OF_ITEM
   + PHOTO
   + CONTENT_OF_PHOTO
   + SCRAP_PHOTO
   + LIKED_PHOTO
   + SCRAP_ITEM
   + VIEW_PHOTO
   + REVIEW
   + COMMENT_PHOTO
   + ITEM_QUESTION
   + QUESTION
   + PURCHASE
   + SELLER_INFORMATION
   + QUESTION_TYPE
   + DISCOUNT_COUPON
   + COLOR
   + CONTENT_ORGANIZATION_OF_ITEM
   + CONTENT_ORGANIZATION_OF_PHOTO
   + AVAILABLE_COUPON
   + USING_FILTER
   + ITEM_COLOR
   + ITEM_ADDITIONAL

+ ERD 구현 : 100% (30/30)

_day3는 day2에 대하여 ERD가 전면 추가 및 수정되었습니다_

### 3. API 리스트업 진행상황 : 20% (4/21)
+ POST /app/users/sign-up : 회원가입
+ POST /app/users/login : 로그인
+ GET /app/users/home : 홈
+ GET /app/users/mypage/:user-id : 마이페이지

### 4. 현재 개발중인 API 
+ 회원가입 : in Progress

### 5. 더미데이터 진행 상황 : 5%

### 6. 클라이언트 개발자와의 회의에 따른 회의록
+ 1차 위클리스크럼 일정 : 22.09.20 14:00
+ 1차 피드백 직후 기획서 전체적 수정 진행 (기능 화면 캡쳐본 추가 및 요구사항 작성)

### 7. 개발팀장님의 피드백(1차, 2차)
   #### 1차 피드백 (22.09.20 22:30)
   #### 2차 피드백 (22.09.28 22:30)

### 8. 개발 도중에 발생하는 이슈

-----

## day4 dev log (22.09.20)

### 1. 기획서의 변동사항
+ 회원가입 : 약관동의 클라이언트단에서만 구현, 추천코드 기능 구현 x
+ 우선순위를 마이페이지보다 스토어를 우선으로 
+ 메인홈은 잠시중단
+ PHOTO 기능 관련 진행x : 클라이언트 개발자가 개발리더에게 오늘의집은 스토어가 주기능이라는 피드백을 들어 스토어기능에 집중하기로 결정
+ 홈 
   + 오늘의 딜 : 후순위(보류)
   + 인기 동영상, 인기 사진 : 구현x (PHOTO관련)
   + 기획전 : 이미지 로컬에서 저장하고 처리
   + 베스트 : 베스트를 구현할 데이터셋이 적으므로, 임의로 카테고리별로 3개씩 클라이언트로 보내기
+ 스토어 홈
   + 오늘의 딜 : 후순위(보류)
   + 누구님을 위한 상품 : 구현x (알고리즘을 알 수 없음)
   + 최근 본 상품 : 클라이언트에서 저장하고 처리
   + 내가 본 상품의 연관 상품 : 상품id받으면, 해당 카테고리와 동일한 카테고리의 상품정보 넘기기(두번째 카테고리기준) 
   + 인기상품 : 프론트의 페이징기능처리위해 데이터 20개이상 필요
   + 상품 카테고리는 나와있는 것 그대로 다 처리
   + 상품의 필터는 서버에서는 대표적으로 뜨는것에서 상단 2개만 처리하며, 클라이언트에서는 필터 전체를 다 보여주기(서버에서 보내는 것 외에는 껍질만)
+ 우선순위
1. 회원가입
2. 로그인
3. 스토어 홈
4. 상품 눌렀을 때 들어가지는 상품상세정보
5. 구매하기
6. 바로결제 눌렀을 때, 주문결제로 넘어가기
7. 결제페이지 
8. 마이페이지
9. 햄버거메뉴와 해당메뉴의 카테고리통해 들어가는 뷰는 마이페이지 끝내고 진행

### 2. ERD 진행상황
+ ERD 설계 : 100% (30/30)
+ ERD 구현 : 100% (30/30)

### 3. API 리스트업 진행상황 : 20% (4/21)
_해당 내용은 day3과 동일_

### 4. 현재 개발중인 API 
+ 로그인 

### 5. 개발 완료 API
+ 회원가입 

### 6. 더미데이터 진행 상황 : 5%

### 7. prod서버 구축 진행 상황 
**In Progress**
+ 서버에 spring-boot setting

**Not started**
+ 서버url(도메인)으로 API가 잘 동작하는지 test

**Done**
+ [aws] ec2 ubuntu20.04 / 인스턴스 today-s_house_prod_server 생성완료
+ 터미널 접속 위해 SSH(22), 서비스 위해 HTTP(80) HTTPS(443) : 모든 IP에서 접근가능하도록 보안 설정완료
+ ubuntu위에 nginx/1.18.0 서버 설치완료 
+ 클라이언트 개발자에게 서버정보를 넘겨야하므로, Elastic IP 설정완료 
+ 서버 접속은 .pem파일로 가능하도록 설정
+ RDS 서버 : MySQL 8.0.28 / RDS 인스턴스 크기 : db.t2.micro(t3가 많은 컴퓨팅 용량을 제공하지만, t2는 개발 및 테스트 서버에 적합하다고하여 사용)
+ RDS 접속은 비밀번호로 접속 가능하도록 설정
+ EC2와 RDS연결 (EC2서버에서 RDS로 접근 가능하게 하기 위해)
+ 로컬에서 RDS 작업위해 보안그룹 설정
+ RDS setting : time_zone (Asia/Seoul),  Character_set (utf8mb4), collation (utf8mb4_unicode_ci)
+ 가비아에서 도메인 구입 후, 도메인명으로도 접속할 수 있게하기 위해 해당 도메인과 ec2서버 연결 
+ 서버 HTTPS 설정 (SSL)

### 8. 클라이언트 개발자와의 회의에 따른 회의록
**회원가입API 기능추가여부 논의사항**
1. 약관 동의 (필수, 선택) 구현 -> 필수 체크못하면 가입 안되도록
2. 이메일, 비밀번호, 닉네임 자릿수 정하기 
3. 추천코드(추천인 닉네임) 구현여부

**논의 결과**
1. 필수 선택약관 체크안되면 못넘어가도록 클라이언트에서 구현, 필수가 아닌 선택약관 관련정보는 서버에서 진행하는 로직이지만 우리가 구현 계획한 기능과 관련이 없으므로 진행x 
2. 이메일 : ‘@’앞 15글자 , 비밀번호 : 8~15글자 (’영문+숫자’만 가능하도록), 닉네임 : 영문 한글 숫자만 사용가능 & 한글기준 8글자
3. 추천코드 기능 x
4. +이메일’@’까지 입력했을 때, 아래에 드롭다운 메뉴로 관련 이메일형식 리스트 뜨도록

### 9. 개발팀장님의 피드백(1차, 2차)
#### 1차 피드백 (22.09.21 22:30) -> 20일에서 21일로 변경됨
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### Error
1. dev(local)서버에서 개발한 API에 대하여 postman테스트를 하기위해 파일을 빌드(./gradlew clean build)하는 과정에서 gradlew가 없다는 에러 발생
`./gradlew no such file or directory`

2. 빌드시 LOMBOK 관련 에러
```
java.lang.illegalaccesserror class lombok.javac.apt.lombokprocessor
Cause: class lombok.javac.apt.LombokProcessor 
```

3. 빌드시 jdk관련 에러
'unsupported class file major version 62'

#### 원인 
1. ./gradlew 파일에 권한이 없어서 생기는 문제

2. spring-boot 파일에서 사용하는 lombok버전(16)과 로컬서버에 설치되어있는 lombok(24)의 버전이 맞지않아 발생하는 문제

3. Gradle시스템이 현재 적용 된 jdk 버전과 호환되지 않을 때 발생하는 문제 : java home경로를 확인하여 해당 BL의 구조를 파악해보았는데, Contents 폴더 내에 Home이 없어서 설치가 제대로 되지않았다고 판단

#### 해결
1. chmod로 해당 파일에 권한 부여

2. build.gradle파일의 내용에 lombok버전을 로컬과 동일하게(24) 수정

3. zulu11을 재설치하고, .zshrc에 경로를 맞추어줌