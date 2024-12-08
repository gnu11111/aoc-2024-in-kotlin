package at.gnu.adventofcode.year2024

class Day06(private val map: List<String>) {

    enum class Direction(val x: Int, val y: Int) { NORTH(0, -1), EAST(1, 0), SOUTH(0, 1), WEST(-1, 0) }

    private var guardY = map.indexOfFirst { it.contains("^") }
    private var guardX = map[guardY].indexOf("^")


    fun part1(): Int {
        val visited = patrol()
        return visited.size
    }

    fun part2(): Int {
        val visited = patrol().keys
        var sum = 0
        for (cell in visited) {
            val newMap = map.mapIndexed { y, line ->
                line.mapIndexed { x, c ->
                    if (!((x == guardX) && (y == guardY)) && (x == cell.first) && (y == cell.second)) '#' else c
                }.joinToString("")
            }
            if (patrol(newMap).isEmpty())
                sum++
        }
        return sum
    }

    private fun patrol(map: List<String> = this.map): Map<Pair<Int, Int>, Direction> {
        var direction = Direction.NORTH
        var x = guardX
        var y = guardY
        val visited = mutableMapOf<Pair<Int, Int>, Direction>()
        while (true) {
            val nextX = x + direction.x
            val nextY = y + direction.y
            if (((nextX < 0) || (nextX >= map[y].length) || (nextY < 0) || (nextY >= map.size)))
                break
            else if (map[nextY][nextX] == '#') {
                direction = when (direction) {
                    Direction.NORTH -> Direction.EAST
                    Direction.EAST -> Direction.SOUTH
                    Direction.SOUTH -> Direction.WEST
                    Direction.WEST -> Direction.NORTH
                }
            } else {
                x = nextX
                y = nextY
                if (visited.containsKey(x to y) && (visited[x to y] == direction))
                    return emptyMap()
                visited[x to y] = direction
            }
        }
        return visited
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day06.txt"
    }
}

fun main() {
    val day06 = Day06(Day06::class.java.getResource(Day06.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day06::part1 -> ${day06.part1()}")
    println("Day06::part2 -> ${day06.part2()}")
}
