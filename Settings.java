import greenfoot.*;

public class Settings extends World {
    private Button closingButton;
    private Button soundButton;
    private MenuScreen menuScreen;
    private PauseScreen pauseScreen;
    private Label volumeLabel;
    private VolumeSlider volumeSlider;
    private AudioManager audioManager;

    /**
     * Constructor for the class, sets up all the variables
     */
    public Settings(MenuScreen menuScreen, PauseScreen pauseScreen) {
        super(600, 750, 1);
        
        // Set background for the settings screen
        GreenfootImage background = new GreenfootImage("blur.png");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        // Store menuScreen and pauseScreen for navigation
        this.menuScreen = menuScreen;
        this.pauseScreen = pauseScreen;
        
        // Get the AudioManager instance for controlling audio
        this.audioManager = AudioManager.getInstance();
        
        // Draw volume bar image on background
        GreenfootImage volumeBarImage = new GreenfootImage("VolumeBar.png");
        getBackground().drawImage(volumeBarImage, 120, 170);
        
        // Set up volume controls
        setupVolumeControls();
        
        // Add labels to the screen
        addLabels();
    }

    /**
     * Creates the volume slider and the mute button
     */
    private void setupVolumeControls() {
        // Create and add the volume slider to the screen
        volumeSlider = new VolumeSlider(audioManager.getVolume());
        addObject(volumeSlider, 250, 350);

        // Create and add the sound button to the screen
        soundButton = new Button(this::toggleSound, "");
        updateSoundButtonImage();  // Set the correct image based on mute state
        addObject(soundButton, 100, 350);
    }

    /**
     * constantly checks if escape is pressed and changes the volume label
     */
    public void act() {
        // Update the volume display based on the current sound settings
        updateVolumeDisplay();
        
        // Handle escape key press for navigation
        handleEscapeNavigation();
    }

    /**
     * changes the volume label
     */
    private void updateVolumeDisplay() {
        // Display the current volume or "Muted" text based on mute state
        String volumeText = audioManager.isMuted() ? "Muted" : audioManager.getVolume() + "%";
        volumeLabel.setValue("Volume: " + volumeText);
    }

    /**
     * if escape is pressed, world switches to previous world.
     */
    private void handleEscapeNavigation() {
        // Check whether we are coming from the settings or pause screen
        if (pauseScreen != null && pauseScreen.isFromSettings()) {
            Util.handleEscapeKey(this, pauseScreen);  // Return to the pause screen
        } else {
            Util.handleEscapeKey(this, menuScreen);  // Return to the menu screen
        }
    }

    /**
     * Adds the labels to the screen
     */
    private void addLabels() {
        // Add volume label to show current volume
        volumeLabel = new Label("Volume: " + audioManager.getVolume() + "%", 30);
        addObject(volumeLabel, 290, 300);

        // Navigation labels for ESC and Back
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);

        // Title of the settings screen
        addObject(new Label("Settings", 50), 300, 100);
    }

    /**
     * Changes the sound button image based on mute state
     */
    private void updateSoundButtonImage() {
        // Switch button image based on whether the sound is muted or not
        String imageName = audioManager.isMuted() ? "Muted.png" : "Sound.png";
        soundButton.changeButtonImage(imageName, 70, 70);
    }

    /**
     * Swaps the volume from muted to unmuted
     */
    public void toggleSound() {
        // Toggle mute state
        audioManager.setMuted(!audioManager.isMuted());
        
        // If muted, set volume to 0
        if (audioManager.isMuted()) {
            volumeSlider.setValue(0);
            updateVolume(0);  // Update volume to 0
        }
        
        // Update button image
        updateSoundButtonImage();
        
        // Update game sounds based on current settings
        updateGameSounds();
    }

    /**
     * Changes the volume in audio manager
     */
    private void updateGameSounds() {
        // Get the effective volume based on mute state
        int effectiveVolume = audioManager.getEffectiveVolume();
    }

    /**
     * updates the volume in the game
     */
    public void updateVolume(int newVolume) {
        // Update the volume in the audio manager
        audioManager.setVolume(newVolume);
        
        // Automatically mute when volume reaches 0
        if (newVolume == 0) {
            audioManager.setMuted(true);
            updateSoundButtonImage();
        } else if (audioManager.isMuted() && newVolume > 0) {
            // Automatically unmute when volume is increased from 0
            audioManager.setMuted(false);
            updateSoundButtonImage();
        }
        
        // If not muted, update the game sounds
        if (!audioManager.isMuted()) {
            updateGameSounds();
        }
    }
}
