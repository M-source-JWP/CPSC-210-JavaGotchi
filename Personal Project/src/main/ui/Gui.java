package ui;

import model.Cheeseburger;
import model.Food;
import model.Pet;
import model.Tiramisu;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;


//EFFECTS: Represents the GUI version of PetInteraction
public class Gui extends JFrame {
    private static final String JSON_STORE = "./data/pet.json";
    Pet pet = new Pet();
    Cheeseburger cheeseburger = new Cheeseburger();
    Tiramisu tiramisu = new Tiramisu();

    //Used for functions where food needs to be passed as a parameter, such as pet.Feed(). Default is cheeseburger.
    Food selectedFood = cheeseburger;
    private static final String SOUND_DIRECTORY = "./data/sounds/";
    private final JsonWriter jsonWriter;
    private final JsonReader jsonReader;

    //EFFECTS: Construct and start the GUI
    public Gui() {
        super();
        this.setTitle("JavaGotchi");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        createButtons();
        this.getContentPane().setLayout(new FlowLayout());
        this.setSize(300, 300);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    //EFFECTS: Creates the GUI
    public static void main(String[] args) {
        new Gui();
    }

    //EFFECTS:Calls each individual button-creating function.
    // NOTE: Ideally, i'd like to just call the same function multiple times while passing the functions as parameters,
    // but we haven't gone over functional interfaces yet and i'm too lazy to learn it myself :/
    private void createButtons() {
        addWalk();
        addPlay();
        addRest();
        addSelectCheeseburger();
        addSelectTiramisu();
        addFeed();
        addAddFavorite();
        addRemoveFavorite();
        addStatus();
        addSave();
        addLoad();
    }

    //MODIFIES: selectedFood
    //EFFECTS: Sets selectedFood to given food
    private String selectFood(Food food) {
        selectedFood = food;
        return food.getFoodName() + " selected";
    }

    //EFFECTS: Saves the pet's current data to JSON_STORE
    private String savePet() {
        try {
            jsonWriter.open();
            jsonWriter.write(pet);
            jsonWriter.close();
            return "Successfully saved your pet to " + JSON_STORE;
        } catch (FileNotFoundException e) {
            return "Unable to write to file: " + JSON_STORE;
        }
    }

    //EFFECTS: Loads the data saved at JSON_STORE to pet.
    private String loadPet() {
        try {
            pet = jsonReader.read();
            return "Loaded pet from " + JSON_STORE;
        } catch (IOException e) {
            return "Unable to read from file: " + JSON_STORE;
        }
    }

    //EFFECTS: Creates a new JButton with given label and adds it to the gui.
    private JButton createButton(String label) {
        JButton button = new JButton(label);
        add(button);
        return button;
    }
    //playSound created by Appy at https://suavesnippets.blogspot.com/2011/06/add-sound-on-jbutton-click-in-java.html?fbclid=IwAR3T-LrlccGKiG_D2Vft80yvd4JlkXC55bi7eR6_1FyVj3FAFBuMbUUCUq4
    //and is NOT created by/written by me.
    //playSound is a simple function that can't be significantly tweaked/modified for the purposes of my code, and thus
    //it is necessary to use the function as-is.

    //EFFECTS: Plays the audio soundName at SOUND_DIRECTORY, or prints an error if not possible
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    new File(SOUND_DIRECTORY + soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    //ADD BUTTON FUNCTIONS:
    // All these functions essentially do the same thing but for different functions. I would ideally like to use
    // functional interfaces to have only one addButton function and pass the functions in as parameters, but it's not
    // covered/required, so priorities is to making the GUI functional first.

    //FOR ALL FUNCTIONS BELOW:
    //MODIFIES:This
    //EFFECTS:Creates and adds a specific button with an associated sound, and a unique label and function
    private JButton addStatus() {
        JButton button = createButton("Status");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = pet.checkStatusPet();
                playSound("click.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addWalk() {
        JButton button = createButton("Walk");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = pet.walkPet();
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addPlay() {
        JButton button = createButton("Play");
        button.addActionListener(e -> {
            String function = pet.playWithPet();
            playSound("play.wav");
            JOptionPane.showMessageDialog(button, function);
        });
        return button;
    }

    private JButton addRest() {
        JButton button = createButton("Rest");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = pet.restPet();
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addSelectTiramisu() {
        JButton button = createButton("Select tiramisu");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = selectFood(tiramisu);
                playSound("click.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addSelectCheeseburger() {
        JButton button = createButton("Select cheeseburger");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = selectFood(cheeseburger);
                playSound("click.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addFeed() {
        JButton button = createButton("Feed");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = pet.feedPet(selectedFood);
                playSound("munch.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addSave() {
        JButton button = createButton("Save");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = savePet();
                playSound("click.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addLoad() {
        JButton button = createButton("Load");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = loadPet();
                playSound("click.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addAddFavorite() {
        JButton button = createButton("Favorite selected");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String function = pet.addFavoriteFood(selectedFood);
                playSound("click.wav");
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }

    private JButton addRemoveFavorite() {
        JButton button = createButton("Un-favorite selected");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playSound("click.wav");
                String function = pet.removeFavoriteFood(selectedFood);
                JOptionPane.showMessageDialog(button, function);
            }
        });
        return button;
    }
}