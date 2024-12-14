package at.gnu.adventofcode.year2024

class Day12(private val garden: List<String>) {

    data class Location(val x: Int, val y: Int)

    fun part1(): Int {
        val regions = findRegions()
        var price = 0
        for ((location, region) in regions) {
            val plant = plantAt(location.x, location.y)
            var fences = region.size * 4
            for (plot in region)
                fences -= countNeighbors(plot.x, plot.y, plant)
            price += (fences * region.size)
        }
        return price
    }

    fun part2(): Int {
        val regions = findRegions()
        var price = 0
        for ((location, region) in regions) {
            val plant = plantAt(location.x, location.y)
            val corners = countCorners(plant, region)
            price += (corners * region.size)
        }
        return price
    }


    private fun findRegions(): MutableMap<Location, Set<Location>> {
        val regions = mutableMapOf<Location, Set<Location>>()
        val allVisited = mutableSetOf<Location>()
        for (y in garden.indices)
            for (x in garden[y].indices) {
                val location = Location(x, y)
                if (location in allVisited)
                    continue
                val visited = getRegion(location, plantAt(x, y))
                if (visited.isNotEmpty()) {
                    allVisited.addAll(visited)
                    regions[location] = visited
                }
            }
        return regions
    }

    private fun getRegion(location: Location, plant: Char, visited: Set<Location> = emptySet()): Set<Location> {
        if ((location in visited) || (plantAt(location.x, location.y) != plant))
            return visited
        val newVisited = (visited + location).toMutableSet()
        newVisited += getRegion(Location(location.x + 1, location.y), plant, newVisited)
        newVisited += getRegion(Location(location.x - 1, location.y), plant, newVisited)
        newVisited += getRegion(Location(location.x, location.y + 1), plant, newVisited)
        newVisited += getRegion(Location(location.x, location.y - 1), plant, newVisited)
        return newVisited
    }

    private fun countNeighbors(x: Int, y: Int, c: Char): Int =
        (if (plantAt(x + 1, y) == c) 1 else 0) +
                (if (plantAt(x - 1, y) == c) 1 else 0) +
                (if (plantAt(x, y + 1) == c) 1 else 0) +
                (if (plantAt(x, y - 1) == c) 1 else 0)

    private fun countCorners(plant: Char, region: Set<Location>): Int =
        region.sumOf { location ->
            val x = location.x
            val y = location.y
            var count = 0
            for ((dir1, dir2) in listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1, 1 to 0).zipWithNext()) {
                val adjacent1 = plantAt(x + dir1.first, y + dir1.second)
                val adjacent2 = plantAt(x + dir2.first, y + dir2.second)
                val diagonal = plantAt(x + dir1.first + dir2.first, y + dir1.second + dir2.second)
                if ((plant != adjacent1) && (plant != adjacent2)) count++
                if ((plant == adjacent1) && (plant == adjacent2) && (plant != diagonal)) count++
            }
            count
        }

    private fun plantAt(x: Int, y: Int): Char =
        garden.getOrNull(y)?.getOrNull(x) ?: ' '

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day12.txt"
    }
}

fun main() {
    val day12 = Day12(Day12::class.java.getResource(Day12.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day12::part1 -> ${day12.part1()}")
    println("Day12::part2 -> ${day12.part2()}")
}
