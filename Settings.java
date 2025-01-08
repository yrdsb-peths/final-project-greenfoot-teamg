import greenfoot.*;

public class Settings extends World {
    private Button ClosingButton;
    private Button soundButton;
    private MenuScreen menuScreen;
    private static int volume = 50; // Default volume
    private Label volumeLabel;
    private VolumeSlider volumeSlider;
    private static boolean isMuted = false;

    public Settings(MenuScreen menuScreen) {
        super(500, 750, 1);
        this.menuScreen = menuScreen;
        
        // Add VolumeBar image 
        GreenfootImage volumeBarImage = new GreenfootImage("VolumeBar.jpg"); 
        getBackground().drawImage(volumeBarImage, 120, 170);
        
        
        volumeSlider = new VolumeSlider(volume);

        addObject(volumeSlider, 250, 350);  // Below the label

        soundButton = new Button(this::toggleSound, "");
        soundButton.changeButtonImage("Sound.jpg", 70, 70);
        addObject(soundButton, 100, 350);

        addLabels();
        setupButtons();
    }

    public void act() {
        // Update volume label in real-time
        volumeLabel.setValue("Volume: " + (isMuted ? "Muted" : volume + "%"));
        Util.handleEscapeKey(this, menuScreen);
    }

    private void addLabels() {
        // Set up volume controls
        volumeLabel = new Label("Volume: " + volume + "%", 30);
        addObject(volumeLabel, 250, 300);
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);
    }

    private void setupButtons() {
        // Closing button
        ClosingButton = new Button(this::goMenuScreen, "");
        ClosingButton.changeButtonImage("Home.png", 70, 70);
        addObject(ClosingButton, 450, 40);
    }

    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }

    public void toggleSound() {
        isMuted = !isMuted;
        if (isMuted) {
            volumeSlider.setValue(0);
            soundButton.changeButtonImage("Muted.jpg", 70, 70);
        } else {
            volumeSlider.setValue(50); // Reset volume to default
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

    // Static methods to get/set volume from anywhere
    public static int getVolume() {
        return volume;
    }

    public static void setVolume(int newVolume) {
        volume = Math.min(100, Math.max(0, newVolume));
    }
}
