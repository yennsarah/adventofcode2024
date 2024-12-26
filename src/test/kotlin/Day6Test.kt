import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day6Test {
    val sample = """
    ....#.....
    .........#
    ..........
    ..#.......
    .......#..
    ..........
    .#..^.....
    ........#.
    #.........
    ......#...
    """.trimIndent().split("\n")

    @Test
    fun testFirstSample() {
        val data = Day6.transform(sample)
        val result = Day6.firstTask(data)
        assertEquals(41, result)
    }

    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day6.txt").readLines()
        val data = Day6.transform(taskInput)
        val result = Day6.firstTask(data)
        assertEquals(4752, result)
    }


    @Test
    fun testSecondSample() {
        val data = Day6.transform(sample)
        val result = Day6.secondTask(data)
        assertEquals(6, result.size)
    }

    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day6.txt").readLines()
        val data = Day6.transform(taskInput)
        val result = Day6.secondTask(data)
        assertEquals(6, result.size)
    }

}