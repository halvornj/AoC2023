import java.io.FileReader


fun main() {
    var sum: Int = 0
    val reader = FileReader("input1")
    val nums = mapOf("one" to "1", "two" to "2", "three" to "3", "four" to "4", "five" to "5", "six" to "6", "seven" to "7", "eight" to "8", "nine" to "9")

    reader.forEachLine {
        val input = it.lowercase()
        var left = 0
        var right : Int = input.length -1
        while(input[left].digitToIntOrNull() == null){
            left++
            if(left > input.length-1){
                left = input.length-1
                break
            }
        }
        while(input[right].digitToIntOrNull() == null){
            right--
            if(right <0){
                right = 0
                break
            }
        }

        var fStr: String = ""
        var lStr : String = ""
        for(num: String in nums.keys){
            val first : Int = input.indexOf(num,0)
            val last:Int = input.lastIndexOf(num)

            if(first < left && first!=-1){
                left = first
                fStr = num
            }
            if(last> right){
                right=last
                lStr = num
            }
        }

        sum += (""+(nums[fStr] ?: input[left]) + (nums[lStr]?:input[right])).toInt()
    }
    println(sum)
}