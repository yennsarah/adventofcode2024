import Day9.DiskContent.DiskFile
import Day9.DiskContent.DiskSpace

/**
 * https://adventofcode.com/2024/day/9
 */
object Day9 {

    fun transform(input: String): List<DiskContent> {
        var currentId = 0L
        return input.map { it.toString().toInt() }.mapIndexedNotNull { index, num ->
            return@mapIndexedNotNull if (index.mod(2) == 0) {
                DiskFile(id = currentId++, blocks = num)
            } else {
                if (num == 0) null else DiskSpace(num)
            }
        }
    }

    fun firstTask(data: List<DiskContent>): Long {
        val spreadList = spread(data).toMutableList()

        spreadList.forEachIndexed { index, int ->
            if (int != null) return@forEachIndexed

            // find last digit
            val lastDigit = spreadList.last { it != null }
            // find index of last digit
            val lastDigitIndex = spreadList.lastIndexOf(lastDigit)
            if (lastDigitIndex <= index) return@forEachIndexed
            // set . at index of last digit
            spreadList[lastDigitIndex] = null

            // set digit at current index
            spreadList[index] = lastDigit
            //println(visualized.joinToString(""))
        }

        return spreadList.filterNotNull()
            .mapIndexed { index, c ->
                index * c.toString().toLong()
            }.sum()
    }

    fun secondTask(data: List<DiskContent>): Long {
        val resultList = data.toMutableList()

        // println(visualize(resultList))
        var index = resultList.size - 1
        // not check for space -> search for number, check all numbers and search for space instead!
        while (index > 0) {
            val file = resultList[index]
            if (file !is DiskFile) {
                index--
                continue
            }

            // find first pace that fits this file
            val fittingSpace: DiskSpace? =
                resultList.firstOrNull { it is DiskSpace && it.space >= file.blocks } as DiskSpace?

            if (fittingSpace == null) {
                index--
                continue
            }

            // find index of fitting space
            val indexOfSpace = resultList.indexOf(fittingSpace)
            if (index < indexOfSpace) {
                index--
                continue
            }
            // move file into part of fitting space
            resultList[indexOfSpace] = file
            // replace file on ending with appropiate space
            resultList[index] = DiskSpace(file.blocks)
            // add remaining space again -> increase index by one
            if (fittingSpace.space > file.blocks) {
                resultList.add(indexOfSpace + 1, DiskSpace(fittingSpace.space - file.blocks))
                index++
            }

            index--
            //println(visualize(resultList))
        }

        val resultForChecksum = mutableListOf<Long?>()
        resultList.forEach { content ->
            when (content) {
                is DiskFile -> repeat(content.blocks) { resultForChecksum.add(content.id) }
                is DiskSpace -> repeat(content.space) { resultForChecksum.add(null) }
            }
        }

        return resultForChecksum.mapIndexed { index, id ->
            if (id != null) index * id else 0
        }.sum()
    }

    fun visualize(data: List<Day9.DiskContent>): String {
        val mapped = (data.map() { content ->
            var v = ""
            when (content) {
                is DiskFile -> repeat(content.blocks) { v += "${content.id}" }
                is DiskSpace -> repeat(content.space) {
                    v += '.'
                }
            }
            v
        })

        return mapped.joinToString("")
    }

    fun spread(data: List<Day9.DiskContent>): List<Long?> {
        val mapped = mutableListOf<Long?>()
        data.forEach { content ->
            when (content) {
                is DiskFile -> repeat(content.blocks) { mapped.add(content.id) }
                is DiskSpace -> repeat(content.space) {
                    mapped.add(null)
                }
            }

        }
        return mapped
    }

    sealed class DiskContent {
        data class DiskFile(val id: Long, val blocks: Int) : DiskContent()
        data class DiskSpace(val space: Int) : DiskContent()
    }
}