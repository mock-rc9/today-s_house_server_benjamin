# today-s_house_server_benjamin

## day1 dev log 

### 1차까지 개발예정인  기능 도메인(server)

#### 이메일 회원가입

+ 이메일 인증 기능구현 x

+ req : 이메일, 비밀번호, 별명

#### 이메일 로그인

#### 홈


## day2 dev log

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
