package at.gnu.adventofcode.year2024

class Day02(input: List<String>) {

    private val reports = input.map { report -> report.split(" ").map(String::toInt) }

    fun part1(): Int =
        reports.count(::checkReport)

    fun part2(): Int =
        reports.count(::checkReportGracefully)

    private fun checkReport(levels: List<Int>): Boolean {
        var sign = 0
        for (delta in levels.zipWithNext().map { it.second - it.first }) {
            if (sign == 0)
                sign = delta
            else if ((sign > 0) && (delta < 0) || (sign < 0) && (delta > 0))
                return false
            if ((delta == 0) || (delta > 3) || (delta < -3))
                return false
        }
        return true
    }

    private fun checkReportGracefully(levels: List<Int>): Boolean {
        for (i in levels.indices)
            if (checkReport(levels.filterIndexed { j, _ -> (j != i) }))
                return true
        return false
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day02.txt"
    }
}

fun main() {
    val day02 = Day02(Day02::class.java.getResource(Day02.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day02::part1 -> ${day02.part1()}")
    println("Day02::part2 -> ${day02.part2()}")
}
