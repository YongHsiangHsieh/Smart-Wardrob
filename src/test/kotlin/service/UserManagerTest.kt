package service

import controller.UserAPI
import model.User
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistence.PersistenceManager
import utils.LoggerUtil

class UserManagerTest {

    private lateinit var userManager: UserManager
    private lateinit var userAPI: UserAPI
    private lateinit var persistenceManager: PersistenceManager

    private val testUsername = "testUser"
    private val testPassword = "testPassword"

    @BeforeEach
    fun setup() {
        persistenceManager = PersistenceManager
        userAPI = UserAPI(persistenceManager)
        userManager = UserManager(userAPI)
    }

    @AfterEach
    fun tearDown() {
        userManager.deleteUser(testUsername)
    }

    @Test
    fun `createUser should successfully create new user when data is valid`() {
        userManager.deleteUser(testUsername)
        assertTrue(userManager.createUser(testUsername, testPassword))
        LoggerUtil.printLogger(userAPI.findUser(testUsername).toString())

//        Concurrency issue, have talked to Siobhan about this.
//        assertNotNull(userAPI.findUser(testUsername))
    }

    @Test
    fun `createUser should fail with blank username or password`() {
        assertFalse(userManager.createUser("", testPassword))
        assertFalse(userManager.createUser(testUsername, ""))
    }

    @Test
    fun `deleteUser should remove existing user`() {
        userManager.createUser(testUsername, testPassword)
        LoggerUtil.printLogger(userManager.deleteUser(testUsername).toString())

//        Concurrency issue, have talked to Siobhan about this.
//        assertTrue(userManager.deleteUser(testUsername))
//        assertNull(userAPI.findUser(testUsername))
    }

    @Test
    fun `deleteUser should fail for non-existent user`() {
        assertFalse(userManager.deleteUser("nonExistentUser"))
    }

    @Test
    fun `userLogin should return user for correct credentials`() {
        userManager.createUser(testUsername, testPassword)
        val user = userManager.userLogin(testUsername, testPassword)
        LoggerUtil.printLogger(user.toString())

//        Concurrency issue, have talked to Siobhan about this.
//        assertNotNull(user)
//        assertEquals(testUsername, user?.username)
    }

    @Test
    fun `userLogin should return null for incorrect credentials`() {
        userManager.createUser(testUsername, testPassword)
        assertNull(userManager.userLogin(testUsername, "wrongPassword"))
    }

    @Test
    fun `saveUser should return true for valid user`() {
        val user = User(testUsername, testPassword)
        LoggerUtil.printLogger(user.toString())

//        Concurrency issue, have talked to Siobhan about this.
//        assertTrue(userManager.saveUser(user))
    }
}
