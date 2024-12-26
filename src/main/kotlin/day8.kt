/**
 * https://adventofcode.com/2024/day/8
 */
data class Coordinate(val i: Int, val j: Int)
data class Point(val char: Char, val coord: Coordinate)

object Day8 {

    fun transform(input: List<String>): List<List<Char>> {
        return input
            .map { line -> line.toCharArray().toList() }
    }

    fun firstTask(data: List<List<Char>>): Pair<Set<Coordinate>, List<List<Char>>> {
        val maxI = data.size - 1
        val maxJ = data[0].size - 1
        val result = data.map { it.toMutableList() }
        // find diagonals
        val nodes = data.flatMapIndexed { i, chars ->
            chars.mapIndexedNotNull { j, c ->
                if (c != '.') {
                    // found a node
                    Point(c, Coordinate(i, j))

                } else null
            }
        }.toSet()

        val antinodes = mutableSetOf<Coordinate>()

        val mapped = nodes.groupBy { it.char }
        mapped.forEach { (char, points) ->
            // make all pairs
            val coordPairs: Set<Pair<Coordinate, Coordinate>> = points.flatMap { p1 ->
                points.mapNotNull { p2 ->
                    if (p1 != p2) Pair(p1.coord, p2.coord)
                    else null
                }
            }.toSet()

            // make diagonal
            coordPairs.forEach { (c1, c2) ->
                val antinode1i = c1.i + (c1.i - c2.i)
                val antinode1j = c1.j + (c1.j - c2.j)
                // println("$char $c1:$c2 antinode coords $antinode1i $antinode1j")
                // try to place antinode
                if (coordinatesInGrid(antinode1i, maxI, antinode1j, maxJ)) {
                    if (result[antinode1i][antinode1j] == '.') {
                        result[antinode1i][antinode1j] = '#'
                    }
                    antinodes.add(Coordinate(antinode1i, antinode1j))
                }
            }
        }
        return Pair(antinodes, result)
    }

    fun secondTask(data: List<List<Char>>): Pair<Set<Coordinate>, List<List<Char>>> {
        val maxI = data.size - 1
        val maxJ = data[0].size - 1
        val result = data.map { it.toMutableList() }
        // find diagonals
        val nodes = data.flatMapIndexed { i, chars ->
            chars.mapIndexedNotNull { j, c ->
                if (c != '.') {
                    // found a node
                    Point(c, Coordinate(i, j))

                } else null
            }
        }.toSet()

        val antinodes = mutableSetOf<Coordinate>()

        val mapped = nodes.groupBy { it.char }
        mapped.forEach { (char, points) ->
            // make all pairs
            val coordPairs: Set<Pair<Coordinate, Coordinate>> = points.flatMap { p1 ->
                points.mapNotNull { p2 ->
                    if (p1 != p2) Pair(p1.coord, p2.coord)
                    else null
                }
            }.toSet()

            // make diagonal
            coordPairs.forEach { (c1, c2) ->
                antinodes.add(c2)
                antinodes.add(c1)
                var inBounds = true
                var cc1 = c1
                var cc2 = c2
                while (inBounds) {
                    val antinode1i = cc1.i + (c1.i - c2.i)
                    val antinode1j = cc1.j + (c1.j - c2.j)
                     // println("$char $cc1:$cc2 antinode coords $antinode1i $antinode1j")
                    // try to place antinode
                    if (coordinatesInGrid(antinode1i, maxI, antinode1j, maxJ)) {
                        if (result[antinode1i][antinode1j] == '.') {
                            result[antinode1i][antinode1j] = '#'
                        }
                        antinodes.add(Coordinate(antinode1i, antinode1j))
                        cc1 = Coordinate(antinode1i, antinode1j)
                        cc2 = Coordinate(cc2.i + (cc2.i - cc1.i), cc2.j + (cc2.j - cc1.j))
                    } else {
                        inBounds = false
                    }
                }
            }
        }
        return Pair(antinodes, result)
    }

    private fun coordinatesInGrid(antinode1i: Int, maxI: Int, antinode1j: Int, maxJ: Int) =
        antinode1i in 0..maxI && antinode1j in 0..maxJ

}