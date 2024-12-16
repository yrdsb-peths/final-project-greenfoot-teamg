import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Represents a character in the game, allowing for dynamic image updates.
 */
public class Character extends Actor {
    public Character(GreenfootImage characterImage) {
        setImage(characterImage);
    }

    public void updateImage(GreenfootImage characterImage) {
        setImage(characterImage);
    }
}
