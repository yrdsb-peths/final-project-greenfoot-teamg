import greenfoot.*;

public class MenuScreen extends World {
    public static boolean enterChecker = false;
    private Button settingButton;
    private Button leaderboardButton;
    private Button instructionsButton;
    private CharacterSelection characterSelection;
    private boolean checker;

    public MenuScreen() {
        super(600, 750, 1);
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        checker = true;
        
        setupButtons();
        addLabels();
    }
    
    public void act() {
        handleEnterKey();
    }

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

    private void addLabels() {
        addObject(new Label("Title", 100), getWidth() / 2, 100);
        addObject(new Label("Press Enter to Start", 50), getWidth() / 2, getHeight() / 2);
    }

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

    private void goInstructionsScreen() {
        Greenfoot.setWorld(new InstructionScreen(this));
    }

    private void goSettingScreen() {
        Greenfoot.setWorld(new Settings(this));
    }

    private void gocharacterselection() {
        Greenfoot.setWorld(new CharacterSelection(this));
    }

    private void goHighScoresScreen() {
        Greenfoot.setWorld(new HighScore(this));
    }
}