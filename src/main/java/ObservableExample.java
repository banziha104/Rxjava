import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.observables.ConnectableObservable;
import io.reactivex.subjects.AsyncSubject;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

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

    public void examplePublish(){
        PublishSubject<String> subject = PublishSubject.create();
        subject.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );}); // 디폴트 값 제공안함
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );}); // 최근 값인 3을 한번 받음 발행후에는 default는 없음
        subject.onNext("5");
        subject.onComplete();
    }

    public void exampleReply(){
        ReplaySubject<String> subject = ReplaySubject.create();
        subject.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );});
        subject.onNext("1");
        subject.onNext("3");
        subject.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );}); // 구독과 동시에 1,3을 받음
        subject.onNext("5");
        subject.onComplete();
    }

    public void exmapleConnectableObservable(){
        String[] arr = {"1","3","5"};
        Observable<String> balls = Observable.interval(100L, TimeUnit.MILLISECONDS)
                .map(Long::intValue)
                .map(i -> arr[i])
                .take(arr.length);
        ConnectableObservable<String> source = balls.publish(); // ConnectObservable 생성
        source.subscribe(data -> {System.out.println("Subscriber #1 =>" +data );});
        source.subscribe(data -> {System.out.println("Subscriber #2 =>" +data );});
        source.connect();
    }
}
