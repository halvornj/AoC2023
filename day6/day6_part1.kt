import java.io.FileReader

fun distance(holdTime:Int, timeLimit:Int):Int{
    return (timeLimit-holdTime)*holdTime
}
fun findSpanSize(timeLimit : Int, minDistance:Int):Int{
    var n = timeLimit/2
    var span = 0
    for(i in n..timeLimit){
        if(distance(i, timeLimit) <= minDistance){
            span = 2*((i-1)-n)
            break
        }
    }
    if(timeLimit%2==0){
        span++
    }
    return span
}

fun main() {
    val reader = FileReader("input")
    val lines :MutableList<String> = mutableListOf()
    val regex = Regex(" +")
    reader.forEachLine {
        lines.add(it.replace(regex, " ").split(": ")[1])
    }
    val times: List<Int> = lines[0].split(" ").map { s ->s.toInt() }
    val distances :List<Int> = lines[1].split(" ").map { s: String ->  s.toInt()}

    val numbers : MutableList<Int> = mutableListOf()
    for(i in times.indices){
        numbers.add(findSpanSize(times[i],distances[i]))
    }
    var n = 1
    for(num in numbers){
        n*=num
    }



    println("numbers: $numbers")
    println("product: $n")
}