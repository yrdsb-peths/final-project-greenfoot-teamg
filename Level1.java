import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level 1 of the game.
 */
public class Level1 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen; // Add menuScreen

    /**
     * Constructor for Level1.
     * @param selectedImage The image for the player's character.
     */
    public Level1(GreenfootImage selectedImage, MenuScreen menuScreen) {
        super(600, 750, 1, selectedImage);
        this.menuScreen = menuScreen; // Initialize menuScreen
        pauseScreen = new PauseScreen(this, menuScreen); // Initialize the pause screen
    }

    @Override
    protected void setupLevel() {
        // Set the background for Level 1
        setBackground("Stage1Background.jpg");
        // Add the player's character to the world at a default position
        addObject(player, getWidth() / 2, getHeight() - 100);

        // Add unique elements for Level 1 (enemies, obstacles, etc.)

        //addObject(new SeekingEnemy(), 250, 350);
        //addObject(new SplitEnemy(), 375, 350);
        //addObject(new RichochetEnemy(), 250, 450);

    }

    public void act() {
        Util.handleEscapeKey(this, pauseScreen);
    }
}
