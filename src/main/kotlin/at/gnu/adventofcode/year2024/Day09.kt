package at.gnu.adventofcode.year2024

class Day09(compressedDiskMap: String) {

    private val uncompressedDiskMap =
        compressedDiskMap.mapIndexed { i, c -> List(c.digitToInt()) { if ((i % 2) == 1) FREE else (i / 2) } }.flatten()


    fun part1(): Long =
        uncompressedDiskMap.packBlocks().checksum()

    fun part2(): Long =
        uncompressedDiskMap.packFiles().checksum()


    private fun List<Int>.packBlocks(): List<Int> {
        val diskMap = this.toMutableList()
        while (diskMap.isFragmented()) {
            val moveBlockFrom = diskMap.indexOfLast { it.isBlock() }
            val moveBlockTo = diskMap.indexOfFirst { it.isFree() }
            diskMap[moveBlockTo] = diskMap[moveBlockFrom]
            diskMap[moveBlockFrom] = FREE
        }
        return diskMap
    }

    private fun List<Int>.packFiles(): List<Int> {
        val diskMap = this.toMutableList()
        val maxBlockId = diskMap.maxOrNull() ?: 0
        for (blockId in maxBlockId downTo 0) {
            val indexesOfFileToMove = diskMap.mapIndexedNotNull { i, id -> if (id == blockId) i else null }
            val indexOfFreeSpace = diskMap.indexOfFreeSpace(indexesOfFileToMove.size)
            if ((indexOfFreeSpace == null) || (indexOfFreeSpace >= indexesOfFileToMove.first()))
                continue
            for (j in indexesOfFileToMove.indices) {
                diskMap[indexOfFreeSpace + j] = diskMap[indexesOfFileToMove[j]]
                diskMap[indexesOfFileToMove[j]] = FREE
            }
        }
        return diskMap
    }

    private fun List<Int>.indexOfFreeSpace(size: Int): Int? {
        var blockSize = 0
        for (i in indices) {
            if (this[i].isFree()) blockSize++ else blockSize = 0
            if (blockSize >= size)
                return i - blockSize + 1
        }
        return null
    }

    private fun List<Int>.isFragmented(): Boolean =
        indexOfFirst { it.isFree() } < indexOfLast { it.isBlock() }

    private fun Int.isFree() =
        this == FREE

    private fun Int.isBlock() =
        this >= 0

    private fun List<Int>.checksum(): Long =
        mapIndexed { i, c -> i.toLong() * if (c > 0) c.toLong() else 0L }.sum()

    companion object {
        const val RESOURCE = "/adventofcode/year2024/Day09.txt"
        const val FREE = -1
    }
}

fun main() {
    val day09 = Day09(Day09::class.java.getResource(Day09.RESOURCE)!!.readText().trim())
    println("Day09::part1 -> ${day09.part1()}")
    println("Day09::part2 -> ${day09.part2()}")
}
