package utils

import model.Clothing
import model.ClothingType
import model.Wardrobe
import java.util.*

object OutfitSuggester {

    fun suggestOutfit(wardrobe: Wardrobe, currentMonth: Int): List<Clothing> {
        val season = determineSeason(currentMonth)
        return wardrobe.getSeasonalOutfit(season)
    }

    private fun determineSeason(month: Int): Season {
        return when (month) {
            in 2..4 -> Season.SPRING
            in 5..7 -> Season.SUMMER
            in 8..10 -> Season.AUTUMN
            else -> Season.WINTER
        }
    }

    private fun Wardrobe.getSeasonalOutfit(season: Season): List<Clothing> {
        return listOf()
    }
}

enum class Season {
    SPRING, SUMMER, AUTUMN, WINTER
}