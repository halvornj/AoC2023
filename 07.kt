import java.io.FileReader
import kotlin.math.max
import kotlin.math.pow


fun main() {
    //points = max(0, 2^(matches-1))
    var points = 0
    val reader = FileReader("input")
    reader.forEachLine {
        var matches:Int = 0
        val line: List<String> = it.trim().split("|",":")
        val winning = line[1].trim().split(" ").toMutableList()
        val nums = line[2].trim().split(" ")
        // println(line)

        for(i in 0..<winning.size){
            winning[i] = winning[i].trim()
        }
        for(num in nums){
            val n = num.trim()
            if(n == ""){
                continue
            }
            if(n in winning){
                matches+=1
            }
        }
        // println("matches: $matches")
        val n = max(0.0, 2.0.pow(matches-1)).toInt()
        // println("point value for ${line[0]}: $n")
        points += n
    }
    println("points: $points")
}