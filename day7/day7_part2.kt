import java.io.FileReader

fun sortByRadixJ(radix:Int, arr: MutableList<Hand>): MutableList<Hand> {
    //ordering from largest to smallest!
    val bucketIndexes :Map<String, Int> = mapOf("T" to 3, "J" to 12, "Q" to 2, "K" to 1, "A" to 0)
    val buckets : Array<MutableList<Hand>> = Array(13) { mutableListOf() }

    for(hand:Hand in arr){
        if(hand.cards[radix].value.toIntOrNull()!= null){
            buckets[13 - hand.cards[radix].value.toInt()].add(hand)
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
fun sortByStrengthJ(arr: MutableList<Hand>): MutableList<Hand> {
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
            //four of a kind?
            if(set.contains('J')){
                buckets[0].add(hand)
            }
            else{buckets[1].add(hand)}
        }else if(set.size==2){
            //Full House?
            if(set.contains('j')){buckets[0].add(hand)}
            else{buckets[2].add(hand)}
        }else if(set.size==3 && set.any { it-> charCount(it) ==3}){
            //Three Of a Kind?
            if(charCount('J') ==1||charCount('J')==3){ buckets[1].add(hand) }
            else{buckets[3].add(hand)}
        }else if(set.size==3){
            //two pair?
            if(charCount('J')==1){buckets[2].add(hand)}
            else if(set.contains('J')){buckets[1].add(hand)}
            else{buckets[4].add(hand)}
        }else if(set.size==4){
            //one pair?

            if(set.contains('J')){buckets[3].add(hand)}
            else{buckets[5].add(hand)}
        }else{
            //lmao loser?
            if(set.contains('J')){buckets[5].add(hand) }
            else{buckets[6].add(hand)}
        }
    }
    //temp

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
    val reader = FileReader("test")
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
        hands = sortByRadixJ(i, hands)
    }
    hands = sortByStrengthJ(hands)

    //hands is now sorted from strongest to weakest. opposite of what makes most sense for the task, but whatever
    var sum = 0
    for(i in hands.indices){
        val hand = hands[i]
        sum += handBids[hand]!!*(hands.size-i)
    }
    println("sum: $sum")
}