import java.io.FileReader

class Card(val value:String){


}
class Hand(val cards: MutableList<Card>){
    fun stringRepresentation():String{
        var s = ""
        for(c in cards){ s+=c.value }
        return s
    }

    override fun toString(): String {
        return stringRepresentation()
    }
}
fun sortByRadix(radix:Int, arr: MutableList<Hand>): MutableList<Hand> {
    //ordering from largest to smallest!
    val bucketIndexes :Map<String, Int> = mapOf("T" to 4, "J" to 3, "Q" to 2, "K" to 1, "A" to 0)
    val buckets : Array<MutableList<Hand>> = Array(13) { mutableListOf() }

    for(hand:Hand in arr){
        if(hand.cards[radix].value.toIntOrNull()!= null){
            buckets[14 - hand.cards[radix].value.toInt()].add(hand)
        }else{//billedkort
            buckets[bucketIndexes[hand.cards[radix].value]!!].add(hand)
        }
    }
    //unpack
    var n = 0
    for(bucket in buckets){
        for(hand in bucket){
            arr[n] = hand
            n++
        }
    }
    return arr
}

fun sortByStrength(arr: MutableList<Hand>): MutableList<Hand> {
    //!ordering from strongest to weakest
    val buckets : Array<MutableList<Hand>> = Array(7) { mutableListOf() }
    //buck-it, we ball
    for(hand in arr){
        val stringRep = hand.stringRepresentation()
        val set = stringRep.toSet()
        val charCount = {c:Char -> stringRep.count {k -> k==c}}
        if(set.size == 1){//five of a kind
            buckets[0].add(hand)
        }else if(set.size==2 && (charCount(set.first())==4 ||charCount(set.first())==1)){
            //bit of a mouthful, but: hand is four of a kind
            buckets[1].add(hand)
        }else if(set.size==2 && (charCount(set.first())==3 ||charCount(set.first())==2)){
            //same as before, but now Full House
            buckets[2].add(hand)
        }else if(set.size==3 && set.any { it-> charCount(it) ==3}){
            //woah buddy, this one is Three Of a Kind
            buckets[3].add(hand)
        }else if(set.size==3){
            //two pair
            buckets[4].add(hand)
        }else if(set.size==4){
            //one pair
            buckets[5].add(hand)
        }else{
            //lmao loser
            buckets[6].add(hand)
        }
    }
    //unpack
    var n = 0
    for(bucket in buckets){
        for(hand in bucket){
            arr[n] = hand
            n++
        }
    }
    return arr
}
fun main() {
    val reader = FileReader("input")
    val handBids : MutableMap<Hand, Int> = mutableMapOf()
    var hands :MutableList<Hand> = mutableListOf()
    reader.forEachLine {
        val hStr = it.split(" ")[0]
        val cards = mutableListOf<Card>()
        for(c in hStr){
            cards.add(Card(c.toString()))
        }
        val hand = Hand(cards)
        hands.add(hand)
        handBids[hand] = it.split(" ")[1].toInt()
    }
    for(i in 4 downTo 0){
        hands = sortByRadix(i, hands)
    }
    hands = sortByStrength(hands)

    //hands is now sorted from strongest to weakest. opposite of what makes most sense for the task, but whatever
    var sum = 0
    for(i in hands.indices){
        val hand = hands[i]
        sum += handBids[hand]!!*(hands.size-i)
    }
    println("sum: $sum")
}
