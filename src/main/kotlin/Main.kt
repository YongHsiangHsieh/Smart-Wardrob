import utils.JsonUtils
import model.*

fun main(args: Array<String>) {
    val cloth1 = Clothing(1, ClothingType.JUMPER, "Nike", "Nike Jumper", "Red", "Cotton")
    println(cloth1)

    val user1 = User("user1", "abc")
    user1.wardrobe.addClothing(cloth1)
    println(user1.toString())

    val wardrobe1 = Wardrobe()
    wardrobe1.addClothing(cloth1)
    println(wardrobe1)

}