# 🏪 java-convenience-store-precourse
## ⭐️ 미션에서 지키려고 노력한 것
1. **<u>과제 제출 전 테스트 실행하기[⭐️⭐️⭐️]</u>**
2. 과제 진행 요구 사항, 기능 요구 사항, 프로그래밍 요구 사항 모두 만족
3. 기능 구현 전 프로그램 진행 순서, 1차 기능 목록 작성
4. 3주차 공통 피드백을 반영하여 TDD 도전
5. 1차 기능 구현 후, 기본 테스트 통과 여부 확인
6. 리팩토링, 필요한 추가 기능이 있는지 고민하고 수립하기
7. 커밋 메시지를 제목과 내용으로 나누어 기능 단위 커밋
8. class와 method의 역할 명확히 구분 / 특히 method는 한 가지 일만 잘 하고, 10라인을 넘기지 않기
9. method naming이 명확한지 한 번 더 확인 **<u>(확인 후 리팩토링 시 IDE Refactor 활용!!!)</u>**
10. Java Code Convention을 지키며 구현
11. 3주차 공통 피드백을 최대한 반영

## 📁 디렉토리 구조
```
```

## 💻 프로그램 진행 순서
1. ``src/main/resources/products.md``, ``src/main/resourcespromotions.md`` 파일을 읽어 들여 상품 목록과 행사 목록을 불러온다.
2. 환영 인사와 함께 현재 보유하고 있는 재고를 출력한다.
3. 사용자로부터 구매할 상품과 수량을 입력받는다.
    * 사용자 입력이 잘못된 값이라면 오류 메시지 출력 후 재입력 받는다.
4. 오늘 날짜를 토대로 프로모션 적용 가능 상품인지 확인한다.
5. 구매할 상품에 프로모션 적용이 가능한 상품이 포함되어 있는지 확인한다.
6. 프로모션 적용 가능한 상품을 고객이 해당 수량만큼 가져오지 않았다면, 그 수량만큼 추가 여부를 묻는 안내 메시지를 출력한다.
    * 사용자 입력이 잘못된 값이라면 오류 메시지 출력 후 재입력 받는다.
7. 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제하는 경우라면, 일부 수량에 대해 정가 결제 여부를 묻는 안내 메시지를 출력한다. 
    * 사용자 입력이 잘못된 값이라면 오류 메시지 출력 후 재입력 받는다.
8. 멤버십 할인 적용 여부를 확인하기 위한 안내 메시지를 출력한다. 
    * 사용자 입력이 잘못된 값이라면 오류 메시지 출력 후 재입력 받는다.
9. 총 구매 금액, 프로모션 할인 금액, 멤버십 할인 금액을 계산한다.
10. 영수증을 출력한다.
11. 추가 구매 여부 확인을 위해 안내 메시지를 출력한다.
    * 사용자 입력이 잘못된 값이라면 오류 메시지 출력 후 재입력 받는다.
12. 사용자가 추가 구매를 원한다면 재고가 업데이트된 상품 목록으로 추가 구매를 진행한다.
13. 사용자가 추가 구매를 원하지 않는다면 프로그램을 종료한다.
14. <details>
    <summary>실행 결과 예시</summary>
    
    ```
    안녕하세요. W편의점입니다.
    현재 보유하고 있는 상품입니다.

    - 콜라 1,000원 10개 탄산2+1
    - 콜라 1,000원 10개
    - 사이다 1,000원 8개 탄산2+1
    - 사이다 1,000원 7개
    - 오렌지주스 1,800원 9개 MD추천상품
    - 오렌지주스 1,800원 재고 없음
    - 탄산수 1,200원 5개 탄산2+1
    - 탄산수 1,200원 재고 없음
    - 물 500원 10개
    - 비타민워터 1,500원 6개
    - 감자칩 1,500원 5개 반짝할인
    - 감자칩 1,500원 5개
    - 초코바 1,200원 5개 MD추천상품
    - 초코바 1,200원 5개
    - 에너지바 2,000원 5개
    - 정식도시락 6,400원 8개
    - 컵라면 1,700원 1개 MD추천상품
    - 컵라면 1,700원 10개

    구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
    [콜라-3],[에너지바-5]

    멤버십 할인을 받으시겠습니까? (Y/N)
    Y

    ===========W 편의점=============
    상품명		수량	금액
    콜라		3 	3,000
    에너지바 		5 	10,000
    ===========증	정=============
    콜라		1
    ==============================
    총구매액		8	13,000
    행사할인			-1,000
    멤버십할인			-3,000
    내실돈			 9,000

    감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
    Y

    안녕하세요. W편의점입니다.
    현재 보유하고 있는 상품입니다.

    - 콜라 1,000원 7개 탄산2+1
    - 콜라 1,000원 10개
    - 사이다 1,000원 8개 탄산2+1
    - 사이다 1,000원 7개
    - 오렌지주스 1,800원 9개 MD추천상품
    - 오렌지주스 1,800원 재고 없음
    - 탄산수 1,200원 5개 탄산2+1
    - 탄산수 1,200원 재고 없음
    - 물 500원 10개
    - 비타민워터 1,500원 6개
    - 감자칩 1,500원 5개 반짝할인
    - 감자칩 1,500원 5개
    - 초코바 1,200원 5개 MD추천상품
    - 초코바 1,200원 5개
    - 에너지바 2,000원 재고 없음
    - 정식도시락 6,400원 8개
    - 컵라면 1,700원 1개 MD추천상품
    - 컵라면 1,700원 10개

    구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
    [콜라-10]

    현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
    Y

    멤버십 할인을 받으시겠습니까? (Y/N)
    N

    ===========W 편의점=============
    상품명		수량	금액
    콜라		10 	10,000
    ===========증	정=============
    콜라		2
    ==============================
    총구매액		10	10,000
    행사할인			-2,000
    멤버십할인			-0
    내실돈			 8,000

    감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
    Y

    안녕하세요. W편의점입니다.
    현재 보유하고 있는 상품입니다.

    - 콜라 1,000원 재고 없음 탄산2+1
    - 콜라 1,000원 7개
    - 사이다 1,000원 8개 탄산2+1
    - 사이다 1,000원 7개
    - 오렌지주스 1,800원 9개 MD추천상품
    - 오렌지주스 1,800원 재고 없음
    - 탄산수 1,200원 5개 탄산2+1
    - 탄산수 1,200원 재고 없음
    - 물 500원 10개
    - 비타민워터 1,500원 6개
    - 감자칩 1,500원 5개 반짝할인
    - 감자칩 1,500원 5개
    - 초코바 1,200원 5개 MD추천상품
    - 초코바 1,200원 5개
    - 에너지바 2,000원 재고 없음
    - 정식도시락 6,400원 8개
    - 컵라면 1,700원 1개 MD추천상품
    - 컵라면 1,700원 10개

    구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
    [오렌지주스-1]

    현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
    Y

    멤버십 할인을 받으시겠습니까? (Y/N)
    Y

    ===========W 편의점=============
    상품명		수량	금액
    오렌지주스		2 	3,600
    ===========증	정=============
    오렌지주스		1
    ==============================
    총구매액		2	3,600
    행사할인			-1,800
    멤버십할인			-0
    내실돈			 1,800

    감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
    N
    ``` 
    </details>

## 📋 기능 목록
### 파일 입력
- [x] 구현에 필요한 상품 목록, 행사 목록을 파일 입력을 통해 읽어 들이기

### 입고 관리
- [x] 읽어 들인 상품 목록을 상품으로 등록

### 프로모션 관리
- [x] 읽어 들인 행사 목록을 프로모션으로 등록

### 사용자 입력
1. 구매할 상품명과 수량
- [x] 구매할 상품명과 수량을 입력 받기
- [x] 입력한 구매할 상품명과 수량의 형식(``[콜라-10],[사이다-3]``) 검증 
- [x] 입력한 상품명이 존재하는 상품인지 검증
- [x] 입력한 수량이 재고 수량 이내인지 검증

2. 프로모션 안내 답변
- [ ] 프로모션 안내에 대한 답변 입력 받기
- [ ] 답변 형식(``Y/N``) 검증

3. 멤버십 할인 적용 여부 답변
- [ ] 멤버십 할인 적용 여부에 대한 답변 입력 받기
- [ ] 답변 형식(``Y/N``) 검증

4. 추가 구매 여부 안내 답변
- [ ] 추가 구매 여부 안내에 대한 답변 입력 받기
- [ ] 답변 형식(``Y/N``) 검증

5. 입력 검증 후 재입력 받기
- [ ] 잘못된 값을 입력할 경우 ``IllegalArgumentException`` 발생 후, 그 부분부터 재입력 받기

### 재고 관리
- [x] 각 상품의 재고 수량을 토대로 판매 가능 여부 확인 = 사용자 입력 중 입력한 수량이 재고 수량 이내인지 검증
- [ ] 사용자가 상품 구매 시 판매한 수량만큼 재고 차감
- [ ] 프로그램 종료 전까지 최신 재고 상태를 유지, 다음 사용자가 구매 시 정확한 재고 정보 제공

### 프로모션 할인
- [ ] 오늘 날짜가 프로모션 기간 내에 포함되었는지 확인 후 할인 적용
- [ ] 프로모션 혜택은 프로모션 재고 내에서만 적용 가능
- [ ] 프로모션 기간 중이라면 프로모션 재고 우선 차감
- [ ] 프로모션 재고가 부족하다면 일반 재고 사용

### 멤버십 할인
- [ ] 멤버십 회원은 프로모션 미적용 금액의 30% 할인 적용
- [ ] 프로모션 적용 후 남은 금액에 대해 멤버십 할인 적용
- [ ] 멤버십 할인의 최대 한도는 8,000원

### 최종 결제 금액 계산
- [ ] ``총구매액 - 행사할인 - 멤버십할인``으로 최종 결제 금액 계산

### 출력
1. 예외 상황 시 오류 메시지 출력
- [ ] "[ERROR]"로 시작하는 지정된 오류 메시지 출력

2. 환영인사, 상품명, 가격, 재고, 프로모션 이름 출력
- [ ] 환영인사, 상품명, 가격, 재고, 프로모션 이름 출력
- [ ] 상품명, 가격, 프로모션 이름, 재고는 띄어쓰기로 구분하여 출력
- [ ] 가격은 ``1,000원``의 형식으로 출력
- [ ] 재고는 ``10개``의 형식으로 출력, 재고가 없다면 ``재고 없음`` 출력

3. 프로모션 안내 출력
- [ ] 프로모션 적용이 가능한 상품에 대해 고객이 해당 수량보다 적게 가져왔다면, 그 수량만큼 추가 여부를 묻는 안내 메시지 출력
- [ ] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제하는 경우라면, 일부 수량에 대해 정가 결제 여부를 묻는 안내 메시지 출력

4. 멤버십 할인 적용 여부 출력
- [ ] 멤버십 할인 적용 여부를 묻는 안내 메시지 출력

5. 영수증 출력
- [ ] 구매 상품 내역(구매한 상품명, 수량, 가격) 출력
- [ ] 증정 상품 내역(프로모션에 따라 무료로 제공된 증정 상품의 목록) 출력 
- [ ] 금액 정보(총구매액, 행사할인, 멤버십할인, 내실돈) 출력
- [ ] 구성 요소를 보기 좋게 정렬하여 출력

6. 추가 구매 여부 안내 출력
- [ ] 추가 구매 여부를 묻는 안내 메시지 출력

## 🤔 요구 사항 이외의 추가 기능

## ✅ 테스트 목록
### 파일 입력
- [x] 파일 입력을 통해 읽어 들인 상품 목록, 행사 목록이 파일 내용과 동일한지 확인

### 입고 관리
- [x] 읽어 들인 상품 목록이 상품으로 등록되는지 확인

### 프로모션 관리
- [x] 읽어 들인 행사 목록이 프로모션으로 등록되는지 확인

### 사용자 입력
1. 구매할 상품명과 수량
- [x] 입력한 구매할 상품과 수량의 형식(``[콜라-10],[사이다-3]``)인지 검증
- [x] 입력한 구매할 상품과 수량의 형식이 올바르지 않다면 예외처리 하는지 검증
- [x] 구매할 개별 상품을 쉼표(,)를 기준으로 구분하는지 검증
- [x] 구매할 상품명과 수량을 하이픈(-)을 기준으로 구분하는지 검증
- [x] 입력한 상품명이 존재하는 상품인지 검증
- [x] 입력한 상품명이 존재하지 않는 상품을 포함하고 있다면 예외처리 하는지 검증
- [x] 입력한 수량이 재고 수량 이내인지 검증
- [x] 입력한 수량이 재고 수량을 초과하면 예외처리 하는지 검증
