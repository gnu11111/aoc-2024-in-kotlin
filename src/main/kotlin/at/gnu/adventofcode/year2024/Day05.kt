package at.gnu.adventofcode.year2024

class Day05(rules: List<String>, updates: List<String>) {

    private val rules = rules.map {
        val (page1, page2) = it.split("|")
        page1.toInt() to page2.toInt()
    }

    private val updates = updates.map { it.split(",").map(String::toInt) }


    fun part1(): Int =
        updates.sumOf { if (it.check()) it.middleNumber() else 0 }

    fun part2(): Int =
        updates.sumOf { if (it.check()) 0 else it.reorder().middleNumber() }


    private fun List<Int>.check(): Boolean =
        rules.all {
            val index1 = indexOf(it.first)
            val index2 = indexOf(it.second)
            (index1 < 0) || (index2 < 0) || (index1 < index2)
        }

    private fun List<Int>.middleNumber(): Int =
        this[size / 2]

    private fun List<Int>.reorder(): List<Int> {
        val reorderedList = toMutableList()
        while (true) {
            for (rule in rules) {
                val index1 = reorderedList.indexOf(rule.first)
                val index2 = reorderedList.indexOf(rule.second)
                if ((index1 >= 0) && (index2 >= 0) && (index1 > index2)) {
                    val temp = reorderedList[index2]
                    reorderedList[index2] = reorderedList[index1]
                    reorderedList[index1] = temp
                    if (reorderedList.check())
                        return reorderedList
                }
            }
        }
    }

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day05.txt"
    }
}

fun main() {
    val (rules, pages) = Day05::class.java.getResource(Day05.RESOURCE)!!.readText().trim().split("\n\n", "\r\n\r\n")
        .map { it.split("\n", "\r\n") }
    val day05 = Day05(rules, pages)
    println("Day05::part1 -> ${day05.part1()}")
    println("Day05::part2 -> ${day05.part2()}")
}
