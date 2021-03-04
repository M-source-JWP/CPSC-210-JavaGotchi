package persistence;

import model.Food;
import model.Pet;
import model.Tiramisu;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //Format and code referenced from JsonSerializationDemo at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Test
    void testWriterInvalidFile() {
        try {
            Pet pet = new Pet();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPet() {
        try {
            Pet pet = new Pet();
            JsonWriter writer = new JsonWriter("./data/testWriterDefaultPet.json");
            writer.open();
            writer.write(pet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterDefaultPet.json");
            pet = reader.read();
            assertEquals(pet.initialHunger, pet.getHunger());
            assertEquals(pet.initialEnergy, pet.getEnergy());
            assertEquals(pet.initialHappiness, pet.getHappiness());
            assertEquals(0, pet.getFavoriteFoods().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPet() {
        try {
            Pet pet = new Pet();
            Tiramisu t = new Tiramisu();
            pet.addFavoriteFood(t);
            pet.walkPet();
            List<Food> favFoods= new LinkedList<>();
            favFoods.add(t);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPet.json");
            writer.open();
            writer.write(pet);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPet.json");
            pet = reader.read();
            assertEquals(pet.initialHunger - pet.hungerLostPerHour, pet.getHunger());
            assertEquals(pet.initialEnergy - pet.energyUsedWalk, pet.getEnergy());
            assertEquals(pet.initialHappiness + pet.happinessGainedWalk, pet.getHappiness());
            assertEquals(1, pet.getFavoriteFoods().size());
            assertEquals("Your pet's favorite foods are: \n" +
                    "Tiramisu:\n" +
                    " A rich, coffee-flavored cake, that is somehow edible to non-humans. Great for happiness and energy.\n",pet.checkFavoriteFoodsPet());


        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}