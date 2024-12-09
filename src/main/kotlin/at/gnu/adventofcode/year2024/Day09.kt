package at.gnu.adventofcode.year2024

class Day09(input: String) {

    private val uncompress = input.uncompress()

    fun part1(): Long =
        uncompress.defragment().checksum()

    fun part2(): Long =
        uncompress.defragment2().checksum()

    private fun String.uncompress(): List<Int> =
        mapIndexed { i, c ->
            if ((i % 2) == 0)
                List(c.digitToInt()) { i / 2 }
            else
                List(c.digitToInt()) { -1 }
        }.flatten()

    private fun List<Int>.defragment(): List<Int> {
        val disk = this.toMutableList()
        while (disk.isFragmented()) {
            val moveFrom = disk.indexOfLast { it.isBlock() }
            val moveTo = disk.indexOfFirst { it.isFree() }
            disk[moveTo] = disk[moveFrom]
            disk[moveFrom] = -1
        }
        return disk
    }

    private fun List<Int>.defragment2(): List<Int> {
        val disk = this.toMutableList()
        val maxBlockId = disk.maxOrNull() ?: 0
        for (blockId in maxBlockId downTo 0) {
            val block = disk.mapIndexedNotNull { i, id -> if (id == blockId) i else null }
            val freeSpace = disk.indexOfFreeSpace(block.size) ?: continue
            if (freeSpace >= block.first())
                continue
            for (j in block.indices) {
                disk[freeSpace + j] = disk[block[j]]
                disk[block[j]] = -1
            }
//            println(disk.joinToString(""))
        }
        return disk
    }

    private fun List<Int>.indexOfFreeSpace(size: Int): Int? {
        var blockSize = 0
        for (i in indices) {
            if (this[i] < 0) blockSize++ else blockSize = 0
            if (blockSize >= size)
                return i - size + 1
        }
        return null
    }

    private fun List<Int>.isFragmented(): Boolean =
        indexOfFirst { it.isFree() } < indexOfLast { it.isBlock() }

    private fun Int.isFree() =
        this < 0

    private fun Int.isBlock() =
        this >= 0

    private fun List<Int>.checksum(): Long =
        mapIndexed { i, c -> i.toLong() * if (c > 0) c.toLong() else 0L }.sum()

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day09.txt"
    }
}

fun main() {
    val day09 = Day09(Day09::class.java.getResource(Day09.RESOURCE)!!.readText().trim())
    println("Day09::part1 -> ${day09.part1()}")
    println("Day09::part2 -> ${day09.part2()}")
}
