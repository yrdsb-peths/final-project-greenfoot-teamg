import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Abstract class representing the base functionality for all game levels.
 */
public abstract class Game extends World {
    protected Character player;
    public boolean isFreeze = false;
    protected SimpleTimer waveTimer; // Add waveTimer as a shared resource for all levels

    public Game(int width, int height, int cellSize, GreenfootImage selectedImage, int whichCharacter) {
        super(width, height, cellSize);

        // Create and add the player's character
        player = new Character(selectedImage, whichCharacter);
        addObject(player, getWidth() / 2, getHeight() - 50);

        // Initialize the wave timer
        waveTimer = new SimpleTimer();

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
    }

    public void freezeGame() {
        isFreeze = true;
        for (Freezable object : getObjects(Freezable.class)) { // Get all Freezable actors
            object.freeze();
        }
    }

    public void resumeGame() {
        isFreeze = false;
        for (Freezable object : getObjects(Freezable.class)) { // Get all Freezable actors
            object.unfreeze();
        }
    }

    /**
     * Resets the wave timer.
     */
    public void resetWaveTimer() {
        waveTimer.mark();
    }

    /**
     * Gets the elapsed time from the wave timer.
     * @return The elapsed time in milliseconds.
     */
    public int getWaveTimeElapsed() {
        return waveTimer.millisElapsed();
    }
}
