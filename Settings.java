import greenfoot.*;

public class Settings extends World {
    private Button ClosingButton;
    private Button soundButton;
    private MenuScreen menuScreen;
    private PauseScreen pauseScreen;
    private static int volume = 50;
    private Label volumeLabel;
    private VolumeSlider volumeSlider;
    private static boolean isMuted = false;

    public Settings(MenuScreen menuScreen, PauseScreen pauseScreen) {
        super(600, 750, 1);
        this.menuScreen = menuScreen;
        this.pauseScreen = pauseScreen;
        
        // Add VolumeBar image 
        GreenfootImage volumeBarImage = new GreenfootImage("VolumeBar.jpg");
        getBackground().drawImage(volumeBarImage, 120, 170);
        
        volumeSlider = new VolumeSlider(volume);

        addObject(volumeSlider, 250, 350);

        soundButton = new Button(this::toggleSound, "");
        soundButton.changeButtonImage("Sound.jpg", 70, 70);
        addObject(soundButton, 100, 350);

        addLabels();
        setupButtons();
    }

    public void act() {
        volumeLabel.setValue("Volume: " + (isMuted ? "Muted" : volume + "%"));
        if (pauseScreen != null && pauseScreen.isFromSettings()) {
            Util.handleEscapeKey(this, pauseScreen);
        } else {
            Util.handleEscapeKey(this, menuScreen);
        }
    }

    private void addLabels() {
        volumeLabel = new Label("Volume: " + volume + "%", 30);
        addObject(volumeLabel, 250, 300);
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);
    }

    private void setupButtons() {
        ClosingButton = new Button(this::goMenuScreen, "");
        ClosingButton.changeButtonImage("Home.png", 70, 70);
        addObject(ClosingButton, 550, 40);
    }

    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }

    public void goPauseScreen() {
        if (pauseScreen != null) {
            Greenfoot.setWorld(pauseScreen);
        } else {
            Greenfoot.setWorld(menuScreen);
        }
    }

    public void toggleSound() {
        isMuted = !isMuted;
        if (isMuted) {
            volumeSlider.setValue(0);
            soundButton.changeButtonImage("Muted.jpg", 70, 70);
        } else {
            volumeSlider.setValue(50);
            soundButton.changeButtonImage("Sound.jpg", 70, 70);
        }
    }

    public void toggleMute() {
        isMuted = !isMuted;
        if (isMuted) {
            volumeSlider.setValue(0);
        } else {
            volumeSlider.setValue(volume);
        }
    }

    public static int getVolume() {
        return volume;
    }

    public static void setVolume(int newVolume) {
        volume = Math.min(100, Math.max(0, newVolume));
    }
}
