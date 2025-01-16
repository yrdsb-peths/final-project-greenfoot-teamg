import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class LaserBeam extends Actor
{
    // Timers to manage the beam's growth and rotation
    SimpleTimer turnTimer = new SimpleTimer();
    SimpleTimer growTimer = new SimpleTimer();
    
    // Variables to manage rotation and appearance
    int turnSpeed;
    int currentAngle;
    int endAngle;
    int thickness;
    boolean isRandom;
    
    /** 
     * Constructor to initialize LaserBeam with speed, angles, and thickness.
     */
    public LaserBeam(int turnSpeed, int currentAngle, int endAngle, int thickness)
    {
        GreenfootImage image = new GreenfootImage("LaserBeam.png");
        image.scale(20,thickness);  // Set the initial thickness of the laser beam
        setImage(image);  // Apply the image to the laser
        growTimer.mark();  // Start the grow timer
        turnTimer.mark();  // Start the turn timer
        this.turnSpeed = turnSpeed;  // Set the rotation speed
        this.currentAngle  = currentAngle;  // Set the current angle
        this.endAngle = endAngle;  // Set the target angle for turning
        this.thickness = thickness;  // Set the thickness of the laser
        turn(currentAngle);  // Apply the initial angle
    }
    
    /** 
     * Act method called every frame to manage the laser's behavior.
     */
    public void act()
    {
        growAnimation();  // Handle the laser's growth animation
        
        // If the beam has grown for more than 1 second, start rotating
        if(growTimer.millisElapsed() > 1000)
        {
            if(turnTimer.millisElapsed() > 20) {  // Rotate the laser beam every 20ms
                turn(turnSpeed);  // Turn the beam by the specified speed
                currentAngle += turnSpeed;  // Update the current angle
                turnTimer.mark();  // Reset the turn timer
            }
        }
        
        // Ensure the current angle stays within the 0-360 degree range
        if(currentAngle > 360)
        {
            currentAngle -= 360;  // Reset if the angle exceeds 360 degrees
        }
        else if(currentAngle < 0)
        {
            currentAngle += 360;  // Reset if the angle is less than 0 degrees
        }
        
        // Remove the laser when it reaches the end angle
        if(turnSpeed <= 0  && currentAngle <= endAngle || turnSpeed > 0 && currentAngle >= endAngle)
        {
            getWorld().removeObject(this);  // Remove the laser from the world
        }
    }
    
    /** 
     * Animate the laser's growth over the first second.
     */
    public void growAnimation()
    {
        if(growTimer.millisElapsed() < 1000)
        {
            GreenfootImage image = new GreenfootImage("LaserBeam.png");
            
            // Scale the image based on how much time has passed since the start
            if(growTimer.millisElapsed() != 0)
            {
                image.scale(2 * growTimer.millisElapsed(),thickness);  // Grow the beam width
            }
            
            setImage(image);  // Apply the new image size to the laser
        }
    }
}
