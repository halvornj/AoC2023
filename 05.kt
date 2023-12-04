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
    val searchRegex = Regex("[^0-9.]")
    for (i in 0..matrix.size - 1) {
        val row = matrix[i]
        var numStart: Int? = null
        var numStop: Int? = null
        for (j in 0..row.length - 1) {

            if (numStart == null && !regexNums.containsMatchIn(row[j].toString())) {
                continue
            } else if (numStart == null) {
                numStart = j
            } else if (regexNums.containsMatchIn(row[j].toString())) {
                continue
            } else {
                //numStart != null && !regexNums.containsMatchIn(row[j].toString()
                //do shit here
                numStop = j-1
                var isValid = false
                val topString = matrix[i - 1].substring(numStart - 1, numStop + 2)
                val bottomString = matrix[i + 1].substring(numStart - 1, numStop + 2)
                if (searchRegex.containsMatchIn(topString) || searchRegex.containsMatchIn(bottomString)) {
                    isValid = true

                }
                if (searchRegex.containsMatchIn(row[numStart - 1].toString()) || searchRegex.containsMatchIn(row[numStop + 1].toString())) {
                    isValid = true
                }
                if (isValid) {
                    sum += row.substring(numStart, numStop+1).toInt()

                }
                numStart = null
                numStop = null
            }
        }
    }
    println("sum: $sum")
}