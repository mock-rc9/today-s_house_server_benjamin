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

-----

## day5 dev log (22.09.21)

### 1. 기획서의 변동사항
기능변경(21개 -> 17개)
+ 오늘의집 이미지 스플래시
+ 이메일 회원가입
+ 이메일 로그인
+ 스토어 메인 화면
+ 상품정보 불러오기
+ 상품 DB연동
+ 상품 구매
+ 주문 / 결제
+ 장바구니 추가
+ 담겨있는 장바구니
+ 리뷰쓰기
+ 스크랩
+ 마이페이지
+ 상단 햄버거 메뉴
+ 팔로우
+ 소셜로그인
+ 홈

### 2. ERD 진행상황
+ ERD 설계 : ??% (30/??)
+ ERD 구현 : ??% (30/??)
_day4 dev log의 1에따라 수정예정_

### 3. API 리스트업 진행상황 : 25% (4/17)
_해당 내용은 day3과 동일_

### 4. 현재 개발중인 API 
+ 로그인 

### 5. 개발 완료 API
+ 회원가입 

### 6. 더미데이터 진행 상황 : 5%

### 7. prod서버 구축 진행 상황 
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
+ 서버에 spring-boot setting
+ 서버url(도메인)으로 API가 잘 동작하는지 test

### 8. 클라이언트 개발자와의 회의에 따른 회의록

### 9. 개발팀장님의 피드백(1차, 2차)
#### 1차 피드백 (22.09.21 22:30) 
+ API 개수 25개 이상 진행권장 (40개이상 만점)
+ validation 꼼꼼하게 진행
-> API개발에 힘을 쏟기
+ 더미데이터 10~20개
+ 클라이언트 개발자와 소통 신경쓰기

#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### Error 및 issue
1. (issue) res코드 파일에는 DB와의 컬럼명과 동일하게 대문자로 변수명 설정(USER_IDX) But Postman 결과값에서는 앞문자가 소문자로 출력(user_IDX)

2. (issue) spring boot 빌드시 시간만 증가하며, :compileJava에서 멈춰있는 현상 발생

3. (issue) 비밀번호가 DB에 암호화된 후 저장되는데, 로그인을 테스트하려고하니 원래 비밀번호 생각안나서 요청값을 못 넣는 상황 발생

#### 원인 
1. spring 내부 로직에 의해 소문자로 출력됨

2. 용량문제로 보임

#### 해결
1. 보기좋게 출력하기위해 코드파일 변수명을 소문자로설정 (res파일 변수명을 DB 컬럼명과 맞춰줘야하는 줄 알았는데, 그럴필요 없음 / res는 DTO개념으로 생각)

2. 일단, 서버가 너무 느리게 동작하는것같아 재부팅 후 swap메모리 설정하여 해결

3. 보안상 관리자도 비밀번호를 몰라야하니, 그게 정상적인 것 / 로그인 테스트시, 다시 회원가입을 진행하고 바로 해당 이메일과 비밀번호를 이용 or 암호화된 비밀번호를 그대로 회원가입 로직에 적용시켜보면, 본래 비밀번호를 알 수 있음(개발팀장님 피드백 -> 확실한 이해를 위해, 진행해 볼 예정) 

-----

## day6 dev log (22.09.22)

### 1. 기획서의 변동사항

### 2. ERD 진행상황
+ ERD 설계 : ??% (30/??)
+ ERD 구현 : ??% (30/??)
_day4 dev log의 1에따라 수정예정_

### 3. API 리스트업 진행상황 : 25% (4/17)
_해당 내용은 day3과 동일_

### 4. 현재 개발중인 API 
+ 로그인 : validation 처리중...

### 5. 개발 완료 API
+ 회원가입 (validation 완료)

### 6. 더미데이터 진행 상황 : 5%

### 7. prod서버 구축 진행 상황 : 100%

### 8. 클라이언트 개발자와의 회의에 따른 회의록

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### Error 및 issue
1. (issue) prod서버에 ssl 적용을 진행하였는데, https 연결이 되지않는 상황 발생 

2. (issue) 회원가입 API를 테스트하다가, 이메일값에 null이 아닌 빈값("")을 넣으면 그대로 db에 들어가는 상황 발생

3. (issue) 요청이 잘못되었을때 예외처리의 반환값은 정상적인 반환값과 달리, result없이 3개의 값(isSuccess,message,code)만 보내주는데, 이를 따로 처리하기에 번거롭다는 클라이언트 개발자의 의견이 있었음

#### 원인 
1. nginx 설정파일에 server블록을 여러개 만들어두었는데, 가장 첫번째에 있는 server블록의 내용에 80포트와 443포트 설정 내용이 있음에도 불구하고, 그 아래 prod 서버 블록에 443포트설정내용이 또 있었음 
-> 기본 블록의 내용이 베이스로 전체에 적용되는듯함

2. 빈값과 null은 다르므로, 빈값에 대한 validation을 하지않은 현재 상황에서는 당연한 것

#### 해결
1. prod서버 블록의 겹치는 포트내용을 지움 -> 해결!  

2. 클라이언트 개발자에게 물어봤을 때 사용자가 값을 쓰지않으면 null로 보낸다고 하였지만, 요청이 꼭 클라이언트에게서만 오지는 않으므로 보안을 위해 빈값("")도 validation 처리

3. 합의하에 서버쪽에서 예외처리 반환값도 정상처리 반환값과 동일하게 4개(isSuccess,message,code,result)로 구성하여 반환하기로 결정

-----

## day7 dev log (22.09.23)

### 1. 기획서의 변동사항

### 2. ERD 진행상황
+ ERD 설계 : ??% (30/??)
+ ERD 구현 : ??% (30/??)
_day4 dev log의 1에따라 수정진행중_

### 3. API 리스트업 진행상황 : 100% (15/15)
+ POST /app/users/sign-up : 회원가입
+ POST /app/users/login : 로그인
+ GET /app/stores/home : 스토어 홈
+ GET /app/stores/item : 상품정보 불러오기
+ GET /app/stores/item/purchase : 상품 구매하기
+ GET /app/stores/item/payment : 주문/결제
+ POST /app/stores/item/add/basket : 장바구니 추가
+ GET /app/stores/item/basket/:user-idx : 담겨있는 장바구니
+ POST /app/stores/item/review : 리뷰쓰기
+ POST /app/stores/item/scrap : 상품 스크랩
+ GET /app/users/mypage/:user-idx : 마이페이지
+ GET /app/stores/category : 상단 햄버거메뉴
+ POST /app/users/follow : 팔로우
+ POST /app/users/social-login : 소셜로그인
+ GET /app/home : 메인 홈

### 4. 현재 개발중인 API 
+ 스토어 홈

### 5. 개발 완료 API
+ 회원가입 (validation 완료)
+ 로그인 (validation 완료)

### 6. 더미데이터 진행 상황 : 10%

### 7. prod서버 구축 진행 상황 : 100%

### 8. 클라이언트 개발자와의 회의에 따른 회의록

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### issue 및 알게된 내용(studied)
1. [studied] validation처리를 하느라, if-else문을 사용하였는데 반환값의 타입이 맞지않다는 오류가 떴다. else에 예외처리 반환에러메시지를 보내더라도, if문은 해당 메소드에 적합한 반환값으로 꼭 짜야한다

2. [studied] 쿼리문 'select exists (~~)'는 해당값이 존재하는지 하지않는지 여부만 알려주는 쿼리문이며, 반환값이 true false이다. 해당 코드에서는 true == 1, false == 0으로 처리된다

3. [error] 로그인API의 다른 기능은 정상적인데, 요청받은 이메일이 존재하는지 확인 후 존재하지않는 상황(비회원)에대한 예외처리에대한 테스트에서 500에러가 났다
터미널의 로그를 확인해보니, 이러했다. `Incorrect result size : expected 1, actual 0` 

#### 원인 
3. 보통 500에러는 Dao에서 문제가 있었기에 확인해보았다. 요청받은 이메일에 대한 정보로 'queryForObject'를 사용하여 객체를 생성하는데, 존재하지않는 이메일이라 모든값이 null이었기에 객체가 생성되지않는게 문제였다

#### 해결
3. 코드의 순서에 문제가 있었기에, Dao의 해당 메소드를 사용하는 Provider애 있는 userDao.getUserInfo의 순서를 변경하였다 (이메일이 존재할 때 객체를 생성하도록, if문내로 이동)

-----

## day8 dev log (22.09.24)

### 1. 기획서의 변동사항

### 2. ERD 진행상황
+ ERD 설계 : ??% (30/??)
+ ERD 구현 : ??% (30/??)
_day4 dev log의 1에따라 수정진행중_

### 3. API 리스트업 진행상황 : 100% (15/15)
_day7의 내용과 동일_

### 4. 현재 개발중인 API 
+ 스토어 홈

### 5. 개발 완료 API
+ 회원가입 (validation 완료)
+ 로그인 (validation 완료)

### 5-1. 개발 완료 API관련 기능 수정
+ 정상처리의 결과변수들과 동일하게, 오류처리도 4개의 값(isSuccess,message,code,result)을 반환하도록 처리

### 6. 더미데이터 진행 상황 : 20%

### 7. prod서버 구축 진행 상황 : 100%

### 8. 클라이언트 개발자와의 회의에 따른 회의록

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### issue 

-----

## day9 dev log (22.09.25)

### 1. 기획서의 변동사항

### 2. ERD 진행상황
+ ERD 설계 : 100% (25/25)
+ ERD 구현 : 100% (25/25)
_day4 dev log의 1에따라 수정됨_
   + QUESTION
   + ITEM_QUESTION
   + QUESTION_TYPE
   + REVIEW
   + PURCHASE
   + USER
   + USER_NOTIFICATION
   + STORE_ADVERTISEMENT
   + STORE
   + ITEM
   + AUTO_PLAY
   + FOLLOW
   + CONTENT_OF_ITEM
   + SCRAP_ITEM
   + NOTIFICATION_SETTING
   + ITEM_LARGE_CATEGORY
   + ITEM_MEDIUM_CATEGORY
   + ITEM_SMALL_CATEGORY
   + ITEM_DETAIL_CATEGORY
   + SELLER_INFORMATION
   + DISCOUNT_COUPON
   + CONTENT_ORGANIZATION_OF_ITEM
   + AVAILABLE_COUPON
   + ITEM_COLOR
   + ITEM_ADDITIONAL

### 3. API 리스트업 진행상황 : 100% (15/15)
_day7의 내용과 동일_

### 4. 현재 개발중인 API 
+ 스토어 홈

### 5. 개발 완료 API
+ 회원가입 (validation 완료)
+ 로그인 (validation 완료)

### 6. 더미데이터 진행 상황 : 20%
_카테고리의 더미데이터가 중요_
+ 대분류 카테고리 (100%)
+ 중분류 카테고리 : 가구,패브릭,조명 진행완료 (19%)

### 7. prod서버 구축 진행 상황 : 100%

### 8. 클라이언트 개발자와의 회의에 따른 회의록
+ 상품관련 정보는 한번에 다 넘기면, 클라이언트에서 '최근 본 상품', '인기상품'.. 나누어서 처리

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### issue 

-----

## day10 dev log (22.09.26)

### 1. 기획서의 변동사항

### 2. ERD 진행상황
_day9 dev log와 동일_

### 3. API 리스트업 진행상황 : 100% (15/15)
_day7의 내용과 동일_

### 4. 현재 개발중인 API 
+ 광고 불러오기 API

### 5. 개발 완료 API
+ 회원가입 
+ 로그인 
+ 스토어 홈

### 6. 더미데이터 진행 상황 : 30%

### 7. prod서버 구축 진행 상황 : 100%

### 8. S3서버 구축 진행 상황 : 90%
+ 구축 및 셋팅 : 완료
+ spring boot와 연결 : 코딩 및 에러처리 진행중

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### issue 

-----

# day11 dev log (22.09.27)

### 1. 기획서의 변동사항(클라이언트와 협의한 내용만 작성 / 시간상 필수적인 기능 위주로)
+ 오늘의집 이미지 스플래시 -> 클라이언트에서만 진행
+ 이메일 회원가입
+ 이메일 로그인
+ 스토어 홈
+ 상품 상세정보 (배송카테고리, 스크랩 수, 관련쿠폰정보)
+ 스토어 홈 광고불러오기
+ 상품 구매하기
+ 상품 문의
+ 상품 판매자정보 불러오기
+ 상품 리뷰 불러오기
+ 사용자관련 주문/결제
+ 개별상품관련 주문/결제
+ 주문/결제의 주문자 수정
+ 장바구니 추가
+ 담겨있는 장바구니
+ 리뷰쓰기
+ 상품 스크랩
+ 마이페이지
+ 상단 햄버거 메뉴
+ 팔로우
+ 소셜 로그인
+ 메인 홈

**클라이언트에서 API연동은 하지않지만, 개발할 API목록도 있음**

_이전에 비해 기능이 세분화 됨_

### 2. ERD 진행상황
+ ERD 설계 : 100% (26/26)
+ ERD 구현 : 100% (26/26)

_기능세분화에따라 수정됨_
+ QUESTION
+ QUESTION_TYPE
+ REVIEW
+ PURCHASE
+ USER
+ USER_NOTIFICATION
+ STORE_ADVERTISEMENT
+ STORE
+ ITEM
+ AUTO_PLAY
+ FOLLOW
+ CONTENT_OF_ITEM
+ SCRAP_ITEM
+ NOTIFICATION_SETTING
+ ITEM_LARGE_CATEGORY
+ ITEM_MEDIUM_CATEGORY
+ ITEM_SMALL_CATEGORY
+ ITEM_DETAIL_CATEGORY
+ SELLER_INFORMATION
+ DISCOUNT_COUPON
+ CONTENT_ORGANIZATION_OF_ITEM
+ AVAILABLE_COUPON
+ ITEM_COLOR
+ ITEM_ADDITIONAL
+ SHIPPING_ADDRESS_LIST
+ SHIPPING_CATEGORY

_배송지 목록 관련하여 추가 (+2)_
_문의 ID와 사용자 일련번호 JOIN한 테이블 삭제 : 전체문의 테이블(QUESTION)에 사용자 일련번호 표현 (-1)_

### 3. API 리스트업 진행상황 : 100% (21/21)
_개발작업하며 수정 예정_ 

### 4. 현재 개발중인 API 

### 5. 개발 완료 API
+ 회원가입 
+ 로그인
+ 스토어 홈

### 6. 더미데이터 진행 상황 : 50%
_카테고리 더미데이터 복잡함_
+ 대분류 카테고리 100%
+ 중분류 카테고리 : 40%
+ 소분류 카테고리 : 20%

_이외 클라이언트에 보이는 중요한 더미데이터(실제같은 데이터)는 신경써서 진행중_

### 7. prod서버 구축 진행 상황 : 100%

### 8. S3서버 구축 진행 상황 : 92%
+ 구축 및 셋팅 : 완료
+ spring boot와 연결 : 완료 
+ 기능 test필요

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)

### 10. 개발 도중에 발생하는 이슈
#### issue 
1. 이미지서버를 따로 관리하기위해 S3서버를 구축하고, 스프링부트와 연동하는과정에서 에러가 발생 : 사용할 코드파일(S3관련 controller, service)을 생성하는 과정에서 `import com.amazonaws~`에서 에러발생 : com.amazonaws에 빨간밑줄(패키지가 없다는 에러)

### 원인
1. build.gradle파일에 `implementation('org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE')`을 하는데, 찾아보니 vscode의 스프링부트 코드내 의존성 패키지에 리스트업이 되지 않았음

### 해결
1. ~/.gradle/caches 파일의 내용을 다 지우고, build.gradle파일을 reload했더니 의존성 리스트에 관련 목록이 생성됨 : reload하면 캐시가 다시 생성되면서 필요한것들이 잘 받아지는것으로 보임 

-----

# day12 dev log (22.09.28)

### 1. 기획서의 변동사항(클라이언트와 협의한 내용만 작성 / 시간상 필수적인 기능 위주로)
_day11과 동일_

### 2. ERD 진행상황
+ ERD 설계 : 100% (26/26)
+ ERD 구현 : 100% (26/26)

### 3. API 리스트업 진행상황 : 100% (21/21)
**확정 리스트**
1. 이메일 회원가입 : /app/users/sign-up
2. 이메일 로그인 : /app/users/login
3. 스토어 홈 : /app/stores/home
4. 상품리뷰 불러오기 : /app/stores/review/:item-id
5. 상품 판매자정보 불러오기 : /app/stores/seller-info/:item-id
6. 상품 문의 불러오기 : app/stores/inquiry/:item-id
7. 상품 구매하기 : /app/stores/item/purchase/:item-id
8. 사용자관련 결제/주문 : /app/stores/payment/user/:user-idx
9. 개별상품관련 결제/주문 : /app/stores/payment/item/:item-id

_나머지는 개발작업하며 수정 예정_ 

### 4. 현재 개발중인 API 
+ 주문/결제의 주문자 수정

### 5. 개발 완료 API
+ 회원가입 
+ 로그인
+ 스토어 홈
+ 상품 상세정보 (배송카테고리, 스크랩 수, 관련쿠폰정보)
+ 상품 리뷰 불러오기
+ 상품 판매자정보 불러오기
+ 상품 문의 불러오기
+ 상품 구매하기
+ 사용자관련 주문/결제
+ 개별상품관련 주문/결제

### 6. 더미데이터 진행 상황 : 50%
_카테고리 더미데이터 복잡함_
+ 대분류 카테고리 100%
+ 중분류 카테고리 : 40%
+ 소분류 카테고리 : 20%

_이외 클라이언트에 보이는 중요한 더미데이터(실제같은 데이터)는 신경써서 진행중_

### 7. prod서버 구축 진행 상황 : 100%

### 8. 클라이언트 개발자와의 회의에 따른 회의록
+ 상품더미데이터는 20개 : 17개(상품 이미지 3~4개) + 메인 3개(어플과 동일하게 전체 이미지)
+ 배송메시지는 클라이언트에서 구현

### 9. 개발팀장님의 피드백(1차, 2차)
#### 2차 피드백 (22.09.28 22:30)
+ 더미데이터는 클라이언트에서 보여주기위함이기때문에, 보이는 기능은 실제데이터처럼 넣고, 클라이언트에서 연결하지못하는기능의 데이터는 테스트식으로 간단히 넣어도 됨.
+ 더미테이더와 API의 중요도는 9:1정도로 API가 훨씬 중요함
+ 서버개발자는 결국 API개발자이기때문에, API기능(쿼리 복잡도, validation처리..)에 신경쓰기

### 10. 개발 도중에 발생하는 이슈

----

# day13 dev log (22.09.29)

### 1. 기획서의 변동사항(클라이언트와 협의한 내용만 작성 / 시간상 필수적인 기능 위주로)
_day11과 동일_

### 2. ERD 진행상황
+ ERD 설계 : 100% (28/28)
+ ERD 구현 : 100% (28/28)

_기능세분화에따라 수정됨_
+ QUESTION
+ QUESTION_TYPE
+ REVIEW
+ PURCHASE
+ USER
+ USER_NOTIFICATION
+ STORE_ADVERTISEMENT
+ STORE
+ ITEM
+ AUTO_PLAY
+ FOLLOW
+ CONTENT_OF_ITEM
+ SCRAP_ITEM
+ NOTIFICATION_SETTING
+ ITEM_LARGE_CATEGORY
+ ITEM_MEDIUM_CATEGORY
+ ITEM_SMALL_CATEGORY
+ ITEM_DETAIL_CATEGORY
+ SELLER_INFORMATION
+ DISCOUNT_COUPON
+ CONTENT_ORGANIZATION_OF_ITEM
+ AVAILABLE_COUPON
+ ITEM_COLOR
+ ITEM_ADDITIONAL
+ SHIPPING_ADDRESS_LIST
+ SHIPPING_CATEGORY
+ MEMBERSHIP_LEVEL
+ BASKET

_day11에대해 2개 테이블 추가됨_

### 3. API 리스트업 진행상황 : 100% (32/32)
_개발 작업하며 수정진행중_

### 4. 현재 개발중인 API 
+ 회원등급 업 

### 5. 개발 완료 API
+ 이메일 회원가입 
+ 이메일 로그인
+ 스토어 홈
+ 상품 리뷰 불러오기
+ 상품 판매자정보 불러오기
+ 상품 문의 불러오기
+ 상품 구매하기
+ 사용자관련 주문/결제
+ 개별상품에서 넘어가는 주문/결제
+ 상품 상세정보 (배송카테고리, 스크랩 수, 관련쿠폰정보)
+ 주문/결제의 주문자 닉네임 수정
+ 주문/결제의 주문자 이메일 수정
+ 주문/결제의 주문자 전화번호 수정
+ 상품 상세정보(상품정보 탭)
+ 장바구니 담기
+ 장바구니 삭제
+ 팔로우
+ 팔로우 취소
+ 리뷰쓰기
+ 리뷰삭제
+ 상품 스크랩
+ 상품 스크랩 삭제
+ 리뷰글 수정
+ 프로필 사진 수정

### 6. 더미데이터 진행 상황 : 70%
_클라이언트에 보이는 중요한 더미데이터(실제같은 데이터)는 신경써서 진행중_

### 7. prod서버 구축 진행 상황 : 100%
