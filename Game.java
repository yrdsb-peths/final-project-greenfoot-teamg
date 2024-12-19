import greenfoot.*;

public class Game extends World {
    private Character player;

    public Game(GreenfootImage selectedImage) {
        super(500, 700, 1); 

        // Create the player's character
        player = new Character(selectedImage);

        // Add the character to the center of the screen
        addObject(player, getWidth() / 2, getHeight() - 50);
    }
}
