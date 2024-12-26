import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

class Day5Test {
    val sample = """
        47|53
        97|13
        97|61
        97|47
        75|29
        61|13
        75|53
        29|13
        97|29
        53|29
        61|53
        97|53
        61|29
        47|13
        75|47
        97|75
        47|61
        75|61
        47|29
        75|13
        53|13

        75,47,61,53,29
        97,61,53,29,13
        75,29,13
        75,97,47,61,53
        61,13,29
        97,13,75,29,47
    """.trimIndent()

    @Test
    fun testTransform() {
        val (rules, updates) = Day5.transform(sample)
        assertEquals(Pair(47,53), rules.first())
        assertEquals(listOf(75,47,61,53,29), updates.first())
    }

    @Test
    fun testFirstSample() {
        val (rules, updates) = Day5.transform(sample)
        val result = Day5.firstTask(rules, updates)
        assertEquals(143, result)
    }

    @Test
    fun testFirstTask() {
        val taskInput = File("$path/day5.txt").readText()
        val (rules, updates) = Day5.transform(taskInput)
        val result = Day5.firstTask(rules, updates)
        assertEquals(3608, result)
    }

    @Test
    fun testSecondSample() {
        val (rules, updates) = Day5.transform(sample)
        val result = Day5.secondTask(rules, updates)
        assertEquals(123, result)
    }

    @Test
    fun testSecondTask() {
        val taskInput = File("$path/day5.txt").readText()
        val (rules, updates) = Day5.transform(taskInput)
        val result = Day5.secondTask(rules, updates)
        assertEquals(4922, result)
    }
}