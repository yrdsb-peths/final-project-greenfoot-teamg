import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class representing the base functionality for all game levels.
 */
public abstract class Game extends World {
    protected Character player;
    
    public Game(int width, int height, int cellSize, GreenfootImage selectedImage) {
        super(width, height, cellSize);

        // Create and add the player's character
        player = new Character(selectedImage);

        // Setup common elements
        setupLevel();
    }

    /**
     * Abstract method to be implemented by each level for unique setup.
     */
    protected abstract void setupLevel();
    
    /**
     * Common method to reset the player's position.
     */
    public void resetPlayerPosition() {
        player.setLocation(getWidth() / 2, getHeight() - 50);

        // Add the character to the center of the game world.
        addObject(player, getWidth() / 2, getHeight() - 50);

        Enemy test1 = new CircleEnemy();
        addObject(test1, 125, 350);

        Enemy test2 = new SeekingEnemy();
        addObject(test2, 250, 350);

        Enemy test3 = new SplitEnemy();
        addObject(test3, 375, 350);
        Enemy test = new RichochetEnemy();
        addObject(test, 250, 350);
    }
}
