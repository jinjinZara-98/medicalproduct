# :paperclip: 의약품/ 의료기기 쇼핑몰
> 첫번째 토이 프로젝트입니다.

![로그인 전](https://user-images.githubusercontent.com/95284639/219711363-2446b2e7-88ae-43f7-9972-f0b3982b068c.png)

## 목차
- [들어가며](#들어가며)
  - [프로젝트 소개](#1-프로젝트-소개)    
  - [프로젝트 기능](#2-프로젝트-기능)    
  - [사용 기술](#3-사용-기술)   
     - [백엔드](#3-1-백엔드)
     - [프론트엔드](#3-2-프론트엔드)
  - [실행 화면](#4-실행-화면)   

- [구조 및 설계](#구조-및-설계)
  - [패키지 구조](#1-패키지-구조)
  - [DB 설계](#2-db-설계)

## 들어가며
### 1. 프로젝트 소개

의약품/ 의료기기를 판매하는 쇼핑몰 웹 어플리케이션입니다. <br/>
책과 강의를 통해 학습한 기술들을 사용해서 프로젝트를 직접 만들어보고 싶은 마음에 시작하게 되었습니다.


### 2. 프로젝트 기능

프로젝트의 주요 기능은 다음과 같습니다.
- **메인 화면 -** 판매 상품 확인, 판매 상품 페이징, 상품 이름으로 상품 검색, 카테고리별로 상품 검색
- **회원 -** 회원가입, 로그인, 아이디 찾기, 비밀번호 찾기, 회원정보 변경
- **상품 -** 상품 상세 화면에서 상품의 용도 확인, 장바구니에 담기, 주문 
- **장바구니 -** 장바구니에 담긴 상품 확인, 장바구니 상품 삭제, 수량 조정, 원하는 상품 선택해 주문
- **주문 -** 주문 상품 수령자 정보 작성, 최종 주문 진행, 주문 내역 리스트에서 주문 취소, 
             주문 상품 이름과 주문 상태로 주문 상품 검색, 주문 상세 정보 확인, 후기 작성 여부 확인
- **후기 -** 주문 완료된 상품에 한해서 후기 작성, 작성된 후기는 상품 상세 화면과 후기 내역 리스트에서 확인, 후기 내역 리스트에서 후기 삭제
- **인증, 인가 -** 스프링 시큐리티를 이용해 인증 기능을 구현, 일반과 관리자 2가지 역할을 만들어 인가 기능을 구현.

### 3. 사용 기술

#### 3-1 백엔드
 Java, Spring Framework, Spring Data JPA, Spring Security, QueryDsl, MySql
##### 주요 프레임워크 / 라이브러리
- Java 11
- SpringBoot 2.6.4
- SpringSecurity
- JPA(Spring Data JPA)
- QueryDsl
- JUnit
- Mokito

##### Build Tool
- Gradle 7.4.1

##### DataBase
- MySQL 8.0.19
- H2

#### 3-2 프론트엔드
- Html/Css
- JavaScript
- Thymeleaf
- Bootstrap 5.1

#### 3-3 데브옵스
- EC2
- S3
- RDS(MariaDB)

### 4. 실행 화면
  <details>
    <summary>메인 화면</summary>        
  <br/>
  
  **1. 로그인 전 메인 화면**   
  ![로그인 전](https://user-images.githubusercontent.com/95284639/219713134-300a5b00-d119-473a-9901-fbc6229033d6.png)
  상단 우측에 로그인과 회원가입 버튼이 보여짐.
     
  **2. 로그인 후 메인 화면**   
  ![로그인 후](https://user-images.githubusercontent.com/95284639/219713180-5297ccc2-8352-49a4-95eb-3d64e2482706.png)
  상단 우측에 로그아웃 버튼이 보여짐.
     
  **3. 페이징 처리**   
![ezgif com-video-to-gif (4)](https://user-images.githubusercontent.com/95284639/219714883-ab7eb976-5c46-4889-bf16-0a54692112ef.gif)
  
  한 화면에 8개의 판매 상품이 보여지도록 페이징 처리. 총 22개의 상품이 존재해 3개의 페이지로 구성. <br/>
  메인 화면에 보여지는 상품 가격은 1,000단위로 쉼표가 적히도록 포맷팅.
     
   **4. 상품 검색**   
![ezgif com-video-to-gif (5)](https://user-images.githubusercontent.com/95284639/219715105-8d5899d9-8241-4f2a-b1a6-c6c1b69a864a.gif)
  
  상단에 있는 검색바를 이용해서 원하는 상품 검색.
  
  **5. 카테고리에 속한 상품 보기**   
![ezgif com-video-to-gif (6)](https://user-images.githubusercontent.com/95284639/219715371-4c5faa8a-38cc-4833-ad7f-22df347bb199.gif)
  
  카테고리 버튼을 누르면 해당 카테고리에 속한 상품들을 볼 수 있음.
     
  </details>
  <br/>   

  <details>
    <summary>회원 가입과 관리자 페이지</summary>   
  </br>
     
  **1. 관리자 페이지, 마이페이지, 장바구니 페이지 등 로그인이 필요한 화면에 가기 위해선 회원가입을 해야한다.**  
 ![ezgif com-video-to-gif (9)](https://user-images.githubusercontent.com/95284639/219716948-a0a46a67-d363-49be-9d35-a9fb996b3433.gif)

 ![ezgif com-video-to-gif (7)](https://user-images.githubusercontent.com/95284639/219715629-5e45f7c0-22a6-48b4-8659-3eb61d062cc9.gif)
    
  **2. 회원가입 진행**   
  ![ezgif com-video-to-gif (8)](https://user-images.githubusercontent.com/95284639/219716323-24530db1-6cc0-4cf4-8143-8a525f2b43f1.gif)
  
  회원의 정보를 입력할 때 값이 없거나 잘못된 값을 입력하고 회원가입을 진행하려고 하면 에러가 발생 <br/> 
  사용자에게 다시 회원가입 화면으로 리다이렉트되어 사용자에게 어떤 정보를 잘못 입력했는지 인지시켜준다. <br/> 
  검증 기능은 spring 의 valid 를 이용해 구현. 또한 중복 아이디 체크를 해 다른 회원이 사용하고 있는 아이디를 입력해도 에러가 발생. <br/> 
  회원 가입 화면 외에도 값을 입력해야 하는 화면에는 valid 를 모두 적용.
  주소 입력은 다음 주소 찾기 API 를 이용했음. <br/> 
     
  **3. 회원으로 관리자 페이지 접근**   
![ezgif com-video-to-gif (10)](https://user-images.githubusercontent.com/95284639/219718027-7cd05972-2190-4f14-89da-c895699c725c.gif)
  
  회원가입한 회원으로 관리자 페이지에 접근. 관리자 페이지에는 관리자만 접근할 수 있으므로 일반 회원은 인가 거부 페이지로 리다이렉트된다. <br/>

  **4. 관리자로 관리자 페이지 접근**
![ezgif com-video-to-gif (11)](https://user-images.githubusercontent.com/95284639/219718595-5a7481bb-0587-43ab-9748-fa1cf86725ce.gif)

  관리자로 로그인한 후 관리자 페이지에 접근하면, 회원 목록을 볼 수 있다.
  
  </details>
  <br/>  
  
 <details>
    <summary>아이디 찾기, 비밀번호 찾기</summary>   
  <br/>
  
  **1. 잘못된 아이디와 비밀번호를 입력해 로그인 실패**   
![ezgif com-video-to-gif (12)](https://user-images.githubusercontent.com/95284639/219719292-36f78e1f-8760-4d63-8940-e8dbddd64b25.gif)

  로그인을 할 때 아이디와 비밀번호중에 하나가 틀리면 로그인에 실패해 다시 로그인 화면으로 리다이렉트된다.
 
  **2. 아이디 찾기**   
![ezgif com-video-to-gif (13)](https://user-images.githubusercontent.com/95284639/219719549-39b4a2bc-b926-47f6-9b72-0a670d7c382a.gif)
  
  회원의 이름과 전화번호를 입력하면 회원의 아이디를 보여줌. 
  
  **3. 비밀번호 찾기**   

![ezgif com-video-to-gif (14)](https://user-images.githubusercontent.com/95284639/219719861-4958307f-2899-4923-8341-6335196c2a6b.gif)
  
  회원의 아이디를 입력하면 비밀번호 재설정 페이지 링크를 메일로 전송. <br/>
  구글의 SMTP 서버를 이용해 메일 송신 기능을 구현.
  
  </details>
  <br/>  
  
  <details>
    <summary>주문</summary>   
  <br/>
  
  **1. 상품 상세 화면에서 주문**   
  ![ezgif com-video-to-gif (15)](https://user-images.githubusercontent.com/95284639/219720776-88d5ccd2-2e4c-4939-9681-e91cba2bc898.gif)
 
  **2. 장바구니를 통한 주문**   
 
  **2-1. 장바구니에 상품 담기**   
![ezgif com-video-to-gif (16)](https://user-images.githubusercontent.com/95284639/219721074-1d5fccf0-77f0-470d-bdfe-e4b93bfeab35.gif)

  의사/ 의료사업자 회원으로 로그인해서 원하는 상품을 장바구니에 담음. <br/>
  메인화면에서 장바구니 담기 버튼을 누르면 1개, 상품 상세 화면에서 장바구니 버튼을 누르면 설정한 개수만큼 징바구니에 담는다.
 
  **2-2. 장바구니 화면에서 상품 확인, 수량 조절 상품 삭제**   
![ezgif com-video-to-gif (17)](https://user-images.githubusercontent.com/95284639/219721641-0fc27788-5f68-4ce0-9982-890893adf5d3.gif)
  
  메인화면 우측 상단에서 장바구니 버튼을 눌러 장바구니 화면으로 이동. <br/>
  장바구니에 담긴 상품의 수량을 버튼을 이용해서 조정할 수 있고, 주문할 상품을 체크박스를 통해 선택할 수 있음. <br/>
  삭제하기 버튼을 클릭해 장바구니에서 상품 삭제 가능.
  
  **2-3. 주문**   
![ezgif com-video-to-gif (18)](https://user-images.githubusercontent.com/95284639/219721965-dcfe4ca3-feea-4bff-b46d-4de963bb715a.gif)

  주문 화면에서는 장바구니 화면에서 체크했던 상품의 정보가 출력. <br/> 
  상품 수령자는 꼭 회원 정보와 일치할 필요가 없으므로 수정이 가능. <br/>
  
  </details>
  <br/>  
  
  <details>
    <summary>마이페이지</summary>   
  <br/>
  
  **1. 마이페이지에서 회원의 정보 변경**   
![ezgif com-video-to-gif (19)](https://user-images.githubusercontent.com/95284639/219725248-2eb68814-2de2-46d9-950c-dc6d7cb8ade4.gif)

  마이페이지에서 회원의 상세 정보를 확인할 수 있고 아이디를 제외한 나머지 정보들은 변경이 가능.<br/>
  비밀번호는 중요하므로 따로 비밀번호 변경 화면을 개발했음.<br/>
  정보를 변경 후 다시 로그인하고 마이페이지에 들어가보면 변경된 정보를 확인 할 수 있다.

  </details>
  <br/>  
  
  <details>
    <summary>주문 내역 리스트</summary>   
  <br/>  
  
  **1. 주문 내역 리스트 확인**   
![ezgif com-video-to-gif (20)](https://user-images.githubusercontent.com/95284639/219726416-2d784c9c-000d-40b5-8276-d60078dc466c.gif)

  마이페이지에서 우측 상단에 있는 주문 내역 조회 버튼을 클릭해 주문 내역 리스트를 확인. <br/>
  주문 상품은 주문 날짜 최신순으로 정렬되어 있음. 주문 날짜는 스프링 포맷터를 이용해 포맷팅 구현.

  **2. 주문 취소**     
![ezgif com-video-to-gif (29)](https://user-images.githubusercontent.com/95284639/219760107-bc13adcb-fbd3-4386-a8bc-512711420212.gif)
  
  주문 취소 버튼을 누르면 주문 취소하려는 상품이 맞는지 사용자에게 다시 한 번 보여준다. <br/>
  사용자가 하단에 주문 취소 버튼을 누르면 최종적으로 주문 취소됨. <br/>
  주문이 취소되면 상품의 주문 상태가 주문 완료에서 주문 취소로 변경됨.
  
  **3. 주문 상품 검색**
  <br/>
![ezgif com-video-to-gif (22)](https://user-images.githubusercontent.com/95284639/219728070-00f0e6ba-5fbd-44cf-ac9a-120c1a4226d3.gif)
  
  주문 상품은 상품 이름과 상품 상태를 이용해 검색 할 수 있다.
  
  **4. 주문 상세 화면**
  <br/>
  
![ezgif com-video-to-gif (23)](https://user-images.githubusercontent.com/95284639/219728774-3f079e40-d456-41e8-aa24-7867c4d5f1ce.gif)

  주문 상품들은 주문 번호를 이용해 어떤 주문에 속해있는지 확인할 수 있다. <br/>
  주문 번호를 누르면 주문 상세 화면으로 이동하고 해당 주문에 속해있는 주문 상품들과 상품 수령자 정보를 출력한다.
  
  </details>
  <br/>  

<details>
    <summary>후기 작성과 후기 내역 리스트</summary>   
<br/> 

  **1. 주문한 상품 후기 작성**   
![ezgif com-video-to-gif (24)](https://user-images.githubusercontent.com/95284639/219729264-8cfa704e-452b-42a7-9bf1-7dd683ec332d.gif)

  주문 상품중 주문 완료인 상품들만 한해서 상품 후기를 작성할 수 있다. 후기는 제목, 내용을 작성하고 후기 사진을 업로드 해야한다. <br/>
  업로드한 후기 사진은 AWS 의 S3 스토리지에 저장된다.

  **2. 상품 상세 화면에서 작성된 후기 확인** 
![ezgif com-video-to-gif (25)](https://user-images.githubusercontent.com/95284639/219729728-08ad4d57-346a-419f-928e-9b2a90d439e1.gif)
  
  후기가 작성된 상품의 상품 상세 화면으로 가면 하단에 작성된 후기를 확인할 수 있다. <br/>
  후기 사진을 클릭하면 다운로드를 할 수 있다.
  
   **3. 후기 내역 리스트에서 작성된 후기 확인, 주문 내역 리스트에서 후기 작성 여부 확인**
![ezgif com-video-to-gif (26)](https://user-images.githubusercontent.com/95284639/219730070-ca8efd88-4cc6-4799-aa89-8b688d40599a.gif)

  마이 페이지에서 후기 내역 리스트 버튼을 눌러서 후기 내역 리스트 화면으로 가면 로그인한 회원이 작성한 후기 리스트를 확인할 수 있다. <br/>
  주문 내역 리스트 화면으로 가면 후기를 작성한 주문 상품의 후기 작성 버튼이 없어지고 후기 작성 완료 문구가 출력된 것을 확인할 수 있다.
  
  **4. 후기 내역 리스트에서 작성된 후기 삭제**
![ezgif com-video-to-gif (27)](https://user-images.githubusercontent.com/95284639/219731578-5e3a9dbf-7299-414e-9dcb-9719327a2a09.gif)
  
  후기 삭제 버튼을 클릭해 후기 삭제 가능.
  
  </details>
  <br/>  

## 구조 및 설계   
   
### 1. 패키지 구조
   
<details>
  
<summary>패키지 구조 보기</summary>   

```
📦src
 ┣ 📂main
 ┃ ┣ 📂java
 ┃ ┃ ┗ 📂capstonedesign
 ┃ ┃ ┃ ┗ 📂medicalproduct
 ┃ ┃ ┃ ┃ ┗ 📂configs
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AwsS3Config
 ┃ ┃ ┃ ┃ ┃ ┗ 📜WebConfig
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AdminController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜HomeController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberController.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderController.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewController.java
 ┃ ┃ ┃ ┃ ┣ 📂domain
 ┃ ┃ ┃ ┃ ┃ ┗ 📂entity
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Cart.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Item.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Member.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜Order.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderItem.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Review.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜Information.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRole.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderStatus.java
 ┃ ┃ ┃ ┃ ┣ 📂dto
 ┃ ┃ ┃ ┃ ┃ ┣ 📂cart
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemDetailDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗  📜ItemSearch.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindIdDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FindPasswordForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜LoginForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberDetailDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜NewPasswordDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜PasswordUpdateDto.java
 ┃ ┃ ┃ ┃ ┃ ┣  📂order
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderItemRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderItemResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderRequestDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderSearch.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜RecipientInfo.java
 ┃ ┃ ┃ ┃ ┃ ┗  📂review
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewRegisterForm.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewResponseDto.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜Uploadedfile.java
 ┃ ┃ ┃ ┃ ┣  📂interceptor
 ┃ ┃ ┃ ┃ ┃ ┗ 📜LogInterceptor.java
 ┃ ┃ ┃ ┃ ┣  📂listener
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SetupDataLoader.java
 ┃ ┃ ┃ ┃ ┣  📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 cart
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartCustomRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartCustomRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 cart
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemCustomRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemCustomRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewQueryRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewRepository.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂 orderr
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderCustomRepository.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderCustomRepositoryImpl.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣  📜OrderRepository.java
 ┃ ┃ ┃ ┃ ┃ ┗📂 review
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewCustomRepository.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewCustomRepositoryImpl.java  
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewRepository.java  
 ┃ ┃ ┃ ┃ ┣ 📂security
 ┃ ┃ ┃ ┃ ┃ ┣ 📂common
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FormWebAuthenticationDetails
 ┃ ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜FormWebAuthenticationDetailsSource
 ┃ ┃ ┃ ┃ ┃ ┣ 📂handler
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜FormWebAuthenticationDetails
 ┃ ┃ ┃ ┃ ┃ ┃ ┗  📜FormWebAuthenticationDetailsSource
 ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CustomUserDetailsService
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberContext
 ┃ ┃ ┃ ┃ ┃ ┃ ┗  📜MemberInfo
 ┃ ┃ ┃ ┃ ┃ ┣ 📜FormAuthenticationProvider
 ┃ ┃ ┃ ┃ ┃ ┗ 📜SecurityConfig
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜AwsS3Service
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartService
 ┃ ┃ ┃ ┃ ┃ ┣ 📜EmailService
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemService
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberServic
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderService
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewService
 ┃ ┃ ┃ ┃ 📜MailUtils
 ┃ ┃ ┃ ┗ 📜MedicalproductApplication
 ┃ ┗ 📂resources
 ┃ ┃ ┣ 📂static
 ┃ ┃ ┃ ┣ 📂css
 ┃ ┃ ┃ ┃ ┗ 📜bootstrap.css
 ┃ ┃ ┃ ┗ 📂js
 ┃ ┃ ┃ ┃ ┗ 📂app
 ┃ ┃ ┃ ┃ ┃ ┣ 📜cart.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜home.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜itemDetails.js
 ┃ ┃ ┃ ┃ ┃ ┣ 📜order.js
 ┃ ┃ ┃ ┃ ┃ ┗ 📜bootstrap.js
 ┃ ┃ ┃ ┃ ┗ 📜app.js
 ┃ ┃ ┣ 📂templates
 ┃ ┃ ┃ ┣ 📂admin
 ┃ ┃ ┃ ┃ ┗ 📜memberList.html
 ┃ ┃ ┃ ┣ 📂cart
 ┃ ┃ ┃ ┃ ┗ 📜cartItems.html
 ┃ ┃ ┃ ┣ 📂fragements
 ┃ ┃ ┃ ┃ ┣ 📜bodyHeader.html
 ┃ ┃ ┃ ┃ ┣ 📜footer.html
 ┃ ┃ ┃ ┃ ┣ 📜header.html
 ┃ ┃ ┃ ┃ ┗ 📜memberHeader.html
 ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┗ 📜itemDetail.html
 ┃ ┃ ┃ ┣ 📂login
 ┃ ┃ ┃ ┃ ┣ 📜demied.html
 ┃ ┃ ┃ ┃ ┗ 📜loginForm.html
 ┃ ┃ ┃ ┣ 📂members
 ┃ ┃ ┃ ┃ ┣ 📜idFind.html
 ┃ ┃ ┃ ┃ ┣ 📜idFindResult.html
 ┃ ┃ ┃ ┃ ┣ 📜myPage.html
 ┃ ┃ ┃ ┃ ┣ 📜newPassword.html
 ┃ ┃ ┃ ┃ ┣ 📜passwordFind.html
 ┃ ┃ ┃ ┃ ┣ 📜passwordFindResult.html
 ┃ ┃ ┃ ┃ ┣ 📜passwordUpdate.html
 ┃ ┃ ┃ ┃ ┗ 📜register.html
 ┃ ┃ ┃ ┣ 📂orders
 ┃ ┃ ┃ ┃ ┣ 📜order.html
 ┃ ┃ ┃ ┃ ┣ 📜orderCancel.html
 ┃ ┃ ┃ ┃ ┣ 📜orderDetail.html
 ┃ ┃ ┃ ┃ ┗ 📜orderList.html
 ┃ ┃ ┃ ┗ 📂reviews
 ┃ ┃ ┃ ┃ ┣ 📜reviewList.html
 ┃ ┃ ┃ ┃ ┗ 📜reviewRegister.html
 ┃ ┃ ┃ ┗ 📜home.html
 ┃ ┃ ┗ 📜application.properties
 ┗ 📂test
 ┃ ┗ 📂java
 ┃ ┃ ┗ 📂capstonedesign
 ┃ ┃ ┃ ┗ 📂medicalproduct
 ┃ ┃ ┃ ┃ ┣ 📂controller
 ┃ ┃ ┃ ┃ ┃ ┣ 📂integration
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ControllerIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜HomeControllerIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂unit
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜HomeControllerUnitTest.java
 ┃ ┃ ┃ ┃ ┗ 📂factory
 ┃ ┃ ┃ ┃ ┃ ┣ 📂cart
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜CartFactory.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂item
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ItemFactory.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂member
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜MemberFactory.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂order
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜OrderFactory.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📂review
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewFactoryjava
 ┃ ┃ ┃ ┃ ┃ ┗ 📜BasicFactory
 ┃ ┃ ┃ ┃ ┣ 📂repository
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜CartRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderRepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┣ 📜RepositoryTest.java
 ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewRepositoryTest .java
 ┃ ┃ ┃ ┃ ┣ 📂service
 ┃ ┃ ┃ ┃ ┃ ┣ 📂integration
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartServiceIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemServiceIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberServiceIntegrationTest .java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderServiceIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ReviewServiceIntegrationTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ServiceIntegrationTest .java
 ┃ ┃ ┃ ┃ ┃ ┗ 📂unit
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜CartServiceUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜ItemServiceUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜MemberServiceUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┣ 📜OrderServiceUnitTest.java
 ┃ ┃ ┃ ┃ ┃ ┃ ┗ 📜ReviewServiceUnitTest.java
 ┃ ┃ ┃ ┣ 📜MedicalproductApplicationTests.java
 ┃ ┃ ┃ ┗ 📜TestDB.java
 ```
  
 </details>   
 <br/>     
 
 ### 2. DB 설계
 # :paperclip: ERD

![제목 없음](https://user-images.githubusercontent.com/95284639/219764182-1c62a9f0-3383-42fa-8649-a2d27bd99a64.png)

<br/>
