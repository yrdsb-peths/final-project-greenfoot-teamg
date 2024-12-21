import javax.xml.stream.events.Characters;

import greenfoot.*;

public class MenuScreen extends World {
    private Button pauseButton;
    private CharacterSelection characterSelection;
    private PauseScreen pauseScreen;
    boolean checker;

    public MenuScreen() {
        super(500, 750, 1);
        GreenfootImage background = new GreenfootImage("CharacterSelection.jpg");
        background.scale(getWidth(), getHeight()); // Adjust to world size (500x700)
        setBackground(background);

        checker = true;
        
        setupButtons();
        addLabels();
    }

    public void act() {
        if (pauseScreen == null) {
            pauseScreen = new PauseScreen(this);
        }
        Util.handleEscapeKey(this, pauseScreen);
        handleEnterKey();
    }

    private void setupButtons() {
        pauseButton = new Button(this::gopauseScreen, "");
        pauseButton.changeButtonImage("pausebutton.png");
        addObject(pauseButton, 450, 30);
    }

    private void addLabels() {
        addObject(new Label("Title", 100), getWidth() / 2, 100);

        addObject(new Label("Press Enter to Start", 50), getWidth()/ 2, getHeight() / 2);
    }

    private void handleEnterKey() {
        if (Greenfoot.isKeyDown("enter")) {
            gocharacterselection();
        }
    }

    private void gopauseScreen() {
        Greenfoot.setWorld(new PauseScreen(this));
    }

    private void gocharacterselection() {
        Greenfoot.setWorld(new CharacterSelection(this));

    }
}
