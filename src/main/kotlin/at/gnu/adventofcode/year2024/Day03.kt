package at.gnu.adventofcode.year2024

class Day03(private val memory: String) {

    private val commands = """(mul)\((\d{1,3}),(\d{1,3})\)|(do)\(\)|(don't)\(\)""".toRegex()

    fun part1(): Long =
        commands.findAll(memory).fold(0L) { acc, command ->
            val (multiplication, number1, number2) = command.destructured
            if (multiplication.isNotBlank()) (acc + (number1.toLong() * number2.toLong())) else acc
        }

    fun part2(): Long =
        commands.findAll(memory).fold(Pair(0L, true)) { acc, command ->
            val result = acc.first
            val enabled = acc.second
            val (_, number1, number2, switchOn, switchOff) = command.destructured
            when {
                switchOn.isNotBlank() -> result to true
                switchOff.isNotBlank() -> result to false
                enabled -> (result + (number1.toLong() * number2.toLong())) to true
                else -> result to false
            }
        }.first

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day03.txt"
    }
}

fun main() {
    val day03 = Day03(Day03::class.java.getResource(Day03.RESOURCE)!!.readText().trim())
    println("Day03::part1 -> ${day03.part1()}")
    println("Day03::part2 -> ${day03.part2()}")
}
