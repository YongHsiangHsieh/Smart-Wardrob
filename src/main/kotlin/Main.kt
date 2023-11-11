import utils.JsonUtils
import model.*

fun main(args: Array<String>) {
    val cloths = Clothing(1, model.ClothingType.JUMPER, "Nike", "Nike Jumper", "Black", "Cotton")
    val jsonCloths = JsonUtils.serializeToJson(cloths)
    println(cloths)
    println(jsonCloths)
}