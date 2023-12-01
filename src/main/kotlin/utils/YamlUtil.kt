package utils

import org.yaml.snakeyaml.Yaml
import java.io.StringWriter

/**
 * Utility object for YAML serialization and deserialization.
 *
 * This utility uses the SnakeYAML library to convert Kotlin objects to YAML strings
 * and vice versa. It provides a simple interface to work with YAML data.
 */
object YamlUtil {

    private val yaml = Yaml()

    /**
     * Serializes the provided data object to a YAML string.
     *
     * This method converts a Kotlin object into its YAML representation.
     * The serialization process uses the SnakeYAML library.
     *
     * @param T The type of the object being serialized.
     * @param data The object to serialize.
     * @return The YAML string representation of the provided object.
     */
    fun <T> serializeToYaml(data: T): String {
        val writer = StringWriter()
        yaml.dump(data, writer)
        return writer.toString()
    }

    /**
     * Deserializes a YAML string to an object of the specified type.
     *
     * This method reconstructs an object of type T from its YAML representation.
     * The deserialization process uses the SnakeYAML library and requires the
     * class type to be explicitly provided.
     *
     * @param T The type of the object to be deserialized.
     * @param yamlString The YAML string to be deserialized.
     * @param type The Class object corresponding to the type T.
     * @return An object of type T represented by the provided YAML string.
     * @throws ClassCastException if the yamlString cannot be cast to the specified type T.
     */
    fun <T> deserializeFromYaml(yamlString: String, type: Class<T>): T {
        return yaml.loadAs(yamlString, type)
    }
}
