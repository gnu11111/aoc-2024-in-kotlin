package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day02Test {

    private val reports = listOf("7 6 4 2 1", "1 2 7 8 9", "9 7 6 2 1", "1 3 2 4 5", "8 6 4 4 1", "1 3 6 7 9")

    private val test = mapOf(
        Day02::part1 to 2,
        Day02::part2 to 4
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day02 = Day02(reports)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day02) }
            println("Day02::${function.name}: ${reports.size} reports -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
