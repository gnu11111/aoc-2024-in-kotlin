package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day09Test {

    private val compressedDiskMap = "2333133121414131402"

    private val test = mapOf(
        Day09::part1 to 1928L,
        Day09::part2 to 2858L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day09 = Day09(compressedDiskMap)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(day09) }
            println("Day09::${function.name}: ${compressedDiskMap.length} compressed map size -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
