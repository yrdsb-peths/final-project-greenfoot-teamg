import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The CharacterSelection class allows the user to select a character avatar.
 */
public class CharacterSelection extends World {
    private int indexShips = 0; // Current character index
    private GreenfootImage[] characters; // Array of character images
    private CharacterDisplay characterDisplay; // Actor to display the current character
    private GreenfootSound menuMusic; // Music for the menu


    /**
     * Constructor for CharacterSelection.
     * Sets up the character selection screen.
     */
    public CharacterSelection() {
        super(500, 700, 1); // Create a new world with specified dimensions
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight()); // Adjust to world size (500x700)
        setBackground(background);

        // Load character images
        characters = new GreenfootImage[] {
            new GreenfootImage("Spaceship1.png"),
            new GreenfootImage("Spaceship2.png"),
            new GreenfootImage("Spaceship3.png")
        };
        for (int i = 0; i < characters.length; i++) {
            characters[i].scale(150, 200); // Resize to 100x100 pixels (adjust size as needed)
            characters[i].rotate(-90);
        }

        // Initialize the character display with the first character
        characterDisplay = new CharacterDisplay(characters[indexShips]);
        addObject(characterDisplay, 250, 350);

        // Add navigation buttons
        addObject(new Button(this::nextCharacter, "Next"), 350, 600);
        addObject(new Button(this::previousCharacter, "Previous"), 150, 600);
        addObject(new Button(this::selectCharacter, "Select"), 250, 650);
        
        menuMusic = new GreenfootSound("Menu.mp3");
        menuMusic.playLoop();
    }

    /**
     * Move to the next character in the selection array.
     */
    public void nextCharacter() {
        if (indexShips < characters.length - 1) {
            indexShips++;
            updateCharacterImage();
        }
    }

    /**
     * Move to the previous character in the selection array.
     */
    public void previousCharacter() {
        if (indexShips > 0) {
            indexShips--;
            updateCharacterImage();
        }
    }

    /**
     * Update the character display image to the current character.
     */
    private void updateCharacterImage() {
        characterDisplay.setImage(characters[indexShips]);
    }

    /**
     * Handle character selection and return to the game screen.
     */
    public void selectCharacter() {
        for (int i = 0; i < characters.length; i++) {
            characters[i].scale(100, 150); // Resize to 100x100 pixels (adjust size as needed)
        }
        // Pass the selected image directly to Game
        Greenfoot.setWorld(new Game(characters[indexShips]));
    }
}
