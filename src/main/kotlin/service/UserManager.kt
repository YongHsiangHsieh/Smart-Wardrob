package service

import controller.UserAPI
import model.User
import utils.LoggerUtil.printLogger

class UserManager(private val userAPI: UserAPI) {
    fun createUser(username: String, password: String): Boolean = userAPI.createUser(username, password)

    fun deleteUser(username: String): Boolean = userAPI.deleteUser(username)

    fun userLogin(username: String, password: String): User? {
        return userAPI.findUser(username)?.takeIf { userAPI.authenticateUser(it, password) }
            ?: printLogger("Invalid username or password").let { null }
    }

}