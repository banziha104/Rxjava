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

---

# 변환연산자

- concatMap() : flatMap() 과 유사하지만, 들어온 데이터를 순서대로 처리함
- switchMap() : 순서를 보장하기 위해 기존에 진행 우이던 작업을 바로 중단함. 즉, 마지막에 들어온 값만 처리하고 싶을 때 사용함
- groupBy() : 단일 Observable을 여러개의 Observable로 이루어진 Observable 그룹을 만듬 
- scan() : 실행할 때마다 맞는 중간결과 및 최종결과를 구독자에게 발행

# 결합 연산자

- zip() : 입력 observable에서 데이터를 모두 새로 발행했을 때 합해줌
- combineLastest() : 최신 값을 발행함
- merge() : 최신 데이터 여부와 상관없이 Observable에서 발행하는 데이터를 그대로 ㅜㄹ력
- concat() : Observable 단위로 이어 붙여