import io.reactivex.Observable
import io.reactivex.rxkotlin.toObservable

val scanner = java.util.Scanner(System.`in`)

var datas : List<DataBase> = arrayListOf()

fun main(args: Array<String>) {

    makeObservable(300)
            .filter { it > 200 }
            .map { "$it 이다" }
            .subscribe { println(it) }
//    var optString : String? = null
//    var abstractClass : AbstractClass = object : AbstractClass(){
//        override fun onClick() {
//            print("온클릭")
//        }
//    }
//
//    abstractClass.isOnClick()

}

fun makeObservable(): Observable<String> {
    return Observable.fromArray("첫번째", "두번째", "세번째", "네번째", "다섯번째", "여섯번째", "일곱번째")
}
fun makeData(){
    for( i in 1..100 ){
        datas += DataBase("$i \uD83E\uDD22","${i+1}")
    }
}
fun makeObservable(num: Int): Observable<Int> {
    return Observable.range(0, num)
}

abstract class AbstractClass{
    abstract fun onClick()
    fun isOnClick(){
        print("실행")
        onClick()
        print("끝")
    }
    companion object {

    }
}

//    var a : String? = null
//
//    a?.let {
//        print(it)
//    }?:run{
//        print("널값")
//    }


//    makeData()
//    datas.toObservable()
//            .filter { it.number.toInt() in 30..50 }
//            .map { it.date }
//            .subscribe {
//                println(it)
//            }
//    var dataBase : DataBase = DataBase("1","2")
//    dataBase.apply {
//        date = "20"
//        number = "30"
//    }.let {
//        dataBase ->
//        print(dataBase)
//    } ?.let{
//
//    }