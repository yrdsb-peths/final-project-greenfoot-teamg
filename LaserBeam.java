import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LaserBeam extends Actor
{
    SimpleTimer turnTimer = new SimpleTimer();
    SimpleTimer growTimer = new SimpleTimer();
    int turnSpeed;
    int currentAngle;
    int endAngle;
    int thickness;
    boolean isRandom;
    public LaserBeam(int turnSpeed, int currentAngle, int endAngle, int thickness)
    {
        GreenfootImage image = new GreenfootImage("LaserBeam.png");
        image.scale(20,thickness);
        setImage(image);
        growTimer.mark();
        turnTimer.mark();
        this.turnSpeed = turnSpeed;
        this.currentAngle  = currentAngle;
        this.endAngle = endAngle;
        this.thickness = thickness;
        turn(currentAngle);
    }
    
    public void act()
    {
        growAnimation();
        if(growTimer.millisElapsed() > 1000)
        {
            if(turnTimer.millisElapsed() > 20) {
                turn(turnSpeed);
                currentAngle += turnSpeed;
                turnTimer.mark();
            }
        }
        if(currentAngle > 360)
        {
            currentAngle -= 360;
        }
        else if(currentAngle < 0)
        {
            currentAngle += 360;
        }
        if(turnSpeed <= 0  && currentAngle <= endAngle || turnSpeed > 0 && currentAngle >= endAngle)
        {
            getWorld().removeObject(this);
        }
    }
    
    public void growAnimation()
    {
        if(growTimer.millisElapsed() < 1000)
        {
            GreenfootImage image = new GreenfootImage("LaserBeam.png");
            image.scale(2 * growTimer.millisElapsed(),thickness);
            setImage(image);
        }
    }
}
