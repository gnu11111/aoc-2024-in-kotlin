package at.gnu.adventofcode.year2024

import kotlin.math.pow

class Day11(private val numbers: List<Long>) {

    fun part1(): Long =
        countStones(numbers, blinks = 25)

    fun part2(): Long =
        countStones(numbers, blinks = 75)


    private fun countStones(numbers: List<Long>, blinks: Int = 25): Long =
        numbers.sumOf { dfs(number = it, max = blinks) }

    private fun dfs(number: Long, i: Int = 1, max: Int = 25, memo: MutableMap<String, Long> = mutableMapOf()): Long =
        if (i > max)
            1L
        else
            memo.getOrPut("$i:$number") {
                val length = number.toString().length
                when {
                    (number == 0L) -> dfs(1L, i + 1, max, memo)
                    ((length % 2) == 0) -> {
                        val divisor = 10.0.pow(length / 2).toLong()
                        dfs(number / divisor, i + 1, max, memo) + dfs(number % divisor, i + 1, max, memo)
                    }
                    else -> dfs(number * 2024L, i + 1, max, memo)
                }
            }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day11.txt"
    }
}

fun main() {
    val day11 = Day11(Day11::class.java.getResource(Day11.RESOURCE)!!.readText().trim().split(" ").map(String::toLong))
    println("Day11::part1 -> ${day11.part1()}")
    println("Day11::part2 -> ${day11.part2()}")
}
