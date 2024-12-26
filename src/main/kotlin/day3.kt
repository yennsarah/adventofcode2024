
/**
 * https://adventofcode.com/2024/day/3
 */
object Day3 {
    fun extractMulsFirst(input: String): List<Pair<Int, Int>> {
        val regex = "mul\\(\\d+,\\d+\\)".toRegex()

        val matches = regex.findAll(input)
        return transform(matches)
    }

    fun extractMulsSecond(input: String): List<Pair<Int, Int>> {
        val regex = "(mul\\(\\d+,\\d+\\))|(don't\\(\\))|(do\\(\\))".toRegex()

        var enabled = true
        val matches = regex.findAll(input)
            .mapNotNull { match ->
                // println(match.value)
                if (match.value == "do()") {
                    enabled = true
                    return@mapNotNull null
                } else if (match.value == "don't()") {
                    enabled = false
                    return@mapNotNull null
                } else if (!enabled) {
                    return@mapNotNull null
                } else {
                    enabled = true
                    return@mapNotNull match
                }
            }
        return transform(matches)
    }

    fun sumOfMuls(input: List<Pair<Int, Int>>): Int {
        return input.sumOf {
            it.first * it.second
        }
    }

    private fun transform(matches: Sequence<MatchResult>): List<Pair<Int, Int>> {
        return matches.toList().map { match ->
            val (n1, n2) = match.value
                .split(",")
                .map {
                    it
                        .replace("[^0-9]+".toRegex(), "").toInt()
                }
            Pair(n1, n2)
        }
    }
}