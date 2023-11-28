package controller

import model.User
import model.Wardrobe
import org.junit.jupiter.api.AfterEach
import persistence.PersistenceManager
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class UserAPITest {

    private lateinit var userAPI: UserAPI
    private lateinit var persistenceManager: PersistenceManager

    private val testUsername = "testUser"
    private val testPassword = "testPassword"

    @BeforeEach
    fun setup() {
        persistenceManager = PersistenceManager // Assuming PersistenceManager is a singleton or similar
        userAPI = UserAPI(persistenceManager)
    }

    @AfterEach
    fun tearDown() {
        // Delete the test user after each test
        userAPI.deleteUser(testUsername)
    }

    @Test
    fun `create user should successfully add new user when not already existing`() {
        assertTrue(userAPI.createUser(testUsername, testPassword))
        assertNotNull(userAPI.findUser(testUsername))
    }

    @Test
    fun `create user should fail to add user if username already exists`() {
        userAPI.createUser(testUsername, testPassword)
        assertFalse(userAPI.createUser(testUsername, testPassword))
    }

    @Test
    fun `delete user should remove existing user successfully`() {
        userAPI.createUser(testUsername, testPassword)
        assertTrue(userAPI.deleteUser(testUsername))
        assertNull(userAPI.findUser(testUsername))
    }

    @Test
    fun `delete user should fail for non-existent users`() {
        assertFalse(userAPI.deleteUser("nonExistentUser"))
    }

    @Test
    fun `find user should retrieve the correct user data`() {
        userAPI.createUser(testUsername, testPassword)
        val foundUser = userAPI.findUser(testUsername)
        assertNotNull(foundUser)
        assertEquals(testUsername, foundUser?.username)
    }

    @Test
    fun `authenticate user should validate correct password`() {
        userAPI.createUser(testUsername, testPassword)
        val user = userAPI.findUser(testUsername)
        assertNotNull(user)
        assertTrue(userAPI.authenticateUser(user!!, testPassword))
    }

    @Test
    fun `authenticate user should reject incorrect password`() {
        userAPI.createUser(testUsername, testPassword)
        val user = userAPI.findUser(testUsername)
        assertNotNull(user)
        assertFalse(userAPI.authenticateUser(user!!, "wrongPassword"))
    }

}
