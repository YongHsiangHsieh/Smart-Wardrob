package service

import controller.WardrobeAPI
import model.ClothingType
import model.Wardrobe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WardrobeManagerTest {

    private lateinit var wardrobeManager: WardrobeManager
    private lateinit var wardrobeAPI: WardrobeAPI
    private lateinit var wardrobe: Wardrobe

    @BeforeEach
    fun setup() {
        wardrobe = Wardrobe()
        wardrobeAPI = WardrobeAPI(wardrobe)
        wardrobeManager = WardrobeManager(wardrobeAPI)
    }

    @Test
    fun `addClothing should successfully add valid clothing`() {
        val initialCount = wardrobeManager.getAllClothing().size
        assertTrue(wardrobeManager.addClothing(2, "Brand", "Name", "Color", "Texture"))

        val newCount = wardrobeManager.getAllClothing().size
        assertTrue(newCount > initialCount) // Check if a new item was added
    }

    @Test
    fun `addClothing should fail for invalid data`() {
        assertFalse(wardrobeManager.addClothing(-1, "", "Name", "Color", "Texture"))
        assertFalse(wardrobeManager.addClothing(2, "Brand", "", "Color", "Texture"))
    }

    @Test
    fun `updateClothing should update clothing for valid data`() {
        wardrobeManager.addClothing(2, "Brand", "Name", "Color", "Texture")
        val lastAddedClothing = wardrobeManager.getAllClothing().last()
        assertTrue(wardrobeManager.updateClothing("NewColor", "NewTexture", lastAddedClothing.id))

        val updatedClothing = wardrobeAPI.getClothingById(lastAddedClothing.id)
        assertEquals("NewColor", updatedClothing?.color)
    }

    @Test
    fun `updateClothing should fail for invalid data`() {
        assertFalse(wardrobeManager.updateClothing("", "Texture", 1))
        assertFalse(wardrobeManager.updateClothing("Color", "", -1))
    }

    @Test
    fun `removeClothing should remove existing clothing`() {
        wardrobeManager.addClothing(2, "Brand", "Name", "Color", "Texture")
        val lastAddedClothing = wardrobeManager.getAllClothing().last()
        assertTrue(wardrobeManager.removeClothing(lastAddedClothing.id))

        assertNull(wardrobeAPI.getClothingById(lastAddedClothing.id))
    }

    @Test
    fun `removeClothing should fail for non-existing or invalid clothing`() {
        assertFalse(wardrobeManager.removeClothing(-1))
    }

    @Test
    fun `getAllClothing should retrieve clothing list`() {
        wardrobeManager.addClothing(2, "Brand", "Name", "Color", "Texture")
        val clothingList = wardrobeManager.getAllClothing()
        assertFalse(clothingList.isEmpty())
        assertEquals(1, clothingList.size)
    }

    @Test
    fun `getClothingByType should retrieve clothing list of specified type`() {
        wardrobeManager.addClothing(2, "Brand", "Name", "Color", "Texture") // Type 2 corresponds to SHIRT
        val clothingList = wardrobeManager.getClothingByType(2) // Requesting SHIRT type
        assertFalse(clothingList.isEmpty())
        assertTrue(clothingList.all { it.type == ClothingType.SHIRT })
    }

    @Test
    fun `getClothingByTypeAndColor should retrieve clothing list of specified type and color`() {
        wardrobeManager.addClothing(2, "Brand", "Name", "Blue", "Texture")
        val clothingList = wardrobeManager.getClothingByTypeAndColor(2, "Blue") // Requesting SHIRT type, Blue color
        assertFalse(clothingList.isEmpty())
        assertTrue(clothingList.all { it.type == ClothingType.SHIRT && it.color == "Blue" })
    }

    @Test
    fun `getClothingByTypeAndColor should return empty list for invalid color`() {
        assertTrue(wardrobeManager.getClothingByTypeAndColor(2, "").isEmpty())
    }

    @Test
    fun `convertToClothingType should return correct ClothingType`() {
        assertEquals(ClothingType.JUMPER, wardrobeManager.convertToClothingType(1))
        assertEquals(ClothingType.SHIRT, wardrobeManager.convertToClothingType(2))
        assertEquals(ClothingType.UNKNOWN, wardrobeManager.convertToClothingType(99))
    }

    @Test
    fun `setWardrobe should update the wardrobe managed by WardrobeAPI`() {
        val newWardrobe = Wardrobe()
        wardrobeManager.setWardrobe(newWardrobe)
        assertSame(newWardrobe.getAllClothing(), wardrobeManager.getAllClothing())
    }
}
