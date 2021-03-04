package model;

import org.json.JSONObject;
import persistence.Writable;


public abstract class Food implements Writable {
    private int happinessRestored;
    private int hungerRestored;
    private int energyRestored;
    private String foodName;


    public Food(int happinessRestored, int hungerRestored, int energyRestored, String foodName) {
        this.happinessRestored = happinessRestored;
        this.hungerRestored = hungerRestored;
        this.energyRestored = energyRestored;
        this.foodName = foodName;
    }

    public int getHappinessRestored() {
        return happinessRestored;
    }

    public int getHungerRestored() {
        return hungerRestored;
    }

    public int getEnergyRestored() {
        return energyRestored;
    }

    public String getFoodName() {
        return foodName;
    }

    //NOTE: This function shouldn't be called by the program, but it's needed to test equals and hashcode.
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    //EFFECTS: Produces a food-specific string describing the food and it's use.
    public abstract String describeFood();

    //Format and code referenced from JsonSerializationDemo at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("food name", foodName);
        json.put("happiness restored", happinessRestored);
        json.put("hunger restored", hungerRestored);
        json.put("energy restored", energyRestored);
        return json;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Food food = (Food) o;

        return foodName.equals(food.foodName);
    }

    @Override
    public int hashCode() {
        return foodName.hashCode();
    }
}