package utils

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class IdGeneratorTest {

    @Test
    fun `generateRandomId should return an id within the specified range`() {
        val from = 1
        val to = 1000
        val randomId = IdGenerator.generateRandomId(from, to)

        assertTrue(randomId >= from && randomId < to)
    }
}
