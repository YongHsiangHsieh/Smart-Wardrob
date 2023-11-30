package service

import controller.UserAPI
import model.User
import model.Wardrobe
import utils.LoggerUtil.printLogger

class UserManager(private val userAPI: UserAPI) {
    fun createUser(username: String, password: String): Boolean =
        if (username.isBlank() || password.isBlank()) {
            false
        } else userAPI.createUser(username, password)

    fun deleteUser(username: String): Boolean =
        if (username.isBlank()) {
            false
        } else userAPI.deleteUser(username)

    fun userLogin(username: String, password: String): User? {
        if (username.isBlank() || password.isBlank()) {
            return null
        }
        return userAPI.findUser(username)?.takeIf { userAPI.authenticateUser(it, password) }
    }


}