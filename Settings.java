import greenfoot.*;

public class Settings extends World {
    private MenuScreen menuScreen;
    private static int volume = 50; // Default volume
    private Label volumeLabel;
    private VolumeSlider volumeSlider;
    
    public Settings(MenuScreen menuScreen) {
        super(500, 750, 1);
        this.menuScreen = menuScreen;
        
        // Set up volume controls
        volumeLabel = new Label("Volume: " + volume + "%", 30);
        addObject(volumeLabel, 250, 300);  // Centered horizontally, about 1/3 down
        
        volumeSlider = new VolumeSlider(volume);
        addObject(volumeSlider, 250, 350);  // Below the label
        
        // Keep your existing back button
        Button backButton = new Button(this::goMenuScreen, "Back");
        addObject(backButton, 250, 700); // Place at bottom center
        
        // Optional: Set background color
        getBackground().setColor(Color.WHITE);
        getBackground().fill();
    }
    
    public void act() {
        // Update volume label in real-time
        volumeLabel.setValue("Volume: " + volume + "%");
    }
    
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
    
    // Static methods to get/set volume from anywhere
    public static int getVolume() {
        return volume;
    }
    
    public static void setVolume(int newVolume) {
        volume = Math.min(100, Math.max(0, newVolume));
    }
}