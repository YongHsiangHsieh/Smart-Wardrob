package utils

interface SerializerUtils<T> {
    fun serialize(data: T): String
    fun deserialize(dataString: String, clazz: Class<T>): T
}