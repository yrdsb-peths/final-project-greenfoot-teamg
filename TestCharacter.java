import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class TestCharacter extends Actor
{
    public TestCharacter() {
        GreenfootImage image = new GreenfootImage("cupcake.jpg");
        image.scale(50, 50);
        setImage(image);
    }

    public void act()
    {
        // Can move with WASD 
        if(Greenfoot.isKeyDown("W")) {
            setLocation(getX(), getY() - 3);
        }
        else if(Greenfoot.isKeyDown("A")) {
            setLocation(getX() - 3, getY());
        }
        else if(Greenfoot.isKeyDown("S")) {
            setLocation(getX(), getY() + 3);
        }
        else if(Greenfoot.isKeyDown("D")) {
            setLocation(getX() + 3, getY());
        }
    }
}
