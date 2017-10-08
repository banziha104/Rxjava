import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;

import java.util.concurrent.Callable;

public class ObservableExample {
    public void example1(){
        Observable
                .just(1,2,3,4)
                .subscribe(System.out::println);
    }

    public void exampleCreate(){
        Observable<Integer> source = Observable.create(
                (ObservableEmitter<Integer> emitter)->{
                   emitter.onNext(100);
                   emitter.onNext(200);
                   emitter.onNext(300);
                   emitter.onComplete();
                });
        source.subscribe(System.out::println);
    }

    public void exmapleCallable(){
        Callable<String> callable = () -> {
            Thread.sleep(1000);
            return "Hello World";
        };
        Observable.fromCallable(callable).subscribe(System.out::println);
    }

    public void exampleAsyncSubject(){
        AsyncSubject<String> subject = AsyncSubject.create();
        subject.subscribe(data -> {System.out.println("Subscribe #1 =>" + data );});
        subject.onNext("1");
        subject.onNext("2");
        subject.subscribe(data -> {System.out.println("Subscribe #2 =>" + data );});
        subject.onNext("3");
        subject.onComplete();
    }

    public void exampleSubscribeSubject(){
        Float[] temperature = {10.5f, 13.4f, 12.5f};
        Observable<Float> source = Observable.fromArray(temperature);

        AsyncSubject<Float> subject = AsyncSubject.create();
        subject.subscribe(data -> {System.out.println("Sub #1 =>" + data);});

        source.subscribe(subject); // Subject클래스는 Observable 클래스와 Observer 인터페이스를 구현하기때문에 가능
    }

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
