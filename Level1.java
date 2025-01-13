import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level 1 of the game, with progressive waves.
 */
public class Level1 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen; // Add menuScreen
    private int waveNumber = 1;  // Starts at wave 1
    private int enemiesInWave = 2; // Number of enemies in the current wave
    private int enemiesSpawned = 0; // Tracks how many enemies have been spawned in the current wave
    private SimpleTimer spawnTimer; // Timer for controlling enemy spawn timing
    private int spawnDelay = 1000; // Delay in milliseconds between spawning each enemy
    private boolean waveDisplayed = false; // Flag to check if wave number is displayed
    private boolean levelDisplayed = true; // Flag to display the level intro message
    private SimpleTimer levelTimer; // Timer for the level
    private boolean levelEnded = false; // Flag to check if the level has ended
    private Label timerLabel; // Label to display the timer
    private boolean timerStopped = false; // Flag to track if the timer is stopped
    private boolean isBossDefeated = false; // Flag to track if the boss is defeated
    GreenfootSound levelMusic; // Music for the level
    GreenfootSound bossMusic; // Music for the boss

    /**
     * Constructor for Level1.
     * @param selectedImage The image for the player's character.
     */
    public Level1(GreenfootImage selectedImage, MenuScreen menuScreen, int whichCharacter) {
        super(600, 750, 1, selectedImage, whichCharacter);
        this.menuScreen = menuScreen; // Initialize menuScreen
        pauseScreen = new PauseScreen(this, menuScreen); // Initialize the pause screen
        spawnTimer = new SimpleTimer(); // Initialize the spawn timer
        spawnTimer.mark(); // Start the spawn timer
        levelTimer = new SimpleTimer(); // Initialize the level timer
        levelTimer.mark(); // Start the level timer
        levelMusic = new GreenfootSound("Stage1.mp3");
        levelMusic.playLoop();
        bossMusic = new GreenfootSound("Stage1Boss.mp3");
    }

    @Override
    protected void setupLevel() {
        // Set the background for Level 1
        setBackground("Stage1Background.jpg");

        // Show the "Level 1" label for 3 seconds before starting the gameplay
        if (levelDisplayed) {
            addObject(new Label("Level 1", 50), getWidth() / 2, getHeight() / 2); // Display "Level 1"
            if (spawnTimer.millisElapsed() > 3000) { // Wait for 3 seconds
                removeObjects(getObjects(Label.class)); // Remove the "Level 1" label
                levelDisplayed = false; // Set flag to false to start the game
                setupWave(waveNumber); // Setup the first wave of enemies
            }
        }
    }

    // Method to setup the current wave
    private void setupWave(int wave) {
        if (wave == 1) {
            // Wave 1: Add only SimpleEnemies
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 2; // Wave 1 starts with 2 SimpleEnemies
        } else if (wave == 2) {
            // Wave 2: Add SimpleEnemies + SeekingEnemies
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 4;  // Wave 2 will have 4 enemies in total
        } else if (wave == 3) {
            // Wave 3: Add SimpleEnemies + SeekingEnemies + SplitEnemies
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 6;  // Wave 3 will have 6 enemies in total
        } else if (wave == 4) {
            // Wave 4: A combination of all previous enemies, plus more
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 8;  // Wave 4 will have 8 enemies in total
        } else if (wave == 5) {
            // Boss wave: Add the Boss1 to the world
            levelMusic.pause();
            bossMusic.playLoop();
            addObject(new Boss1(), getWidth() / 2, 10);
            enemiesInWave = 1;  // Wave 5 has only the boss (1 enemy)
        }
        waveDisplayed = true; // Set flag to true to display wave number
        resetWaveTimer(); // Reset the wave timer for displaying wave number
    }

    public void act() {
        // Handle pause and escape key
        Util.handleEscapeKey(this, pauseScreen);

        // Check if wave number should be displayed
        if (waveDisplayed) {
            addObject(new Label("Wave: " + waveNumber, 80), getWidth() / 2, getHeight() / 2); // Display wave number label
            if (getWaveTimeElapsed() > 2000) { // Check if 2 seconds have elapsed
                removeObjects(getObjects(Label.class)); // Remove wave number label
                waveDisplayed = false; // Reset flag
            }
        }

        // Check if it's time to spawn new enemies
        if (enemiesSpawned < enemiesInWave && spawnTimer.millisElapsed() > spawnDelay) {
            // Spawn the next enemy if wave is not complete
            spawnEnemies();
            spawnTimer.mark(); // Reset the timer after spawning an enemy
        }

        // Check if all enemies in the current wave have been removed from the world
        if (enemiesSpawned >= enemiesInWave && areAllEnemiesDead()) {
            // Wait for some time before transitioning to the next wave
            if (waveNumber < 5) {
                // Move to the next wave if it's not the boss wave
                waveNumber++;
                setupWave(waveNumber); // Setup the next wave
            }
        }

        // Display the timer in the top right corner
        if (!levelEnded) {
            updateTimerDisplay();
        }

        // Check if boss is defeated and freeze the timer
        if (waveNumber == 5 && isBossDefeated) {
            stopTimer(); // Freeze the timer when the boss is defeated
        }
    }

    private void spawnEnemies() {
        // Spawn enemies based on the current wave
        if (waveNumber == 1) {
            // Wave 1: Add SimpleEnemies
            addObject(new SimpleEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            enemiesSpawned++; // Increase the spawn counter for the wave
        } else if (waveNumber == 2) {
            // Wave 2: Add SimpleEnemies + SeekingEnemies
            if (enemiesSpawned % 2 == 0) {
                addObject(new SimpleEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            } else {
                addObject(new SeekingEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            }
            enemiesSpawned++; // Increase the spawn counter for the wave
        } else if (waveNumber == 3) {
            // Wave 3: Add SimpleEnemies + SeekingEnemies + SplitEnemies
            if (enemiesSpawned % 3 == 0) {
                addObject(new SimpleEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            } else if (enemiesSpawned % 3 == 1) {
                addObject(new SeekingEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            } else {
                addObject(new SplitEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            }
            enemiesSpawned++; // Increase the spawn counter for the wave
        } else if (waveNumber == 4) {
            // Wave 4: A combination of all previous enemies, plus more
            if (enemiesSpawned % 4 == 0) {
                addObject(new SimpleEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            } else if (enemiesSpawned % 4 == 1) {
                addObject(new SeekingEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            } else {
                addObject(new SplitEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
            }
            enemiesSpawned++; // Increase the spawn counter for the wave
        }
    }

    private boolean areAllEnemiesDead() {
        // Check if there are no Enemy objects in the world
        return getObjects(Enemy.class).isEmpty();
    }

    private void updateTimerDisplay() {
        // Remove the old timer label if it exists
        if (timerLabel != null) {
            removeObject(timerLabel);
        }

        // Create and add a new timer label with the updated time
        timerLabel = new Label("Time: " + levelTimer.millisElapsed() / 1000, 30);
        addObject(timerLabel, getWidth() - 100, 20); // Display timer in the top-right corner
    }

    private void stopTimer() {
        // Freeze the timer by setting the timerStopped flag to true
        timerStopped = true;
    }

    public void bossDefeated() {
        // This method is called when the boss is defeated
        isBossDefeated = true; // Set the boss as defeated
    }
    
    public void started() {
        // Ensure the music resumes when the world starts
        if(waveNumber < 5)
        {
            levelMusic.playLoop();
        }
        else
        {
            bossMusic.playLoop();
        }
    }
    
    public void stopped() {
        // Pause the music when the world is stopped
        if(waveNumber < 5)
        {
            levelMusic.pause();
        }
        else
        {
            bossMusic.pause();
        }   
    }
}
