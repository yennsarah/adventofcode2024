import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

@org.junit.jupiter.api.DisplayName("Day 1")
class Day1Test {

    val testInput = """
        3   4
        4   3
        2   5
        1   3
        3   9
        3   3
    """.trimIndent()

    @Test
    fun testTransform() {
        val transformed = Day1.transform(testInput)
        assertEquals(3, transformed.first.first())
        assertEquals(4, transformed.second.first())
        assertEquals(3, transformed.first.last())
        assertEquals(3, transformed.second.last())
    }

    @Test
    fun testSample() {
        assertEquals(11, Day1.firstTask(Day1.transform(testInput)))
    }

    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day1.txt").readText()
        val result = Day1.firstTask(Day1.transform(taskInput))
        println("Day 1 #1 result: $result")
        assertEquals(1660292, result)
    }

    @Test
    fun testSecondSample() {
        assertEquals(31, Day1.secondTask(Day1.transform(testInput)))
    }

    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day1.txt").readText()
        val result = Day1.secondTask(Day1.transform(taskInput))
        println("Day 1 #2 result: $result")
        assertEquals(22776016, result)
    }
}