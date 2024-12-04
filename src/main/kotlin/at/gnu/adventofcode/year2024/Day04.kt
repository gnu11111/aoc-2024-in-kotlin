package at.gnu.adventofcode.year2024

class Day04(private val wordSearch: List<String>) {

    fun part1(): Int {
        var count = 0
        for (y in wordSearch.indices)
            for (x in wordSearch[y].indices)
                if (wordSearch[y][x] == 'X') {
                    count += xmasAtPosition(x, y, 1, 0)
                    count += xmasAtPosition(x, y, -1, 0)
                    count += xmasAtPosition(x, y, 0, 1)
                    count += xmasAtPosition(x, y, 0, -1)
                    count += xmasAtPosition(x, y, 1, 1)
                    count += xmasAtPosition(x, y, 1, -1)
                    count += xmasAtPosition(x, y, -1, 1)
                    count += xmasAtPosition(x, y, -1, -1)
                }
        return count
    }

    fun part2(): Int {
        var count = 0
        for (y in wordSearch.indices)
            for (x in wordSearch[y].indices)
                if (wordSearch[y][x] == 'A')
                    count += xMasAtPosition(x, y)
        return count
    }

    private fun xmasAtPosition(startX: Int, startY: Int, dx: Int, dy: Int): Int {
        for (i in XMAS.indices) {
            val x = startX + (i * dx)
            val y = startY + (i * dy)
            if ((y < 0) || (y >= wordSearch.size) || (x < 0) || (x >= wordSearch[y].length)
                || (wordSearch[y][x] != XMAS[i]))
                return 0
        }
        return 1
    }

    private fun xMasAtPosition(x: Int, y: Int): Int =
        when {
            ((y < 1) || (y >= wordSearch.size - 1) || (x < 1) || (x >= wordSearch[y].length - 1)) -> 0
            (((wordSearch[y + 1][x + 1]) == 'M') && ((wordSearch[y - 1][x + 1]) == 'M')
                    && ((wordSearch[y - 1][x - 1]) == 'S') && ((wordSearch[y + 1][x - 1]) == 'S')) -> 1
            (((wordSearch[y + 1][x + 1]) == 'S') && ((wordSearch[y - 1][x + 1]) == 'S')
                    && ((wordSearch[y - 1][x - 1]) == 'M') && ((wordSearch[y + 1][x - 1]) == 'M')) -> 1
            (((wordSearch[y + 1][x + 1]) == 'M') && ((wordSearch[y - 1][x + 1]) == 'S')
                    && ((wordSearch[y - 1][x - 1]) == 'S') && ((wordSearch[y + 1][x - 1]) == 'M')) -> 1
            (((wordSearch[y + 1][x + 1]) == 'S') && ((wordSearch[y - 1][x + 1]) == 'M')
                    && ((wordSearch[y - 1][x - 1]) == 'M') && ((wordSearch[y + 1][x - 1]) == 'S')) -> 1
            else -> 0
        }

    companion object {
        const val XMAS = "XMAS"
        const val RESOURCE = "/adventofcode/year2024/Day04.txt"
    }
}

fun main() {
    val day04 = Day04(Day04::class.java.getResource(Day04.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day04::part1 -> ${day04.part1()}")
    println("Day04::part2 -> ${day04.part2()}")
}
