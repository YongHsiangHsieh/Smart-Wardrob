package model

import kotlinx.serialization.Serializable

@Serializable
class User(
    val username: String,
    private val password: String,
    var wardrobe: Wardrobe = Wardrobe()
) {
    fun checkPassword(inputPassword: String): Boolean {
        return inputPassword == password
    }

    override fun toString(): String {
        return "User: $username, Password=$password\nWardrobe: $wardrobe"
    }
}
