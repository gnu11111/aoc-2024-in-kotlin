package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day07Test {

    private val equations = listOf("190: 10 19", "3267: 81 40 27", "83: 17 5", "156: 15 6", "7290: 6 8 6 15",
        "161011: 16 10 13", "192: 17 8 14", "21037: 9 7 18 13", "292: 11 6 16 20")

    private val test = mapOf(
        Day07::part1 to 3749L,
        Day07::part2 to 11387L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day07 = Day07(equations)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day07) }
            println("Day07::${function.name}: ${equations.size} entries -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
