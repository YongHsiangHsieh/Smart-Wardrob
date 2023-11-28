package utils

import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class JsonUtilTest {

    @Serializable
    data class TestDataClass(val name: String, val age: Int)

    @Test
    fun `serializeToJson should correctly serialize objects to JSON strings`() {
        val testData = TestDataClass("John Doe", 30)
        val jsonResult = JsonUtil.serializeToJson(testData)
        val deserializedData = JsonUtil.deserializeFromJson<TestDataClass>(jsonResult)

        assertEquals("John Doe", deserializedData.name)
        assertEquals(30, deserializedData.age)
    }

    @Test
    fun `deserializeFromJson should correctly deserialize JSON strings to objects`() {
        val jsonString = """{"name":"Jane Doe","age":25}"""
        val deserializedObject = JsonUtil.deserializeFromJson<TestDataClass>(jsonString)

        assertEquals("Jane Doe", deserializedObject.name)
        assertEquals(25, deserializedObject.age)
    }
}
