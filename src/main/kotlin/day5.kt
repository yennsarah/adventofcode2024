object Day5 {
    fun transform(sample: String): Pair<List<Pair<Int, Int>>, List<List<Int>>> {
        val (first, second) = sample.split("\n\n")
        val rules = first.split("\n").map { rule ->
            val (n1, n2) = rule.split("|").map { it.toInt() }
            Pair(n1, n2)
        }

        val updates = second.split("\n").map { update ->
            update.split(",").map { it.toInt() }
        }
        return Pair(rules, updates)
    }

    fun firstTask(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
        val filterd = updates.filterIndexed { ind, update ->
            val localRules = rules.filter { rule -> rule.toList().any { update.contains(it) } }
            // println(localRules.joinToString("\n") { "${it.first}|${it.second}" })
            update.forEachIndexed { index, page ->
                if (!localRules.map { it.second }.contains(page)) {
                    return@forEachIndexed
                }
                // check if all pages before are this page
                var i = 0
                while (i < index) {
                    val nr = update[i]
                    localRules.filter { rule -> rule.toList().all { it == page || it == nr } }
                        .forEach { rule ->
                            if (rule.second == nr) {
                                return@filterIndexed false
                            }
                        }
                    i++
                }

                // check all after
                var j = index + 1
                while (j < update.size) {
                    val nr = update[j]
                    localRules.filter { rule -> rule.toList().all { it == page || it == nr } }
                        .forEach { rule ->
                            if (rule.first == nr) {
                                return@filterIndexed false
                            }
                        }

                    j++
                }
            }
            true
        }

        return filterd.sumOf { update -> update[update.size / 2] }
    }

    fun secondTask(rules: List<Pair<Int, Int>>, updates: List<List<Int>>): Int {
        val reorderedUpdates: MutableList<List<Int>> = mutableListOf()
        updates.forEach { upd ->
            val updateRules = rules.filter { rule -> rule.toList().any { upd.contains(it) } }
            val update = upd.toMutableList()
            var index = 0
            var reordered = false
            while (index < upd.size) {
                val page = update[index]
                if (updateRules.map { it.second }.contains(page)) {
                    var i = 0
                    var b = true
                    while (i < index && b) {
                        val nr = update[i]
                        updateRules.filter { rule -> rule.toList().all { it == page || it == nr } }
                            .forEach inner@{ rule ->
                                if (rule.second == nr) {
                                    update[index--] = nr
                                    update[i] = page
                                    reordered = true
                                    b = false
                                }
                            }
                        i++
                    }
                    // check all after
                    var j = index + 1
                    var c = true
                    while (j < update.size && c) {
                        val nr = update[j]
                        updateRules.filter { rule -> rule.toList().all { it == page || it == nr } }
                            .forEach inner@{ rule ->
                                if (rule.first == nr) {
                                    update[index--] = nr
                                    update[j] = page
                                    reordered = true
                                    c = false
                                }
                            }

                        j++
                    }
                }
                index++
            }
            if (reordered) {
                reorderedUpdates.add(update)
            }
        }

        println(reorderedUpdates.joinToString("\n"))
        return reorderedUpdates.sumOf { update -> update[update.size / 2] }
    }
}