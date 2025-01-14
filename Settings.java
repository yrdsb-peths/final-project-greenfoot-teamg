import greenfoot.*;

public class Settings extends World {
    private Button closingButton;
    private Button soundButton;
    private MenuScreen menuScreen;
    private PauseScreen pauseScreen;
    private Label volumeLabel;
    private VolumeSlider volumeSlider;
    private AudioManager audioManager;

    public Settings(MenuScreen menuScreen, PauseScreen pauseScreen) {
        super(600, 750, 1);
        this.menuScreen = menuScreen;
        this.pauseScreen = pauseScreen;
        this.audioManager = AudioManager.getInstance();

        GreenfootImage volumeBarImage = new GreenfootImage("VolumeBar.jpg");
        getBackground().drawImage(volumeBarImage, 120, 170);

        setupVolumeControls();
        addLabels();
        setupButtons();
    }

    private void setupVolumeControls() {
        volumeSlider = new VolumeSlider(audioManager.getVolume());
        addObject(volumeSlider, 250, 350);

        soundButton = new Button(this::toggleSound, "");
        updateSoundButtonImage();
        addObject(soundButton, 100, 350);
    }

    public void act() {
        updateVolumeDisplay();
        handleEscapeNavigation();
    }

    private void updateVolumeDisplay() {
        String volumeText = audioManager.isMuted() ? "Muted" : audioManager.getVolume() + "%";
        volumeLabel.setValue("Volume: " + volumeText);
    }

    private void handleEscapeNavigation() {
        if (pauseScreen != null && pauseScreen.isFromSettings()) {
            Util.handleEscapeKey(this, pauseScreen);
        } else {
            Util.handleEscapeKey(this, menuScreen);
        }
    }

    private void addLabels() {
        volumeLabel = new Label("Volume: " + audioManager.getVolume() + "%", 30);
        addObject(volumeLabel, 250, 300);

        // Navigation labels
        addObject(new Label("ESC", 30), 40, 700);
        addObject(new Label("Back", 25), 100, 700);

        // Settings title
        addObject(new Label("Settings", 50), 300, 100);
    }

    private void setupButtons() {
        closingButton = new Button(this::goMenuScreen, "");
        closingButton.changeButtonImage("Home.png", 70, 70);
        addObject(closingButton, 550, 40);
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

    private void updateSoundButtonImage() {
        String imageName = audioManager.isMuted() ? "Muted.jpg" : "Sound.jpg";
        soundButton.changeButtonImage(imageName, 70, 70);
    }

    public void toggleSound() {
        audioManager.setMuted(!audioManager.isMuted());
        updateSoundButtonImage();
        updateGameSounds();
    }

    private void updateGameSounds() {
        int effectiveVolume = audioManager.getEffectiveVolume();
        if (menuScreen != null) {
            menuScreen.started(); // Ensure menu music state matches current settings
        }
    }

    public void updateVolume(int newVolume) {
        audioManager.setVolume(newVolume);
        if (!audioManager.isMuted()) {
            updateGameSounds();
        }
    }
}
