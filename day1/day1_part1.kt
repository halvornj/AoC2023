import java.io.FileReader
import java.io.File

fun main() {
    var sum :Int = 0
    val reader = FileReader("input1")
    reader.forEachLine {
        var left:Int = 0
        var right : Int = it.length -1
        while(it[left].digitToIntOrNull() == null){
            left++
        }
        while(it[right].digitToIntOrNull() == null){
            right--
        }
        sum += (""+it[left]+it[right]+"").toInt()
    }
    println(sum)
}