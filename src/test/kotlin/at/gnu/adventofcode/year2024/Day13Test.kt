package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day13Test {

    private val rules = """
        Button A: X+94, Y+34
        Button B: X+22, Y+67
        Prize: X=8400, Y=5400

        Button A: X+26, Y+66
        Button B: X+67, Y+21
        Prize: X=12748, Y=12176

        Button A: X+17, Y+86
        Button B: X+84, Y+37
        Prize: X=7870, Y=6450

        Button A: X+69, Y+23
        Button B: X+27, Y+71
        Prize: X=18641, Y=10279
    """.trimIndent().split("\n\n").map { it.split("\n") }

    private val test = mapOf(
        Day13::part1 to 480L,
        Day13::part2 to 875318608908L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day13 = Day13(rules)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day13) }
            println("Day13::${function.name}: ${rules.size} rules -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
