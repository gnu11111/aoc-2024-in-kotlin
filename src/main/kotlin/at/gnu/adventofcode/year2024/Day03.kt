package at.gnu.adventofcode.year2024

class Day03(private val memory: String) {

    private val multiplication = """(mul)\((\d{1,3}),(\d{1,3})\)"""
    private val switchOn = """(do)\(\)"""
    private val switchOff = """(don't)\(\)"""

    fun part1(): Long =
        multiplication.toRegex().findAll(memory).fold(0L) { acc, instruction ->
            val (_, number1, number2) = instruction.destructured
            acc + (number1.toLong() * number2.toLong())
        }

    fun part2(): Long {
        var enabled = true
        return "$multiplication|$switchOn|$switchOff".toRegex().findAll(memory).asSequence().fold(0L) { acc, match ->
            val (_, number1, number2, switchOn, switchOff) = match.destructured
            when {
                switchOn.isNotBlank() -> { enabled = true; acc }
                switchOff.isNotBlank() -> { enabled = false; acc }
                else -> acc + if (enabled) (number1.toLong() * number2.toLong()) else 0L
            }
        }
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day03.txt"
    }
}

fun main() {
    val day03 = Day03(Day03::class.java.getResource(Day03.RESOURCE)!!.readText().trim())
    println("Day03::part1 -> ${day03.part1()}")
    println("Day03::part2 -> ${day03.part2()}")
}
