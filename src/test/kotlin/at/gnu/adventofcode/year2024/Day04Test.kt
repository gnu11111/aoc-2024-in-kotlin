package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day04Test {

    private val wordSearch = listOf("MMMSXXMASM", "MSAMXMSMSA", "AMXSXMAAMM", "MSAMASMSMX", "XMASAMXAMM", "XXAMMXXAMA",
        "SMSMSASXSS", "SAXAMASAAA", "MAMMMXMMMM", "MXMXAXMASX")

    private val test = mapOf(
        Day04::part1 to 18,
        Day04::part2 to 9
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day04 = Day04(wordSearch)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day04) }
            println("Day04::${function.name}: ${wordSearch.size} entries -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
