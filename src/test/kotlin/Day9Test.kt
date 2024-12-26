import Day9.DiskContent.DiskFile
import Day9.DiskContent.DiskSpace
import Day9.visualize
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day9Test {
    val sample = "2333133121414131402"
    val sample1 = "12345"
    val sample2 = "90909"

    @Test
    fun testTransformSample() {
        val result: List<Day9.DiskContent> = Day9.transform(sample)
        assert(result[0] is DiskFile)
        assert(result[1] is DiskSpace)
        assert(result[2] is DiskFile)
        assert(result[3] is DiskSpace)
        assert(result[4] is DiskFile)
        assertEquals(2, (result[0] as DiskFile).blocks)
        assertEquals(0, (result[0] as DiskFile).id)
        assertEquals(3, (result[1] as DiskSpace).space)
        assertEquals(3, (result[2] as DiskFile).blocks)
        assertEquals(1, (result[2] as DiskFile).id)
        assertEquals("00...111...2...333.44.5555.6666.777.888899", visualize(result))
    }


    @Test
    fun testTransformSample1() {
        val result: List<Day9.DiskContent> = Day9.transform(sample1)
        assert(result[0] is DiskFile)
        assert(result[1] is DiskSpace)
        assert(result[2] is DiskFile)
        assert(result[3] is DiskSpace)
        assert(result[4] is DiskFile)
        assertEquals(1, (result[0] as DiskFile).blocks)
        assertEquals(0, (result[0] as DiskFile).id)
        assertEquals(2, (result[1] as DiskSpace).space)
        assertEquals(3, (result[2] as DiskFile).blocks)
        assertEquals(1, (result[2] as DiskFile).id)
        assertEquals("0..111....22222", visualize(result))
    }

    @Test
    fun testTransformSample2() {
        val result: List<Day9.DiskContent> = Day9.transform(sample2)
        assert(result.all { it is DiskFile })
        assertEquals(9, (result[0] as DiskFile).blocks)
        assertEquals(0, (result[0] as DiskFile).id)
        assertEquals(9, (result[1] as DiskFile).blocks)
        assertEquals(1, (result[1] as DiskFile).id)
        assertEquals(9, (result[2] as DiskFile).blocks)
        assertEquals(2, (result[2] as DiskFile).id)
        assertEquals("000000000111111111222222222", visualize(result))
    }

    @Test
    fun testFirstSample() {
        val result = Day9.firstTask(Day9.transform(sample))
        assertEquals(result, 1928)
    }

    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day9.txt").readText()
        val result = Day9.firstTask(Day9.transform(taskInput))
        assertEquals(6334655979668, result)
    }


    @Test
    fun testSecondSample() {
        val result = Day9.secondTask(Day9.transform(sample))
        assertEquals(2858, result)
    }

    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day9.txt").readText()
        val result = Day9.secondTask(Day9.transform(taskInput))
        assertEquals(6349492251099, result)
    }

}