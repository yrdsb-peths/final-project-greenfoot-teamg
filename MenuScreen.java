import greenfoot.*;

public class MenuScreen extends World {
    public static boolean enterChecker = true;
    private Button settingButton;
    private Button leaderboardButton;
    private Button instructionsButton;
    private CharacterSelection characterSelection;
    private boolean checker;
    GreenfootSound menuMusic; // Music for the menu 
    private AudioManager audioManager;

    /**
     * Constructor for the menu screen, sets up every variable.
     */
    public MenuScreen() {
        super(600, 750, 1);
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        checker = true;
        audioManager = AudioManager.getInstance();
        setupButtons();
        addLabels();
        
        // Initialize characterSelection
        characterSelection = new CharacterSelection(this);
        
        // Initialize the background music
        menuMusic = new GreenfootSound("Menu.mp3");
        updateMusic();
    }

    /**
     * Updates the music volume and checks if enter is pressed
     */
    public void act() {
        handleEnterKey();
        updateMusic();
    }

    /**
     * Adds the butons at the top right of the screen.
     */
    private void setupButtons() {
        // Setting button
        settingButton = new Button(this::goSettingScreen, "");
        settingButton.changeButtonImage("setting.png", 70, 70);
        addObject(settingButton, 550, 40);

        // Leaderboard button
        leaderboardButton = new Button(this::goHighScoresScreen, "");
        leaderboardButton.changeButtonImage("leaderboard.png", 90, 90);
        addObject(leaderboardButton, 550, 115);

        instructionsButton = new Button(this::goInstructionsScreen, "");
        instructionsButton.changeButtonImage("questionMark.png", 95, 95);
        addObject(instructionsButton, 550, 190);
    }
    
    /**
     * Adds the labels of the menu screen
     */
    private void addLabels() {
        addObject(new Label("Space Fighters: \n Bullet Hell", 70), getWidth() / 2, 100);
        addObject(new Label("Press Enter to Start", 50), getWidth() / 2, getHeight() / 2);
        addObject(new Label("(New Players click on question mark before starting)", 30), getWidth() / 2, getHeight() / 2 + 50);

    }

    /**
     * If enter is pressed, world will switch to CharacterSelection.
     */
    private void handleEnterKey() {
        if(Greenfoot.isKeyDown("enter") && enterChecker == false) {
            enterChecker = true;
            gocharacterselection();
        }
        else if(!Greenfoot.isKeyDown("enter") && enterChecker == true)
        {
            enterChecker = false;
        }
    }

    /**
     * World switches to InstructionsScreen
     */
    private void goInstructionsScreen() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }

    /**
     * World switches to SettingScreen
     */
    private void goSettingScreen() {
        Greenfoot.setWorld(new Settings(this, null)); // Pass the MenuScreen instance and null for PauseScreen
    }

    /**
     * World switches to CharacterSelection
     */
    private void gocharacterselection() {
        Greenfoot.setWorld(characterSelection);
    }

    /**
     * World switches to HighScoresScreen
     */
    private void goHighScoresScreen() {
        Greenfoot.setWorld(new HighScore(this));
    }
    
    /**
     * If start button is pressed, play music.
     */
    public void started() {
        // Ensure the music resumes when the world starts
        menuMusic.playLoop();
    }
    
    /**
     * If pause button is pressed, stop music.
     */
    public void stopped() {
        // Pause the music when the world is stopped
        menuMusic.pause();
    }

    /**
     * Changes the volume of the music.
     */
    private void updateMusic() {
        int effectiveVolume = audioManager.getEffectiveVolume();
        menuMusic.setVolume(effectiveVolume);
    }
}
