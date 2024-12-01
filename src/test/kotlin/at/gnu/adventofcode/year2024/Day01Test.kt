package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day01Test {

    private val locationIDs = listOf("3   4", "4   3", "2   5", "1   3", "3   9", "3   3")

    private val test = mapOf(
        Day01::part1 to locationIDs to 11,
        Day01::part2 to locationIDs to 31,
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for ((function, input) in test.keys) {
            val (result, time) = measureTimedValue { function(Day01(input)) }
            println("Day01::${function.name}: ${input.size}x2 location-ids -> $result [$time]")
            assertEquals(test[function to input], result)
        }
    }
}
