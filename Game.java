import greenfoot.*;

public class Game extends World {
    Character player;

    public Game(GreenfootImage selectedImage) {
        super(600, 800, 1); 

        // Create the player's character
        player = new Character(selectedImage);

        // Add the character to the center of the game world.
        addObject(player, getWidth() / 2, getHeight() - 50);

        Enemy test1 = new CircleEnemy();
        addObject(test1, 125, 350);

        Enemy test2 = new SeekingEnemy();
        addObject(test2, 250, 350);

        Enemy test3 = new SplitEnemy();
        addObject(test3, 375, 350);
        Enemy test = new RichochetEnemy();
        addObject(test, 250, 350);
    }
}
