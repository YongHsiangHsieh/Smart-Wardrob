package model

import kotlinx.serialization.Serializable


@Serializable
enum class ClothingType {
    JUMPER, SHIRT, SHORTS, TRACKSUIT, JACKET
}

@Serializable
data class Clothing(
    val id: Int = -1,
    val type: ClothingType = ClothingType.SHIRT,
    val brand: String = "Unknown",
    val name: String = "Unknown",
    val color: String = "Unknown",
    val texture: String = "Unknown",
)
