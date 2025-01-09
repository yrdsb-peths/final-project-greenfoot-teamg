import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Level 2 of the game.
 */
public class Level2 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen; // Add menuScreen

    /**
     * Constructor for Level2.
     * @param selectedImage The image for the player's character.
     */
    public Level2(GreenfootImage selectedImage, MenuScreen menuScreen) {
        super(600, 750, 1, selectedImage);
        this.menuScreen = menuScreen; // Initialize menuScreen
        pauseScreen = new PauseScreen(this, menuScreen); // Initialize the pause screen
    }

    @Override
    protected void setupLevel() {
        // Load the background image
        GreenfootImage background = new GreenfootImage("Stage2Background.jpg");

        // Scale the image to match the world dimensions
        background.scale(getWidth(), getHeight());

        // Set the scaled image as the background
        setBackground(background);

        // Add the player's character to the world at a default position
        addObject(player, getWidth() / 2, getHeight() - 100);

        // Add unique elements for Level 2 (enemies, obstacles, etc.)
        //addObject(new CircleEnemy(), 125, 350);
        //addObject(new SeekingEnemy(), 250, 350);
        //addObject(new SplitEnemy(), 375, 350);
        //addObject(new RichochetEnemy(), 250, 450);
    }

    public void act() {
        Util.handleEscapeKey(this, pauseScreen);
    }
}
