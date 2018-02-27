# 연산자

<li> 생성 연산자 : 데이터의 흐름을 만들어내는 함수(create,just 등)
<li> 변환 연산자 : 입력값을 받아 원하는 출력 결과를 만들어내는 함수(map, flatMap 등)
<li> 필터 연산자 : 입력 데이터 중에 원하는 것만 걸러냄(filter, first)
<li> 합성 연산자 : d여러 Observable을 조합하는 역할
<li> 오류 처리 연산자 : onErrorReturn 등이 있음
<li> 유틸리티 연산자 : subscrbeOn()과 같이 비동기프로그래밍을 지원.
<li> 조건 연산자 : Observable의 흐름을 다루는 연산자
<li> 수학 연산자 : 수학 함수와 연관 있는 연산자
<li> 배압 연산자 : 배압 이슈에 대응하는 연산

<br>

---

### map() 함수

입력값을 어떤 함수에 넣어서 원하는 값으로 변환하는 함수임

```java
public void mapExample(){
        String[] ball = {"1","2","3","4"};
        Observable<String> source = Observable.fromArray(ball)
                .map(data -> data+"입니다");
        source.subscribe();
    }
```

<br>

---

### flatMap() 함수

map()함수는 원하는 입력값을 어떤 함수에 넣어서 변환하는 일대일 함수이지만
flatmap은 결과가 Observable로 나옮

```java
 public void flatMapExample(){
        Function<String,Observable<String>> myFunction = data -> Observable.just(data + "입니다");

        String[] ball = {"1","2","3","4"};
        Observable<String> source = Observable.fromArray(ball).flatMap(myFunction);

        source.subscribe(System.out::println);
    }
```

<br>

---

### filter() 함수

원하는 데이터만 걸러내는 역할을 함

```java
public void exampleFillter(){
        Integer[] data = {100,201,302,403,504};
        
        Observable<Integer> source = Observable.fromArray(data)
                .filter(number -> number %2 ==0);
        
        source.subscribe(System.out::println);
    }
```

### reduce() 함수

```kotlin
fun test(){
    val balls = arrayOf("1", "3", "5")
    val source : Maybe<String> = Observable.fromArray(balls)
    .reduce{ (ball1,ball2) -> "$ball2 ( $ball1 )"}
    source.subscribe(System.out::println)
}
    

```