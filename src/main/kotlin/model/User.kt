package model

import kotlinx.serialization.Serializable

@Serializable
class User(
    val username: String,
    private val password: String,
    private var wardrobe: Wardrobe = Wardrobe()
) {
    fun checkPassword(inputPassword: String): Boolean {
        return inputPassword == password
    }
    fun getWardrobe(): Wardrobe {
        return wardrobe
    }
    override fun toString(): String {
        return "User: $username, Password=$password\nWardrobe: $wardrobe"
    }
}
