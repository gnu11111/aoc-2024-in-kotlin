package at.gnu.adventofcode.year2024

class Day02(input: List<String>) {

    private val reports = input.map { report -> report.split(" ").map(String::toInt) }

    fun part1(): Int =
        reports.count(::isReportValid)

    fun part2(): Int =
        reports.count(::isReportValidWithDampener)

    private fun isReportValid(levels: List<Int>): Boolean =
        levels.zipWithNext().map { it.second - it.first }.let { deltas ->
            return ((deltas.all { it > 0 } || deltas.all { it < 0 }) && deltas.all { it in -3..3 })
        }

    private fun isReportValidWithDampener(levels: List<Int>): Boolean =
        levels.indices.any { i -> isReportValid(levels.filterIndexed { j, _ -> (j != i) }) }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day02.txt"
    }
}

fun main() {
    val day02 = Day02(Day02::class.java.getResource(Day02.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day02::part1 -> ${day02.part1()}")
    println("Day02::part2 -> ${day02.part2()}")
}
