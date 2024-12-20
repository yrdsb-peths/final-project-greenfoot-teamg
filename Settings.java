import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Settings extends World {
    private MenuScreen menuScreen;

    public Settings(MenuScreen menuScreen) {
        super(500, 750, 1);

        this.menuScreen = menuScreen;

        // Adding a "Back" button to return to the MenuScreen
        Button backButton = new Button(this::goMenuScreen, "Back");
        addObject(backButton, 250, 700); // Place at bottom center
    }

    /**
     * This method switches back to the MenuScreen.
     */
    public void goMenuScreen() {
        Greenfoot.setWorld(menuScreen);
    }
}
