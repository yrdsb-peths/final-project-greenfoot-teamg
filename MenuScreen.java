import greenfoot.*;

public class MenuScreen extends World {
    private Button pauseButton;
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
        handleEscapeKey();
    }

    private void setupButtons() {
        pauseButton = new Button(this::gopauseScreen, "");
        addObject(pauseButton, 450, 30);
    }

    private void addLabels() {
        addObject(new Label("Title", 100), getWidth() / 2, 100);
    }

    private void handleEscapeKey() {
        // Continuously check for "escape" key press to return to the menu screen
        if (Greenfoot.isKeyDown("escape") && checker != true) {
            checker = true;
            gopauseScreen();
        } else if (!Greenfoot.isKeyDown("escape")) {
            checker = false;
        }
    }

    private void gopauseScreen() {
        Greenfoot.setWorld(new pauseScreen(this));
    }
}
