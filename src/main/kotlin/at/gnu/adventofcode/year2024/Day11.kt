package at.gnu.adventofcode.year2024

import kotlin.math.pow

class Day11(val numbers: List<Long>) {

    fun part1(): Long {
        var numbers = numbers.toMutableList()
        repeat(25) {
            val newNumbers = mutableListOf<Long>()
            for (number in numbers) {
                val length = number.toString().length
                when {
                    (number == 0L) -> newNumbers += 1L
                    ((length % 2) == 0) -> {
                        val bla = 10.0.pow(length / 2).toLong()
                        newNumbers += (number / bla)
                        newNumbers += (number % bla)
                    }
                    else -> newNumbers += (number * 2024L)
                }
            }
//            println("${++count} ${newNumbers}")
            numbers = newNumbers
        }
        return numbers.size.toLong()
    }

    fun part2(): Long {
        return 0L
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
