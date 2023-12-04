import java.io.FileReader
import java.util.HashMap
fun main() {
    val reader = FileReader("input")
    //cardTallies[cardNumber] -> number of cards with that number accumulated
    val cardTallies= HashMap<Int, Int>().withDefault { 0 }
    reader.forEachLine {
        val line: List<String> = it.trim().split("|",":")
        val winning = line[1].trim().split(" ").toMutableList()
        val nums = line[2].trim().split(" ")
        val n = countPoints(winning, nums)
        val lineInfo = line[0].split(" ")
        val currentCardNumber = lineInfo[lineInfo.size-1].toInt()
        for(i in 0..cardTallies.getValue(currentCardNumber)){
            for(j in currentCardNumber+1..currentCardNumber+n) {
                cardTallies[j] = cardTallies.getValue(j)+1
            }
        }
        cardTallies[currentCardNumber] = cardTallies.getValue(currentCardNumber)+1
    }
    println("total Cards: ${cardTallies.values.sum()}")
}
fun countPoints(winning: MutableList<String>, nums:List<String>):Int{
    var matches = 0
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
    return matches
}