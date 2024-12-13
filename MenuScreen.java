import greenfoot.*;

public class MenuScreen extends World {
    private MenuButton menuButton;
    private MenuSubscreen menuSubscreen;
    private boolean isSettingsVisible = false;

    public MenuScreen() {
        super(500, 700, 1);
        
        // Create the MenuSubscreen
        menuSubscreen = new MenuSubscreen(this);
        
        // Create settings button in right corner
        menuButton = new MenuButton(this);
        addObject(menuButton, getWidth() - 50, 50);
    }

    public void toggleSettings() {
        if (!isSettingsVisible) {
            addObject(menuSubscreen, 450, 300);
            isSettingsVisible = true;
        } else {
            removeObject(menuSubscreen);
            isSettingsVisible = false;
        }
    }
}