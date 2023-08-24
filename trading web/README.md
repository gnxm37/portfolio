# trading_web
## 웹 영상
<img width="100%" src="https://github.com/yeijeong/trading_web/assets/32433509/5e1fd687-6673-4cc0-a4d6-e51b3f106f7d"/>

## 환경세팅
- Java 11 version
- Java Script
- Spring Boot
- Maria DB
- BootStrap

## 작동방식
- 구글 클라우드를 이용해 증권사, 네이버 등에서 데이터 수집
- 수집한 데이터를 DB에 저장
- 웹과 DB를 연결하여 그래프, 뉴스리스트, 거래 정보 등 표시
- 그래프의 경우 3초마다 DB에서 새로운 데이터 가져와서 계속 이어져서 그래프를 그리도록 구현.

## 아쉬운점
- 시간이 많지 않아 제대로 된 실시간 그래프를 구현하지 못했다. 레디스를 사용하여 만들고 싶었으나 시간 부족으로 인하여 구현하지 못한 부분이 아쉽다.
- 그래프를 불러오는 속도가 조금 느린 부분이 아쉽다.
