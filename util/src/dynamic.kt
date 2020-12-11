fun <T, R> dynamic(values: List<T>, calc: (i: Int, memory: List<R>) -> R): R {
    val memory = mutableListOf<R>()
    for (i in values.indices) {
        memory.add(calc(i, memory))
    }
    return memory.last()
}
