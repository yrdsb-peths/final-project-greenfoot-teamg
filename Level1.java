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
        // Set the background for Level 1
        setBackground("Stage1Background.jpg");
        // Add the player's character to the world at a default position
        addObject(player, getWidth() / 2, getHeight() - 100);

        // Add unique elements for Level 1 (enemies, obstacles, etc.)

        //addObject(new SeekingEnemy(), 250, 350);
        //addObject(new SplitEnemy(), 375, 350);
        //addObject(new RichochetEnemy(), 250, 450);

    }
}
