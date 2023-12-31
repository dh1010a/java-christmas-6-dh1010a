# 🚀 구현할 기능 목록

## ✔ InputView

- 12월 중 식당 예상 방문 일자 입력 받는 기능
- 주문 메뉴와 개수를 입력받는 기능

#### 공통 예외 처리

- 입력한 데이터에 공백 포함시 에러 발생 - `IllegalArgumentException`
- 값이 입력되지 않을시 에러 발생 - `IllegalArgumentException`

## ✔ OutputView

- 준비된 이벤트 멘트를 출력해주는 기능
- 주문한 메뉴 목록을 출력해주는 기능
- 할인 전 총 주문금액을 출력해주는 기능
- Receipt를 받아 정보를 출력해주는 기능
    - 증정 메뉴를 출력
    - 혜택 내역
    - 총 혜택 금액
    - 할인 후 예상 결제 금액
- 이벤트 배지를 출력해주는 기능

## ✔ EventPlanner

이벤트를 책임지는 관리자

- InputView를 통해 고객에게 주문을 받는 기능
- OutputView를 통해 받은 주문을 확인해주는 기능
- 주문내역을 할인계산기에 입력하는 기능
- 주문 날짜에 맞게 OutputView를 통해 이벤트 멘트를 고객에게 안내하는 기능
- 할인 결과인 Receipt를 받아 OutputView를 통해 고객에게 안내하는 기능
- 이벤트 배지를 부여하고 OutputView를 통해 고객에게 안내하는 기능

## ✔ VisitDate

12월의 날짜와 요일 정보를 담고있는 객체

- 날짜를 받으면 주말인지 평일인지 판단해주는 기능
- 날짜를 문자열 형식으로 변환하여 반환해주는 기능
- 날짜를 받으면 starDay인지 아닌지 판단해주는 기능

#### 유효성 검증

- 1~31일이 아니라면 에러 발생 - `IllegalArgumentException`
- 날짜가 숫자형식이 아니라면 에러 발생 - `IllegalArgumentException`

#### 받아올 정보

- 주문 날짜

## ✔ Orders

주문한 메뉴의 내용을 저장하는 객체

- 메뉴의 금액을 알려주는 기능
- 총 주문금액을 알려주는 기능
- 총 주문금액이 이벤트 대상인지 알려주는 기능
- 메인 메뉴와 디저트 개수를 알려주는 기능
- 주문 정보를 toString()을 통해 문자열로 반환해주는 기능

#### 활용할 데이터 Enum 타입

- 메뉴 타입 (디저트, 메인, 음료, 애피타이저)
- 메뉴 금액

#### 받아올 정보

- 주문한 내역

#### 유효성 검증

- 메뉴판에 없는 메뉴 주문시 에러 발생 - `IllegalArgumentException`
- 주문할 메뉴의 개수가 한개 이상이 아닌경우 에러 발생 - `IllegalArgumentException`
- 중복된 메뉴가 있는 경우 에러 발생- `IllegalArgumentException`
- 주문 형식에 맞지 않게 주문하면 에러 발생 - `IllegalArgumentException`
- 총 주문한 음식 수가 20개 이상일시 에러 발생 - `IllegalArgumentException`
- 음료만 주문했을 시 에러 발생 - `IllegalArgumentException`

## ✔ DiscountCalculator

날짜와 할인 정책에 맞게 할인 금액을 계산해주는 객체

- 크리스마스 디데이 할인 금액을 계산해주는 기능
- 평일과 주말에 따라 할인 금액을 계산해주는 기능
    - 평일이면 디저트 개수에 따라 계산
    - 주말이라면 메인 메뉴 개수에 따라 계산
- 특별 할인 금액을 계산해주는 기능
- 총 금액이 120,000원 이상이면 샴페인을 증정해주는 기능
- 구해진 할인 정보를 ReceiptPrinter을 통해 Receipt 객체에 담아 리턴해주는 기능

#### 받아올 정보

- Orders 객체
- visitDate 객체

#### 유효성 검증

- 메뉴가 없는 경우 에러 발생 : `IllegalStateException`

## ✔ ReceiptPrinter

할인 적용 결과를 Receipt에 저장/적용해주는 객체

- 혜택 내역을 Receipt에 작성
- 총 할인 금액을 Receipt에 작성
- 할인 후 최종 결제 금액을 Receipt에 작성
- 증졍할 메뉴 목록을 Receipt에 작성

## ✔ Receipt

할인이 적용된 주문의 결과를 담고있는 객체

- 혜택 내역을 문자열 리스트로 반환해주는 기능
- 총 할인 금액을 반환해주는 기능
- 할인 적용 후의 금액을 반환해주는 기능




