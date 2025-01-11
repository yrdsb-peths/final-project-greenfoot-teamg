import greenfoot.*;  

public class Level1 extends Game {

    private PauseScreen pauseScreen;
    private MenuScreen menuScreen;
    private GreenfootImage selectedShip; 
    private int waveNumber = 1; 
    private int enemiesInWave = 2; 
    private int enemiesSpawned = 0;
    private SimpleTimer spawnTimer; 
    private int spawnDelay = 1000; 
    private boolean waveDisplayed = false; 
    private boolean levelDisplayed = true; 
    private int indexShips;

    public Level1(GreenfootImage selectedShip, MenuScreen menuScreen, int indexShips) {
        super(600, 750, 1, selectedShip, 1);
        this.selectedShip = selectedShip; 
        this.menuScreen = menuScreen;
        this.indexShips = indexShips;
        pauseScreen = new PauseScreen(this, menuScreen);
        spawnTimer = new SimpleTimer();
        spawnTimer.mark();
    }

    @Override
    protected void setupLevel() {
        setBackground("Stage1Background.jpg");
        if (levelDisplayed) {
            addObject(new Label("Level 1", 50), getWidth() / 2, getHeight() / 2);
            if (spawnTimer.millisElapsed() > 3000) {
                removeObjects(getObjects(Label.class));
                levelDisplayed = false;
                setupWave(waveNumber);
            }
        }
    }

    private void setupWave(int wave) {
        enemiesSpawned = 0;
        if (wave < 5) {
            enemiesInWave = wave * 2;
        } else {
            addObject(new Boss1(), getWidth() / 2, 10);
            enemiesInWave = 1;
        }
        waveDisplayed = true;
        resetWaveTimer();
    }

    public void act() {
        Util.handleEscapeKey(this, pauseScreen);

        if (waveDisplayed) {
            addObject(new Label("Wave: " + waveNumber, 80), getWidth() / 2, getHeight() / 2);
            if (getWaveTimeElapsed() > 2000) {
                removeObjects(getObjects(Label.class));
                waveDisplayed = false;
            }
        }

        if (enemiesSpawned < enemiesInWave && spawnTimer.millisElapsed() > spawnDelay) {
            spawnEnemies();
            spawnTimer.mark();
        }

        if (enemiesSpawned >= enemiesInWave && areAllEnemiesDead()) {
            if (waveNumber < 5) {
                waveNumber++;
                setupWave(waveNumber);
            } else {
                transitionToNextLevel();
            }
        }
    }

    private void spawnEnemies() {
        if (waveNumber < 5) {
            addObject(new SimpleEnemy(), Greenfoot.getRandomNumber(getWidth()), 0);
        }
        enemiesSpawned++;
    }

    private boolean areAllEnemiesDead() {
        return getObjects(Enemy.class).isEmpty();
    }

    private void transitionToNextLevel() {
        Greenfoot.setWorld(new Level2(selectedShip, menuScreen, indexShips)); // Now selectedShip is accessible
    }
}
