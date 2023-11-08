package model

class User(
    val username: String,
    private var password: String,
    val wardrobe: Wardrobe = Wardrobe()
) {
    fun checkPassword(inputPassword: String): Boolean {
        return inputPassword == password
    }
}
