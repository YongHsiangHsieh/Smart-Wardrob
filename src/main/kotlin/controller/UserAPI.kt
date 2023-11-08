package controller

import model.User
import model.Wardrobe


class UserAPI() {

    private val users: MutableMap<String, User> = mutableMapOf()

    fun createUser(username: String, password: String): User {
       return User(username, password)
    }

    fun deleteUser(username: String) {
    }

    fun getUser(username: String): User? {
        return users[username]
    }

    fun authenticateUser(username: String, password: String): Boolean {
        return false
    }

    fun updateUserWardrobe(username: String, wardrobe: Wardrobe) {
    }
}
