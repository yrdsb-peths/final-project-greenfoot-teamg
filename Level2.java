import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Level 2 of the game.
 */
public class Level2 extends Game {

    /**
     * Constructor for Level2.
     * @param selectedImage The image for the player's character.
     */
    public Level2(GreenfootImage selectedImage) {
        super(600, 800, 1, selectedImage);

        // delete if on main
        Enemy test1 = new CircleEnemy();
        addObject(test1, 125, 350);

        Enemy test2 = new SeekingEnemy();
        addObject(test2, 250, 350);

        Enemy test3 = new SplitEnemy();
        addObject(test3, 375, 350);
        
        Enemy test4 = new RichochetEnemy();
        addObject(test4, 450, 350);
    }

    @Override
    protected void setupLevel() {
        // Load the background image
        GreenfootImage background = new GreenfootImage("Stage2Background.jpg");

        // Scale the image to match the world dimensions
        background.scale(getWidth(), getHeight());

        // Set the scaled image as the background
        setBackground(background);

    }
}
