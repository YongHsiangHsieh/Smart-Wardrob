package utils

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.RepeatedTest

class IdGeneratorTest {

    @Test
    fun `generateRandomId should return an id within the specified range`() {
        val from = 1
        val to = 1000
        val randomId = IdGenerator.generateRandomId(from, to)

        assertTrue(randomId in from..<to)
    }

    @RepeatedTest(100)
    fun `generateRandomId should return diverse ids over multiple calls`() {
        val from = 1
        val to = 1000
        val idSet = mutableSetOf<Int>()

        repeat(100) {
            idSet.add(IdGenerator.generateRandomId(from, to))
        }

        assertTrue(idSet.size > 50)
    }
}
