import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Level2 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen; // Add menuScreen
    private GreenfootImage selectedShip; // Store the selected ship image
    private int whichCharacter; // Store the character index
    private AudioManager audioManager;

    private Label timerLabel;  // To display the timer

    /**
     * Constructor for Level2.
     * 
     * @param selectedImage The image for the player's character.
     */
    public Level2(GreenfootImage selectedImage, MenuScreen menuScreen, int whichCharacter, SimpleTimer levelTimer) {
        super(600, 750, 1, selectedImage, whichCharacter, levelTimer);
        this.selectedShip = selectedImage;  // Store the selected ship image
        this.menuScreen = menuScreen;  // Store the menu screen
        this.whichCharacter = whichCharacter;  // Store the character index
        audioManager = AudioManager.getInstance();

        pauseScreen = new PauseScreen(this, menuScreen); // Initialize the pause screen

        levelMusic = new GreenfootSound("Stage2.mp3");
        bossMusic = new GreenfootSound("Stage2Boss.mp3");
    }

    @Override
    protected void setupLevel() {
        // Set the background for Level 2
        setBackground("Stage2Background.png");

        // Show the "Level 2" label for 3 seconds before starting the gameplay
        if (levelDisplayed) {
            addObject(new Label("Level 2", 50), getWidth() / 2, getHeight() / 2); // Display "Level 2"
            if (spawnTimer.millisElapsed() > 3000) { // Wait for 3 seconds
                removeObjects(getObjects(Label.class)); // Remove the "Level 2" label
                levelDisplayed = false; // Set flag to false to start the game
                setupWave(waveNumber); // Setup the first wave of enemies
            }
        }
    }

    // Method to setup the current wave
    private void setupWave(int wave) {
        if (wave == 1) {
            // Wave 1: Add only SimpleEnemies
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 10; // Wave 1 starts with 10 SimpleEnemies
        } else if (wave == 2) {
            // Wave 2: Add SimpleEnemies + SeekingEnemies
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 12; // Wave 2 will have 12 enemies in total
        } else if (wave == 3) {
            // Wave 3: Add SimpleEnemies + SeekingEnemies + SplitEnemies
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 14; // Wave 3 will have 14 enemies in total
        } else if (wave == 4) {
            // Wave 4: A combination of all previous enemies, plus more
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 16; // Wave 4 will have 16 enemies in total
        } else if (wave == 5) {
            levelMusic.pause();
            isWaveStart = false;
            warningSound.play();
            addObject(new BossAlertAnimation(), getWidth()/2, getHeight()/6);
            enemiesInWave = 1; // Wave 5 has only the boss (1 enemy)
        }
        waveDisplayed = true; // Set flag to true to display wave number
        resetWaveTimer(); // Reset the wave timer for displaying wave number
    }

    public void act() {
        updateMusic();
        if (levelDisplayed == true) {
            setupLevel();
        }

        // Handle pause and escape key
        Util.handleEscapeKey(this, pauseScreen);

        // Display the timer in Level 2 (same as Level 1)
        if(levelTimer != null)
        {
            updateTimerDisplay();
        }

        // Check if wave number should be displayed
        if (waveDisplayed) {
            addObject(new Label("Wave: " + waveNumber, 80), getWidth() / 2, getHeight() / 2); // Display wave number
                                                                                              // label
            if (getWaveTimeElapsed() > 3000) { // Check if 2 seconds have elapsed

                removeObjects(getObjects(Label.class)); // Remove wave number label
                waveDisplayed = false; // Reset flag
                isWaveStart = true;
                if (waveNumber == 5) {
                    bossMusic.playLoop();
                    Boss boss = new Boss2();
                    addObject(boss, getWidth() / 2, -100);
                    addObject(boss.hitbox, boss.getX(), boss.getY());
                    enemiesSpawned++;
                }
                else if(waveNumber <= 3)
                {
                    spawnEnemy(waveNumber-1);
                }
            }
        }

        if (isWaveStart == true) {
            // Check if it's time to spawn new enemies
            if (enemiesSpawned < enemiesInWave && spawnTimer.millisElapsed() > spawnDelay) {
                // Spawn the next enemy if wave is not complete
                spawnEnemies();
                spawnTimer.mark(); // Reset the timer after spawning an enemy
            }

            // Check if all enemies in the current wave have been removed from the world
            if (enemiesSpawned >= enemiesInWave && areAllEnemiesDead()) {
                // Wait for some time before transitioning to the next wave
                // Check if this was the boss wave and the boss is defeated
                if (waveNumber == 5 && areAllEnemiesDead()) {
                    // Transition to Level 3 when the boss is defeated
                    stopped();
                    Greenfoot.setWorld(new Level3(selectedShip, menuScreen, whichCharacter, levelTimer));
                } else {
                    if (waveNumber < 5) {
                        // Move to the next wave if it's not the last wave
                        waveNumber++;
                        setupWave(waveNumber); // Setup the next wave
                    }
                }
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

    private void spawnEnemies() {
        // Spawn enemies based on the current wave
        int enemyType = 0;
        if (waveNumber == 1) {
            // Wave 1: Add SimpleEnemies
            enemyType = Util.randomInt(3);
        } else if (waveNumber == 2) {
            // Wave 2: Add SimpleEnemies + SeekingEnemies
            enemyType = Util.randomInt(4);
        } else if (waveNumber == 3 || waveNumber == 4) {
            // Wave 3: Add SimpleEnemies + SeekingEnemies + SplitEnemies
            enemyType = Util.randomInt(5);
        }
        spawnEnemy(enemyType);
    }

    private boolean areAllEnemiesDead() {
        // Check if there are no Enemy objects in the world
        return getObjects(Enemy.class).isEmpty();
    }

    public void started() {
        // Ensure the music resumes when the world starts
        if (waveNumber < 5) {
            levelMusic.playLoop();
        } else {
            bossMusic.playLoop();
        }
    }

    public void stopped() {
        // Pause the music when the world is stopped
        if (waveNumber < 5) {
            levelMusic.pause();
        } else {
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
