import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day4Test {
    val X = 'X'
    val M = 'M'
    val A = 'A'
    val S = 'S'

    val sampleInput = """
        MMMSXXMASM
        MSAMXMSMSA
        AMXSXMAAMM
        MSAMASMSMX
        XMASAMXAMM
        XXAMMXXAMA
        SMSMSASXSS
        SAXAMASAAA
        MAMMMXMMMM
        MXMXAXMASX
    """.trimIndent()


    @Test
    fun testFirstTransform() {
        val transformed = Day4.transform(sampleInput)
        assertEquals(M, transformed[0][0])
        assertEquals(X, transformed[0][4])
        assertEquals(M, transformed[0][9])
        assertEquals(X, transformed[9][9])
    }

    @Test
    fun testFirstSample() {
        val input = Day4.transform(sampleInput)
        val result = Day4.firstTask(input)
        assertEquals(18, result)
    }

    @Test
    fun firstTask() {
        val taskInput = File("$path/day4.txt").readText()
        val input = Day4.transform(taskInput)
        val result = Day4.firstTask(input)
        assertEquals(2557, result)
    }

    @Test
    fun testSecondSample() {
        val input = Day4.transform(sampleInput)
        val result = Day4.secondTask(input)
        assertEquals(9, result)
    }

    @Test
    fun secondTask() {
        val taskInput = File("$path/day4.txt").readText()
        val input = Day4.transform(taskInput)
        val result = Day4.secondTask(input)
        assertEquals(1854, result)
    }
}