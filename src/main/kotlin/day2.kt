import kotlin.math.abs

/**
 * https://adventofcode.com/2024/day/2
 */
object Day2 {

    fun transform(input: String): List<List<Int>> {
        return input.split("\n").map { line ->
            line.split(" ").mapNotNull { it.toIntOrNull() }
        }.filter { it.isNotEmpty() }
    }

    fun firstTask(input: List<List<Int>>): Int {
        val safeReportsCount = input.filter { report ->
            val allSafeSteps = allSafeSteps(report)
            val allIncrease = allIncrease(report)
            val allDecrease = allDecrease(report)
            val result = allSafeSteps && (allIncrease || allDecrease)
            // println("${report.joinToString(" ")}\nallSafeSteps $allSafeSteps && allIncrease $allIncrease && allDecrease $allDecrease: $result")

            result
        }.size

        return safeReportsCount
    }

    fun secondTask(input: List<List<Int>>): Int {
        val safeReportsCount = input.map { report ->
            val dampenedReports = report.indices.map { index -> report.filterIndexed {i, _ -> i != index}}
            var result = allSafeSteps(report) && (allIncrease(report) || allDecrease(report))
            result = result || dampenedReports.map { dampenedReport ->
                allSafeSteps(dampenedReport) && (allIncrease(dampenedReport) || allDecrease(dampenedReport))
            }.any { it }

            result
        }.count { it }

        return safeReportsCount
    }

    private fun allIncrease(report: List<Int>): Boolean {
        val safe = report.windowed(2).map { (l, r) -> l < r }.all { it }
        return safe
    }

    private fun allDecrease(report: List<Int>): Boolean {
        val safe = report.windowed(2).map { (l, r) -> l > r }.all { it }
        return safe
    }

    private fun allSafeSteps(report: List<Int>): Boolean {
        val safe = report.windowed(2).map { (l,r) -> (1..3).contains(abs(l-r)) }.all { it }
        return safe
    }
}