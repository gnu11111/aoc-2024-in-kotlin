package at.gnu.adventofcode.year2024

import kotlin.math.abs

class Day01(lines: List<String>) {

    private val locationIDs = lines.map { it.split("\\s+".toRegex()) }
        .map { it.first().toInt() to it.last().toInt() }.unzip()

    fun part1(): Int =
        (locationIDs.first.sorted() zip locationIDs.second.sorted()).sumOf { abs(it.first - it.second) }

    fun part2(): Int =
        locationIDs.first.sorted().sumOf { left -> left * locationIDs.second.count { it == left } }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day01.txt"
    }
}

fun main() {
    val day01 = Day01(Day01::class.java.getResource(Day01.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day01::part1 -> ${day01.part1()}")
    println("Day01::part2 -> ${day01.part2()}")
}
