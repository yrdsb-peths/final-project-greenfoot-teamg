import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TimeStopAnimation extends Actor
{
    SimpleTimer timer = new SimpleTimer();
    Boss3 boss;
    boolean isResume;
    
    /**
     * Constructor for the animation, sets up all the variables
     */
    public TimeStopAnimation(Boss3 boss, boolean isResume)
    {
        GreenfootImage image = new GreenfootImage("TranslucentBox.png");
        image.scale(600,800);
        setImage(image);
        timer.mark();
        this.boss = boss;
        this.isResume = isResume;
    }
    
    /**
     * Flahses the screen, freezes time, then flashes the screen again to unfreeze time.
     */
    public void act()
    {
        if(timer.millisElapsed() > 200)
        {
            getWorld().removeObject(this);
            if(isResume)
            {
                for(ImageDisplay display: boss.getWorld().getObjects(ImageDisplay.class))
                {
                    boss.getWorld().removeObject(display);
                }
                boss.resumeTime();
            }
            else
            {
                ImageDisplay display = new ImageDisplay(new GreenfootImage("TimeStopImage.png"));
                boss.getWorld().addObject(display, boss.getWorld().getWidth()/2, boss.getWorld().getHeight()/2);
            }
        }
    }
}
