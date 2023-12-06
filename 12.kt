import java.io.FileReader

fun distance(holdTime:Long, timeLimit:Long):Long{
    return (timeLimit-holdTime)*holdTime
}
fun findSpanSize(timeLimit : Long, minDistance:Long):Long{
    val n = timeLimit/2
    var span: Long = 0
    for(i in n..timeLimit){
        if(distance(i, timeLimit) <= minDistance){
            span = 2*((i-1)-n)
            break
        }
    }
    if((timeLimit%2).toInt()==0){
        span++
    }
    return span
}
fun main() {
    val reader = FileReader("input")
    val lines: MutableList<String> = mutableListOf()
    val regex = Regex(" +")
    reader.forEachLine {
        lines.add(it.replace(regex, "").split(":")[1])
    }
    val time = lines[0].toLong()
    val distance = lines[1].toLong()

    println(findSpanSize(time, distance))
}