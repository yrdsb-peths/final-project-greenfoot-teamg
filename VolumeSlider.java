import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class VolumeSlider extends Actor {
    private boolean isDragging = false;
    private int minX, maxX;
    private GreenfootImage sliderBar;
    private int sliderWidth;
    private int sliderHeight;

    public VolumeSlider(int initialVolume) {
        setupSliderBar();
        setInitialPosition(initialVolume);
    }

    private void setupSliderBar() {
        sliderBar = new GreenfootImage("VolumeSlider.png");
        sliderBar.scale(60, 60);
        sliderWidth = sliderBar.getWidth();
        sliderHeight = sliderBar.getHeight();
        setImage(sliderBar);
    }

    private void setInitialPosition(int initialVolume) {
        GreenfootImage finalImage = new GreenfootImage(sliderWidth, sliderHeight);
        finalImage.drawImage(sliderBar, 0, 0);
        setImage(finalImage);
        // This method no longer sets the position of a knob
    }

    public void act() {
        if (Greenfoot.mousePressed(this)) {
            isDragging = true;
        }

        if (isDragging && Greenfoot.mouseDragged(null)) {
            MouseInfo mouse = Greenfoot.getMouseInfo();
            if (mouse != null) {
                if (minX == 0) {
                    minX = getX() - (sliderWidth * 1);
                    maxX = getX() + (sliderWidth * 3);
                }

                int newX = Math.min(maxX, Math.max(minX, mouse.getX()));
                setLocation(newX, getY());

                int volume = (int) (((double) (newX - minX) / (maxX - minX)) * 100);
                Settings.setVolume(volume);
            }
        }

        if (Greenfoot.mouseClicked(null)) {
            isDragging = false;
        }
    }

    public void setValue(int volume) {
        volume = Math.min(100, Math.max(0, volume)); // Ensure volume is within 0-100 range
        Settings.setVolume(volume);
    }
}
