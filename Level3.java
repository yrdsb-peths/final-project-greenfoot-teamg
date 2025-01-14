import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Level 1 of the game, with progressive waves.
 */
public class Level3 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen; // Add menuScreen
    private AudioManager audioManager;

    /**
     * Constructor for Level3.
     * @param selectedImage The image for the player's character.
     */
    public Level3(GreenfootImage selectedImage, MenuScreen menuScreen, int whichCharacter, SimpleTimer levelTimer) {
        super(600, 750, 1, selectedImage, whichCharacter, levelTimer);
        this.menuScreen = menuScreen; // Initialize menuScreen

        audioManager = AudioManager.getInstance();

        pauseScreen = new PauseScreen(this, menuScreen); // Initialize the pause screen

        levelMusic = new GreenfootSound("Stage3.mp3");
        bossMusic = new GreenfootSound("Stage3Boss.mp3");
    }

    @Override
    protected void setupLevel() {
        // Set the background for Level 1
        setBackground("Stage3Background.png");

        // Show the "Level 1" label for 3 seconds before starting the gameplay
        if (levelDisplayed) {
            addObject(new Label("Level 2", 50), getWidth() / 2, getHeight() / 2); // Display "Level 1"
            if (spawnTimer.millisElapsed() > 3000) { // Wait for 3 seconds
                removeObjects(getObjects(Label.class)); // Remove the "Level 1" label
                levelDisplayed = false; // Set flag to false to start the game
                setupWave(waveNumber); // Setup the first wave of enemies
            }
        }
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
    
    // Method to setup the current wave
    private void setupWave(int wave) {
        if (wave == 1) {
            // Wave 1: Add only SimpleEnemies
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 18; // Wave 1 starts with 18 SimpleEnemies
        } else if (wave == 2) {
            // Wave 2: Add SimpleEnemies + SeekingEnemies
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 20;  // Wave 2 will have 20 enemies in total
        } else if (wave == 3) {
            // Wave 3: Add SimpleEnemies + SeekingEnemies + SplitEnemies
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 22;  // Wave 3 will have 22 enemies in total
        } else if (wave == 4) {
            // Wave 4: A combination of all previous enemies, plus more
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 24;  // Wave 4 will have 24 enemies in total
        } else if (wave == 5) {
            // Boss wave: Add the Boss1 to the world
            levelMusic.pause();
            isWaveStart = false;
            enemiesInWave = 1;  // Wave 5 has only the boss (1 enemy)
        }
        waveDisplayed = true; // Set flag to true to display wave number
        resetWaveTimer(); // Reset the wave timer for displaying wave number
    }

    public void act() {
        updateMusic();
        if(levelDisplayed == true)
        {
            setupLevel();
        }
        
        updateTimerDisplay();
        // Handle pause and escape key
        Util.handleEscapeKey(this, pauseScreen);

        // Check if wave number should be displayed
        if (waveDisplayed) {
            addObject(new Label("Wave: " + waveNumber, 80), getWidth() / 2, getHeight() / 2); // Display wave number label
            if (getWaveTimeElapsed() > 3000) { // Check if 2 seconds have elapsed
                removeObjects(getObjects(Label.class)); // Remove wave number label
                waveDisplayed = false; // Reset flag
                isWaveStart = true;
                if(waveNumber == 5)
                {
                    bossMusic.playLoop();
                    Boss boss = new Boss3();
                    addObject(boss, getWidth() / 2, -100);
                    addObject(boss.hitbox, boss.getX(), boss.getY());
                }
                else if(waveNumber <= 3)
                {
                    spawnEnemy(waveNumber-1);
                }
            }
        }

        if(isWaveStart == true)
        {
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
                    // Move to the next wave if it's not the last wave
                    waveNumber++;
                    setupWave(waveNumber); // Setup the next wave
                }
            }
        }
    }

    private void spawnEnemies() {
        // Spawn enemies based on the current wave
        int enemyType = 0;
        if (waveNumber == 1) {
            // Wave 1: Add SimpleEnemies
            enemyType = Util.randomInt(6);
        } else if (waveNumber == 2) {
            // Wave 2: Add SimpleEnemies + SeekingEnemies
            enemyType = Util.randomInt(7);
        } else if (waveNumber == 3 || waveNumber == 4) {
            // Wave 3: Add SimpleEnemies + SeekingEnemies + SplitEnemies
            enemyType = Util.randomInt(8);
        }
        spawnEnemy(enemyType);
    }

    private boolean areAllEnemiesDead() {
        // Check if there are no Enemy objects in the world
        return getObjects(Enemy.class).isEmpty();
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
    public void updateMusic() {
        int effectiveVolume = audioManager.getEffectiveVolume();
        levelMusic.setVolume(effectiveVolume);
        bossMusic.setVolume(effectiveVolume);

        if (audioManager.isMuted()) {
            levelMusic.pause();
            bossMusic.pause();
        } else if (!levelMusic.isPlaying()) {
            levelMusic.playLoop();
            bossMusic.playLoop();
        }
    }
}
