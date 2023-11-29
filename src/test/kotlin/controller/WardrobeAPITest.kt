package controller

import model.ClothingType
import model.Wardrobe
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Assertions.assertSame
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WardrobeAPITest {

    private lateinit var wardrobeAPI: WardrobeAPI
    private lateinit var wardrobe: Wardrobe
    private val testClothingData = mapOf(
        "id" to "1",
        "type" to "SHIRT",
        "brand" to "TestBrand",
        "name" to "TestName",
        "color" to "Red",
        "texture" to "Cotton"
    )

    @BeforeEach
    fun setup() {
        wardrobe = Wardrobe()
        wardrobeAPI = WardrobeAPI(wardrobe)
    }

    @Test
    fun `add clothing to wardrobe should successfully add clothing item`() {
        assertTrue(wardrobeAPI.addClothingToWardrobe(testClothingData))
        assertEquals(1, wardrobeAPI.getAllClothing().size)
    }

    @Test
    fun `update clothing in wardrobe should correctly update clothing attributes`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertTrue(wardrobeAPI.updateClothingInWardrobe(1, mapOf("color" to "Blue", "texture" to "Silk")))
        val updatedClothing = wardrobeAPI.getClothingById(1)
        assertEquals("Blue", updatedClothing?.color)
        assertEquals("Silk", updatedClothing?.texture)
    }

    @Test
    fun `delete clothing from wardrobe should remove the clothing item`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertTrue(wardrobeAPI.deleteClothingFromWardrobe(1))
        assertNull(wardrobeAPI.getClothingById(1))
    }

    @Test
    fun `get all clothing should return all clothing items in the wardrobe`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertEquals(1, wardrobeAPI.getAllClothing().size)
    }

    @Test
    fun `get clothing by ID should return the correct clothing item`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        val clothing = wardrobeAPI.getClothingById(1)
        assertNotNull(clothing)
        assertEquals("TestName", clothing?.name)
    }

    @Test
    fun `get clothing by type should return all clothing of specified type`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        val clothingList = wardrobeAPI.getClothingByType(ClothingType.SHIRT)
        assertTrue(clothingList.all { it.type == ClothingType.SHIRT })
    }

    @Test
    fun `search clothing by color and type should return matching clothing items`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        val searchResult = wardrobeAPI.searchClothingByColorAndType("Red", ClothingType.SHIRT)
        assertTrue(searchResult.all { it.color == "Red" && it.type == ClothingType.SHIRT })
    }

    @Test
    fun `set wardrobe should correctly update the wardrobe instance`() {
        val newWardrobe = Wardrobe()
        wardrobeAPI.setWardrobe(newWardrobe)
        assertSame(newWardrobe.getAllClothing(), wardrobeAPI.getAllClothing())
    }
}
