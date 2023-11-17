package service

import model.Clothing
import model.ClothingType
import model.Wardrobe

object OutfitSuggester {

    fun suggestOutfit(wardrobe: Wardrobe, currentMonth: Int): List<Clothing> {
        val season = determineSeason(currentMonth)
        return getSeasonalOutfit(season)
    }

    private fun determineSeason(month: Int): Season {
        return when (month) {
            in 2..4 -> Season.SPRING
            in 5..7 -> Season.SUMMER
            in 8..10 -> Season.AUTUMN
            else -> Season.WINTER
        }
    }

    private fun getSeasonalOutfit(season: Season): List<Clothing> {
        return when(season) {
            // TODO: Randomise the selection of clothing
            Season.SPRING -> listOf(
                Clothing(0, ClothingType.TRACKSUIT),
                Clothing(0, ClothingType.SHIRT)
            )
            Season.SUMMER -> listOf(
                Clothing(0, ClothingType.SHIRT),
                Clothing(0, ClothingType.SHORTS)
            )
            Season.AUTUMN -> listOf(
                Clothing(0, ClothingType.JUMPER),
                Clothing(0, ClothingType.TRACKSUIT)
            )
            Season.WINTER -> listOf(
                Clothing(0, ClothingType.JUMPER),
                Clothing(0, ClothingType.TRACKSUIT),
                Clothing(0, ClothingType.JACKET)
            )
        }
    }

    private fun selectRandomOutfit(clothingTypes: List<ClothingType>, wardrobe: Wardrobe): List<Clothing> {
        return clothingTypes.mapNotNull {
            wardrobe.getClothesByType(it).randomOrNull()
        }
    }

}


enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}