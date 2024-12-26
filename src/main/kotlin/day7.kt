import kotlin.math.pow

/**
 * https://adventofcode.com/2024/day/7
 */
object Day7 {
    fun transform(sample: List<String>): List<Pair<Long, List<Long>>> {
        return sample.map { line ->
            val (result, numbers) = line.split(":")
            Pair(result.toLong(), numbers.split(" ").mapNotNull { it.trim().toLongOrNull() })
        }
    }

    enum class Ops { Add, Mul }

    fun firstTask(data: List<Pair<Long, List<Long>>>): Long {
        val list = data.mapNotNull { line ->
            val (result, numbers) = line
            val opsList = ops(numbers.size - 1)
            opsList.forEach { ops ->
                val r = numbers.reduceIndexed { index, acc, i ->
                    when (ops[index - 1]) {
                        Ops.Add -> acc + i
                        Ops.Mul -> acc * i
                    }
                }
                if (r == result) return@mapNotNull result
            }
            return@mapNotNull null
        }

        return list.sum()
    }

    fun ops(count: Int): List<List<Ops>> {
        val options = (Ops.entries.size.toDouble()).pow(count.toDouble()).toInt()
        val ops = List(options) { List(count) { Ops.Add }.toMutableList() }
        ops.forEachIndexed { i, list ->
            list.forEachIndexed { j, _ ->
                list[j] = if ((i shr j and 1) == 1) Ops.Add else Ops.Mul
            }
        }

        return ops
    }

    fun secondTask(data: List<Pair<Long, List<Long>>>): Long {
        TODO()
    }
}