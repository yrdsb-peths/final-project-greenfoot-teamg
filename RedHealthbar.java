import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Healthbar filler

public class RedHealthbar extends Actor
{
    int barLength, barHeight;

    /**
     * Constructor for the red health bar, sets up all the varaibles.
     */
    public RedHealthbar(int barLength, int barHeight) {
        GreenfootImage image = new GreenfootImage("HealthBar.png");
        image.scale(barLength, barHeight);
        this.barLength = barLength;
        this.barHeight = barHeight;
        setImage(image);
    }
}
