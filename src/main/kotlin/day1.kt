import kotlin.math.abs

typealias CompareLists = Pair<List<Int>, List<Int>>

/**
 * https://adventofcode.com/2024/day/1
 */
object Day1 {

    fun firstTask(input: CompareLists): Int {
        val list1 = input.first.sorted().toMutableList()
        val list2 = input.second.sorted().toMutableList()
        var distance = 0
        while (list1.isNotEmpty() && list2.isNotEmpty()) {
            val value1 = list1.first()
            val value2 = list2.first()

            distance += abs(value1 - value2)
            list1.removeAt(0)
            list2.removeAt(0)
        }
        return distance
    }

    fun secondTask(input: CompareLists): Int {
        val list1 = input.first.toMutableList()
        val list2 = input.second.toMutableList()
        var similarity = 0

        while (list1.isNotEmpty() && list2.isNotEmpty()) {
            val value1 = list1.first()
            val count = list2.count { it == value1 }
            val sim = value1 * count
            // println("$value1 * $count = $sim")
            similarity += value1 * count

            list1.removeAt(0)
        }
        return similarity
    }

    fun transform(input: String): CompareLists {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()

        input.split("\n")
            .map { line ->
                val (first, second) = line.split("   ").map { it.trim().toInt() }
                Pair(first, second)
            }.forEach { (first, second) ->
                list1.add(first)
                list2.add(second)
            }

        return Pair(list1, list2)
    }
}