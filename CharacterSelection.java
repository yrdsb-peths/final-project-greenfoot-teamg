import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The CharacterSelection class allows the user to select a character avatar.
 */
public class CharacterSelection extends World {
    private int indexShips = 0; // Current character index
    private GreenfootImage[] characters; // Array of character images
    private CharacterDisplay characterDisplay; // Actor to display the current character
    private GreenfootSound menuMusic; // Music for the menu
    private MenuScreen menuScreen;

    /**
     * Constructor for CharacterSelection.
     * Sets up the character selection screen.
     */
    public CharacterSelection() {
        super(500, 700, 1); // Create a new world with specified dimensions
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight()); // Adjust to world size (500x700)
        setBackground(background);
        
        this.menuScreen = menuScreen;

        // Load character images
        characters = new GreenfootImage[] {
            new GreenfootImage("Spaceship1.png"),
            new GreenfootImage("Spaceship2.png"),
            new GreenfootImage("Spaceship3.png")
        };

        // Rotate the images to fit your required orientation
        for (int i = 0; i < characters.length; i++) {
            characters[i].rotate(-90); // Rotate them to match your desired orientation
        }

        // Scale the characters to the correct size without cutting off the top
        characters[0].scale(270, 420); 
        characters[1].scale(285, 330); 
        characters[2].scale(300, 255);

        // Initialize the character display with the first character
        characterDisplay = new CharacterDisplay(characters[indexShips]);
        addObject(characterDisplay, 250, 350); // Position it at the center of the screen

        // Add navigation buttons for selecting characters
        addObject(new Button(this::nextCharacter, "Next"), 350, 600);
        addObject(new Button(this::previousCharacter, "Previous"), 150, 600);
        addObject(new Button(this::selectCharacter, "Select"), 250, 650);

        // Play the background music
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
        menuMusic.stop(); // Stop the menu music
        // Scale the images properly for the game world
        characters[0].scale(90, 140);
        characters[1].scale(95, 110);
        characters[2].scale(100, 85);

        // Get the selected spaceship image
        GreenfootImage selectedShip = new GreenfootImage(characters[indexShips]); // Use the selected character image
        
        // Pass the scaled image to the Game world
        Greenfoot.setWorld(new Game(selectedShip));
    }
}
