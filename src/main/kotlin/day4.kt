typealias Matrix = List<List<Char>>

object Day4 {
    val debug = false
    fun transform(sampleInput: String): Matrix {
        val matrix = sampleInput
            .split("\n")
            .map { line ->
                line.toList()
            }
        return matrix
    }

    fun firstTask(input: Matrix): Int {

        var count = 0
        val size = input.size
        for ((i, line) in input.withIndex()) {
            for ((j, char) in line.withIndex()) {
                if (char == 'X') {
                    // X M
                    // . .
                    if (j + 1 < size && input[i][j + 1] == 'M') {
                        if (j + 2 < size && input[i][j + 2] == 'A') {
                            if (j + 3 < size && input[i][j + 3] == 'S') {
                                count++
                            }
                        }
                    }

                    // M X
                    // . .
                    if (j - 1 >= 0 && input[i][j - 1] == 'M') {
                        if (j - 2 >= 0 && input[i][j - 2] == 'A') {
                            if (j - 3 >= 0 && input[i][j - 3] == 'S') {
                                count++
                            }
                        }
                    }

                    // X .
                    // M .
                    if (i + 1 < size && input[i + 1][j] == 'M') {
                        if (i + 2 < size && input[i + 2][j] == 'A') {
                            if (i + 3 < size && input[i + 3][j] == 'S') {
                                count++
                            }
                        }
                    }

                    // M .
                    // X .
                    if (i - 1 >= 0 && input[i - 1][j] == 'M') {
                        if (i - 2 >= 0 && input[i - 2][j] == 'A') {
                            if (i - 3 >= 0 && input[i - 3][j] == 'S') {
                                count++
                            }
                        }
                    }

                    // X .
                    // . M
                    if (i + 1 < size && j + 1 < size && input[i + 1][j + 1] == 'M') {
                        if (i + 2 < size && j + 2 < size && input[i + 2][j + 2] == 'A') {
                            if (i + 3 < size && j + 3 < size && input[i + 3][j + 3] == 'S') {
                                count++
                            }
                        }
                    }

                    // . X
                    // M .
                    if (i + 1 < size && j - 1 >= 0 && input[i + 1][j - 1] == 'M') {
                        if (i + 2 < size && j - 2 >= 0 && input[i + 2][j - 2] == 'A') {
                            if (i + 3 < size && j - 3 >= 0 && input[i + 3][j - 3] == 'S') {
                                count++
                            }
                        }
                    }

                    // . M
                    // X .
                    if (i - 1 >= 0 && j + 1 < size && input[i - 1][j + 1] == 'M') {
                        if (i - 2 >= 0 && j + 2 < size && input[i - 2][j + 2] == 'A') {
                            if (i - 3 >= 0 && j + 3 < size && input[i - 3][j + 3] == 'S') {
                                count++
                            }
                        }
                    }

                    // M .
                    // . X
                    if (i - 1 >= 0 && j - 1 >= 0 && input[i - 1][j - 1] == 'M') {
                        if (i - 2 >= 0 && j - 2 >= 0 && input[i - 2][j - 2] == 'A') {
                            if (i - 3 >= 0 && j - 3 >= 0 && input[i - 3][j - 3] == 'S') {
                                count++
                            }
                        }
                    }
                }
            }
        }

        return count
    }

    fun secondTask(input: Matrix): Int {
        var count = 0
        val size = input.size
        for ((i, line) in input.withIndex()) {
            for ((j, char) in line.withIndex()) {
                if (char == 'A') {
                    // M . S
                    // . A .
                    // M . S
                    if (j + 1 < size && i - 1 >= 0 && input[i - 1][j + 1] == 'S') {
                        if (j + 1 < size && i + 1 < size && input[i + 1][j + 1] == 'S') {
                            if (j - 1 >= 0 && i + 1 < size && input[i + 1][j - 1] == 'M') {
                                if (j - 1 >= 0 && i - 1 >= 0 && input[i - 1][j - 1] == 'M') {
                                    count++

                                    if (debug) {
                                        println(
                                            """
                                          ($i,$j)
                                          M . S
                                          . A .
                                          M . S
                                    """.trimIndent()
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // M . M
                    // . A .
                    // S . S
                    if (j + 1 < size && i - 1 >= 0 && input[i - 1][j + 1] == 'M') {
                        if (j + 1 < size && i + 1 < size && input[i + 1][j + 1] == 'S') {
                            if (j - 1 >= 0 && i + 1 < size && input[i + 1][j - 1] == 'S') {
                                if (j - 1 >= 0 && i - 1 >= 0 && input[i - 1][j - 1] == 'M') {
                                    count++

                                    if (debug) {
                                        println(
                                            """
                                          ($i,$j)
                                          M . M
                                          . A .
                                          S . S
                                    """.trimIndent()
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // S . M
                    // . A .
                    // S . M
                    if (j + 1 < size && i - 1 >= 0 && input[i - 1][j + 1] == 'M') {
                        if (j + 1 < size && i + 1 < size && input[i + 1][j + 1] == 'M') {
                            if (j - 1 >= 0 && i + 1 < size && input[i + 1][j - 1] == 'S') {
                                if (j - 1 >= 0 && i - 1 >= 0 && input[i - 1][j - 1] == 'S') {
                                    count++
                                    if (debug) {
                                        println(
                                            """
                                          ($i,$j)
                                          S . M
                                          . A .
                                          S . M
                                    """.trimIndent()
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // S . S
                    // . A .
                    // M . M
                    if (j + 1 < size && i - 1 >= 0 && input[i - 1][j + 1] == 'S') {
                        if (j + 1 < size && i + 1 < size && input[i + 1][j + 1] == 'M') {
                            if (j - 1 >= 0 && i + 1 < size && input[i + 1][j - 1] == 'M') {
                                if (j - 1 >= 0 && i - 1 >= 0 && input[i - 1][j - 1] == 'S') {
                                    count++
                                    if (debug) {
                                        println(
                                            """
                                          ($i,$j)
                                          S . S
                                          . A .
                                          M . M
                                    """.trimIndent()
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }

        return count
    }
}