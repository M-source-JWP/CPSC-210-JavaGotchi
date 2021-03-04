package persistence;

import model.Food;
import model.Pet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.LinkedList;

public class JsonTest {
    //Format and code referenced from JsonSerializationDemo at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    protected void checkPet(Pet p, int happiness, int hunger, int energy, LinkedList<Food> favoriteFoods) {
        assertEquals(happiness,p.getHappiness());
        assertEquals(hunger,p.getHunger());
        assertEquals(energy,p.getEnergy());
        assertEquals(favoriteFoods,p.getFavoriteFoods());
    }
}
