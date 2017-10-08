import io.reactivex.Observable;

public class OperaterExample {
    public void mapExample(){
        String[] ball = {"1","2","3","4"};
        Observable<String> source = Observable.fromArray(ball)
                .map(data -> data+"입니다");
        source.subscribe();
    }
}
