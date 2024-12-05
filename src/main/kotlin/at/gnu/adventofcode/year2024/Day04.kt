package at.gnu.adventofcode.year2024

class Day04(private val wordSearch: List<String>) {

    private val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1, 1 to 1, 1 to -1, -1 to 1, -1 to -1)

    fun part1(): Int =
        wordSearch.indices.sumOf { y ->
            wordSearch[y].indices.sumOf { x ->
                if (wordSearch[y][x] == 'X')
                    directions.sumOf { xmasAtPosition(x, y, it.first, it.second) }
                else
                    0
            }
        }

    fun part2(): Int =
        wordSearch.indices.sumOf { y ->
            wordSearch[y].indices.sumOf { x ->
                if (wordSearch[y][x] == 'A')
                    xMasAtPosition(x, y)
                else
                    0
            }
        }

    private fun xmasAtPosition(startX: Int, startY: Int, dx: Int, dy: Int): Int =
        if (XMAS.indices.all {
            val x = startX + (it * dx)
            val y = startY + (it * dy)
            ((y >= 0) && (y < wordSearch.size) && (x >= 0) && (x < wordSearch[y].length)
                    && (wordSearch[y][x] == XMAS[it]))
        }) 1 else 0

    private fun xMasAtPosition(x: Int, y: Int): Int {
        if ((y > 0) && (y < wordSearch.size - 1) && (x > 0) && (x < wordSearch[y].length - 1)) {
            val letters = "MMSS"
            for (i in letters.indices) {
                if (((wordSearch[y + 1][x + 1]) == letters[i])
                    && ((wordSearch[y - 1][x + 1]) == letters[(i + 1) % letters.length])
                    && ((wordSearch[y - 1][x - 1]) == letters[(i + 2) % letters.length])
                    && ((wordSearch[y + 1][x - 1]) == letters[(i + 3) % letters.length]))
                    return 1
            }
        }
        return 0
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day04.txt"
        const val XMAS = "XMAS"
    }
}

fun main() {
    val day04 = Day04(Day04::class.java.getResource(Day04.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day04::part1 -> ${day04.part1()}")
    println("Day04::part2 -> ${day04.part2()}")
}
