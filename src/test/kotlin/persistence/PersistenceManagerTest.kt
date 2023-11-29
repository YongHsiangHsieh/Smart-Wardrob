package persistence

import model.User
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.*

class PersistenceManagerTest {

    private val testDirectory = "test_data/users/"
    private val testUsername = "testUser"
    private val testPassword = "testPassword"
    private val rootTestDirectory = "test_data"

    @BeforeEach
    fun setup() {
        File(testDirectory).mkdirs()
        PersistenceManager.USER_DATA_PATH = testDirectory
    }

    @AfterEach
    fun tearDown() {
        val testDir = File(testDirectory)
        if (testDir.exists()) {
            testDir.deleteRecursively()
        }

        val rootDir = File(rootTestDirectory)
        if (rootDir.exists()) {
            val deleted = rootDir.delete()
            assertTrue(deleted, "Root test directory should be deleted")
        }
    }

    @Test
    fun `saveUserData should create a file with user data`() {
        val user = User(testUsername, testPassword)
        PersistenceManager.saveUserData(user)

        val userFilePath = "${testDirectory}$testUsername.json"
        val userFile = File(userFilePath)
        assertTrue(userFile.exists(), "User file should exist after saving user data")
    }

    @Test
    fun `loadUserData should retrieve the correct user data`() {
        val user = User(testUsername, testPassword)
        PersistenceManager.saveUserData(user)

        val loadedUser = PersistenceManager.loadUserData(testUsername)
        assertNotNull(loadedUser)
        assertEquals(testUsername, loadedUser.username)
    }

    @Test
    fun `deleteUserData should remove the user file`() {
        val user = User(testUsername, testPassword)
        PersistenceManager.saveUserData(user)

        PersistenceManager.deleteUserData(testUsername)
        val userFile = File("${PersistenceManager.USER_DATA_PATH}/$testUsername.json")
        assertFalse(userFile.exists())
    }
}
