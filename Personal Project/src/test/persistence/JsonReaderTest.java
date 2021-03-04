package persistence;

import model.Cheeseburger;
import model.Pet;
import model.Food;
import model.Tiramisu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
//Format and code referenced from JsonSerializationDemo at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Pet pet = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderDefaultPet() {
        JsonReader reader = new JsonReader("./data/testReaderDefaultPet.json");
        try {
            Pet pet = reader.read();
            assertEquals(pet.initialHunger, pet.getHunger());
            assertEquals(pet.initialEnergy, pet.getEnergy());
            assertEquals(pet.initialHappiness, pet.getHappiness());
            assertEquals(0, pet.getFavoriteFoods().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralPet() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPet.json");
        try {
            Tiramisu t = new Tiramisu();
            Cheeseburger c = new Cheeseburger();
            Pet pet = reader.read();


            assertEquals(80, pet.getHunger());
            assertEquals(80, pet.getEnergy());
            assertEquals(80, pet.getHappiness());
            assertEquals(2, pet.getFavoriteFoods().size());
            assertEquals("Your pet's favorite foods are: \n" +
                    "Tiramisu:\n" +
                    " A rich, coffee-flavored cake, that is somehow edible to non-humans. Great for happiness and energy.\n" +
                    "Cheeseburger:\n" +
                    " A hearty, somehow not-unhealthy food for any discerning pet. Restores lots of hunger and some happiness.\n",pet.checkFavoriteFoodsPet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


    @Test
    void testReaderPetFoodSkipped() {
        JsonReader reader = new JsonReader("./data/testReaderPetFoodSkipped.json");
        try {
            Pet pet = reader.read();


            assertEquals(44, pet.getHunger());
            assertEquals(44, pet.getEnergy());
            assertEquals(44, pet.getHappiness());
            assertEquals(2, pet.getFavoriteFoods().size());
            assertEquals("Your pet's favorite foods are: \n" +
                    "Cheeseburger:\n" +
                    " A hearty, somehow not-unhealthy food for any discerning pet. Restores lots of hunger and some happiness.\n" +
                    "Tiramisu:\n" +
                    " A rich, coffee-flavored cake, that is somehow edible to non-humans. Great for happiness and energy.\n",pet.checkFavoriteFoodsPet());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}