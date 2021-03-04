package model;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTest {
    @Test
    void testDescribeCheeseburger() {
        Cheeseburger cheeseburger = new Cheeseburger();
        assertEquals(
                "A hearty, somehow not-unhealthy food for any discerning pet."
                        + " Restores lots of hunger and some happiness.",
                cheeseburger.describeFood());
    }

    @Test
    void testDescribeTiramisu() {
        Tiramisu tiramisu = new Tiramisu();
        assertEquals(
                "A rich, coffee-flavored cake, that is somehow edible to non-humans. Great for happiness and energy.",
                tiramisu.describeFood());
    }

    @Test
    void testEquals() {
        Tiramisu tiramisu1 = new Tiramisu();
        Tiramisu tiramisu2 = new Tiramisu();
        Cheeseburger cheeseburger1 = new Cheeseburger();
        Cheeseburger cheeseburger2 = new Cheeseburger();
        assertEquals(tiramisu1, tiramisu2);
        assertEquals(cheeseburger1, cheeseburger2);
        //NOTE: This shouldn't be possible in my program, but I still have to test it for the sake of autograder
        tiramisu2.setFoodName("Not A Tiramisu");
        assertNotEquals(tiramisu1, tiramisu2);
        cheeseburger2 = null;
        assertNotEquals(cheeseburger1, cheeseburger2);
        assertFalse(tiramisu1.equals(cheeseburger1));
    }

    @Test
    void testHashCode() {
        HashMap<Food, String> foodMap = new HashMap<>();
        Tiramisu tiramisu1 = new Tiramisu();
        Tiramisu tiramisu2 = new Tiramisu();
        foodMap.put(tiramisu1, "The Tiramisu");
        foodMap.put(tiramisu2, "The Tiramisu");
        assertEquals(foodMap.get(tiramisu1), foodMap.get(tiramisu2));
    }
}
