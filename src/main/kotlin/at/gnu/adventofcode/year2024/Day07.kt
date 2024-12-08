package at.gnu.adventofcode.year2024

import kotlin.math.pow

class Day07(equations: List<String>) {

    private val equations = equations.map { it.split(": ") }
        .associate { it.first().toLong() to it.last().split(" ").map(String::toInt) }

    fun part1(): Long =
        checkEquations()

    fun part2(): Long =
        checkEquations(operators = 3)

    private fun checkEquations(operators: Int = 2): Long {
        var sum = 0L
        for ((result, numbers) in equations)
            for (i in 0 until operators.toFloat().pow(numbers.size - 1).toInt())
                if (result == numbers.evaluate(result, createOperators(i, numbers.size, operators))) {
                    sum += result
                    break
                }
        return sum
    }

    private fun createOperators(i: Int, size: Int, amount: Int = 2): List<Char> {
        val operators = mutableListOf<Char>()
        var temp = i
        repeat(size) {
            operators += when {
                ((temp % amount) == 0) -> '+'
                ((temp % amount) == 1) -> '*'
                else -> '|'
            }
            temp /= amount
        }
        return operators
    }

    private fun List<Int>.evaluate(max: Long, operators: List<Char>): Long {
        var result = first().toLong()
        drop(1).zip(operators) { number, operator ->
            when (operator) {
                '+' -> result += number
                '*' -> result *= number
                else -> result = "$result$number".toLong()
            }
            if (result > max)
                return result
        }
        return result
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day07.txt"
    }
}

fun main() {
    val day07 = Day07(Day07::class.java.getResource(Day07.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day07::part1 -> ${day07.part1()}")
    println("Day07::part2 -> ${day07.part2()}")
}
