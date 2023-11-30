package utils

import kotlin.random.Random

object IdGenerator {
    fun generateRandomId(from: Int, to: Int): Int {
        return Random.nextInt(from, to)
    }
}