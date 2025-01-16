import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The CharacterSelection class allows the user to select a character avatar.
 */
public class CharacterSelection extends World {
    private int indexShips = 0; // Current character index
    private GreenfootImage[] characters; // Array of character images
    private CharacterDisplay characterDisplay; // Actor to display the current character
    private MenuScreen menuScreen;
    private Actor leftArrow;
    private Actor rightArrow;
    private static boolean checker = true;
    private static final int[] MENU_WIDTHS = {160, 200, 200};
    private static final int[] MENU_HEIGHTS = {320, 200, 200};
    
    // Power-up descriptions for each ship
    private Label powerUpLabel;

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

        // Scale and rotate each character directly
        characters[0].scale(MENU_WIDTHS[0], MENU_HEIGHTS[0]);
        characters[0].rotate(-90);
        characters[1].scale(MENU_WIDTHS[1], MENU_HEIGHTS[1]);
        characters[1].rotate(-90);
        characters[2].scale(MENU_WIDTHS[2], MENU_HEIGHTS[2]);
        characters[2].rotate(-90);

        // Initialize the character display with the first character
        characterDisplay = new CharacterDisplay(characters[indexShips]);
        addObject(characterDisplay, getWidth() / 2, getHeight() / 2); // Position it at the center of the screen
        // Initialize the power-up label for the first character
        updatePowerUpLabel();
        // Play the background music
    }

    public void act() {
        Util.handleEscapeKey(this, menuScreen);
        handleNavigateKey(); // Call the method to handle navigation
        handleEnterKey(); // Call the method to handle enter key
    }

    public void addLabels() {
        addObject(new Label("Press Enter to Select character \n Use arrow keys to navigate", 42), getWidth() / 2, 100);
    }

    public void arrow() {
        // Add arrow images beside the character image
        leftArrow = new Actor() {};
        GreenfootImage leftArrowImage = new GreenfootImage("newarrow.png");
        leftArrowImage.mirrorHorizontally(); // Reflect the image horizontally
        leftArrow.setImage(leftArrowImage);
        addObject(leftArrow, getWidth() / 2 - 200, 390);

        rightArrow = new Actor() {};
        rightArrow.setImage("newarrow.png");
        addObject(rightArrow, getWidth() / 2 + 200, 390);
    }

    /**
     * check for key presses
     */
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
            updatePowerUpLabel(); // Update the power-up label for the new ship
        }
    }

    /**
     * Move to the previous character in the selection array.
     */
    public void previousCharacter() {
        if (indexShips > 0) {
            indexShips--;
            updateCharacterImage();
            updatePowerUpLabel(); // Update the power-up label for the new ship
        }
    }

    /**
     * Handle character selection and return to the game screen.
     */
    public void selectCharacter() {        
        // Create a new image for the selected character
        GreenfootImage selectedShip = new GreenfootImage(characters[indexShips]);
        
        // Scale only the copy, not the original
        if (indexShips == 0) {
            selectedShip.scale(75, 120);
        } else if (indexShips == 1) {
            selectedShip.scale(85, 100);
        } else if (indexShips == 2) {
            selectedShip.scale(75, 67);
        }
        
        Greenfoot.setWorld(new StageSelection(selectedShip, menuScreen, indexShips, this));
    }

    /**
     * Update the character display image to the current character.
     */
    private void updateCharacterImage() {
        characterDisplay.setImage(characters[indexShips]);
    }

    /**
     * Update the power-up label based on the selected character.
     */
    private void updatePowerUpLabel() {
        // Remove the old label if it exists
        if (powerUpLabel != null) {
            removeObject(powerUpLabel);
        }

        // Add a new label for the current character's power-up
        if (indexShips == 0) {
            powerUpLabel = new Label("Power-Up: Homing Bullet \n 30s cooldown \n 'v' to activate", 40);
        } else if (indexShips == 1) {
            powerUpLabel = new Label("Power-Up: Double Damage \n 30s cooldown \n 'v' to activate", 40);
        } else if (indexShips == 2) {
            powerUpLabel = new Label("Power-Up: Force Field \n 30s cooldown \n 'v' to activate", 40);
        }

        // Position the label below the selected ship
        addObject(powerUpLabel, getWidth()/2, 530);
    }

    public void resetScales() {
        // Reload the images fresh instead of rescaling
        characters = new GreenfootImage[] {
            new GreenfootImage("Spaceship1.png"),
            new GreenfootImage("Spaceship2.png"),
            new GreenfootImage("Spaceship3.png")
        };

        // Apply the menu scales
        for (int i = 0; i < characters.length; i++) {
            characters[i].scale(MENU_WIDTHS[i], MENU_HEIGHTS[i]);
        }
        
        // Update the display with the reset image
        updateCharacterImage();
    }
    
    public void started() {
        // Ensure the music resumes when the world starts
        menuScreen.menuMusic.playLoop();
    }
    
    public void stopped() {
        // Pause the music when the world is stopped
        menuScreen.menuMusic.pause();
    }
}
