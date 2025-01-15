import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Level3 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen;
    private boolean bossDefeated = false;  // Track if the boss is defeated
    private GreenfootSound levelMusic;
    private GreenfootSound bossMusic;
    private AudioManager audioManager;

    /**
     * Constructor for Level3.
     * @param selectedImage The image for the player's character.
     */
    public Level3(GreenfootImage selectedImage, MenuScreen menuScreen, int whichCharacter, SimpleTimer levelTimer) {
        super(600, 750, 1, selectedImage, whichCharacter, levelTimer);
        this.menuScreen = menuScreen;

        audioManager = AudioManager.getInstance();

        pauseScreen = new PauseScreen(this, menuScreen); // Initialize the pause screen

        levelMusic = new GreenfootSound("Stage3.mp3");
        bossMusic = new GreenfootSound("Stage3Boss.mp3");
    }

    @Override
    protected void setupLevel() {
        setBackground("Stage3Background.png");

        if (levelDisplayed) {
            addObject(new Label("Level 3", 50), getWidth() / 2, getHeight() / 2); // Display "Level 1"
            if (spawnTimer.millisElapsed() > 3000) { // Wait for 3 seconds
                removeObjects(getObjects(Label.class)); // Remove the "Level 1" label
                levelDisplayed = false; // Set flag to false to start the game
                setupWave(waveNumber); // Setup the first wave of enemies
            }
        }
    }

    private void updateTimerDisplay() {
        if (timerLabel != null) {
            removeObject(timerLabel);
        }

        timerLabel = new Label("Time: " + levelTimer.millisElapsed() / 1000, 30);
        addObject(timerLabel, getWidth() - 100, 20);
    }

    private void setupWave(int wave) {
        if (wave == 1) {
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 18;
        } else if (wave == 2) {
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 20;
        } else if (wave == 3) {
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 22;
        } else if (wave == 4) {
            isWaveStart = false;
            enemiesSpawned = 0; // Reset the spawn counter for the wave
            enemiesInWave = 24;
        } else if (wave == 5) {
            levelMusic.pause();
            warningSound.play();
            addObject(new BossAlertAnimation(), getWidth()/2, getHeight()/6);
            enemiesInWave = 1; // Only the boss in wave 5
        }
        waveDisplayed = true;
        resetWaveTimer();
    }

    public void act() {
        if(isFreeze == false)
        {
            updateMusic();
            if(levelDisplayed == true)
            {
                setupLevel();
            }
    
            if(levelTimer != null)
            {
                updateTimerDisplay();
            }
            Util.handleEscapeKey(this, pauseScreen);
    
            if (waveDisplayed) {
                addObject(new Label("Wave: " + waveNumber, 80), getWidth() / 2, getHeight() / 2);
                if (getWaveTimeElapsed() > 3000) {
                    removeObjects(getObjects(Label.class));
                    waveDisplayed = false;
                    isWaveStart = true;
                    if (waveNumber == 5) {
                        bossMusic.playLoop();
                        Boss boss = new Boss3();
                        addObject(boss, getWidth() / 2, -100);
                        addObject(boss.hitbox, boss.getX(), boss.getY());
                        enemiesSpawned++;
                    }
                    else if(waveNumber <= 3)
                    {
                        spawnEnemy(waveNumber + 5);
                    }
                }
            }
    
            if (isWaveStart) {
                if (enemiesSpawned < enemiesInWave && spawnTimer.millisElapsed() > spawnDelay) {
                    spawnEnemies();
                    spawnTimer.mark();
                }
    
                if (enemiesSpawned >= enemiesInWave && areAllEnemiesDead()) {
                    if (waveNumber < 5) {
                        waveNumber++;
                        setupWave(waveNumber);
                    } else if (waveNumber == 5 && areAllEnemiesDead() && waveTimer.millisElapsed() > 5000) {
                        // Transition to Victory screen
                        Greenfoot.setWorld(new VictScreen(levelTimer, menuScreen)); 
                    }
                }
            }
        }
    }


    private void spawnEnemies() {
        int enemyType = 0;
        if (waveNumber == 1) {
            enemyType = Util.randomInt(6);
        } else if (waveNumber == 2) {
            enemyType = Util.randomInt(7);
        } else if (waveNumber == 3 || waveNumber == 4) {
            enemyType = Util.randomInt(8);
        }
        spawnEnemy(enemyType);
    }

    private boolean areAllEnemiesDead() {
        return getObjects(Enemy.class).isEmpty();
    }

    public void started() {
        if (waveNumber < 5) {
            levelMusic.playLoop();
        } else {
            bossMusic.playLoop();
        }
    }

    public void stopped() {
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
