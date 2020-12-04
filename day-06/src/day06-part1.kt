fun main() {
    val sample = """
    """.trimIndent()

//    val input = sample.splitToSequence("\n")
    val input = readResourceText("input.txt").splitToSequence("\n")
    val values = input.filter { it.isNotBlank() }.toList()
}
