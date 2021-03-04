package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class PetTest {


    @Test
    void testFeedCheeseburger() {
        Pet p = new Pet();
        Cheeseburger cheeseburger = new Cheeseburger();
        p.feedPet(cheeseburger);
        assertEquals(p.initialHunger + cheeseburger.getHungerRestored(), p.getHunger());
        assertEquals(p.initialHappiness + cheeseburger.getHappinessRestored(), p.getHappiness());
        assertEquals(p.initialEnergy + cheeseburger.getEnergyRestored(), p.getEnergy());
    }

    @Test
    void testFeedTiramisu() {
        Pet p = new Pet();
        Tiramisu tiramisu = new Tiramisu();
        p.feedPet(tiramisu);
        assertEquals(p.initialHunger + tiramisu.getHungerRestored(), p.getHunger());
        assertEquals(p.initialHappiness + tiramisu.getHappinessRestored(), p.getHappiness());
        assertEquals(p.initialEnergy + tiramisu.getEnergyRestored(), p.getEnergy());
    }

    @Test
    void testPlayNormal() {
        Pet p = new Pet();
        p.playWithPet();
        assertEquals(p.initialHunger - p.hungerLostPerHour, p.getHunger());
        assertEquals(p.initialHappiness + p.happinessGainedPlay, p.getHappiness());
        assertEquals(p.initialEnergy - p.energyUsedPlay, p.getEnergy());
    }

    @Test
    void testWalkNormal() {
        Pet p = new Pet();
        p.walkPet();
        assertEquals(p.initialHunger - p.hungerLostPerHour, p.getHunger());
        assertEquals(p.initialHappiness + p.happinessGainedWalk, p.getHappiness());
        assertEquals(p.initialEnergy - p.energyUsedWalk, p.getEnergy());

    }

    @Test
    void testRestNormal() {
        Pet p = new Pet();
        p.restPet();
        assertEquals(p.initialEnergy+p.energyGainedResting,p.getEnergy());
        assertEquals(p.initialHunger-p.hungerLostPerHour,p.getHunger());
        assertEquals(p.initialHappiness,p.getHappiness());
    }

    @Test
    void testCheckStatusDefault() {
        Pet p = new Pet();
        assertEquals("Your pet is very happy! You're a great owner!", p.checkHappinessPet());
        assertEquals("Your pet is not hungry.", p.checkHungerPet());
        assertEquals("Your pet is full of energy! Take them out on a walk to burn off that energy!", p.checkEnergyPet());
        assertEquals("Your pet has no favorite foods.", p.checkFavoriteFoodsPet());
    }
    @Test
    void testCheckStatusSadStarvingExhausted() {
        Pet p = new Pet();
        p.setHappiness(p.sadThreshold - 10);
        p.setHunger(p.starvingThreshold - 10);
        p.setEnergy(p.exhaustedThreshold - 10);
        assertEquals("Your pet is sad! Play with them or take them on a walk!\n" +
                "Your pet is still starving!\n" +
                "Your pet is exhausted! Let them rest for some time.\n" +
                "Your pet has no favorite foods.",p.checkStatusPet());
    }
    @Test
    void testCheckStatusBoredHungryTired() {
        Pet p = new Pet();
        p.setHappiness(p.boredThreshold - 10);
        p.setHunger(p.hungryThreshold - 10);
        p.setEnergy(p.tiredThreshold - 10);
        assertEquals("Your pet is a little bored.\n" +
                "Your pet is still hungry.\n" +
                "Your pet is a little tired.\n" +
                "Your pet has no favorite foods.",p.checkStatusPet());
    }

    @Test
    void testAddFavoriteFood() {
        Pet p = new Pet();
        Tiramisu t = new Tiramisu();
        Cheeseburger c = new Cheeseburger();
        assertTrue(p.favoriteFoods.isEmpty());
        assertEquals("Your pet's new favorite food is Tiramisu ", p.addFavoriteFood(t));
        assertEquals(1, p.favoriteFoods.size());
        assertEquals("Your pet's new favorite food is Cheeseburger ", p.addFavoriteFood(c));
        assertEquals(2, p.favoriteFoods.size());
        System.out.println(p.checkFavoriteFoodsPet());
    }

    @Test
    void testRemoveFavoriteFood() {
        Pet p = new Pet();
        Tiramisu t = new Tiramisu();
        Cheeseburger c = new Cheeseburger();
        assertTrue(p.favoriteFoods.isEmpty());
        assertEquals("Your pet's new favorite food is Tiramisu ", p.addFavoriteFood(t));
        assertEquals(1, p.favoriteFoods.size());
        assertEquals("Your pet's new favorite food is Cheeseburger ", p.addFavoriteFood(c));
        assertEquals(2, p.favoriteFoods.size());
        System.out.println(p.checkFavoriteFoodsPet());
        assertEquals("Tiramisu is no longer a favorite food of your pet.", p.removeFavoriteFood(t));
        assertEquals(1, p.favoriteFoods.size());
    }
}