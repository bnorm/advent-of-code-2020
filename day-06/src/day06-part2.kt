fun main() {
    val sample = """
        abc

        a
        b
        c

        ab
        ac

        a
        a
        a
        a

        b
    """.trimIndent()

//    val input = sample.splitToSequence("\n\n")
    val input = readResourceText("input.txt").splitToSequence("\n\n")

    val groups = input
        .map { it.trim().splitToSequence("\n").map { it.toSet() } }
        .map { it.reduce { next, acc -> next intersect acc } }
        .toList()

    println("part2 = $groups")
    println("part2 = ${groups.map { it.size }.sum()}")
}
