import greenfoot.*;

public class SettingsSubscreen extends Actor {
    private MenuScreen menuscreen;
    private int screenWidth;
    private int screenHeight;

    public SettingsSubscreen(MenuScreen menuscreen) {
        screenWidth = menuscreen.getWidth();
        screenHeight = menuscreen.getHeight();

        // Create a new image for 1/3 of the screen width
        GreenfootImage settingsImage = new GreenfootImage(screenWidth / 3, screenHeight);
        settingsImage.setColor(Color.GRAY);
        settingsImage.fill();
        
        setImage(settingsImage);
        this.menuscreen = menuscreen;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            menuscreen.toggleSettings();
        }
    }
}