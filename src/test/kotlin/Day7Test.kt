import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day7Test {
    val sample = """
    190: 10 19
    3267: 81 40 27
    83: 17 5
    156: 15 6
    7290: 6 8 6 15
    161011: 16 10 13
    192: 17 8 14
    21037: 9 7 18 13
    292: 11 6 16 20
    """.trimIndent().split("\n")

    @Test
    fun testOps() {
        val ops = Day7.ops(3)
        println(ops.joinToString("\n") { it.joinToString(" ") })
        assertEquals(ops[2], listOf(Day7.Ops.Mul, Day7.Ops.Add, Day7.Ops.Mul))
    }

    @Test
    fun testTransform() {
        val result = Day7.transform(sample)
        assertEquals(190L, result.first().first)
        assertEquals(listOf(10L, 19L), result.first().second)
        assertEquals(292L, result.last().first)
        assertEquals(listOf(11L, 6L, 16L, 20L), result.last().second)
    }

    @Test
    fun testFirstSample() {
        val data = Day7.transform(sample)
        val result = Day7.firstTask(data)
        assertEquals(3749L, result)
    }

    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day7.txt").readLines()
        val data = Day7.transform(taskInput)
        val result = Day7.firstTask(data)
        assertEquals(7885693428401, result)
    }


    @Test
    fun testSecondSample() {
        val data = Day7.transform(sample)
        val result = Day7.secondTask(data)
         assertEquals(11387, result)
    }

    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day7.txt").readLines()
        val data = Day7.transform(taskInput)
        val result = Day7.secondTask(data)
         assertEquals(11387, result)
    }

}