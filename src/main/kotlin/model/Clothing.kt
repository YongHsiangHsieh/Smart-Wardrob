package model

import kotlinx.serialization.Serializable


@Serializable
enum class ClothingType {
    JUMPER, SHIRT, SHORTS, TRACKSUIT, JACKET
}

@Serializable
data class Clothing(
    val id: Int,
    val type: ClothingType,
    val brand: String,
    val name: String,
    val color: String,
    val texture: String,
)
