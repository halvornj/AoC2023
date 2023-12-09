import java.io.FileReader

//could do a whole thing with a node-class, but:
//map maps node to a pair of its left and right nodes.
//this has the advantage of 'map[node].first' is the left of node, and .second is right
fun main() {
    val reader = FileReader("input")
    var instructions = ""
    val map : MutableMap<String, Pair<String, String>> = mutableMapOf()
    reader.forEachLine {
        if(instructions == ""){
            instructions = it
            return@forEachLine
        }
        if(it==""){ return@forEachLine }
        val line = it.split(" = ")


        val neighbours = line[1].replace(Regex("[()]"),"").split(", ")
        map[line[0]] = Pair(neighbours[0], neighbours[1])

    }

    var nodeStr:String = "AAA"
    var instructionCounter = 0

    while(nodeStr != "ZZZ"){
        nodeStr = if(instructions[instructionCounter%instructions.length]=='L'){
            map[nodeStr]!!.first
        } else{
            map[nodeStr]!!.second
        }
        instructionCounter++
    }
    println("instructionCounter: $instructionCounter")
}