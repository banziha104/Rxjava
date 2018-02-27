import io.reactivex.Observable
import io.reactivex.observables.GroupedObservable

fun main(args : Array<String>){
    val objs : Array<String> = arrayOf("6","4","2-T","2","6-T","4-T")
    val source : Observable<GroupedObservable<String,String>> = Observable.fromArray(objs).groupBy{

    }
}

fun getShape(string : String){
}