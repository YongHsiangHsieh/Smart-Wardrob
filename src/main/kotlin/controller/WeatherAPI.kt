package controller

import io.github.cdimascio.dotenv.dotenv
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
object WeatherAPI {
    private val dotenv = dotenv()
    private const val BASE_URL = "https://api.openweathermap.org/data/3.0/onecall"
    private val parameters = mutableMapOf<String, String>(
        "lat" to "52.259319",
        "lon" to "-7.110070",
        "exclude" to "current,minutely,daily"
    )

    fun getApiResponse(): String {
        val apiKey = dotenv["OPEN_WEATHER_API_KEY"]
        if (apiKey.isNullOrEmpty()) {
            return ""
        }

        parameters["appid"] = apiKey

        val client = OkHttpClient()

        val httpUrlBuilder = BASE_URL.toHttpUrlOrNull()?.newBuilder() ?: throw IllegalArgumentException("Invalid URL")
        parameters.forEach { (key, value) ->
            httpUrlBuilder.addQueryParameter(key, value)
        }
        val url = httpUrlBuilder.build()

        val request = Request.Builder()
            .url(url)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            return response.body?.string() ?: ""
        }
    }
}
