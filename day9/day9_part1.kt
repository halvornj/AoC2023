package day9

import java.io.FileReader

fun main() {
    val reader = FileReader("input")
    var sum = 0
    reader.forEachLine {
        val l = it.split(" ").map { s ->s.toInt() }
        sum+= l.last() +findListWithNext(l).last()
    }
    println("sum: $sum")
}

fun findListWithNext(list: List<Int>):List<Int>{
    if(list.all { i ->i==0 }){return list}
    val newList:MutableList<Int> = mutableListOf()
    for(i in 0..<list.size-1){
        newList.add(list[i+1]-list[i])
    }
    newList.add(newList.last()+findListWithNext(newList).last())
    return newList
}