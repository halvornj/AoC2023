import java.io.FileReader

fun main() {
    val reader = FileReader("input")
    var sum = 0

    val matrix = mutableListOf<String>()
    reader.forEachLine {
        matrix.addLast(".$it.")
    }
    matrix.addFirst(".".repeat(matrix[0].length))
    matrix.addLast((".").repeat(matrix[0].length))
    val regexNums = Regex("^[0-9]")

    for(i in 0..matrix.size-1){
        val row = matrix[i]
        for(j in 0..row.length-1){
            if(row[j]!= '*'){continue}
            if(!regexNums.containsMatchIn("${matrix[i-1].subSequence(j-1,j+1)}${row[j-1]}${row[j+1]}${matrix[i+1].subSequence(j-1,j+1)}")){
                continue
            }
            sum += findRatio(matrix[i-1], row, matrix[i+1], j)
        }
    }
}
fun findRatio(upper:String, row:String, lower:String, i:Int):Int{
    val regexNums = Regex("^[0-9]")


    assert(row[i]=='*')
    val upperN = findNum(upper,i)
    val lowerN = findNum(lower,i)

    var leftN = 1
    var rightN = 1
    if(regexNums.containsMatchIn(row[i-1].toString())){
        var leftP = i-2
        while(regexNums.containsMatchIn(row[leftP].toString())){
            leftP-=1
        }
        leftN = row.substring(leftP, i-1).toInt()
    }
    val predicate: (Int) -> Boolean = {it > 1}
    if(listOf(upperN, lowerN, leftN, rightN).count(predicate)>2){
        return 0
    }
    val ratio = upperN * lowerN * leftN * rightN
    return ratio
}
fun findNum(str:String,i:Int):Int{
    val regexNums = Regex("^[0-9]")

    var n = 1
    var leftP: Int = i
    var rightP : Int = i
    for(j in i-1..i+1){
        if(regexNums.containsMatchIn(str[j].toString())){
            leftP = j
            rightP = j

        }
    }
    return n
}