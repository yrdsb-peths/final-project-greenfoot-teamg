import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Health Bar for bosses

public class Healthbar extends Actor
{
    RedHealthbar bar;
    Label label;
    int barLength = 500;
    int barHeight = 50;

    public Healthbar() {
        GreenfootImage image = new GreenfootImage("HealthBarBox.png");
        image.scale(barLength, barHeight);
        setImage(image);   
    }

    public void act() {
        if(bar == null) {
            createBar();
        }
    }

    // Make the filler for the bar
    public void createBar() {
        bar = new RedHealthbar(barLength, barHeight);
        getWorld().addObject(bar, getX(), getY());

        label = new Label("BOSS HP:", 20); // Label for health bar description
        getWorld().addObject(label, getX() - barLength / 2, getY() + 3);
    }

    // Scale filler based on health remaining
    public void changeSize(double percentage) {
        int newLength = (int) (bar.barLength * percentage);

        // Scale cannot be less than 0
        if(newLength <= 0) {
            bar.getImage().scale(1, barHeight);
            getWorld().removeObject(label);
        }
        else {
            bar.getImage().scale(newLength, barHeight);
        }
    }
}
