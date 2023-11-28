package controller

import model.ClothingType
import model.Wardrobe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class WardrobeAPITest {

    private lateinit var wardrobe: Wardrobe
    private lateinit var wardrobeAPI: WardrobeAPI
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
    fun `add clothing to wardrobe should successfully add item`() {
        assertTrue(wardrobeAPI.addClothingToWardrobe(testClothingData))
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
    fun `delete clothing from wardrobe should successfully remove item`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertTrue(wardrobeAPI.deleteClothingFromWardrobe(1))
        assertNull(wardrobeAPI.getClothingById(1))
    }

    @Test
    fun `get all clothing should return correct number of items`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertEquals(1, wardrobeAPI.getAllClothing().size)
    }

    @Test
    fun `get clothing by ID should return the correct item`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertNotNull(wardrobeAPI.getClothingById(1))
    }

    @Test
    fun `get clothing by type should return items of specified type`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertEquals(1, wardrobeAPI.getClothingByType(ClothingType.SHIRT).size)
    }

    @Test
    fun `search clothing by color and type should return matching items`() {
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertEquals(1, wardrobeAPI.searchClothingByColorAndType("Red", ClothingType.SHIRT).size)
    }

    @Test
    fun `set wardrobe should correctly update the wardrobe instance`() {
        val newWardrobe = Wardrobe()
        wardrobeAPI.setWardrobe(newWardrobe)
        // Perform some operations to verify the wardrobe instance is updated
        wardrobeAPI.addClothingToWardrobe(testClothingData)
        assertEquals(1, newWardrobe.getAllClothing().size)
    }
}
