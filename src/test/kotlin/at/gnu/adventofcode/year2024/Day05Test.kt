package at.gnu.adventofcode.year2024

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class Day05Test {

    private val rules = listOf("47|53", "97|13", "97|61", "97|47", "75|29", "61|13", "75|53", "29|13", "97|29",
        "53|29", "61|53", "97|53", "61|29", "47|13", "75|47", "97|75", "47|61", "75|61", "47|29", "75|13", "53|13")
    private val updates = listOf("75,47,61,53,29", "97,61,53,29,13", "75,29,13", "75,97,47,61,53", "61,13,29",
        "97,13,75,29,47")

    private val test = mapOf(
        Day05::part1 to 143,
        Day05::part2 to 123
    )

    @Test @ExperimentalTime
    fun testMySolution() {
        val day05 = Day05(rules, updates)
        for (function in test.keys) {
            val (result, time) = measureTimedValue { function(day05) }
            println("Day05::${function.name}: ${rules.size} rules, ${updates.size} updates -> $result [$time]")
            assertEquals(test[function], result)
        }
    }
}
