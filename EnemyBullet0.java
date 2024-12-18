import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBullet0 extends Actor
{
    public EnemyBullet0(int rotation) {
        GreenfootImage image = new GreenfootImage("EnemyBullet0.png");
        image.scale(10, 10);
        this.setImage(image);
        setRotation(rotation);
    }

    public void act()
    {
        move(5);
    }
}
