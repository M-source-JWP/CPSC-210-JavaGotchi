package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.LinkedList;
import java.util.List;

// Represents a pet having a name, hunger, happiness, and energy.
public class Pet implements Writable {
    public String name;   // The name of the pet. At the moment, names are not in use.
    public int hunger;    // The "hunger meter" of the pet, with a set threshold being full and 0 being starving
    public int happiness; // The happiness meter of the pet, with a set threshold being content and 0 being unhappy
    public int energy;    // THe energy meter of the pet, with a set threshold being energetic and 0 being exhausted

    //Additional constants that affect how the rate the Pet's status meters change. Not all of these are in use,
    //but will be properly utilised or pruned as the project develops. In the future, I hope to have different species
    //of pets, which will have different values for different fields, hence why they aren't just constants.
    //HUNGER FIELDS
    public int fullThreshold = 100;                 //hunger level where your pet cannot eat anymore food
    public int hungerLostPerHour = 18;              //hunger lost per game-hour passing
    public int hungryThreshold = 35;                //hunger level where your pet qualifies as hungry
    public int starvingThreshold = 12;              //hunger level where your pet qualifies as starving
    public int energyLostPerHourStarving = 100;     //energy lost per game hour when your pet is starving

    //ENERGY FIELDS
    public int energyLostPerHourHungry = 15;         //energy lost per game hour when your pet is hungry
    public int energyGainedNotExercising = 22;       //energy gained when not exercising
    public int energyGainedResting = 45;             //energy gained when resting
    public int energyUsedWalk = 40;                  //energy used per hour when on a walk
    public int energyUsedPlay = 25;                  //energy used up when playing
    public int energyLevelAtWakeup = 70;             //energy level the pet has at the start of the day
    public int exhaustedThreshold = 20;              //energy level where your pet qualifies as exhausted
    public int tiredThreshold = 50;                  //energy level where your pet qualifies as tired
    public int energeticThreshold = 90;              //energy level where your pet qualifies as energetic

    //HAPPINESS FIELDS
    public int happinessLostPerHourHungry = 15;      //happiness lost per game hour when your pet is hungry
    public int happinessLostPerHourStarving = 90;    //happiness lost per game hour when your pet is starving
    public int happinessGainedWalk = 45;             //happiness gained after going on a walk
    public int happinessGainedPlay = 35;             //happiness gained after playing
    public int sadThreshold = 20;                    //happiness level where your pet qualifies as sad :(
    public int boredThreshold = 40;                  //happiness level where your pet qualifies as bored :|
    public int happyThreshold = 65;                  //happiness level where your pet qualifies as happy :)


    public final int initialHunger = 70;
    public final int initialEnergy = 70;
    public final int initialHappiness = 50;
    List<Food> favoriteFoods;

    //CONSTRUCTOR
    //Constructs new pet with initial hunger, energy, and happiness, as well as an empty list of favorite foods.
    public Pet() {
        this.hunger = initialHunger;
        this.energy = initialEnergy;
        this.happiness = initialHappiness;
        favoriteFoods = new LinkedList<>();

    }

    //METHODS
    //Note that I skipped REQUIRES clauses for any methods that don't have any special requirements and
    //skipped MODIFIES clauses for methods where it's clear that
    // they don't modify anything except for the Pet they are called on.


    //EFFECTS: raises the happiness, hunger, and energy of the pet by the food's corresponding values
    public String feedPet(Food f) {
        this.happiness = this.happiness + f.getHappinessRestored();
        this.hunger = this.hunger + f.getHungerRestored();
        this.energy = this.energy + f.getEnergyRestored();

        return "You fed your pet!\n".concat(checkHungerPet());

    }

    //EFFECTS: Raises the energy of the pet by it's energyGainedResting field, but reduces
    // it's hunger by hungerLostPerHour field.
    public String restPet() {
        this.energy = energy + energyGainedResting;
        this.hunger = hunger - hungerLostPerHour;
        return "You rested with your pet.\n".concat(checkEnergyPet());
    }


    //EFFECTS: raises the happiness of the pet by it's happinessGainedPlay field, but reduces it's hunger and energy
    //         by it's hungerLostPerHour and energyUsedPlay fields.
    public String playWithPet() {
        this.happiness = happiness + happinessGainedPlay;
        this.hunger = hunger - hungerLostPerHour;
        this.energy = energy - energyUsedPlay;
        return "You played with your pet!\n".concat(checkHappinessPet());
    }

    //EFFECTS: Raises the pet's happiness by happinessGainedWalk, but reduces it's hunger and energy by
    //         hungerLostPerHour and energyUsedWalk
    public String walkPet() {
        this.happiness = happiness + happinessGainedWalk;
        this.hunger = hunger - hungerLostPerHour;
        this.energy = energy - energyUsedWalk;
        this.checkHappinessPet();
        return "You walked your pet!\n".concat(checkHappinessPet());
    }


    //EFFECTS: Describes the current happiness, hunger, and energy levels of the pet in written form,
    //         and returns the list of favorite foods the Pet has.
    public String checkStatusPet() {
        return this.checkHappinessPet() + "\n" + this.checkHungerPet()
                + "\n" + this.checkEnergyPet() + "\n" + this.checkFavoriteFoodsPet();
    }

    //EFFECTS: Returns one of three happiness status strings, depending on the happiness of the pet.
    public String checkHappinessPet() {
        if (this.happiness <= sadThreshold) {
            return ("Your pet is sad! Play with them or take them on a walk!");
        } else if (this.happiness <= boredThreshold) {
            return ("Your pet is a little bored.");
        } else {
            return ("Your pet is very happy! You're a great owner!");
        }
    }

    //EFFECTS: Returns one of four hunger status strings based on the hunger of the pet.
    public String checkHungerPet() {
        if (this.hunger <= starvingThreshold) {
            return ("Your pet is still starving!");
        } else if (this.hunger <= hungryThreshold) {
            return ("Your pet is still hungry.");
        } else if (this.hunger >= fullThreshold) {
            return ("Your pet is full.");             //NOTE:In the future, food will be less effective when a pet is
        } else {                                      //full, hence the need for an extra threshold for hunger.
            return ("Your pet is not hungry.");
        }
    }


    //EFFECTS: Returns one of three energy status strings based on the energy of the pet.
    public String checkEnergyPet() {
        if (this.energy <= exhaustedThreshold) {
            return "Your pet is exhausted! Let them rest for some time.";
        } else if (this.energy <= tiredThreshold) {
            return ("Your pet is a little tired.");
        } else {
            return ("Your pet is full of energy! Take them out on a walk to burn off that energy!");
        }
    }


    //EFFECTS: Returns a string listing the pet's favorite foods, or a message saying they have no favorite foods
    // if they don't have any yet.
    public String checkFavoriteFoodsPet() {
        String output = "Your pet's favorite foods are: \n";
        if (!this.favoriteFoods.isEmpty()) {
            for (Food food : favoriteFoods) {
                output = output.concat(food.getFoodName() + ":\n " + food.describeFood() + "\n");
            }
            return output;
        } else {
            return "Your pet has no favorite foods.";
        }
    }

    //MODIFIES: this
    //EFFECTS:Adds given food to the pet's favorite foods
    public String addFavoriteFood(Food food) {
        favoriteFoods.add(food);
        return "Your pet's new favorite food is ".concat(food.getFoodName() + " ");
    }

    //MODIFIES: this
    //EFFECTS:Removes given food from the pet's favorite foods.
    public String removeFavoriteFood(Food food) {
        this.favoriteFoods.remove(food);
        return food.getFoodName().concat(" is no longer a favorite food of your pet.");
    }


    //GETTERS AND SETTERS
    public int getHunger() {
        return this.hunger;
    }

    public int getHappiness() {
        return this.happiness;
    }

    public int getEnergy() {
        return this.energy;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public void setHappiness(int happiness) {
        this.happiness = happiness;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public List<Food> getFavoriteFoods() {
        return favoriteFoods;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("hunger", hunger);
        json.put("happiness", happiness);
        json.put("energy", energy);
        json.put("favorite foods", favFoodsToJson());
        return json;
    }

    private JSONArray favFoodsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Food f : favoriteFoods) {
            jsonArray.put(f.toJson());
        }

        return jsonArray;
    }
}
