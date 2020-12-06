fun String.translate(fromChars: String, toChars: String) = buildString {
    val translation = fromChars.zip(toChars).toMap()
    for (c in this@translate) {
        append(translation[c] ?: c)
    }
}
