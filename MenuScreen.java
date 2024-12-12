import greenfoot.*;

public class MenuScreen extends World {
    private SettingButton settingsButton;
    private SettingsSubscreen settingsSubScreen;
    private boolean isSettingsVisible = false;

    public MenuScreen() {
        super(500, 700, 1);
        
        // Create settings button in right corner
        settingsButton = new SettingButton(this);
        addObject(settingsButton, getWidth() - 50, 50);

        // Create settings sub-screen (initially not visible)
        settingsSubScreen = new SettingsSubscreen(this);
    }

    public void toggleSettings() {
        if (!isSettingsVisible) {
            addObject(settingsSubScreen, 400, 400);
            isSettingsVisible = true;
        } else {
            removeObject(settingsSubScreen);
            isSettingsVisible = false;
        }
    }
}