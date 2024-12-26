import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day3Test {
    val sample = """
        xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
    """.trimIndent()


    @Test
    fun testFirstSample() {
        val expect = 161
        val input = Day3.extractMulsFirst(sample)
        assertEquals(expect, Day3.sumOfMuls(input))
    }

    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day3.txt").readText()
        val input = Day3.extractMulsFirst(taskInput)
        val result = Day3.sumOfMuls(input)
        assertEquals(180233229, result)
    }


    @Test
    fun testSecondSample() {
        val expect = 48
        val input = Day3.extractMulsSecond(sample)
        assertEquals(expect, Day3.sumOfMuls(input))
    }

    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day3.txt").readText()
        val input = Day3.extractMulsSecond(taskInput)
        val result = Day3.sumOfMuls(input)
        // println(result)
        assertEquals(95411583, result)
    }



}