import greenfoot.*;

public class Button extends Actor {
    private Runnable action;
    protected String text;
    private GreenfootSound buttonClickSound;

    /**
     * Constructor for the Button class that sets up the action and text.
     */
    public Button(Runnable action, String text) {
        this.action = action;
        this.text = text;
        updateButtonImage();
    }

    /**
     * This method is called to set up or update the button image.
     * Subclasses can override this method to customize the button appearance.
     */
    public void updateButtonImage() {
        // Default button background
        GreenfootImage buttonImage = new GreenfootImage("buttonLong_beige.png");
        
        // Adding text
        GreenfootImage textOverlay = new GreenfootImage(text, 23, Color.BLACK, new Color(0, 0, 0, 0));
        
        // Positioning the text
        int textX = (buttonImage.getWidth() - textOverlay.getWidth()) / 2;
        int textY = (buttonImage.getHeight() - textOverlay.getHeight()) / 2;
        buttonImage.drawImage(textOverlay, textX, textY);
        
        setImage(buttonImage); // Set the final button image
    }

    /**
     * The act method checks for button clicks and performs the associated action.
     */
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            playClickSound();
            if (action != null) {
                action.run();
            }
        }
    }

    /**
     * Plays the button click sound.
     */
    private void playClickSound() {
        if (buttonClickSound == null) {
            buttonClickSound = new GreenfootSound("ButtonClick.mp3");
        }
        buttonClickSound.setVolume(100);
        buttonClickSound.play();
    }

    /**
     * Allows the text to be updated dynamically.
     */
    public void setText(String newText) {
        this.text = newText;
        updateButtonImage(); // Refresh the button image with the new text
    }
    
    public void changeButtonImage(String imageName) {
        // Verify image exists or use default
        GreenfootImage newButtonImage = new GreenfootImage(imageName);
        
        // Fallback to default if image is invalid
        if (newButtonImage.getWidth() <= 0 || newButtonImage.getHeight() <= 0) {
            newButtonImage = new GreenfootImage(200, 50);
            newButtonImage.setColor(Color.LIGHT_GRAY);
            newButtonImage.fill();
        }
        
        // Reapply text
        GreenfootImage textOverlay = new GreenfootImage(text, 23, Color.BLACK, new Color(0, 0, 0, 0));
        
        int textX = (newButtonImage.getWidth() - textOverlay.getWidth()) / 2;
        int textY = (newButtonImage.getHeight() - textOverlay.getHeight()) / 2;
        
        newButtonImage.drawImage(textOverlay, textX, textY);
        setImage(newButtonImage);
    }
}
