package ui;

import model.Cheeseburger;
import model.Pet;
import model.Tiramisu;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

//EFFECTS: Represents the pet interaction application
public class PetInteraction {
    Scanner input = new Scanner(System.in);
    Pet pet = new Pet();
    Cheeseburger cheeseburger = new Cheeseburger();
    Tiramisu tiramisu = new Tiramisu();
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/pet.json";


    //EFFECTS: Constructs the pet interaction application and begins processing commands
    public PetInteraction() {
        System.out.println("Welcome to JavaGotchi!");
        options();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        processCommands();

    }

    //EFFECTS: processes user commands and calls appropriate methods
    private void processCommands() {
        while (true) {
            String s = input.nextLine();
            if ("walk".equals(s)) {
                System.out.println(pet.walkPet());
            } else if ("play".equals(s)) {
                System.out.println(pet.playWithPet());
            } else if ("feed".equals(s)) {
                feed();
            } else if ("favorite".equals(s)) {
                favorite();
            } else if ("status".equals(s)) {
                System.out.println(pet.checkStatusPet());
            } else if ("save".equals(s)) {
                savePet();
            } else if ("load".equals(s)) {
                loadPet();
            } else if ("quit".equals(s)) {
                System.exit(0);
            } else {
                System.out.println("Not a valid interaction");
            }
            options();
        }
    }

    //EFFECTS: Displays all available actions to the user
    public void options() {
        System.out.println("Available actions are:\n favorite, walk, play, feed, status, save, load, quit");
    }

    //EFFECTS: Prompts the user to select a food for their pet, then feeds the pet that food.
    public void feed() {
        System.out.println("What would you like to feed your pet?");
        System.out.println("Options: cheeseburger, tiramisu");
        String s = input.nextLine();
        switch (s) {
            case "cheeseburger":
                System.out.println(pet.feedPet(cheeseburger));
                break;
            case "tiramisu":
                System.out.println(pet.feedPet(tiramisu));
                break;
            default:
                System.out.println("Not a valid food");
                break;

        }
    }

    //EFFECTS: Prompts the user to either add or remove a food to add to their pet's favorite foods,
    // then adds or removes that food to the pet's favorites.
    public void favorite() {
        System.out.println("Would you like to add or remove food from your pet's favorites?");
        String s = input.nextLine();
        if ("add".equals(s)) {
            System.out.println("What food would you like to favorite for this pet?");
            System.out.println("Options: cheeseburger, tiramisu");
            String a = input.nextLine();
            if ("cheeseburger".equals(a)) {
                System.out.println(pet.addFavoriteFood(cheeseburger));
            } else if ("tiramisu".equals(a)) {
                System.out.println(pet.addFavoriteFood(tiramisu));
            } else {
                System.out.println("Not a valid food");
            }
        } else if ("remove".equals(s)) {
            System.out.println("What food would you like to remove from this pet's favorites?");
            System.out.println(pet.checkFavoriteFoodsPet());
            String b = input.nextLine();
            if ("cheeseburger".equals(b)) {
                System.out.println(pet.removeFavoriteFood(cheeseburger));
            } else if ("tiramisu".equals(b)) {
                System.out.println(pet.removeFavoriteFood(tiramisu));
            }
        }
    }

//Format and code referenced from JsonSerializationDemo at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    //EFFECTS: saves the pet's current status to file
    private void savePet() {
        try {
            jsonWriter.open();
            jsonWriter.write(pet);
            jsonWriter.close();
            System.out.println("Successfully saved your pet to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads pet's status from file
    private void loadPet() {
        try {
            pet = jsonReader.read();
            System.out.println("Loaded pet from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}

