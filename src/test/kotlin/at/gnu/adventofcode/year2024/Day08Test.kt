package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day08Test {

    private val antennas = listOf("............", "........0...", ".....0......", ".......0....", "....0.......",
        "......A.....", "............", "............", "........A...", ".........A..", "............", "............")

    private val test = mapOf(
        Day08::part1 to 14,
        Day08::part2 to 34
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day08 = Day08(antennas)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day08) }
            println("Day08::${function.name}: ${antennas.size} lines  -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
