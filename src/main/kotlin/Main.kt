import controller.UserAPI
import controller.WardrobeAPI
import persistence.PersistenceManager
import view.ConsoleView
import java.util.*

fun main() {
    val userAPI = UserAPI(PersistenceManager)
    val wardrobeAPI = WardrobeAPI()
    val consoleView = ConsoleView(userAPI, wardrobeAPI)
    consoleView.startApplication()

}
