import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents the main game screen with the selected character.
 */
public class Game extends World {
    private Character selectedCharacter;

    /**
     * Constructor for the Game class.
     * @param selectedCharacterImage The image of the selected character.
     */
    public Game(GreenfootImage selectedCharacterImage) {
        // Create a new world with 600x400 cells.
        super(600, 400, 1);

        // Instantiate the selected character based on the passed image
        selectedCharacter = new Character(selectedCharacterImage);

        // Add the character to the center of the game world.
        addObject(selectedCharacter, getWidth() / 2, getHeight() - 50);
    }
}
