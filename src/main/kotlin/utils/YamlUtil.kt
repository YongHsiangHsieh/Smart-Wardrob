package utils

import model.Wardrobe
import org.yaml.snakeyaml.Yaml
import java.io.StringWriter


object YamlUtil {

    private val yaml = Yaml()

    fun <T> serializeToYaml(data: T): String {
        val writer = StringWriter()
        yaml.dump(data, writer)
        return writer.toString()
    }

    fun <T> deserializeFromYaml(yamlString: String, type: Class<T>): T {
        return yaml.loadAs(yamlString, type)
    }



}
