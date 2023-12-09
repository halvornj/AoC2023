package day2
import java.io.FileReader

fun main() {
    val reader = FileReader("input3")
    var sum = 0

    reader.forEachLine {
        var dice = it.slice(IntRange(it.indexOf(':')+1, it.length-1))
        dice = dice.replace(';', ',')
        val diceArr = dice.split(',')
        val dicePairs: MutableList<Pair<Int, String>> = mutableListOf()
        for(p : String in diceArr){
            val pair = p.split(" ")
            dicePairs.add(Pair(pair[1].trim().toInt(), pair[2].trim()))
        }
        //fuck it, radix sort
        val buckets = arrayOf(mutableListOf<Pair<Int, String>>(),mutableListOf<Pair<Int, String>>(),mutableListOf<Pair<Int, String>>())
        val positions = mapOf("red" to 0, "green" to 1, "blue" to 2)
        for(p in dicePairs){
            buckets[positions[p.second]!!].add(p)
        }
        for(bucket in buckets){
            bucket.sortByDescending { pair -> pair.first }
        }
        val product = buckets[0][0].first * buckets[1][0].first*buckets[2][0].first
        sum+=product
    }
    println("sum: $sum")
}
