import java.io.FileReader
import java.nio.CharBuffer
import kotlin.math.abs

fun main() {
    val reader = FileReader("input")
    var seeds:MutableList<Long> = mutableListOf()
    var map : MutableMap<Pair<Long, Long>,Long> = HashMap()
    reader.forEachLine {
        if (seeds.isEmpty()){
            seeds=it.subSequence(7,it.length).split(" ").map { s ->s.toLong()}.toMutableList()
            return@forEachLine //cringe continue
        }
        if(it==""){
            //iterate over seeds, updating with the return from findCorrespondingNumber w built map
            for(i in 0..<seeds.size){
                seeds[i] = findCorrespondingNumber(seeds[i], map)
            }
            return@forEachLine
        }
        if(it.contains(':')){
            //start of a new map. kill the old one.
            map = HashMap()
            return@forEachLine
        }
        //invariant: this is a map population line
        val line = it.split(" ").map{s->s.toLong()}
        map[Pair(line[0], line[1])] = line[2]
    }
    //update through final map, due to structure of file
    for(i in 0..<seeds.size){
        seeds[i] = findCorrespondingNumber(seeds[i], map)
    }
    println("seeds: $seeds")
    println("solution: ${seeds.min()}")
}

fun findCorrespondingNumber(i:Long, map:Map<Pair<Long, Long>,Long>):Long{
    var num = i
    for(p in map.keys){
        if(p.second <= i && i < p.second+map[p]!!){
            num = p.first+(abs(i-p.second))
            break
        }
    }
    return num
}