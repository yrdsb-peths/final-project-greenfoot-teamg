import greenfoot.*;

public class Game extends World {
    private Character player;

    public Game(GreenfootImage selectedImage) {
        super(500, 700, 1); 

        // Create the player's character
        player = new Character(selectedImage);

        // Add the character to the center of the game world.
        addObject(player, getWidth() / 2, getHeight() - 50);

        Enemy test = new SeekingEnemy();
        addObject(test, 250, 350);

        Enemy a = new CircleEnemy();
        addObject(a, 35, 100); 
    }
}
