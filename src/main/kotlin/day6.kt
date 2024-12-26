
/**
 * https://adventofcode.com/2024/day/6
 */
object Day6 {
    fun transform(sample: List<String>): List<List<Char>> {
        return sample.map { line ->
            line.toCharArray().toList()
        }
    }

    val up = '^'
    val down = 'v'
    val left = '<'
    val right = '>'
    val obstacle = '#'

    fun firstTask(data: List<List<Char>>): Int {
        val pathData = data.map { it.toMutableList() }
        val height = data.size
        val width = data.first().size

        // find ^
        val positions: MutableList<Pair<Int, Int>> = mutableListOf()
        val initialPosition = findPosition(data, up)

        // start running - look ahead according to character, then go & count step
        var currentPosition = initialPosition
        var direction: Char = up
        while (currentPosition != null) {
            var (i, j) = currentPosition
            pathData[i][j] = 'X'
            when (direction) {
                up -> i -= 1
                down -> i += 1
                left -> j -= 1
                right -> j += 1
            }
            if (i < 0 || i >= height || j < 0 || j > width) {
                // walked out of grid
                break
            }
            val nextChar = data[i][j]
            if (nextChar == obstacle) {
                // turn 90 degree right
                direction = changeDirection(direction)
                i = currentPosition.first
                j = currentPosition.second
            } else {
                currentPosition = Pair(i, j)
                positions.add(currentPosition)
            }
            pathData[i][j] = direction

            // printStatus(pathData)
        }

        return positions.distinct().size
    }

    private fun printStatus(pathData: List<MutableList<Char>>) {
        println()
        println(pathData.joinToString("\n") { it.joinToString("") })
    }

    private fun changeDirection(direction: Char): Char {
        return when (direction) {
            up -> right
            down -> left
            left -> up
            right -> down
            else -> throw IndexOutOfBoundsException()
        }
    }

    fun secondTask(data: List<List<Char>>): List<Pair<Int, Int>> {
        TODO()
        val positions: MutableList<Pair<Int, Int>> = mutableListOf()
        val pathData = data.map { it.toMutableList() }

        println(positions.joinToString("\n"))
        return positions
    }

    private fun findPosition(data: List<List<Char>>, searchChar: Char): Pair<Int, Int>? {
        for ((i, line) in data.withIndex()) {
            for ((j, char) in line.withIndex()) {
                if (char == searchChar) {
                    return i to j
                }
            }
        }
        return null
    }
}