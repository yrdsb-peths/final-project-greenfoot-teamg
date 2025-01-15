import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Healthbar filler

public class RedHealthbar extends Actor
{
    int barLength, barHeight;

    public RedHealthbar(int barLength, int barHeight) {
        GreenfootImage image = new GreenfootImage("HealthBar.png");
        image.scale(barLength, barHeight);
        image.setTransparency(75);
        this.barLength = barLength;
        this.barHeight = barHeight;
        setImage(image);
    }
}
