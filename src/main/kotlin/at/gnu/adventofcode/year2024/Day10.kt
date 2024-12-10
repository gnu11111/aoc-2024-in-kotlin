package at.gnu.adventofcode.year2024

class Day10(private val map: List<String>) {

    data class Location(val x: Int, val y: Int)

    private val heads = map.flatMapIndexed { y, row ->
        row.mapIndexedNotNull { x, c -> if (c == '0') Location(x, y) else null }
    }


    fun part1(): Int =
        heads.sumOf { it.countPaths().distinct().size }

    fun part2(): Int =
        heads.sumOf { it.countPaths().size }


    private fun Location.countPaths(height: Int = 0, ends: List<Location> = emptyList()): List<Location> =
        when {
            (height == 9) -> ends + this
            else -> neighbors(height + 1).fold(emptyList()) { acc, it -> acc + it.countPaths(height + 1, ends) }
        }

    private fun Location.neighbors(height: Int): List<Location> {
        val neighbors = mutableListOf<Location>()
        if (heightAt(x + 1, y) == height) neighbors += Location(x + 1, y)
        if (heightAt(x - 1, y) == height) neighbors += Location(x - 1, y)
        if (heightAt(x, y + 1) == height) neighbors += Location(x, y + 1)
        if (heightAt(x, y - 1) == height) neighbors += Location(x, y - 1)
        return neighbors
    }

    private fun heightAt(x: Int, y: Int): Int =
        map.getOrNull(y)?.getOrNull(x)?.digitToInt() ?: -1

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day10.txt"
    }
}

fun main() {
    val day10 = Day10(Day10::class.java.getResource(Day10.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day10::part1 -> ${day10.part1()}")
    println("Day10::part2 -> ${day10.part2()}")
}
