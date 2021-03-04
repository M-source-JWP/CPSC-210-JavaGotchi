package model;

public class Cheeseburger extends Food {
    //Constructs a Cheeseburger with a set happinessRestored, hungerRestored, energyRestored, and foodName.
    public Cheeseburger() {
        super(35, 55, 15, "Cheeseburger");
    }

    @Override
    public String describeFood() {
        return "A hearty, somehow not-unhealthy food for any "
                + "discerning pet. Restores lots of hunger and some happiness.";
    }
}
