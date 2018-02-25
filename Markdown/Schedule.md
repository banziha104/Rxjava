# 스케쥴러

- Scheduler.computation()(계산 스케쥴러) : IO처리를 하지않고 순수하게 계산만하는 쓰레드
- Scheduler.io()(입출력 스케쥴러) : 네트워크 요청, 파일 입출력, DB 쿼리등 
- Scheduler.trampoline()(트램펄린 스케쥴러) : 새로운 스레드를 생성하지 않고 현재 스레드에 무한 대기 행렬을 생성
- Scheduler.newThread()(뉴스레드 스케쥴러) : 새로운 이름의 스레드를 생성함 
- AndroidSchedulers.mainThread() : 안드로이드 전용, 안드로이드의 UI 스레드 동작
- HandlerSchedulers.from(hanler) : 특정 핸들러에 의존해 동작



