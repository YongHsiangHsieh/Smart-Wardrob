package utils

import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString

object JsonUtils {
    val json = Json { prettyPrint = true }

    inline fun <reified T> serializeToJson(data: T): String {
        return json.encodeToString(data)
    }

    inline fun <reified T> deserializeFromJson(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }

}
