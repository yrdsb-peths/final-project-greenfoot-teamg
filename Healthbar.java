import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Health Bar for bosses

public class Healthbar extends Actor
{
    RedHealthbar bar; // Changeable bar inside health bar
    Label label;
    int barLength = 500; // Size of health bar
    int barHeight = 50;

    /**
     * Constructor of the health bar, sets up all the variables.
     */
    public Healthbar() {
        GreenfootImage image = new GreenfootImage("HealthBarBox.png");
        image.scale(barLength, barHeight);
        setImage(image);   
    }

    /**
     * Checks if the red health bar exists inthe world, if it doesn't it adds it.
     */
    public void act() {
        if(bar == null) {
            createBar();
        }
    }

    /**
     * Creates the red health bar to fill inside.
     */
    public void createBar() {
        bar = new RedHealthbar(barLength, barHeight); // Filler has same size as health bar
        getWorld().addObject(bar, getX(), getY());

        label = new Label("BOSS HP:", 20); // Label for health bar description
        getWorld().addObject(label, getX() - barLength / 2, getY() + 3);
    }

    // Scale red health bar based on health remaining
    public void changeSize(double percentage) {
        int newLength = (int) (bar.barLength * percentage);

        // Image cannot be equal to/less than 0 wide
        if(newLength <= 0) {
            bar.getImage().scale(1, barHeight);
            getWorld().removeObject(label);
        }
        else {
            bar.getImage().scale(newLength, barHeight);
        }
    }
}
