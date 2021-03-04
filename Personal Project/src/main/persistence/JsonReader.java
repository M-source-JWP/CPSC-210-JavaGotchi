package persistence;

import model.Cheeseburger;
import model.Pet;
import model.Tiramisu;
import model.exceptions.MissingFoodException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;



// class taken from JsonSerializationDemo at https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads pet from JSON data stored in file
public class JsonReader {
    private String source;
    Tiramisu tiramisu = new Tiramisu();
    Cheeseburger cheeseburger = new Cheeseburger();

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads pet from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Pet read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePet(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses pet from JSON object and returns it
    private Pet parsePet(JSONObject jsonObject) {
        int hunger = jsonObject.getInt("hunger");
        int happiness = jsonObject.getInt("happiness");
        int energy = jsonObject.getInt("energy");
        Pet pet = new Pet();
        pet.setHappiness(happiness);
        pet.setHunger(hunger);
        pet.setEnergy(energy);
        addFavFoods(pet, jsonObject);
        return pet;
    }

    // MODIFIES: pet
    // EFFECTS: parses favorite foods from JSON object and adds them to pet
    private void addFavFoods(Pet pet, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("favorite foods");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            try {
                addFood(pet, nextThingy);
            } catch (MissingFoodException e) {
                System.out.println("Unknown food found, not added to favFoods");
            }
        }
    }

    // MODIFIES: pet
    // EFFECTS: parses favorite food from JSON object and adds it to pet
    private void addFood(Pet pet, JSONObject jsonObject) throws MissingFoodException {
        String foodName = jsonObject.getString("food name");
        switch (foodName) {
            case "Cheeseburger":
                pet.addFavoriteFood(cheeseburger);
                break;
            case "Tiramisu":
                pet.addFavoriteFood(tiramisu);
                break;
            default:
                throw new MissingFoodException();

        }
    }
}
