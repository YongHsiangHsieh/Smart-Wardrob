import controller.UserAPI
import controller.WardrobeAPI
import controller.WeatherAPI
import io.github.cdimascio.dotenv.dotenv
import persistence.PersistenceManager
import view.ConsoleView

fun main() {
//    val userAPI = UserAPI(PersistenceManager)
//    val wardrobeAPI = WardrobeAPI()
//    val consoleView = ConsoleView(userAPI, wardrobeAPI)
//    consoleView.startApplication()

    val response = WeatherAPI.getApiResponse()
    println(response)


}
