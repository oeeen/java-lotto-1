# java-lotto
로또 미션 진행을 위한 저장소

## 기능 구현 목록
1. 구입 금액 입력 받기 
    * 최소 구매 금액(1,000원) ~ 최대 구매 금액(100,000원) 허용
2. 해당 금액으로 로또 구매
    * 수동 구매
        * 사용자 입력에 대한 예외 처리 ( 1 ~ 45 )
        * 사용자 입력에서 중복 예외 처리(보너스 볼 포함)
    * 자동 구매
4. 당첨 번호 입력 받기
    * 사용자 입력에 대한 예외 처리 ( 1 ~ 45 )
    * 사용자 입력에서 중복 예외 처리(보너스 볼 포함)
5. 보너스 볼 입력 받기
    * 당첨 번호와 중복 되는지 예외 처리
6. 총 상금 계산
    * 수익률 계산
    

## Web UI 구현
1. 구입 금액 입력
2. 수동 구매 개수 입력
3. 수동 로또 입력
4. 자동 로또 구매 후 **출력
5. 당첨 번호 입력
6. 보너스 볼 입력
7. 최종 결과 출력

## DB 연동
1. DB Update, 조회 가능
    * 사용자가 구매한 로또 - [1,2,3,4,5,6]
    * 당첨 번호 - [1,2,3,4,5,6] + bonusNum
    * 당첨 결과 - Rank(MISS부터 FIRST까지)
    * 당첨 금액 - 실제 당첨 금액
    * 수익률 - 실제 수익률