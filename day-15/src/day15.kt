fun main() {
    val sample = """
        0,3,6
    """.trimIndent()

//    val input = sample.trim().splitToSequence(",")
    val input = readResourceText("input.txt").trim().splitToSequence(",")

    val startingNumbers = input.map { it.toLong() }

    val sequence = sequence {
        val memory0 = mutableMapOf<Long, Int>()
        val memory1 = mutableMapOf<Long, Int>()
        var i = 0
        fun commitToMemory(n: Long) {
            val prev = memory0[n]
            memory0[n] = i++
            if (prev != null) memory1[n] = prev
        }

        for (n in startingNumbers) {
            yield(n)
            commitToMemory(n)
        }

        var prev = startingNumbers.last()
        while (true) {
            // latest index is always (i - 1) because it was the previous value
            val n = ((i - 1) - (memory1[prev] ?: (i - 1))).toLong()
            yield(n)
            commitToMemory(n)
            prev = n
        }
    }

    val part1 = sequence
        .take(2020)
        .last()
    println("part1=$part1")

    val part2 = sequence
        .take(30_000_000)
        .last()
    println("part1=$part2")
}
