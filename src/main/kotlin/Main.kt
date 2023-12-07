import controller.UserAPI
import controller.WardrobeAPI
import persistence.PersistenceManager
import view.ConsoleView

fun main() {
    val userAPI = UserAPI(PersistenceManager)
    val wardrobeAPI = WardrobeAPI()
    val consoleView = ConsoleView(userAPI, wardrobeAPI)
    consoleView.startApplication()
    // b6770f78e47769f1287c2dc3268b309f
}
