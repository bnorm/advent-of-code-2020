fun main() {
    val sample = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent()

    assert(permutations(listOf(1, 2, 3)) == 2)
    assert(permutations(listOf(1, 2, 3, 4)) == 4)
    assert(permutations(listOf(1, 2, 3, 4, 5)) == 7)
    assert(permutations(listOf(1, 2, 3, 4, 5, 6)) == 13)
    assert(permutations(listOf(1, 2, 3, 4, 5, 6, 7)) == 24)

//    val input = sample.trim().splitToSequence("\n")
    val input = readResourceText("input.txt").trim().splitToSequence("\n")

    val values = sequence {
        val adapters = input.map { it.toInt() }.sorted()
        yield(0)
        yieldAll(adapters)
        yield(adapters.last() + 3)
    }.toList()

    val part1 = values
        .windowed(2) {
            val (prev, next) = it
            next - prev
        }
        .groupingBy { it }
        .eachCount()

    println("part1=${part1[1]!! * part1[3]!!}")

    var count = 1L
    var start = 0
    var end = 0
    while (end < values.size - 1) {
        end++
        if (values[end] - values[end - 1] >= 3) {
            // Extract ranges with adjacent numbers, these are the only ranges which can actually be changed
            // E.g., if you have the list [1,2,3,6,7,8], break it into [1,2,3] and [6,7,8] and calculate
            // permutations on each separately
            count *= permutations(values.subList(start, end))
            start = end
        }
    }
    println("part2=$count")
}

fun permutations(values: List<Int>): Int {
    var count = 1
    for (i in 1 until values.size - 1) {
        // add permutations without the i-1..i elements
        count += permutations(values.subList(i + 1, values.size))
    }
    for (i in 1 until values.size - 2) {
        // add permutations without the i-1..i+1 elements
        count += permutations(values.subList(i + 2, values.size))
    }
    return count
}
