# Fashion Coordinator
다양한 브랜드의 카테고리별 상품을 조합하여 코디를 완성하는 서비스

## 구현 기능
### REST API
> 최고가 또는 최저가에 해당하는 상품이 여러 개인 경우, 이름이 사전순으로 가장 마지막에 위치한 브랜드의 상품 선택
1. 고객 대상
   - 카테고리 별 최저가 상품 조회
   - 최저가 단일 브랜드의 상품 조회
   - 특정 카테고리의 최고 및 최저가 상품 조회
2. 운영자 대상
   - 브랜드 추가 및 상품 추가/수정/삭제

   👉 **API 하나로 여러 기능을 수행하기 위해 브랜드와 상품들을 한꺼번에 등록/수정/삭제하도록 구현**

### UI
1. 고객 대상
    - 카테고리 별 최저가 상품 조회 [[링크]](http://localhost:8080/ui/products/cheapest-by-category)
    - 최저가 단일 브랜드의 상품 조회 [[링크]](http://localhost:8080/ui/products/cheapest-brand)
    - 특정 카테고리의 최고 및 최저가 상품 조회 [[링크]](http://localhost:8080/ui/products/categories/%EC%83%81%EC%9D%98/price-range)
2. 운영자 대상
    - 브랜드 추가 및 상품 추가/수정/삭제 [[링크]](http://localhost:8080/ui/admin/products)
3. 공통 에러 페이지

### API 문서
> http://localhost:8080/swagger-ui.html
- REST API 주소, 요청/응답 스키마, 상태코드 등 확인 가능
  - 전역적인 에러인 `422`, `500` 응답은 문서에 미포함
- API 테스트 호출 가능

### H2 콘솔
> http://localhost:8080/h2-console
> - JDBC URL: `jdbc:h2:mem:coordinator`
> - User Name: `sa`
> - Password: (없음)

## 프로젝트 실행
### 1. 빌드
   - 일반 빌드: `./gradlew build`
   - 테스트 제외 빌드:`./gradlew build -x test`

### 2. 테스트
   - `./gradlew test`
   - 초기 데이터는 `data.sql`로 세팅
   - 테스트 결과는 `build/reports/tests/test/index.html`에서 확인 가능

### 3. 실행
   - `java -jar build/libs/fashion-coordinator-0.0.1-SNAPSHOT.jar`
   - 초기 데이터는 `data.sql`로 세팅
   - `8080`(기본 포트)에서 실행

## 프로젝트 구조
- 사용 대상을 기준으로 `admin`과 `core` 패키지 분리
- 역할을 기준으로 `api`, `domain`, `db` 패키지 분리
  - api: 클라이언트 통신 담당
  - domain: 비즈니스 로직 담당
  - db: 데이터 CRUD 작업 담당
- 계층 구조: `Controller`, `Service`, `Repository`
  - 순방향 참조만 가능
  - 건너뛰기 의존 불가

## 기술 스택
`Java21, SpringBoot3, Springdoc, JPA, H2, Thymeleaf, JUnit5`

## 개발 기간
2025.04.02 ~ 2025.04.08