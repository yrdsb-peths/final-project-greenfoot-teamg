import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The CharacterSelection class allows the user to select a character avatar.
 */
public class CharacterSelection extends World {
    private int indexShips = 0; // Current character index
    private GreenfootImage[] characters; // Array of character images
    private CharacterDisplay characterDisplay; // Actor to display the current character
    private GreenfootSound menuMusic; // Music for the menu
    private MenuScreen menuScreen;
    private Actor leftArrow;
    private Actor rightArrow;
    private static boolean checker = true;

    /**
     * Constructor for CharacterSelection.
     * Sets up the character selection screen.
     */
    public CharacterSelection(MenuScreen menuScreen) {
        super(600, 750, 1); // Create a new world with specified dimensions
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        this.menuScreen = menuScreen;

        addLabels();
        arrow();

        // Load character images
        characters = new GreenfootImage[] {
                new GreenfootImage("Spaceship1.png"),
                new GreenfootImage("Spaceship2.png"),
                new GreenfootImage("Spaceship3.png")
        };

        // Rotate the images to fit your required orientation
        characters[0].scale(160, 320); // Resize to 160x320 pixels (adjust size as needed)
        characters[0].rotate(-90);
        characters[1].scale(200, 200); // Resize to 200x200 pixels (adjust size as needed)
        characters[1].rotate(-90);
        characters[2].scale(200, 200); // Resize to 200x200 pixels (adjust size as needed)
        characters[2].rotate(-90);

        // Initialize the character display with the first character
        characterDisplay = new CharacterDisplay(characters[indexShips]);
        addObject(characterDisplay, getWidth() / 2, getHeight() / 2); // Position it at the center of the screen

        // Play the background music
        menuMusic = new GreenfootSound("Menu.mp3");
        menuMusic.playLoop();
    }

    public void act() {
        Util.handleEscapeKey(this, menuScreen);
        handleNavigateKey(); // Call the method to handle navigation
        handleEnterKey(); // Call the method to handle enter key
    }

    public void addLabels() {
        addObject(new Label("Press Enter to Select character", 42), getWidth() / 2, 100);
    }

    public void arrow() {
        // Add arrow images beside the character image
        leftArrow = new Actor() {
        };
        GreenfootImage leftArrowImage = new GreenfootImage("arrow.png");
        leftArrowImage.mirrorHorizontally(); // Reflect the image horizontally
        leftArrow.setImage(leftArrowImage);
        addObject(leftArrow, getWidth() / 2 - 200, 390);

        rightArrow = new Actor() {
        };
        rightArrow.setImage("arrow.png");
        addObject(rightArrow, getWidth() / 2 + 200, 390);
    }

    public void handleEnterKey() {
        if (Greenfoot.isKeyDown("enter") && !MenuScreen.enterChecker) {
            MenuScreen.enterChecker = true;
            selectCharacter();
        } else if (!Greenfoot.isKeyDown("enter")) {
            MenuScreen.enterChecker = false;
        }
    }

    public void handleNavigateKey() {
        // Continuously check for "left", "a", "right", "d", and "escape" key presses
        if ((Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("a")) && !checker) {
            checker = true;
            previousCharacter();
        } else if ((Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("d")) && !checker) {
            checker = true;
            nextCharacter();
        } else if (!Greenfoot.isKeyDown("left") && !Greenfoot.isKeyDown("a") && !Greenfoot.isKeyDown("right")
                && !Greenfoot.isKeyDown("d")) {
            checker = false;
        }
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
     * Handle character selection and return to the game screen.
     */
    public void selectCharacter() {
        menuMusic.stop(); // Stop the menu music
        // Scale the images properly for the game world
        GreenfootImage[] scaleCharacters = characters;
        scaleCharacters[0].scale(75, 120);
        scaleCharacters[1].scale(85, 100);
        scaleCharacters[2].scale(80, 67);

        // Get the selected spaceship image
        GreenfootImage selectedShip = new GreenfootImage(scaleCharacters[indexShips]); // Use the selected character
                                                                                       // image

        // Pass the scaled image to the Game world
        Greenfoot.setWorld(new Level1(selectedShip, menuScreen)); // Pass menuScreen as a parameter
    }

    /**
     * Update the character display image to the current character.
     */
    private void updateCharacterImage() {
        characterDisplay.setImage(characters[indexShips]);
    }
}
