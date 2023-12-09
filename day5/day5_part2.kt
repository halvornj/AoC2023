import java.awt.font.NumericShaper
import java.io.FileReader
import java.nio.CharBuffer
import kotlin.math.abs
import kotlin.math.max

fun main() {
    val reader = FileReader("input")
    val seeds:MutableList<LongRange> = mutableListOf()
    var map : MutableMap<Pair<Long, Long>,Long> = HashMap()
    val maps : MutableList<MutableMap<Pair<Long, Long>,Long>> = mutableListOf()
    var max:Long = 0
    reader.forEachLine {
        if (seeds.isEmpty()){
            val nums =it.subSequence(7,it.length).split(" ").map { s ->s.toLong()}.toMutableList()
            for(i in 0..<nums.size step 2){
                max = max(max(max, nums[i]), nums[i+1])
                seeds.add(nums[i]..nums[i]+nums[i+1])
            }
            return@forEachLine //cringe continue
        }
        if(it==""){
            //i ain't doin nuffin
            return@forEachLine
        }
        if(it.contains(':')){
            //start of a new map. kill the old one.
            maps.add(map)
            map = HashMap()
            return@forEachLine
        }
        //invariant: this is a map population line
        val line = it.split(" ").map{s->s.toLong()}
        map[Pair(line[0], line[1])] = line[2]
    }
    maps.add(map)
    maps.removeFirst()

    //what if we go backward? eh?
    for(i:Long in 0..max){
        var out: Long = i
        for(currentMap in maps.reversed()){
           out = findBackwardsNum(out, currentMap)
        }
        for(r in seeds){
            if(out in r){
                println("lowest output: $i")
                return
            }
        }
    }
    println("shit")

}

fun findBackwardsNum(i:Long, map:Map<Pair<Long,Long>,Long>):Long{
    var num = i
    for(p in map.keys){
        if(p.first <= i && i < p.first+map[p]!!){
            num = p.second+abs(i-p.first)
            break
        }
    }
    return num
}