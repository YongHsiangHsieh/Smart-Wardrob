package utils

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Utility object for JSON serialization and deserialization.
 * Uses Kotlin serialization for converting objects to JSON strings and vice versa.
 */
object JsonUtil {
    // A Json instance with pretty printing enabled.
    val json = Json { prettyPrint = true }

    /**
     * Serializes an object of any type to a JSON string.
     *
     * The function uses inline reification to preserve type information at runtime.
     *
     * @param T The type of the object being serialized.
     * @param data The object to be serialized.
     * @return A JSON string representation of the object.
     */
    inline fun <reified T> serializeToJson(data: T): String {
        return json.encodeToString(data)
    }

    /**
     * Deserializes a JSON string to an object of any type.
     *
     * The function uses inline reification to infer the type of the object at runtime.
     *
     * @param T The type of the object to which the JSON string is to be deserialized.
     * @param jsonString The JSON string to be deserialized.
     * @return An object of type T represented by the JSON string.
     */
    inline fun <reified T> deserializeFromJson(jsonString: String): T {
        return json.decodeFromString(jsonString)
    }
}
