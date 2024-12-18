package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day17Test {

    private val input1 = """
        Register A: 729
        Register B: 0
        Register C: 0

        Program: 0,1,5,4,3,0
    """.trimIndent().split("\n\n").map { it.split("\n") }

    private val input2 = """
        Register A: 2024
        Register B: 0
        Register C: 0
        
        Program: 0,3,5,4,3,0
    """.trimIndent().split("\n\n").map { it.split("\n") }

    private val test = mapOf(
        Day17::part1 to input1 to "4,6,3,5,6,3,5,2,1,0",
        Day17::part2 to input2 to "117440"
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for ((function, input) in test.keys) {
            val (result, time) = measureTimedValue { function.invoke(Day17(input.first(), input.last().first())) }
            println("Day17::${function.name}: 3 registers + program -> $result [${time}]")
            assertEquals(test[function to input], result)
        }
    }
}
