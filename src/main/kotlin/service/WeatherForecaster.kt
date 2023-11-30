package service

import controller.WeatherAPI
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import utils.JsonUtil


object WeatherForecaster {

    fun willRainToday(): Boolean {
        val rawData: String = WeatherAPI.getApiResponse()
        val jsonData = JsonUtil.deserializeFromJson<JsonObject>(rawData)

        return jsonData["hourly"]?.jsonArray?.let { hourlyData ->
            hourlyData.take(minOf(hourlyData.size, 12)).any { hour ->
                val weatherId = hour.jsonObject["weather"]
                    ?.jsonArray
                    ?.firstOrNull()
                    ?.jsonObject
                    ?.get("id")
                    ?.jsonPrimitive
                    ?.intOrNull

                weatherId != null && weatherId < 700
            }
        } ?: false
    }

}