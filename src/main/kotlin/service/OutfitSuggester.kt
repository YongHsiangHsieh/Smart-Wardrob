package service

import model.Clothing
import model.ClothingType
import model.Wardrobe

object OutfitSuggester {

    fun suggestOutfit(wardrobe: Wardrobe, currentMonth: Int): List<Clothing> {
        val season = determineSeason(currentMonth)
        return getSeasonalOutfit(season, wardrobe)
    }

    private fun determineSeason(month: Int): Season {
        return when (month) {
            in 2..4 -> Season.SPRING
            in 5..7 -> Season.SUMMER
            in 8..10 -> Season.AUTUMN
            else -> Season.WINTER
        }
    }


    private fun selectRandomOutfit(clothingTypes: List<ClothingType>, wardrobe: Wardrobe): List<Clothing> {
        return clothingTypes.mapNotNull {
            wardrobe.getClothesByType(it).randomOrNull()
        }
    }

    private fun getSeasonalOutfit(season: Season, wardrobe: Wardrobe): List<Clothing> {
        return when (season) {
            Season.SPRING -> selectRandomOutfit(listOf(ClothingType.TRACKSUIT, ClothingType.SHIRT), wardrobe)
            Season.SUMMER -> selectRandomOutfit(listOf(ClothingType.SHIRT, ClothingType.SHORTS), wardrobe)
            Season.AUTUMN -> selectRandomOutfit(listOf(ClothingType.JUMPER, ClothingType.TRACKSUIT), wardrobe)
            Season.WINTER -> selectRandomOutfit(listOf(ClothingType.JUMPER, ClothingType.TRACKSUIT, ClothingType.JACKET), wardrobe)
        }
    }


}


enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}