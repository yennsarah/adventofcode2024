import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day2Test {
    val sampleInput = """
        7 6 4 2 1
        1 2 7 8 9
        9 7 6 2 1
        1 3 2 4 5
        8 6 4 4 1
        1 3 6 7 9
    """.trimIndent()

    @Test
    fun testFirstSample() {
        assertEquals(2, Day2.firstTask(Day2.transform(sampleInput)))
    }

    // actual is now 328? what changed?
    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day2.txt").readText()
        val input = Day2.transform(taskInput)
        val result = Day2.firstTask(input)
        println(result)
        assertEquals(306, result)
    }

    @Test
    fun testSecondSample() {
        assertEquals(4, Day2.secondTask(Day2.transform(sampleInput)))
    }

    // 388 too high
    // 343 too low - right answer for other account? :D
    // 363 too low?
    // actual is now 528? what changed?
    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day2.txt").readText()
        val input = Day2.transform(taskInput)
        val result = Day2.secondTask(input)
        println(result)
        assertEquals(366, result)
    }
}