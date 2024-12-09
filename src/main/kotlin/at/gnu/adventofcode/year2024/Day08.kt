package at.gnu.adventofcode.year2024

class Day08(city: List<String>) {

    data class Location(val x: Int, val y: Int)

    private val height = city.size
    private val width = city.first().length
    private val antennas = buildMap<Char, MutableList<Location>> {
        city.mapIndexed { y, line ->
            line.mapIndexed { x, frequency ->
                if (frequency != '.') {
                    if (this.containsKey(frequency))
                        this[frequency]!!.add(Location(x, y))
                    else
                        this[frequency] = mutableListOf(Location(x, y))
                }
            }
        }
    }

    fun part1(): Int =
        createAllAntinodes().size

    fun part2(): Int =
        createAllAntinodes(harmonics = true).size

    private fun createAllAntinodes(harmonics: Boolean = false): Set<Location> {
        val antinodes = mutableSetOf<Location>()
        for ((_, locations) in antennas)
            for (i in locations.indices)
                for (j in (i + 1) until locations.size)
                    antinodes += createAntinodesFor(locations[i], locations[j], harmonics).filter {
                        (it.x in 0 until width) && (it.y in 0 until height)
                    }
        return antinodes
    }

    private fun createAntinodesFor(antenna1: Location, antenna2: Location, harmonics: Boolean = false): Set<Location> {
        val nodes = mutableSetOf<Location>()
        val dx = antenna2.x - antenna1.x
        val dy = antenna2.y - antenna1.y
        val start = if (harmonics) 0 else 1
        val end = if (harmonics) width.coerceAtLeast(height) else 1
        for (i in start..end) {
            nodes += Location(antenna2.x + (i * dx), antenna2.y + (i * dy))
            nodes += Location(antenna1.x - (i * dx), antenna1.y - (i * dy))
        }
        return nodes
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day08.txt"
    }
}

fun main() {
    val day08 = Day08(Day08::class.java.getResource(Day08.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day08::part1 -> ${day08.part1()}")
    println("Day08::part2 -> ${day08.part2()}")
}
