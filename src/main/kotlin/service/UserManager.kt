package service

import controller.UserAPI
import model.User
import utils.LoggerUtil.printLogger

class UserManager (private val userAPI: UserAPI) {
    fun createUser(username: String, password: String) {
        val success = userAPI.createUser(username, password)
        if (success) {
            printLogger("User created successfully")
        } else {
            printLogger("User already exists")
        }
    }

     fun deleteUser(username: String) {
        val success = userAPI.deleteUser(username)
        if (success) {
            printLogger("User deleted successfully")
        } else {
            printLogger("User does not exist")
        }
    }

    fun userLogin(username: String, password: String): User? {
        return userAPI.findUser(username)?.takeIf { userAPI.authenticateUser(it, password) }
            ?: printLogger("Invalid username or password").let { null }
    }

}