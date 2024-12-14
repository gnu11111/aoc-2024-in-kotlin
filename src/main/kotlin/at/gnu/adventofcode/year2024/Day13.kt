package at.gnu.adventofcode.year2024

class Day13(rules: List<List<String>>) {

    data class Rule(val ax: Long, val ay: Long, val bx: Long, val by: Long, val px: Long, val py: Long)

    private val rules = rules.map { rule ->
        val (buttonA, buttonB, prize) = rule
        val (ax, ay) = buttonA.split(",").map { it.substringAfter("+").toLong() }
        val (bx, by) = buttonB.split(",").map { it.substringAfter("+").toLong() }
        val (px, py) = prize.split(",").map { it.substringAfter("=").toLong() }
        Rule(ax, ay, bx, by, px, py)
    }

    /*
    x * 94 + y * 22 = 8400     -> y = (8400 - x * 94) / 22
    x * 34 + y * 67 = 5400     -> x * 34 + ((8400 - x * 94) / 22) * 67 = 5400
                                  x * 34 / 67 + ((8400 - x * 94) / 22) = 5400 / 67
                                  22 * x * 34 / 67 + 8400 - x * 94 = 22 * 5400 / 67
                                  (22 * 34 * x - 94 * 67 * x) + 67 * 8400 = 22 * 5400
                                  (22 * 34 - 94 * 67) * x = 22 * 5400 - 67 * 8400
                                  (bx * ay - ax * by) * x = bx * py - by * px
                                  a = (by * px - bx * py) / (ax * by - bx * ay)
                                  -5550 * x = 118800 - 562000
                                  -5550 * x = -444000
                                  a = 80
                                  b = (8400 - 80 * 94) / 22 = (8400 - 7520) / 22 = 880 / 22 = 40
                                  b = (px - a * ax) / bx
    */
    fun part1(): Long =
        rules.sumOf(::calculatePrize)

    fun part2(): Long =
        rules.sumOf { calculatePrize(it, additionalPrize = 10000000000000L) }

    private fun calculatePrize(rule: Rule, additionalPrize: Long = 0L): Long {
        val px = rule.px + additionalPrize
        val py = rule.py + additionalPrize
        val a = ((rule.by * px) - (rule.bx * py)) / ((rule.ax * rule.by) - (rule.bx * rule.ay))
        val b = (px - (a * rule.ax)) / rule.bx
        val px1 = (a * rule.ax) + (b * rule.bx)
        val py1 = (a * rule.ay) + (b * rule.by)
        return if ((px == px1) && (py == py1))
            (3L * a) + b
        else
            0
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day13.txt"
    }
}

fun main() {
    val day13 = Day13(Day13::class.java.getResource(Day13.RESOURCE)!!.readText().trim()
        .split("\n\n", "\r\n\r\n").map { it.split("\n", "\r\n") })
    println("Day13::part1 -> ${day13.part1()}")
    println("Day13::part2 -> ${day13.part2()}")
}
