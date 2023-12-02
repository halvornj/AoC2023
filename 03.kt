import java.io.FileReader

fun main() {
    val max = mapOf("red" to 12,"green" to 13,"blue" to 14)
    var sum = 0
    val reader = FileReader("input3")
    reader.forEachLine {
        var dice = it.slice(IntRange(it.indexOf(':')+1, it.length-1))
        dice = dice.replace(';', ',')
        val dicePairs = dice.split(',')
        sum+= addGameNum(max, dicePairs, it.split("Game", ":")[1].trim().toInt())
    }
    println(sum)
}
fun addGameNum(max: Map<String, Int>,dicePairs:List<String>, gameNumber: Int):Int {
    for(pair:String in dicePairs){
        val currentPair = pair.split(" ")
        if(currentPair[1].trim().toInt() > max[currentPair[2]]!!){
            return 0
        }
    }
    return gameNumber
}