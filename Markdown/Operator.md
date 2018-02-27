# 생성 연산자

- interval() : 일정 시간 간격으로 데이터 흐름을 생성

```kotlin
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

class RxJavaWithKotlinTest{
    // 1초간격으로 다섯개를 생성
    val source : Observable<Long> = Observable.interval(100L, TimeUnit.MICROSECONDS)
            .map { (it + 1)*100 }
            //최초의 5개만 
            .take(5)
}
```

- timer() : interval과 유사하지만 한번만 실행됌

- range() : 주어진 값(n)부터 m개의 Integer객체를 발행함

```kotlin
class RxJavaWithKotlinTest{
    fun main(args : Array<String>) {
        val source : Observable<Int> = Observable.range(1,10)
                .filter{ it%2 == 0}
        source.subscribe(System.out::print)
    }
}
```

- intervalRange() : 일정한간격으로 n부터 m개 까지 생성

- defer() : 데이터흐름 생성을 구독자가 subscribe() 함수를 호출할때까지 미룰 수 있음.
- repeat() : 단순히 반복 실행만 함

```kotlin
String[] bal
```