import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class ClosingButton extends Button {
    public ClosingButton(Runnable action) {
        super(action, ""); // Pass an empty string for text
    }

    @Override
    protected void updateButtonImage() {
        // Custom red button background
        GreenfootImage buttonImage = new GreenfootImage("button_X.png");

        // Set the image without adding text
        setImage(buttonImage);
      }
}
