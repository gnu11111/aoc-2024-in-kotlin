package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day14Test {

    private val robots = listOf("p=0,4 v=3,-3", "p=6,3 v=-1,-3", "p=10,3 v=-1,2", "p=2,0 v=2,-1", "p=0,0 v=1,3",
        "p=3,0 v=-2,-2", "p=7,6 v=-1,-3", "p=3,0 v=-1,-2", "p=9,3 v=2,3", "p=7,3 v=-1,2", "p=2,4 v=2,-3",
        "p=9,5 v=-3,-3")

    private val test = mapOf(
        Day14::part1 to 12L,
        Day14::part2 to 7051L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day14 = Day14(robots, width = 11, height = 7)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day14) }
            println("Day14::${function.name}: ${robots.size} robots -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
