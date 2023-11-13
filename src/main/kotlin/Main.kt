import persistence.PersistenceManager
import model.*
import utils.*
import controller.*
import view.*

fun main(args: Array<String>) {
    val cloth1 = Clothing(1, ClothingType.JUMPER, "Nike", "Nike Jumper", "Red", "Cotton")
    val cloth2 = Clothing(2, ClothingType.SHIRT, "Adidas", "Adidas Shirt", "Blue", "Cotton")
    val cloth3 = Clothing(3, ClothingType.SHORTS, "Puma", "Puma Shorts", "Black", "Cotton")
//    println(cloth1)

    val user1 = User("user1", "abc")
    val user2 = User("user2", "def")
    user1.wardrobe.addClothing(cloth1)
    user1.wardrobe.addClothing(cloth2)
    user1.wardrobe.addClothing(cloth3)
    user2.wardrobe.addClothing(cloth1)
    user2.wardrobe.addClothing(cloth2)
    user2.wardrobe.addClothing(cloth3)
//    println(user1.toString())

    val wardrobe1 = Wardrobe()
//    wardrobe1.addClothing(cloth1)
//    println(wardrobe1)

//    val user1Json = JsonUtils.serializeToJson(user1)
//    println(user1Json)
//
//    val user1Obj: User = JsonUtils.deserializeFromJson(user1Json)
//    println(user1Obj)

//    println(PersistenceManager.saveUserData(user1))
//    println(PersistenceManager.saveUserData(user2))

    val u1 = PersistenceManager.loadUserData("user1")
//    if (u1 != null) {
//        for (c in u1.wardrobe.clothes) {
//            println(c)
//        }
//    }

    val outfit = u1?.let { OutfitSuggester.suggestOutfit(it.wardrobe, 8) }
//    println(outfit)
    val wardrobe = controller.WardrobeAPI(wardrobe1)
    val users = controller.UserAPI(PersistenceManager)
    val view = ConsoleView(users, wardrobe)

    view.displayMainMenu()


}