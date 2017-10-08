import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

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
}
