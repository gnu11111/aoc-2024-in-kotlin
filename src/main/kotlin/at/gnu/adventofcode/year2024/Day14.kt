package at.gnu.adventofcode.year2024


class Day14(robots: List<String>, private val width: Int = 101, private val height: Int = 103) {

    data class Robot(var x: Int = 0, var y: Int = 0, var dx: Int = 0, var dy: Int = 0)

    private val robots = robots.map {
        val (location, velocity) = it.split(" ")
        val (x, y) = location.substringAfter("=").split(",").map(String::toInt)
        val (dx, dy) = velocity.substringAfter("=").split(",").map(String::toInt)
        Robot(x, y, dx, dy)
    }


    fun part1(): Long {
        repeat(100) { robots.move() }
        val firstQuarter = robots.count { (it.x < (width / 2)) && (it.y < (height / 2)) }
        val secondQuarter = robots.count { (it.x > (width / 2)) && (it.y < (height / 2)) }
        val thirdQuarter = robots.count { (it.x < (width / 2)) && (it.y > (height / 2)) }
        val fourthQuarter = robots.count { (it.x > (width / 2)) && (it.y > (height / 2)) }
        return firstQuarter.toLong() * secondQuarter * thirdQuarter * fourthQuarter
    }

    fun part2(): Long {
        var count = 99L
        while (++count < 7051L) { robots.move() }
//        robots.display()
        return count
    }

    private fun List<Robot>.move(): List<Robot> {
        forEach {
            it.x = (it.x + it.dx).mod(width)
            it.y = (it.y + it.dy).mod(height)
        }
        return this
    }

    @Suppress("unused")
    private fun List<Robot>.display() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (any { it.x == x && it.y == y }) print("#") else print(".")
            }
            println()
        }
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day14.txt"
    }
}

fun main() {
    val day14 = Day14(Day14::class.java.getResource(Day14.RESOURCE)!!.readText().trim().split("\n", "\r\n"))
    println("Day14::part1 -> ${day14.part1()}")
    println("Day14::part2 -> ${day14.part2()}")
}
