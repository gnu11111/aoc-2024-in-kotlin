package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day12Test {

    private val garden = listOf("RRRRIICCFF", "RRRRIICCCF", "VVRRRCCFFF", "VVRCCCJFFF", "VVVVCJJCFE", "VVIVCCJJEE",
        "VVIIICJJEE", "MIIIIIJJEE", "MIIISIJEEE", "MMMISSJEEE")

    private val test = mapOf(
        Day12::part1 to 1930,
        Day12::part2 to 1206
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day12 = Day12(garden)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day12) }
            println("Day12::${function.name}: ${garden.size} entries -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
