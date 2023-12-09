package day8
import java.io.FileReader

//could do a whole thing with a node-class, but:
//map maps node to a pair of its left and right nodes.
//this has the advantage of 'map[node].first' is the left of node, and .second is right
fun main() {
    val reader = FileReader("input")
    var instructions = ""
    val map : MutableMap<String, Pair<String, String>> = mutableMapOf()
    val startNodes: MutableList<String> = mutableListOf()
    reader.forEachLine {
        if(instructions == ""){
            instructions = it
            return@forEachLine
        }
        if(it==""){ return@forEachLine }
        val line = it.split(" = ")

        val neighbours = line[1].replace(Regex("[()]"),"").split(", ")
        map[line[0]] = Pair(neighbours[0], neighbours[1])
        if(line[0].last() =='A'){ startNodes.add(line[0]) }

    }

    var instructionCounter:Long = 0
    var isValid = false
    outer@ while(!isValid){
        for(i in startNodes.indices){
           startNodes[i] = if(instructions[(instructionCounter%instructions.length).toInt()]=='L'){
                map[startNodes[i]]!!.first
            } else{
                map[startNodes[i]]!!.second
            }
        }
        for(i in startNodes.indices){
            if(startNodes[i].last()!='Z'){
                instructionCounter++
                continue@outer
            }
        }
        isValid = true
    }
    instructionCounter++
    println("instructionCounter: $instructionCounter")
}