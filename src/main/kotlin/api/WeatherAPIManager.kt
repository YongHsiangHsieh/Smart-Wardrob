//package api
//
//import okhttp3.HttpUrl
//import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
//import okhttp3.OkHttpClient
//import okhttp3.Request
//import java.io.IOException
//
//object WeatherAPIManager {
//    fun getApiResponse(baseUrl: String, params: Map<String, String>): String {
//        val client = OkHttpClient()
//
//        // Build the URL with parameters
//        val httpUrlBuilder = baseUrl.toHttpUrlOrNull()?.newBuilder() ?: throw IllegalArgumentException("Invalid URL")
//        params.forEach { (key, value) ->
//            httpUrlBuilder.addQueryParameter(key, value)
//        }
//        val url = httpUrlBuilder.build()
//
//        val request = Request.Builder()
//            .url(url)
//            .build()
//
//        // Execute the request
//        client.newCall(request).execute().use { response ->
//            if (!response.isSuccessful) throw IOException("Unexpected code $response")
//
//            return response.body?.string() ?: ""
//        }
//    }
//}