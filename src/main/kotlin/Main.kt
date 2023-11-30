import controller.UserAPI
import controller.WardrobeAPI
import controller.WeatherAPI
import io.github.cdimascio.dotenv.dotenv
import kotlinx.serialization.json.JsonObject
import persistence.PersistenceManager
import service.WeatherForecaster
import utils.JsonUtil
import view.ConsoleView

fun main() {
//    val userAPI = UserAPI(PersistenceManager)
//    val wardrobeAPI = WardrobeAPI()
//    val consoleView = ConsoleView(userAPI, wardrobeAPI)
//    consoleView.startApplication()

//    val response = WeatherAPI.getApiResponse()
////    println(response)
//    val jason = JsonUtil.deserializeFromJson<JsonObject>(response)
//    println(jason)

    println(WeatherForecaster.willRainToday())


}
