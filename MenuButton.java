import greenfoot.*;

public class MenuButton extends Actor {
    private GreenfootSound buttonClickSound;
    private Runnable action;
    private String text;
    private MenuScreen menuScreen;

    public MenuButton(MenuScreen menuScreen) {
        GreenfootImage setImage = new GreenfootImage("setting.png");
        setImage(setImage);
        this.menuScreen = menuScreen;
    }

    public void act() {
        if (Greenfoot.mouseClicked(this) || Greenfoot.isKeyDown("escape")) {
            // Play click sound
            buttonClickSound = new GreenfootSound("ButtonClick.mp3");
            buttonClickSound.setVolume(100);
            buttonClickSound.play();
            
            // Toggle settings screen
            menuScreen.toggleSettings();
        }
    }
}