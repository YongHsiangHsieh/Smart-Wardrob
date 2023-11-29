package controller

import model.ClothingType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistence.PersistenceManager

class UserAPITest {

    private lateinit var userAPI: UserAPI
    private lateinit var persistenceManager: PersistenceManager

    private val testUsername = "testUser"
    private val testPassword = "testPassword"

    @BeforeEach
    fun setup() {
        persistenceManager = PersistenceManager
        userAPI = UserAPI(persistenceManager)
    }

    @AfterEach
    fun tearDown() {
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

    @Test
    fun `update user's wardrobe should persist changes`() {
        assertTrue(userAPI.createUser(testUsername, testPassword))
        val user = userAPI.findUser(testUsername)
        assertNotNull(user)
        val wardrobeAPI = WardrobeAPI(user!!.getWardrobe())
        val clothingData = mapOf(
            "id" to "1",
            "type" to "SHIRT",
            "brand" to "TestBrand",
            "name" to "TestBrand",
            "color" to "Blue",
            "texture" to "Cotton"
        )
        assertTrue(wardrobeAPI.addClothingToWardrobe(clothingData))
        userAPI.updateUser(user)
        val updatedUser = userAPI.findUser(testUsername)
        assertNotNull(updatedUser)
        val updatedWardrobe = updatedUser!!.getWardrobe()
        assertNotNull(updatedWardrobe)
        assertTrue(updatedWardrobe.getAllClothing().any { it.id == 1 && it.type == ClothingType.SHIRT })
    }
}
