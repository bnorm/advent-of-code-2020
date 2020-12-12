import kotlin.math.abs
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.round
import kotlin.math.sin
import kotlin.math.sqrt

fun main() {
    val sample = """
        F10
        N3
        F7
        R90
        F11
    """.trimIndent()

//    val input = sample.trim().splitToSequence("\n")
    val input = readResourceText("input.txt").trim().splitToSequence("\n")

    val commands = input.map {
        val (cmd, amount) = it.extract("([A-Z]+)(\\d+)")!!
        cmd to amount.toInt()
    }

    val directions = listOf("N", "E", "S", "W")

    run {
        var curr = "E"
        var x = 0
        var y = 0
        for ((cmd, amount) in commands) {
            when (if (cmd == "F") curr else cmd) {
                "R" -> curr = directions[(directions.size + directions.indexOf(curr) + (amount / 90)) % directions.size]
                "L" -> curr = directions[(directions.size + directions.indexOf(curr) - (amount / 90)) % directions.size]
                "N" -> y += amount
                "E" -> x += amount
                "S" -> y -= amount
                "W" -> x -= amount
            }
        }
        println("part1=${abs(x) + abs(y)}")
    }

    run {
        var wX = 10
        var wY = 1
        var sX = 0
        var sY = 0
        for ((cmd, amount) in commands) {
            println("$cmd $amount")
            when (cmd) {
                "R" -> {
                    val angle = atan2(wY.toDouble(), wX.toDouble()) - Math.toRadians(amount.toDouble())
                    val dist = sqrt(wY.toDouble() * wY.toDouble() + wX.toDouble() * wX.toDouble())
                    wX = round(dist * cos(angle)).toInt()
                    wY = round(dist * sin(angle)).toInt()
                }
                "L" -> {
                    val angle = atan2(wY.toDouble(), wX.toDouble()) + Math.toRadians(amount.toDouble())
                    val dist = sqrt(wY.toDouble() * wY.toDouble() + wX.toDouble() * wX.toDouble())
                    wX = round(dist * cos(angle)).toInt()
                    wY = round(dist * sin(angle)).toInt()
                }
                "N" -> wY += amount
                "E" -> wX += amount
                "S" -> wY -= amount
                "W" -> wX -= amount
                "F" -> {
                    sX += wX * amount
                    sY += wY * amount
                }
            }
            println("$wX, $wY & $sX, $sY")
        }
        println("part2=${abs(sX) + abs(sY)}")
    }
}
