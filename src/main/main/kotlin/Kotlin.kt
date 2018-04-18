
class DataBase(var date:String, var number:String){

    fun isValid()  {
        println("date : $date")
        println("number : $number")
    }
    fun isValid(date : String, number : String){
        println("date : $date")
        println("number : $number")
    }
}