import greenfoot.*;

public class VolumeSlider extends Actor {
    private boolean isDragging = false;
    private int minX = 170;
    private int maxX = 430;
    private GreenfootImage sliderBar;
    private int sliderWidth;
    private int sliderHeight;
    private AudioManager audioManager;

    public VolumeSlider(int initialVolume) {
        audioManager = AudioManager.getInstance();
        setupSliderBar();
        // Initial position is set in addedToWorld to ensure correct positioning
    }

    private void setupSliderBar() {
        sliderBar = new GreenfootImage("VolumeSlider.png");
        sliderBar.scale(60, 60);
        sliderWidth = sliderBar.getWidth();
        sliderHeight = sliderBar.getHeight();
        setImage(sliderBar);
    }

    @Override
    protected void addedToWorld(World world) {
        setInitialPosition(audioManager.getVolume());
    }

    private void setInitialPosition(int initialVolume) {
        // Calculate initial position based on volume
        int newX = minX + (int)((maxX - minX) * (initialVolume / 100.0));
        setLocation(newX, getY());
    }

    public void act() {
        handleDragging();
    }

    private void handleDragging() {
        if (Greenfoot.mousePressed(this)) {
            isDragging = true;
        }

        if (isDragging && Greenfoot.mouseDragged(this)) {  // Ensuring only this actor is dragged
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                int mouseX = Math.max(minX, Math.min(maxX, mouse.getX())); // Clamp mouseX to slider bounds
                updateVolumeFromPosition(mouseX);
            }
        }

        if (Greenfoot.mouseClicked(null)) {
            isDragging = false;
        }
    }

    private void updateVolumeFromPosition(int mouseX) {
        setLocation(mouseX, getY());

        int volume = (int)(((double)(mouseX - minX) / (maxX - minX)) * 100);
        
        // Update volume through AudioManager
        audioManager.setVolume(volume);

        // Update the Settings world display if present
        World world = getWorld();
        if (world instanceof Settings) {
            ((Settings)world).updateVolume(volume);
        }
    }

    public void setValue(int volume) {
        volume = Math.min(100, Math.max(0, volume));

        // Update AudioManager
        audioManager.setVolume(volume);

        // Calculate and set position based on volume
        int newX = minX + (int)((maxX - minX) * (volume / 100.0));
        setLocation(newX, getY());

        // Update the Settings world display if present
        World world = getWorld();
        if (world instanceof Settings) {
            ((Settings)world).updateVolume(volume);
        }
    }
}
