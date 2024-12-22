import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level 1 of the game.
 */
public class Level1 extends Game {

    /**
     * Constructor for Level1.
     * @param selectedImage The image for the player's character.
     */
    public Level1(GreenfootImage selectedImage) {
        super(600, 800, 1, selectedImage);
    }

    @Override
    protected void setupLevel() {
        // Set the background for Stage 1
        setBackground("Stage1Background.jpg");

        // Add unique elements for Level 1
        /*
        addObject(new Enemy(), 300, 200);
        addObject(new Enemy(), 100, 150);
        addObject(new Enemy(), 500, 150);
        */
    }
}
