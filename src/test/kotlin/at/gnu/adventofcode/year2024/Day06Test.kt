package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day06Test {

    private val map = listOf("....#.....", ".........#", "..........", "..#.......", ".......#..", "..........",
        ".#..^.....", "........#.", "#.........", "......#...")

    private val test = mapOf(
        Day06::part1 to 41,
        Day06::part2 to 6
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day06 = Day06(map)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day06) }
            println("Day06::${function.name}: ${map.size}x${map.first().length} map -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
