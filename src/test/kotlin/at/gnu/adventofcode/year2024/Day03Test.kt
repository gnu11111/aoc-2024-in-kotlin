package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day03Test {

    private val memory = listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))",
        "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")

    private val test = mapOf(
        Day03::part1 to memory[0] to 161L,
        Day03::part2 to memory[1] to 48L
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function.first.invoke(Day03(function.second)) }
            println("Day03::${function.first.name}: ${function.second.length} characters -> $result [${time}]")
            assertEquals(test[function], result)
        }
    }
}
