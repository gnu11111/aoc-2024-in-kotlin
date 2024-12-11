package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day11Test {

    private val numbers = listOf(125L, 17L)

    private val test = mapOf(
        Day11::part1 to 55312L,
        Day11::part2 to 65601038650482L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day11 = Day11(numbers)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day11) }
            println("Day11::${function.name}: ${numbers.size} numbers -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
