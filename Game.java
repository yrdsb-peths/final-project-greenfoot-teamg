import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstract class representing the base functionality for all game levels.
 */
public abstract class Game extends World {
    protected Character player;
    public boolean isFreeze = false;
    protected SimpleTimer waveTimer; // Add waveTimer as a shared resource for all levels
    int waveNumber = 1;  // Starts at wave 1
    int enemiesInWave = 1; // Number of enemies in the current wave
    int enemiesSpawned = 0; // Tracks how many enemies have been spawned in the current wave
    SimpleTimer spawnTimer; // Timer for controlling enemy spawn timing
    int spawnDelay = 1000; // Delay in milliseconds between spawning each enemy
    boolean waveDisplayed = false; // Flag to check if wave number is displayed
    boolean levelDisplayed = true; // Flag to display the level intro message
    SimpleTimer levelTimer; // Timer for the level
    boolean levelEnded = false; // Flag to check if the level has ended
    Label timerLabel; // Label to display the timer
    boolean isBossDefeated = false; // Flag to track if the boss is defeated
    boolean isWaveStart = false;
    PauseScreen pauseScreen;
    GreenfootSound levelMusic; // Music for the level
    GreenfootSound bossMusic; // Music for the boss
    GreenfootSound warningSound; //warning sound for boss
    AudioManager audioManager;
    
    /**
     * Initializes a Game object. Sets up all the variables used in the game.
     */
    public Game(int width, int height, int cellSize, GreenfootImage selectedImage, int whichCharacter, SimpleTimer levelTimer) {
        super(width, height, cellSize);

        // Create and add the player's character
        player = new Character(selectedImage, whichCharacter);
        addObject(player, getWidth() / 2, getHeight() - 50);

        // Initialize the wave timer
        waveTimer = new SimpleTimer();
        spawnTimer = new SimpleTimer(); // Initialize the spawn timer
        spawnTimer.mark(); // Start the spawn timer
        this.levelTimer = levelTimer; // saves the timer
        warningSound = new GreenfootSound("beep_warning.mp3"); // initializes the warning sound before a boss comes
        audioManager = AudioManager.getInstance(); // get the instance of the audio manager
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
    
    
    /**
     *  Makes health bar
     */
    public void makeHealthBar(Boss boss) {
        Healthbar bar = new Healthbar();
        boss.setHealthBar(bar);
        addObject(bar, 300, 15);
    }

    /**
     * Takes in a integer parameter and spawns a different enemy depending on the parameter value
     */
    public void spawnEnemy(int enemyType)
    {
        Enemy enemy;
        if(enemyType == 0)
        {
            enemy = new SimpleEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 1)
        {
            enemy = new SeekingEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 2)
        {
            enemy = new SplitEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 3)
        {
            enemy = new CircleEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 4)
        {
            enemy = new RichochetEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 5)
        {
            enemy = new TripleSplitEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 6)
        {
            enemy = new DoubleEndEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else if(enemyType == 7)
        {
            enemy = new BigEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        else
        {
            enemy = new EverythingEnemy();
            addObject(enemy, Greenfoot.getRandomNumber(getWidth()), 0);
        }
        addObject(enemy.hitbox, enemy.getX(), enemy.getY());
        enemiesSpawned++; // Increase the spawn counter for the wave
    }

    /**
     * Freezes all SimpleTimer classes
     */
    public void freezeGame() {
        isFreeze = true;
        waveTimer.freeze();
        spawnTimer.freeze();
        if(levelTimer != null)
        {
            levelTimer.freeze();
        }
        for (Freezable object : getObjects(Freezable.class)) { // Get all Freezable actors
            object.freeze();
        }
    }

    /**
     * Unfreezes all SimpleTimer classes
     */
    public void resumeGame() {
        isFreeze = false;
        waveTimer.unfreeze();
        spawnTimer.unfreeze();
        if(levelTimer != null)
        {
            levelTimer.unfreeze();
        }
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
    
    /**
     * returns true if no enemies are on screen
     */
    public boolean areAllEnemiesDead() {
        // Check if there are no Enemy objects in the world
        boolean isClear = getObjects(Enemy.class).isEmpty();
        return isClear;
    }
    
    /**
     * Updates the music based on the current volume settings from AudioManager.
     */
    public void updateMusic() {
        int effectiveVolume = audioManager.getEffectiveVolume();

        levelMusic.setVolume(effectiveVolume);
        bossMusic.setVolume(effectiveVolume);
        warningSound.setVolume(effectiveVolume);
    }
    
    /**
     * If starte is pressed, play music.
     */
    public void started() {
        if (waveNumber < 5) {
            levelMusic.playLoop();
        } else {
            bossMusic.playLoop();
        }
    }

    /**
     * If pause if pressed, stop all music.
     */
    public void stopped() {
        levelMusic.pause();
        bossMusic.pause();
    }
}
