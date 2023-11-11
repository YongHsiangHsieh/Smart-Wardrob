import utils.JsonUtils
import model.*

fun main(args: Array<String>) {
    val cloth1 = Clothing(1, model.ClothingType.JUMPER, "Nike", "Nike Jumper", "Black", "Cotton")
    val cloth2 = Clothing(2, model.ClothingType.SHIRT, "Adidas", "Adidas Shirt", "White", "Cotton")
    val cloth3 = Clothing(3, model.ClothingType.SHORTS, "Puma", "Puma Shorts", "Blue", "Cotton")
    val cloth4 = Clothing(4, model.ClothingType.TRACKSUIT, "Reebok", "Reebok Tracksuit", "Red", "Cotton")
    val cloths = listOf(cloth1, cloth2, cloth3, cloth4)
    for (cloth in cloths) {
        println(cloth)
    }
    val jsonCloths = JsonUtils.serializeToJson(cloths)
    println(jsonCloths)
    val clothsFromJson = JsonUtils.deserializeFromJson<List<Clothing>>(jsonCloths)
    for (cloth in clothsFromJson) {
        println(cloth)
    }
}