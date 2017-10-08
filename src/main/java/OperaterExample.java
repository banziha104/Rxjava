import io.reactivex.Observable;
import io.reactivex.functions.Function;

import java.util.Scanner;


public class OperaterExample {
    public void mapExample(){
        String[] ball = {"1","2","3","4"};
        Observable<String> source = Observable.fromArray(ball)
                .map(data -> data+"입니다");
        source.subscribe();
    }

    public void flatMapExample(){
        Function<String,Observable<String>> myFunction = data -> Observable.just(data + "입니다");

        String[] ball = {"1","2","3","4"};
        Observable<String> source = Observable.fromArray(ball).flatMap(myFunction);

        source.subscribe(System.out::println);
    }
    public void GuGudan(){
        Scanner in = new Scanner(System.in);
        System.out.println("Gugudan Input:");
        int dan = Integer.parseInt(in.nextLine());

        Function<Integer,Observable<String>> gugudan = num ->
                Observable.range(1,9)
                        .map(row -> num + "*" + row + "=" + num * row);

        Observable<String> observable = Observable.just(dan).flatMap(gugudan);
        observable.subscribe(System.out::println);
    }

    public void exampleFillter(){
        Integer[] data = {100,201,302,403,504};

        Observable<Integer> source = Observable.fromArray(data)
                .filter(number -> number %2 ==0);

        source.subscribe(System.out::println);
    }
}
