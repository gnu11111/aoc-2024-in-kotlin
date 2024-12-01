package at.gnu.adventofcode.year2024

import kotlin.math.abs

class Day01(lines: List<String>) {

    private val locationIDs = lines.map { it.split("\\s+".toRegex()) }.map { it.first().toInt() to it.last().toInt() }


    fun part1(): Int {
        val left = locationIDs.map { it.first }.sorted()
        val right = locationIDs.map { it.second }.sorted()
        var bla = 0
        for ((l1, l2) in left zip right) {
            bla += abs(l2 - l1)
        }
        return bla
    }

    fun part2(): Int {
        val left = locationIDs.map { it.first }.sorted()
        val right = locationIDs.map { it.second }.sorted()
        var bla = 0
        for (l1 in left) {
            bla += (l1 * right.count { it == l1 })
        }
        return bla
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day01.txt"
    }
}

fun main() {
    val day01 = Day01(Day01::class.java.getResource(Day01.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day01::part1 -> ${day01.part1()}")
    println("Day01::part2 -> ${day01.part2()}")
}
