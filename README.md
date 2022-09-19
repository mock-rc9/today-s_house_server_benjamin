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


