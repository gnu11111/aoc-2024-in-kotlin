package at.gnu.adventofcode.year2024

class Day12(private val garden: List<String>) {

    data class Plot(val x: Int, val y: Int)

    fun part1(): Int =
        findRegions().entries.sumOf { (startPlot, region) ->
            val plant = plantAt(startPlot)
            val fences = (region.size * 4) - region.sumOf { plot -> countNeighbors(plot, plant) }
            (fences * region.size)
        }

    fun part2(): Int =
        findRegions().entries.sumOf { (startPlot, region) ->
            val plant = plantAt(startPlot)
            val corners = countCorners(plant, region)
            (corners * region.size)
        }


    private fun findRegions(): MutableMap<Plot, Set<Plot>> {
        val regions = mutableMapOf<Plot, Set<Plot>>()
        val allVisited = mutableSetOf<Plot>()
        for (y in garden.indices)
            for (x in garden[y].indices) {
                val startPlot = Plot(x, y)
                if (startPlot in allVisited)
                    continue
                val visited = fillRegion(startPlot, plantAt(startPlot))
                if (visited.isNotEmpty()) {
                    allVisited.addAll(visited)
                    regions[startPlot] = visited
                }
            }
        return regions
    }

    private fun fillRegion(plot: Plot, plant: Char, visited: Set<Plot> = emptySet()): Set<Plot> {
        if ((plot in visited) || (plantAt(plot) != plant))
            return visited
        val newVisited = (visited + plot).toMutableSet()
        newVisited += fillRegion(Plot(plot.x + 1, plot.y), plant, newVisited)
        newVisited += fillRegion(Plot(plot.x - 1, plot.y), plant, newVisited)
        newVisited += fillRegion(Plot(plot.x, plot.y + 1), plant, newVisited)
        newVisited += fillRegion(Plot(plot.x, plot.y - 1), plant, newVisited)
        return newVisited
    }

    private fun countNeighbors(plot: Plot, c: Char): Int =
        (if (plantAt(Plot(plot.x + 1, plot.y)) == c) 1 else 0) +
                (if (plantAt(Plot(plot.x - 1, plot.y)) == c) 1 else 0) +
                (if (plantAt(Plot(plot.x, plot.y + 1)) == c) 1 else 0) +
                (if (plantAt(Plot(plot.x, plot.y - 1)) == c) 1 else 0)

    private fun countCorners(plant: Char, region: Set<Plot>): Int =
        region.sumOf { plot ->
            var count = 0
            for ((dir1, dir2) in listOf(1 to 0, 0 to 1, -1 to 0, 0 to -1, 1 to 0).zipWithNext()) {
                val adjacent1 = plantAt(Plot(plot.x + dir1.first, plot.y + dir1.second))
                val adjacent2 = plantAt(Plot(plot.x + dir2.first, plot.y + dir2.second))
                val diagonal = plantAt(Plot(plot.x + dir1.first + dir2.first, plot.y + dir1.second + dir2.second))
                if ((plant != adjacent1) && (plant != adjacent2)) count++
                if ((plant == adjacent1) && (plant == adjacent2) && (plant != diagonal)) count++
            }
            count
        }

    private fun plantAt(plot: Plot): Char =
        garden.getOrNull(plot.y)?.getOrNull(plot.x) ?: ' '

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day12.txt"
    }
}

fun main() {
    val day12 = Day12(Day12::class.java.getResource(Day12.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day12::part1 -> ${day12.part1()}")
    println("Day12::part2 -> ${day12.part2()}")
}
