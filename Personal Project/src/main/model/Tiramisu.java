package model;

public class Tiramisu extends Food {
    //Constructs a Tiramisu with a set happinessRestored, hungerRestored, energyRestored, and foodName.
    public Tiramisu() {
        super(55,25,30,"Tiramisu");
    }

    @Override
    public String describeFood() {
        return "A rich, coffee-flavored cake, that is somehow edible to non-humans. Great for happiness and energy.";
    }
}
