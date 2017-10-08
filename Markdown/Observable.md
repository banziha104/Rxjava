# Observable

현재는 관찰되지 않지만, 관찰할 가능성이 있는 것을 뜻함.

기본 메소드

<li> onNext() : Observable이 데이터 발행을 알림.
<li> onComplete(): 모든 데이터의 발행을 완료했음을 알림. 한번만 발생하며 발생 이후 onNext() 이벤트가 호출되서는 안됨.
<li> onError(): 어떤 이유로 에러가 발생했음을 알림. 발생시 onNext 및 onComplete가 호출되지 않음, 즉 Observable의 실행을 종료함.

<br>

---


### subscribe() 함수와 Disposable 객체

동작시키는 시점을 조절할수 있음 subscribe() 함수를 호출해야 실제로 데이터를 발행함

<li> 인자가 없는 함수는 onError만 처리
<li> 인자가 한개인 경우 : onNext처리
<li> 인자가 두개인 경우 : onNext와 onError만 처리
<li> 인자가 세개인 경우 : onNext, onError, onComplete처리

<li> dispose() : 구독을 끊음. onCompelte가 실행되면 자동으로 실행.

<br>

---

### 팩토리 함수

<li> just() : 인자로 넣은 데이터를 차례로 발행하기 위해 사용(최대 10개, 타입은 같아야함)

<br>

```java
 Observable
                .just(1,2,3,4)
                .subscribe(System.out::println);
```

<br>

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

<li> fromArray() : 배열로 부터 생성
<li> fromIterable() : 리스트와 같이 Interable 인터페이스를 구현한 클래스로 부터 생성
<li> fromCallable() : callable 객체로 부터 생성

<br>

```java
Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello World";  
        };
        Observable.fromCallable(callable).subscribe(System.out::println);
```


<br>

---

# Single 클래스

하나가 발행됨과 동시에 종료됨. Observable을 상속받았기 때문에 Observable에서 변환 가능


<br>

---

### Hot vs Cold

<li> Hot : 구독자 존재 여부와 상관없이 데이터를 발행함, 배압을 고려해야함(마우스 이벤트와 같은 이벤트 주식 가격 , 온도 등)
<li> Cold : 구독하지 않으면 데이터를 방행하지 않음 (웹 요청, 데이터베이스 쿼리 등 처리)


<br>
---

# Subject 클래스

Observable을 Hot Obervable로 바꾸어주며 발행자이기도하고 구독자이기도함.

<li> AsyncSubject 클래스 : 발행한 마지막 데이터를 얻어오는 Subject.

```java
/*발행자로 사용시*/
public void exampleAsyncSubject(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> {System.out.println("Subscribe #1 =>" + data );});
        subject.onNext("1");
        subject.onNext("2");
        subject.subscribe(data -> {System.out.println("Subscribe #2 =>" + data );});
        subject.onNext("3");
        subject.onComplete(); //onCompelete 직전의 데이터가 발행됨.
    }

/*구독자로 사용시*/
public void exampleSubscribeSubject(){
        Float[] temperature = {10.5f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);
        
        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> {System.out.println("Sub #1 =>" + data);});
        
        source.subscribe(subject) // Subject클래스는 Observable 클래스와 Observer 인터페이스를 구현하기때문에 가능
    }
```

<li> BehaviorSubject 클래스 : 구독을 하면 최근 값 혹은 기본값을 넘겨주는 클래스.

```java
  public void exampleBehavior(){
        BehaviorSubject<String> subject = BehaviorSubject.createDefault("6"); //BehaviorSubject는 createDefault로 생성
        subject.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );}); // 6을 먼저 제공
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );}); // 최근 값인 3을 한번 받음 발행후에는 default는 없음
        subject.onNext("5");
        subject.onComplete();
    }
}
```

<li>