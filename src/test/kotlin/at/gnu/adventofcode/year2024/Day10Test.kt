package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day10Test {

    private val map = listOf("89010123", "78121874", "87430965", "96549874", "45678903", "32019012", "01329801",
        "10456732")

    private val test = mapOf(
        Day10::part1 to 36,
        Day10::part2 to 81
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day10 = Day10(map)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day10) }
            println("Day10::${function.name}: ${map.size}x${map.first().length} map -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
