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
        GreenfootImage background = new GreenfootImage("blur.png");
        background.scale(getWidth(), getHeight());
        setBackground(background);

        this.menuScreen = menuScreen;
        this.pauseScreen = pauseScreen;
        this.audioManager = AudioManager.getInstance();
        GreenfootImage volumeBarImage = new GreenfootImage("VolumeBar.png");
        getBackground().drawImage(volumeBarImage, 120, 170);
        

        setupVolumeControls();
        addLabels();
    }

    /**
     * Creates the volume slider and the mute button
     */
    private void setupVolumeControls() {
        volumeSlider = new VolumeSlider(audioManager.getVolume());
        addObject(volumeSlider, 250, 350);

        soundButton = new Button(this::toggleSound, "");
        updateSoundButtonImage();
        addObject(soundButton, 100, 350);
    }

    /**
     * constantly checks if escape is pressed and changes the volume label
     */
    public void act() {
        updateVolumeDisplay();
        handleEscapeNavigation();
    }

    /**
     * changes the volume label
     */
    private void updateVolumeDisplay() {
        String volumeText = audioManager.isMuted() ? "Muted" : audioManager.getVolume() + "%";
        volumeLabel.setValue("Volume: " + volumeText);
    }

    /**
     * if escape is pressed, world switches to previous world.
     */
    private void handleEscapeNavigation() {
        if (pauseScreen != null && pauseScreen.isFromSettings()) {
            Util.handleEscapeKey(this, pauseScreen);
        } else {
            Util.handleEscapeKey(this, menuScreen);
        }
    }

    /**
     * Adds the labels
     */
    private void addLabels() {
        volumeLabel = new Label("Volume: " + audioManager.getVolume() + "%", 30);
        addObject(volumeLabel, 290, 300);

        // Navigation labels
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);

        // Settings title
        addObject(new Label("Settings", 50), 300, 100);
    }

    /**
     * Changes the sound button image
     */
    private void updateSoundButtonImage() {
        String imageName = audioManager.isMuted() ? "Muted.png" : "Sound.png";
        soundButton.changeButtonImage(imageName, 70, 70);
    }

    /**
     * Swaps the volume from muted to unmuted
     */
    public void toggleSound() {
        audioManager.setMuted(!audioManager.isMuted());
        if (audioManager.isMuted()) {
            volumeSlider.setValue(0);
            updateVolume(0);
        }
        updateSoundButtonImage();
        updateGameSounds();
    }

    /**
     * Changes the volume in audio manager
     */
    private void updateGameSounds() {
        int effectiveVolume = audioManager.getEffectiveVolume();
    }

    /**
     * updates the volume in the game
     */
    public void updateVolume(int newVolume) {
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
        if (!audioManager.isMuted()) {
            updateGameSounds();
        }
    }
}
