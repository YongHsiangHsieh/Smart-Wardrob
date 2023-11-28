package model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import model.*

class UserTest {
    private lateinit var user: User
    private val username = "testUser"
    private val password = "testPass"

    @BeforeEach
    fun setUp() {
        user = User(username, password)
    }

    @Test
    fun `checkPassword returns true for correct password`() {
        assertTrue(user.checkPassword(password))
    }

    @Test
    fun `checkPassword returns false for incorrect password`() {
        assertFalse(user.checkPassword("wrongPassword"))
    }

    @Test
    fun `getWardrobe returns the correct wardrobe instance`() {
        val wardrobe = user.getWardrobe()
        assertNotNull(wardrobe)
    }

    @Test
    fun `toString returns correctly formatted string`() {
        val expectedString = "User: $username, Password=$password\nWardrobe: ${user.getWardrobe()}"
        assertEquals(expectedString, user.toString())
    }

}
