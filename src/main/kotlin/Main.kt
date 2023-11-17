import controller.UserAPI
import controller.WardrobeAPI
import persistence.PersistenceManager
import view.ConsoleView

fun main(args: Array<String>) {
    val userAPI = UserAPI(PersistenceManager)
    val wardrobeAPI = WardrobeAPI()
    val consoleView = ConsoleView(userAPI, wardrobeAPI)

    consoleView.startApplication()

}