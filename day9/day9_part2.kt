
package day9

import java.io.FileReader

fun main() {
    val reader = FileReader("input")
    var sum = 0
    reader.forEachLine {
        val l = it.split(" ").map { s ->s.toInt() }
        sum+= l.first() - findListWithPrevious(l).first()
    }
    println("sum: $sum")
}

fun findListWithPrevious(list: List<Int>):List<Int>{
    if(list.all { i ->i==0 }){return list}
    val newList:MutableList<Int> = mutableListOf()
    for(i in 0..<list.size-1){
        newList.add(list[i+1]-list[i])
    }
    newList.add(0,newList.first()- findListWithPrevious(newList).first())
    return newList
}