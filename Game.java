import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Game extends World
{

    /**
     * Constructor for objects of class Game.
     * 
     */
    public Game()
    {    
        super(600, 400, 1); 

        TestCharacter test = new TestCharacter();
        addObject(test, 300, 350);
    }
}
