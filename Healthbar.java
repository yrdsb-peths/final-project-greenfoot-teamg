import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Healthbar for bosses

public class Healthbar extends Actor
{
    RedHealthbar bar;
    int barLength = 500;
    int barHeight = 50;

    public Healthbar() {
        GreenfootImage image = new GreenfootImage("HealthBarBox.png");
        image.scale(barLength, barHeight);
        image.setTransparency(75);
        setImage(image);   
    }

    public void act() {
        if(bar == null) {
            createBar();
        }
    }

    public void createBar() {
        bar = new RedHealthbar(barLength, barHeight);
        getWorld().addObject(bar, getX(), getY());
    }

    public void changeSize(int percentage) {
        bar.getImage().scale(barLength * percentage, barHeight);
    }
}
