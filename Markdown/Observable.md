# Observable

현재는 관찰되지 않지만, 관찰할 가능성이 있는 것을 뜻함.

기본 메소드

<li> onNext() : Observable이 데이터 발행을 알림.
<li> onComplete(): 모든 데이터의 발행을 완료했음을 알림. 한번만 발생하며 발생 이후 onNext() 이벤트가 호출되서는 안됨.
<li> onError(): 어떤 이유로 에러가 발생했음을 알림. 발생시 onNext 및 onComplete가 호출되지 않음, 즉 Observable의 실행을 종료함.

<br>

---

### 팩토리 함수

<li> just() : 인자로 넣은 데이터를 차례로 발행하기 위해 사용(최대 10개, 타입은 같아야함)

```java
 Observable
                .just(1,2,3,4)
                .subscribe(System.out::println);
```

<li> create() : just와는 달리 개발자가 직접 onNext, onComplete, onError를 처리해야함.

```java
 Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter)->{
                   emitter.onNext(100);
                   emitter.onNext(200);
                   emitter.onNext(300);
                   emitter.onComplete();
                });
        source.subscribe(System.out::println);

```




### subscribe() 함수와 Disposable 객체

동작시키는 시점을 조절할수 있음 subscribe() 함수를 호출해야 실제로 데이터를 발행함

<li> 인자가 없는 함수는 onError만 처리
<li> 인자가 한개인 경우 : onNext처리
<li> 인자가 두개인 경우 : onNext와 onError만 처리
<li> 인자가 세개인 경우 : onNext, onError, onComplete처리

<li> dispose() : 구독을 끊음. onCompelte가 실행되면 자동으로 실행.

